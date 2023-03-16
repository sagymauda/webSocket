
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@WebSocket
public class EchoWebSocket {

    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<Session>();

    @OnWebSocketConnect
    public void connected(Session session) {
        System.out.println("im finally connected");
        sessions.add(session);
    }

    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        sessions.remove(session);
    }

    @OnWebSocketMessage
    public void message(Session session, String message)throws IOException{
        System.out.println("Got :"+ message);// print message
        session.getRemote().sendString(message);// and send it back
    }
}