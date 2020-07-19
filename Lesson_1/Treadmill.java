package Obstacle_course;

public class Treadmill extends Obstacle implements Run {

    private double length;

    public Treadmill(double length){
        this.length = length;
    }

    @Override
    public String getName() {
        return String.format("Дорожка длиной %.2f м",length);
    }

    @Override
    public boolean run(Participant part) {
        return (part.getRunLimit()>=length);
    }
}
