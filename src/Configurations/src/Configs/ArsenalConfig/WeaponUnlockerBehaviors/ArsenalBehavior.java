package Configs.ArsenalConfig.WeaponUnlockerBehaviors;

import Configs.ArsenalConfig.Arsenal;
import Configs.Behaviors.Behavior;

import java.util.List;

public abstract class ArsenalBehavior implements Behavior<Arsenal> {
    public static final String myLabel = "Arsenal Behavior";
    public static final List<Class> IMPLEMENTING_BEHAVIORS = List.of(
            LevelUnlockable.class);

    @Override
    public List<Class> getBehaviorOptions() {
        return IMPLEMENTING_BEHAVIORS;
    }
}
