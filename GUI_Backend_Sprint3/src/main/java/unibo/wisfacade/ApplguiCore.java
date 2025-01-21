package unibo.wisfacade;

import org.json.JSONObject;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.msg.ApplMessage;
import unibo.basicomm23.utils.CommUtils;

import java.util.Arrays;
import java.util.List;

/*
Logica applicativa (domain core) della gui
Creata da ServiceFacadeController usando FacadeBuilder
 */
public class ApplguiCore {
    private ActorOutIn outinadapter;
    private String reqid = "dofibo"; // config.get(6); CHE NE SA?
    private String reqcontent = "dofibo(X)"; // config.get(7);
    private String destActor = "";

    public ApplguiCore(ActorOutIn outinadapter) {
        this.outinadapter = outinadapter;
        // ApplSystemInfo.setup(); //MAY2024 al momento, non si vede PERCHE'
        destActor = ApplSystemInfo.applActorName;
    }

    // Chiamato da CoapObserver
    public void handleMsgFromActor(String msg, String requestId) {
        CommUtils.outcyan("AGC | hanldeMsgFromActor " + msg + " requestId=" + requestId);
        updateMsg(msg);
    }

    public void handleReplyMsg(String msg) { // IApplMessage msg
        CommUtils.outcyan("AGC | handleReplyMsg " + msg);
        // Mando la risppsta alla ws-conn del browser che ha fatto la richiesta
        // updateMsg( msg );
        outinadapter.sendToOne(msg);
    }

    public void handleReplyMsg(IApplMessage msg) {
        CommUtils.outcyan("AGC | handleReplyMsg " + msg);
        // Mando la risppsta alla ws-conn del browser che ha fatto la richiesta
        outinadapter.sendToOne(msg);
    }

    public void updateMsg(String msg) {
        CommUtils.outblue("AGC updateMsg " + msg);
        // system_state(4,true,false,0.2777777777777778,checked,off)

        String[] tks = msg.split("\\(")[1].split("\\)")[0].split("\\,");
        String[] posTks=tks[5].substring(1).split("y");
        Position pos=new Position(Integer.parseInt(posTks[0]),Integer.parseInt(posTks[1]));

        JSONObject systemState = new JSONObject(new SystemState(Integer.parseInt(tks[0]),
                Boolean.parseBoolean(tks[1]),
                Boolean.parseBoolean(tks[2]),
                Double.parseDouble(tks[3]),
                tks[4],
                pos,
                tks[6]));

        outinadapter.sendToAll(systemState.toString());
        // potrei mandare a M2M ... che poi manda la risposta a REST POST
        // M2MController.m2mCtrl.setAnswer(msg);
    }

    public void handleWsMsg(String id, String msg) {
        CommUtils.outcyan("AGC | handleWsMsg msg " + msg);
        JSONObject jsonMsg = new JSONObject(msg);
        String cmdStr = jsonMsg.toString();
        IApplMessage message = CommUtils.buildDispatch("robotgui", "cmd", cmdStr, "msg_receiver");
        outinadapter.docmd(message);
        return;

    }

    private void docmd(String id, String payload) {
        outinadapter.docmd(id, payload);
    }

    public void handleWsMsgOld(String id, String msg) {
        CommUtils.outcyan("AGC | handleWsMsg msg " + msg);
        String[] parts = msg.split("/");
        String message = parts[0];
        String payload = parts[1];
        CommUtils.outcyan("AGC | handleWsMsg " + message + " from " + id);

        switch (message) {
            case "request":
                dorequest(id, payload);
                break;
            case "cmd":
                docmd(id, payload);
                break;
            case "reply":
                doreply(id, payload);
                break;
            case "requestInfo":
                dorequestInfo();
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                break;
        }
    }

    public void dorequest(String id, String payload) { // public per M2MController
        if (payload.startsWith("dofibo")) { // payload from M2M
            outinadapter.dorequest(id, destActor, reqid, payload);
        } else { // payload from GUI
            outinadapter.dorequest(id, destActor, reqid, reqcontent.replace("X", payload));
        }
    }

    private void doreply(String id, String payload) {
        CommUtils.outred("AGC | doreply id=" + id + " payload=" + payload);
        // payload=yes|confirm(DYNAMICACTORNAME,N)
        String[] pa = payload.split("\\|");
        CommUtils.outcyan("AGC | pa[1] " + pa[0] + " " + pa[1]);
        String destName = pa[1].replace("confirm(", "").split(",")[0];
        CommUtils.outcyan("AGC | doreply " + destName);
        // Non destActor, ma il nome del dynamic che ha fatto la ask Got response
        outinadapter.doreply(id, destName, "confirmed", "confirmed(P)".replace("P", pa[0]));
    }

    private void dorequestInfo() {
        // ApplSystemInfo.setup();
        List<String> actorNames = ApplSystemInfo.getActorNamesInApplCtx();
        CommUtils.outgreen(" AGC | actorNames= " + actorNames);

        FacadeBuilder.wsHandler.sendToAll("ACTORS:" + actorNames.toString() +
                " interacting with:" + ApplSystemInfo.applActorName);

    }

    public void doRobotPos(String x, String y) {
        RobotUtils.doRobotPos(x, y);
    }

    public void setRobotPos(String x, String y, String d) {
        RobotUtils.setRobotPos(x, y, d);
    }

    public void setalarm() {
        RobotUtils.setalarm();
    }

    public void basicrobotconnect(String robotip) {
        RobotUtils.connectWithRobotUsingTcp(robotip);
    }

    public void robotmove(String move) {
        RobotUtils.sendMsg("basicrobot", move);
    }

    public String doplan(String plan, String steptime) {
        String answer = "";

        CommUtils.outcyan("AGC | doplan:" + plan);
        if (plan == null || plan.isEmpty())
            plan = "lr"; // defensive
        try {
            answer = RobotUtils.sendPlanMsg(plan, steptime);
            CommUtils.outmagenta("AGC | doplan answer=" + answer);
            return answer;
        } catch (Exception e) {
            CommUtils.outred("Robotfacade24Controller | doplan ERROR:" + e.getMessage());
            return "doplan ERROR";
        }
        // try {
        // Thread.sleep(5000);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }

    }

    public String getEnvmap() {
        IApplMessage req = CommUtils.buildRequest(RobotUtils.applName, "getenvmap", "getenvmap(ok)", "basicrobot");
        String answer = RobotUtils.sendApplMsg(req);
        String maprep = new ApplMessage(answer).msgContent();
        CommUtils.outmagenta("AGC | getenvmap answer=" + answer);
        return maprep.replace("envmap('", "").replace("')", "").replaceAll("@", "\n"); // Meglio lato js?
    }

    public String getRobotpos() {
        IApplMessage req = CommUtils.buildRequest(RobotUtils.applName, "getrobotstate", "getrobotstate(ok)",
                "basicrobot");
        String answer = RobotUtils.sendApplMsg(req);
        String posrep = new ApplMessage(answer).msgContent();
        CommUtils.outmagenta("AGC | getrobotpos answer=" + answer);
        return posrep; // posrep.replace("robotstate(","").replaceLast(")","");
    }
}
