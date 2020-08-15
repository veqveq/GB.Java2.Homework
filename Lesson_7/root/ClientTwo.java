import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientTwo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", Server.PORT);
        System.out.println("Connected to server: " + socket);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    out.writeUTF("/auth l1 p1");
                    while (true) {
                        String message = in.readUTF();
                        if (message.startsWith("/authok")) {
                            System.out.println("Authorized");
                            break;
                        }
                    }

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
        });
        thread.start();
    }
}
