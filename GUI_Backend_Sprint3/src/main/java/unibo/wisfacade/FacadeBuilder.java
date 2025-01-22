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
    public static WSClient wsClient;
    protected IMqttClient mqttClient;
    public static ApplguiCore guiCore;

    public FacadeBuilder() {
        create();
    }

    public void create() {

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);

        try {
            mqttClient = new MqttClient("ws://localhost:9001", "facade");
            mqttClient.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }

        wsClient = new WSClient();

        guiCore=new ApplguiCore(mqttClient,wsClient);
        

        try {
            wsClient.setGuiCore(guiCore);

            mqttClient.subscribe("system_state", (topic, mqttMSg) -> {
                String payloadStr = new String(mqttMSg.getPayload());
                IApplMessage qakMsg = new ApplMessage(payloadStr);
                CommUtils.outblue("Facade24  MqttObserver | Got update : " + qakMsg.msgContent());
                guiCore.handleMqttMsg(qakMsg.msgContent(), "2");
            });

        } catch (MqttException e) {
            CommUtils.outred("Facade24  MqttObserver | ERROR : ");
            e.printStackTrace();
        }

    }
}
