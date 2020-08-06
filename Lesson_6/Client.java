import java.io.IOException;
import java.net.Socket;

public class Client extends Talker {                                    //Класс Клиент - наследник Болтуна

    public Client(String name) {
        super(name);                                                    //Имя задается конструктором родителем
        try {
            client = new Socket("localhost", 9999);           //Создание сокета для клиента
            System.out.println("Выполнено подключение к серверу");
            initDataStreams();                                          //Инициализация обработчиков потока Input и Output
            getMessage();                                               //Запуск потока для получения сообщений
            sendMessage();                                              //Запуск потока для отправки сообщений
        } catch (IOException e) {
            System.out.println("Не удалось подключиться к серверу");    //Если что то полшло не так - бросить ошибку
        }
    }
}
