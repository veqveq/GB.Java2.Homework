package Obstacle_course;

public class Main {

    private static Participant[] parts = initPartsArray();
    private static Obstacle[] obstacleCourse = initObstArray();

    public static void main(String[] args) {
        for (Participant part : parts) {
            goObstacleCourse(part);
        }
    }

    static Participant[] initPartsArray(){
        Participant[] parts = {new Cat("Васька", 3.5, 500),
                new Homo("Виктор Артёмыч", 1.5, 3000),
                new Robot("R2D2", 35, 60000)};
        return parts;
    }

    static Obstacle[] initObstArray(){
        Obstacle[] obstacleCourse = {new Wall(1.5),
                new Treadmill(100),
                new Wall(3),
                new Wall(0.5),
                new Treadmill(1000),
                new Wall(15),
                new Treadmill(61000)};
        return obstacleCourse;
    }

    static void goObstacleCourse(Participant part){
        int count = 0;
        for (Obstacle obstacle: obstacleCourse) {
            if (part.action(obstacle)) {
                count++;
            } else {
                System.out.printf("%s сошел с диастанции. Пройдено %.0f процентов полосы \n", part.getName(), (double) count / (double) obstacleCourse.length * 100);
                System.out.println("********");
                break;
            }
        }
        if (count == obstacleCourse.length) {
            System.out.printf("%s прошел 100 процентов полосы \n", part.getName());
            System.out.println("********");
        }
    }
}