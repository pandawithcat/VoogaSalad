package Configs.ProjectileConfig;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.WeaponsConfig.Weapon;

import java.util.List;

public class ProjectileConfig implements Viewable, Configurable, Updatable {
    @Configure
    ProjectileOptions projectileType;
    @Configure
    List<Behavior<ProjectileConfig>> myBehaviors;

    Configuration myConfiguration;
    public ProjectileConfig(){
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<View> getViews() {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
