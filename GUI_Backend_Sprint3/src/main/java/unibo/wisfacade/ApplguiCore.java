package unibo.wisfacade;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
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
    private IMqttClient mqttClient;
    private WSClient wsClient;
    private String reqid = "dofibo"; // config.get(6); CHE NE SA?
    private String reqcontent = "dofibo(X)"; // config.get(7);

    public ApplguiCore(IMqttClient mqttClient,WSClient wsClient) {
        this.mqttClient = mqttClient;
        this.wsClient=wsClient;
    }

    // Chiamato da CoapObserver
    public void handleMqttMsg(String msg, String requestId) {
        CommUtils.outcyan("AGC | hanldeMqttMSG " + msg + " requestId=" + requestId);
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

        wsClient.sendToAll(systemState.toString()); 
    }


    public void handleWsMsg(String id, String msg) {
        CommUtils.outcyan("AGC | handleWsMsg msg " + msg);
        IApplMessage message = CommUtils.buildEvent("facade", "cmd","cmd("+msg+")"); 
        
        try {
            mqttClient.publish("mock_cmd", new MqttMessage(message.toString().getBytes()));
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return;

    }
}
