package Configs.ProjectilePackage.ProjectileBehaviors;

import ActiveConfigs.ActiveProjectile;
import Configs.Configuration;
import Configs.ProjectilePackage.ProjectileConfig;
import Configs.Updatable;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class Explosive extends ProjectileBehavior{

    @XStreamOmitField
    private transient Configuration myConfiguration;
    public static final String DISPLAY_LABEL = "Explosive Behavior";



    public Explosive (ProjectileConfig projectileConfig){
        super(projectileConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(double ms, Updatable parent) {
        ((ActiveProjectile) parent).getMyShooter().getShooterRange()
//TODO: FILL IN
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }
}
