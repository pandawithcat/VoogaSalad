package ActiveConfigs;

import Configs.ArsenalConfig.WeaponConfig;
import Configs.Updatable;
import Configs.Viewable;

public class ActiveWeapon extends WeaponConfig implements Updatable, Viewable {

    public ActiveWeapon(WeaponConfig weaponConfig) {
        super(weaponConfig);
    }

}
