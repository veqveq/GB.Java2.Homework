package root;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {
    public static final int PORT = 8082;
    private BasicAuthService authService;
    private Set<ClientHandler> clientHandlers;

    public Server() {
        this(PORT);
    }

    public Server(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            authService = new BasicAuthService();
            System.out.println("Сервер запущен");
            clientHandlers = new HashSet<>();

            while (true) {
                System.out.println("Ожидание подключений...");
                Socket socket = serverSocket.accept();
                System.out.println("Попытка подключения клиента: " + socket);
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized boolean isOccupied(AuthService.Record record) {
        for (ClientHandler ch : clientHandlers) {
            if (ch.getRecord().equals(record)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void subscribe(ClientHandler ch) {
        clientHandlers.add(ch);
    }

    public synchronized void unsubscribe(ClientHandler ch) {
        clientHandlers.remove(ch);
    }

    public synchronized void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler ch : clientHandlers) {
            if (!sender.equals(ch)) {
                ch.sendMessage(message);
            }
        }
    }

    public synchronized void unicastMessage(String message, String taker) {
        for (ClientHandler ch : clientHandlers) {
            if (ch.getRecord().getName().equals(taker)) {
                ch.sendMessage(message);
            }
        }
    }
}
