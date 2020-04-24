package inf112.skeleton.app;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

public class Server {
    Net net = new Net() {
        @Override
        public void sendHttpRequest(HttpRequest httpRequest, HttpResponseListener httpResponseListener) {

        }

        @Override
        public void cancelHttpRequest(HttpRequest httpRequest) {

        }

        @Override
        public ServerSocket newServerSocket(Protocol protocol, String s, int i, ServerSocketHints serverSocketHints) {
            return null;
        }

        @Override
        public ServerSocket newServerSocket(Protocol protocol, int i, ServerSocketHints serverSocketHints) {
            return null;
        }

        @Override
        public Socket newClientSocket(Protocol protocol, String s, int i, SocketHints socketHints) {
            return null;
        }

        @Override
        public boolean openURI(String s) {
            return false;
        }
    };
}
