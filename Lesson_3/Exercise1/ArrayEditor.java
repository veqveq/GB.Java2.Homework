package Exercise1;

import java.util.*;

public class ArrayEditor implements ArrayScanner {              //Класс редактор массива
    private Map<String, Integer> editedArray;                   //Объявление мапы редактора массива

    public ArrayEditor() {                                      //Конструктор класса
        this.editedArray = new HashMap<>();                     //Инициализация мапы
    }

    @Override
    public void print() {                                                                   //Переопределение метода для распечатки
        if (!editedArray.isEmpty()) {                                                       //Если мапа с уникальными значениями не пустая
            System.out.println("Количество повторов");
            for (Map.Entry<String, Integer> ent : editedArray.entrySet()) {
                System.out.printf("%s: %d раза \n", ent.getKey(), ent.getValue());          //Распечатать элементы и их количество
            }
        } else {                                                                            //Иначе
            System.out.println("Сканер пуст. Перед печатью выполни сканирование массива");  //Заругаться
        }
    }

    @Override
    public void searchUnique(String[] array) {                                              //Переопределение метода для поиска уникальных значений
        editedArray.clear();                                                                //Очистить мапу
        System.out.printf("Принят массив %s \n", Arrays.toString(array));                   //Вывести загруженный в сканер массив
        System.out.println("Идет сканирование...");
        for (Object obj : array) {
            editedArray.put(String.valueOf(obj), count(array, obj));                        //Ключ мапы - имя элемента в массиве, значение - количество повторов
        }
    }

    private int count(String[] array, Object obj) {                                         //Метод для подсчёта количества поторений элемента
        int sum = 0;                                                                        //Инициализация счётчика
        for (String c : array) {
            if (c.equals(String.valueOf(obj))) {                                            //Если элемент встречен в массиве
                sum += 1;                                                                   //Увеличить счётчик
            }
        }
        return sum;                                                                         //Вернуть значение счётчика
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayEditor that = (ArrayEditor) o;
        return Objects.equals(editedArray, that.editedArray);
    }

    @Override
    public int hashCode() {
        return Objects.hash(editedArray);
    }
}
