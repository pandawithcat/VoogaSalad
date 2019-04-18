package Configs.ArsenalConfig;

import Configs.ArsenalConfig.WeaponUnlockerBehaviors.WeaponUnlockerBehavior;
import Configs.Configurable;
import Configs.Configuration;

public class WeaponUnlocker implements Configurable {
    @Configure
    private WeaponUnlockerBehavior unlockerMode;

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public String getLabel() {
        return null;
    }
}
