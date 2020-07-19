package Obstacle_course;

public class Robot implements Participant {


    private String name;
    private double maxJump;
    private double maxRun;

    public Robot(String name, double maxJump, double maxRun) {
        this.name = name;
        this.maxJump = maxJump;
        this.maxRun = maxRun;
    }

    @Override
    public boolean action(Obstacle obst) {
        boolean result = true;
        String resultToString = null;
        if (obst instanceof Run){
            result = ((Run) obst).run(this);
            resultToString = printResult(actionType.RUN,result);
        } else if (obst instanceof Jump){
            result = ((Jump) obst).jump(this);
            resultToString = printResult(actionType.JUMP,result);
        }
        System.out.printf("Робот %s %s препятствие: %s \n",name, resultToString, obst.getName());
        return result;
    }

    private String printResult (actionType actionType, boolean result){
        switch (actionType){
            case RUN:
                if (result){
                    return "пробежал";
                }else{
                    return "не пробежал";
                }
            case JUMP:
                if (result){
                    return "перепрыгнул";
                }else{
                    return "не перепрыгнул";
                }
        }
        return "#######";
    }

    @Override
    public double getRunLimit() {
        return maxRun;
    }

    @Override
    public double getJumpLimit() {
        return maxJump;
    }

    @Override
    public String getName() {
        return String.format("Робот %s",name);
    }
}
