package Configs.ArsenalConfig.WeaponUnlockerBehaviors;

import Configs.ArsenalConfig.WeaponBehaviors.*;
import Configs.ArsenalConfig.WeaponUnlocker;
import Configs.Behaviors.Behavior;

import java.util.List;

public abstract class WeaponUnlockerBehavior implements Behavior<WeaponUnlocker> {
    public static final List<Class> IMPLEMENTING_BEHAVIORS = List.of(
            LevelUnlocker.class);

    @Override
    public List<Class> getBehaviorOptions() {
        return IMPLEMENTING_BEHAVIORS;
    }
}
