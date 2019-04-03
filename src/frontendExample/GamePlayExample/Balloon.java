package frontendExample.GamePlayExample;

public abstract class Balloon {
    private final int maxHits = 3;
    private int currentHitsLeft;
    private String color;
    private double speed;

    public Balloon(){

    }
    public abstract void subtractHit();

    public abstract Balloon transformBalloon();
}
