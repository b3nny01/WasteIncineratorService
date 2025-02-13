package unibo.wisfacade;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    public final String wsPath  = "/accessgui";

    public WebSocketConfiguration(){
        //Inovocato alla CONNESSIONE
        //CommUtils.outred("WebSocketConfiguration on");
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(FacadeBuilder.wsHandler, wsPath).setAllowedOrigins("*");
    }
}