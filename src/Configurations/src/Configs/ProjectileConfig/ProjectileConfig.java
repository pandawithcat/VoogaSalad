package Configs.ProjectileConfig;

import Configs.Behaviors.Behavior;
import Configs.Configurable;
import Configs.Updatable;
import Configs.View;
import Configs.Viewable;
import Configs.WeaponsConfig.Weapon;

import java.util.List;

public class ProjectileConfig implements Viewable, Configurable, Updatable {
    @Configure
    ProjectileOptions projectileType;
    @Configure
    List<Behavior<ProjectileConfig>> myBehaviors;
//    public ProjectileConfig(){
//         ProjectileOptions.values() how to get all options of the enum
//    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<View> getViews() {
        return null;
    }
}
