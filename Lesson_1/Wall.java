package Obstacle_course;

public class Wall extends Obstacle implements Jump{

    private double heigth;

    public Wall(double heigth){
        this.heigth = heigth;
    }


    @Override
    public String getName() {
        return String.format("Стена высотой %.2f м",heigth);
    }

    @Override
    public boolean jump(Participant part) {
        return part.getJumpLimit()>=heigth;
    }
}
