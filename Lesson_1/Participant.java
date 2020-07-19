package Obstacle_course;

public interface Participant {
    boolean action(Obstacle obst);
    double getRunLimit ();
    double getJumpLimit ();
    String getName();
}
