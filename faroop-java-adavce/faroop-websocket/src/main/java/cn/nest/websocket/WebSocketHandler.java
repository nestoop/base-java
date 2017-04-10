package cn.nest.websocket;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by botter
 * on 17-4-10.
 */
public class WebSocketHandler extends TextWebSocketHandler {

    private volatile static List<WebSocketSession> sessions = Collections.synchronizedList(new ArrayList<>());

    private ExecutorService executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 10, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000));


    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        super.handleBinaryMessage(session, message);
        System.out.println("handleBinaryMessage......");
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
        System.out.println("handleMessage......");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("handleTextMessage message body= " + message.getPayload());
            }
        });

    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        super.handlePongMessage(session, message);
        System.out.println("pong");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        sessions.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        sessions.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
