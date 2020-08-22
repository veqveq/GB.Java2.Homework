package root;

import root.GUI.ChatWindow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ClientApp {

    private static String name;
    private static Set<String> users;
    private static Set<String> online;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", Server.PORT);
        System.out.println("Connected to server: " + socket);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String message = in.readUTF();
                        if (message.startsWith("/authok")) {
                            System.out.println("Authorized");
                            break;
                        }
                    }

                    while (true) {
                        String message = in.readUTF();
                        if (message.startsWith("/name")) {
                            name = message.split("\\s")[1];
                            System.out.println("Имя получено " + name);
                            break;
                        }
                    }

                    while (true) {
                        String message = in.readUTF();
                        if (message.startsWith("/logs")) {
                            String[] logs = message.split("\\s");
                            users = new HashSet<>();
                            for (int i = 1; i < logs.length; i++) {
                                users.add(logs[i]);
                            }
                            System.out.println("Логи получены " + users.toString());
                            break;
                        }
                    }

                    while (true) {
                        String message = in.readUTF();
                        if (message.startsWith("/online")) {
                            String[] clientsOn = message.split("\\s");
                            online = new HashSet<>();
                            for (int i = 1; i < clientsOn.length; i++) {
                                if (!clientsOn[i].equals(name)) {
                                    online.add(clientsOn[i]);
                                }
                            }
                            System.out.println("Онлайн список получен " + online.toString());
                            break;
                        }
                    }

                    ChatWindow chatWindow = new ChatWindow(users, online, name, in, out);

                    while (true) {
                        String message = in.readUTF();
                        if (message.equals("/end")) {
                            System.out.println("Session closed. Cau!");
                            break;
                        }
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
