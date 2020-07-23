public class Main {
    public static void main(String[] args) {
        String[][] array = {{"4", "3", "4", "5"},
                {"5", "4", "2", "4"},
                {"1", "12", "2", "2"},
                {"222", "1", "6", "4"}};

        System.out.printf("Сумма элементов массива - %s", sumArray(array));
    }

    static int sumArray(String[][] arr) {
        int sum = 0;
        try {
            for (int i = checkSizeArray(arr.length); i < arr.length; i++) {
                for (int j = checkSizeArray(arr[i].length); j < arr[i].length; j++) {
                    try {
                        sum += Integer.parseInt(arr[i][j]);
                    } catch (IllegalArgumentException e) {
                        throw new MyArrayDataException(String.format("Ошибка преобразования в ячейке [%d,%d]. Значение ячейки - \"%s\"", i, j, arr[i][j]));
                    }
                }
            }
            return sum;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MyArraySizeException("Неверная размерность массива", e);
        }
    }

    static int checkSizeArray(int size) throws MyArraySizeException {
        if (size == 4) {
            return 0;
        }
        return -1;
    }
}