package unibo.wisfacade;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.msg.ApplMessage;
import unibo.basicomm23.utils.CommUtils;

public class MqttHandler {

    protected IMqttClient mqttClient;
    private ApplguiCore guiCore;

    public MqttHandler() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);

        try {
            mqttClient = new MqttClient("ws://mqttBroker:9001", "facade");
            mqttClient.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    // Injiection
    public void setGuiCore(ApplguiCore gui) {
        guiCore = gui;
        try {
            mqttClient.subscribe("system_state", (topic, mqttMSg) -> {
                String payloadStr = new String(mqttMSg.getPayload());
                IApplMessage qakMsg = new ApplMessage(payloadStr);
                CommUtils.outblue("Facade24  MqttObserver | Got update : " + qakMsg.msgContent());
                guiCore.handleMqttMsg(qakMsg.msgContent(), "2");
            });
        } catch (Exception e) {
            CommUtils.outred("Facade24  MqttObserver | ERROR : ");
            e.printStackTrace();
        }
    }

    public void publish(String label,String msg) {
        try {
            mqttClient.publish(label, new MqttMessage(msg.getBytes()));
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
