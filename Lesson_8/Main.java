public class Main {
    public static void main(String[] args) {
        DualLinkedList names = new DualLinkedList();
        names.add("Иван");
        names.add("Армен");
        names.add("Сократ");
        names.add("Рамзан Ахматович");
        names.add("Гришка");
        System.out.println(names);
        names.replace(3,"Арсен");
        System.out.println(names);
        names.replace(0,"Кирилл");
        System.out.println(names);
        names.replace(1,"Арсен");
        System.out.println(names);
        System.out.println(names.getSize());
        names.delete(2);
        System.out.println(names);
        names.replace(3,"Арсен");
        System.out.println(names);
        System.out.println(names.iterator().current());
        while (names.iterator().hasNext()){
            System.out.println(names.iterator().next());
        }
        System.out.println(names.getSize());
        names.deleteAll("Арсен");
        System.out.println(names);
    }
}
