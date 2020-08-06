import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Talker {            //Класс Сервер - наследник Болтуна

    private ServerSocket serverSocket;          //Объявление переменной сервер сокета


    public Server(String name) {                                                    //Конструктор класса Сервер
        super(name);                                                                //Имя задается конструктором родителем
        try {
            serverSocket = new ServerSocket(9999);                             //Инициализация сервера на порту 9999
            System.out.println("Сервер запущен");
            client = serverSocket.accept();                                         //Перевод сервера в режим ожидания клиента
            System.out.printf("Клиент %s подключен \n", client.getLocalAddress());  //Оповещение о подключении клиента
            initDataStreams();                                                      //Инициализация обработчиков потока Input и Output
            getMessage();                                                           //Запуск потока для получения сообщений
            sendMessage();                                                          //Запуск потока для отправки сообщений
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void specialClose() {         //Переопределение спец.метода для корректного закрытия соединения
        try {
            serverSocket.close();           //Закрытие сервер сокета
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
