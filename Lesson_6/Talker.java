import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public abstract class Talker {          //Абстрактный класс Болтун

    protected DataInputStream in;                   //Переменная входящего потока
    protected DataOutputStream out;                 //Переменная исходящего потока
    protected Socket client;                        //Переменная сокета клиента
    private String nickname;                            //Переменная имени Болтуна
    private Scanner sc = new Scanner(System.in);    //Инициализация сканнера


    public Talker(String nickname) {        //Конструктор класса болтун
        this.nickname = nickname;
    }


    protected void initDataStreams() {                              //Метод инициализации обработчиков потоков
        try {
            in = new DataInputStream(client.getInputStream());      //Инициализация обработчика входящего потока
            out = new DataOutputStream(client.getOutputStream());   //Инициализация обработчика исходящего потока
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected synchronized void getMessage() {              //Метод запускающий поток для получения сообщений
        new Thread(new Runnable() {                         //Прикрутил синхронизацию для избежания коллизий при одновременной
            @Override                                       //Отправке сообщения клиентом и сервером
            public void run() {
                while (true) {                              //Бесконечный цикл
                    try {
                        String message = in.readUTF();      //Запись информации из потока в переменную
                        System.out.println(message);        //Напечатать сообщение в консоль
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }).start();
    }

    protected synchronized void sendMessage() {                                     //Метод отправки сообщения
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {                                                      //Бесконечный цикл
                    String message = sc.nextLine();                                 //Запись сообщения из консоли в переменную
                    try {
                        if (message.equals("/end")) {                                   //Если в консоль было введено /end
                            closeConnection();                                          //Закрыть все соединения текущего Болтуна
                            break;                                                      //Завершить бесконечный цикл
                        }
                        out.writeUTF(String.format("[%s]: %s", nickname, message));     //Записать в поток сообщение и ник отправителя
                    } catch (SocketException e) {
                        System.out.println("Соединение потеряно!");
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Сообщение не отправлено!");
                    }
                }
            }
        }).start();
    }

    private void closeConnection() {                                    //Метод закрытия соединений
        System.out.println("Переписка окончена");
        try {
            out.writeUTF(String.format("[%s]: отключился", nickname));
            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        specialClose();
    }

    protected void specialClose() {             //Спец.метод для дозакрытия открытых соединений в наследниках
        return;                                 //Можно переопределить по мере необходимости
    }
}
