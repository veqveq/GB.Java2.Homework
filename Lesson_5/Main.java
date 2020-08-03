import java.util.Arrays;

public class Main {

    static final int size = 10000000;           //Инициализация размерности исходного массива
    static final int h = size / 2;              //Инициализация размерности разделенного массива
    static float[] taskArr = new float[size];   //Инициализация исходного массива
    static long t1;                             //Объявление переменной для хранения времени старта метода

    public static void main(String[] args) {
        /*
        Проверка метода 1
         */
        Arrays.fill(taskArr, 1);                                                                   //Заполнение массива единицами
        t1 = System.currentTimeMillis();                                                               //Запись времени перед выполнением метода 1
        method1(taskArr, 0);                                                                  //Выполнение метода 1 для исходного массива целиком
        System.out.printf("Время расчёта в один поток - %d мс \n",System.currentTimeMillis() - t1);    //Вывод в консоль времени выполнения метода 1

        /*
        Проверка метода 2
         */
        Arrays.fill(taskArr, 1);                                                                   //Заполнение массива единицами
        t1 = System.currentTimeMillis();                                                               //Запись времени перед выполнением метода 2
        method2();                                                                                     //Выполнение метода 2
        System.out.printf("Время расчёта в два потока - %d мс \n",System.currentTimeMillis() - t1);    //Вывод в консоль времени выполнения метода 2
    }

    /*
     * Метод 1
     * Преобразование каждого элемента загруженного массива по заданной формуле
     * Аргумент deltaIndex введен для учета разницы индексов одного и того же элемента
     * В исходном массиве и массиве после разделения. В противном случае преобразования
     * По формуле становятся некорректными
     */
    public static void method1(float[] arr, int deltaIndex) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (i + deltaIndex) / 5) * Math.cos(0.2f + (i + deltaIndex) / 5) * Math.cos(0.4f + (i + deltaIndex) / 2));
        }
    }

    /*
     * Метод 2
     * Выполнение преобразований с применением многопоточности
     */
    public static void method2() {
        Thread stream1 = new Thread(new Runnable() {                            //Создание потока и реализация функионального интерфейса
            @Override
            public void run() {
                float[] halfArr = new float[h];                                 //Инициализация вспомогательного проежуточного массива
                System.arraycopy(taskArr, 0, halfArr, 0, h);      //Выполнение копирования первой половины элементов исходого массива во вспомогательный
                method1(halfArr, 0);                                   //Выполнение метода 1 для вспомогательного массива
                System.arraycopy(halfArr, 0, taskArr, 0, h);      //Копирование элементов из вспомогательного массива в основной
            }
        });

        /*
         * Создание потока для второй половины массива
         * Все действия аналогичны
         */
        Thread stream2 = new Thread(new Runnable() {
            @Override
            public void run() {
                float[] halfArr = new float[h];
                System.arraycopy(taskArr, h, halfArr, 0, h);
                method1(halfArr, h);
                System.arraycopy(halfArr, 0, taskArr, h, h);
            }
        });

        stream1.start();    //Запуск потока 1
        stream2.start();    //Запуск потока 2
        /*
         * Дальше костыли.
         * Попытался организовать проверку завершения обоих потоков.
         * Ввел пустой цикл с предусловием. Прокручивается
         * пока хотя бы один из потоков stream живой.
         * Ничего лучше сочинить не получилось.
         */
        while (stream1.isAlive() || stream2.isAlive()) {
        }
    }
}