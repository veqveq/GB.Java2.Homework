import Exercise1.ArrayEditor;
import Exercise2.Catalog;
import Exercise2.PhoneBook;

public class Main {

    public static void main(String[] args) {
        doExercise1();  //Проверка упражнения 1
        doExercise2();  //Проверка упражнения 2

    }

    //Метод для проверки упражнения 1
    private static void doExercise1() {
        ArrayEditor uniqueValues = new ArrayEditor();                           //Создание объекта класса редактор массивов
        String[] arr = {"2", "56", "Cat", "Патрон", "ВВП", "ВВП", "Карма",      //Инициализация исходного массива для проерки
                "Cat", "2", "21", "KDRR", "Dog", "KDRR", "Dog", "KDRR", "ВВП"};
        uniqueValues.searchUnique(arr);                                         //Сканирование исходного массива объектом редактор массивов
        uniqueValues.print();                                                   //Распечатка отредактированного массива
    }

    private static void doExercise2() {
        Catalog phoneBook = new PhoneBook();              //Создание объекта телефонная книга
        phoneBook.add("Иванов", "8-800-555-35-35"); //Запись номера в книгу
        phoneBook.add("Петров", "8-999-666-56-47"); //--\\--\\--\\--\\--\\--
        phoneBook.add("Иванов", "8-930-125-62-40"); //--\\--\\--\\--\\--\\--
        phoneBook.add("Иванов", "8-941-161-60-54"); //--\\--\\--\\--\\--\\--
        phoneBook.add("Иванов", "8-911-213-50-20"); //--\\--\\--\\--\\--\\--
        phoneBook.get("Иванов");                          //Поиск номера абонента
        phoneBook.get("Петров");                          //Поиск номера абонента

    }
}
