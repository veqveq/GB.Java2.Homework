package Exercise2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PhoneBook implements Catalog {             //Класс телефонная книга
    Map<String, String> phoneBook;                      //Объявление мапы для хранения номеров

    public PhoneBook() {                                //Конструктор класса
        this.phoneBook = new HashMap<>();               //Инициализация мапы
    }

    @Override
    public void add(Object surname, Object phoneNumber) {                       //Метод для добавления записи в телефонную книгу
        phoneBook.put(String.valueOf(phoneNumber), String.valueOf(surname));
    }

    @Override
    public void get(Object surname) {                                                   //Метод поиска номеров по фамилии абонента
        System.out.printf("Поиск абонентов: %s \n", surname);                           //Вывести фамилию искомого абонента
        System.out.printf("Найдено записей: %d \n", count(surname));                    //Вывести количество найденых записей
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            if (entry.getValue().equals(surname))
                System.out.println(entry.getKey());                                     //Распечатать все найденные номера
        }
    }


    private int count(Object requiredKey) {                                 //Метод подсчёта количества найденых абонентов
        int sum = 0;                                                        //Счётчик повторов
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            if (requiredKey.equals(entry.getValue())) {                     //Если искомая фамилия есть в мапе
                sum += 1;                                                   //Увеличить счётчик на единицу
            }
        }
        return sum;                                                         //Вернуть значение счётчика
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneBook phoneBook1 = (PhoneBook) o;
        return Objects.equals(phoneBook, phoneBook1.phoneBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneBook);
    }
}
