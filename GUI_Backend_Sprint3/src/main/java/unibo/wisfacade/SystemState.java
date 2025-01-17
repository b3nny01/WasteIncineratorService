package unibo.wisfacade;
import unibo.wisfacade.Position;

public class SystemState {
    private int rps;
    private boolean active;
    private boolean burning;
    private double ashLevel;
    private String opRobotState;
    private Position opRobotPosition;
    private String ledState;

    public SystemState(int rps, boolean active, boolean burning, double ashLevel, String opRobotState,Position opRobotPosition,
            String ledState) {
        this.rps = rps;
        this.active = active;
        this.burning = burning;
        this.ashLevel = ashLevel;
        this.opRobotState = opRobotState;
        this.opRobotPosition=opRobotPosition;
        this.ledState = ledState;
    }

    public SystemState() {
        this.rps = -1;
        this.active = false;
        this.burning = false;
        this.ashLevel = -1;
        this.opRobotState = "";
        this.opRobotPosition=new Position(0,0);
        this.ledState = "";
    }

    public int getRps() {
        return rps;
    }

    public void setRps(int rps) {
        this.rps = rps;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isBurning() {
        return burning;
    }

    public void setBurning(boolean burning) {
        this.burning = burning;
    }

    public double getAshLevel() {
        return ashLevel;
    }

    public void setAshLevel(double ashLevel) {
        this.ashLevel = ashLevel;
    }

    public String getOpRobotState() {
        return opRobotState;
    }

    public void setOpRobotState(String opRobotState) {
        this.opRobotState = opRobotState;
    }

    public String getLedState() {
        return ledState;
    }

    public void setLedState(String ledState) {
        this.ledState = ledState;
    }

    public Position getOpRobotPosition() {
        return opRobotPosition;
    }

    public void setOpRobotPosition(Position opRobotPosition) {
        this.opRobotPosition = opRobotPosition;
    }

}
