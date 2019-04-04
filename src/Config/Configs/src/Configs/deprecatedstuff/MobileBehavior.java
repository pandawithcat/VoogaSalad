package Configs.deprecatedstuff;

import java.util.List;

public interface MobileBehavior {
    enum Motion{
        FigureEight, Straight;
    }

    void setVelocity(double velocity);
    void setMotion(Motion type);

}
