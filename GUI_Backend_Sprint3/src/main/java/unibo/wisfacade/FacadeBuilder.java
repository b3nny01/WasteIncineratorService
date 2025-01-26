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
    protected MqttHandler mqttHandler;
    public static ApplguiCore guiCore;

    public FacadeBuilder() {
        create();
    }

    public void create() {

        mqttHandler = new MqttHandler();
        wsHandler = new WSHandler();
        guiCore = new ApplguiCore(mqttHandler, wsHandler);

        wsHandler.setGuiCore(guiCore);
        mqttHandler.setGuiCore(guiCore);
    }
}
