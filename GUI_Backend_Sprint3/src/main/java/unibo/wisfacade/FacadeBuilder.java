package unibo.wisfacade;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import unibo.basicomm23.coap.CoapConnection;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.msg.ApplMessage;
import unibo.basicomm23.utils.CommUtils;

public class FacadeBuilder {
    public static WSHandler wsHandler;
    public static ApplguiCore guiCore;
    protected ActorOutIn outinadapter;

    public FacadeBuilder() {
        create();
    }

    public void create() {
        wsHandler = new WSHandler();
        outinadapter = new ActorOutIn(wsHandler);
        guiCore = new ApplguiCore(outinadapter);
        outinadapter.setGuiCore(guiCore); // Injection
        wsHandler.setGuiCore(guiCore); // Injection

        // String qakSysHost = ApplSystemInfo.qakSysHost;
        // String ctxportStr = ApplSystemInfo.ctxportStr;
        // String qakSysCtx = ApplSystemInfo.qakSysCtx;
        // String applActorName = ApplSystemInfo.applActorName;

        // CoapObserver obs = new CoapObserver(guiCore, applActorName);
        // CoapConnection coapConn = new CoapConnection(qakSysHost + ":" + ctxportStr,
        //         qakSysCtx + "/" + applActorName);
        // CommUtils.outblue("FacadeBuilder | Stabilita coapConn : " + coapConn);

        // coapConn.observeResource(obs);

        try {

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            IMqttClient subscriber = new MqttClient("ws://localhost:9001", "facade");
            subscriber.connect(options);
            subscriber.subscribe("system_state",(topic,mqttMSg)->{
                String payloadStr=new String(mqttMSg.getPayload());
                IApplMessage qakMsg=new ApplMessage(payloadStr);
                CommUtils.outblue("Facade24  MqttObserver | Got update : " + qakMsg.msgContent());
                guiCore.handleMsgFromActor(qakMsg.msgContent(), "2");
            });
        } catch (MqttException e) {
            CommUtils.outred("Facade24  MqttObserver | ERROR : ");
            e.printStackTrace();
        }

    }
}
