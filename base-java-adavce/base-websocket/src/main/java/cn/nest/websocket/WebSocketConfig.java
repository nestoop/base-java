package cn.nest.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * Created by botter
 * on 17-4-10.
 */
@SuppressWarnings("unused")
@Configuration
@EnableWebSocket
public class WebSocketConfig  implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
         registry.addHandler(handler(), "/test");
    }

    @Bean
    public HandshakeInterceptor interceptor() {
        return new WebSocketHandlerShakeInterceptor();
    }

    @Bean
    public WebSocketHandler handler() {
        return new WebSocketHandler();
    }
}
