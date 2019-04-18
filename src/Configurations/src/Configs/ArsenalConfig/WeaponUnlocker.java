package Configs.ArsenalConfig;

import Configs.ArsenalConfig.WeaponUnlockerBehaviors.WeaponUnlockerBehavior;
import Configs.Configurable;

public class WeaponUnlocker implements Configurable {
    @Configure
    private WeaponUnlockerBehavior unlockerMode;


    @Override
    public String getLabel() {
        return null;
    }
}
