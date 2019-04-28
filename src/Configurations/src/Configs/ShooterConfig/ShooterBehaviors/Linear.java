package Configs.ShooterConfig.ShooterBehaviors;

import ActiveConfigs.ActiveLevel;
import ActiveConfigs.ActiveProjectile;
import ActiveConfigs.ActiveWeapon;
import Configs.Configuration;
import Configs.MapFeature;
import Configs.ShooterConfig.Shooter;
import Configs.Updatable;
import Configs.View;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class Linear extends ShooterBehavior {
    public static final String DISPLAY_LABEL = "Linear Shooter";
    @XStreamOmitField
    private transient Configuration myConfiguration;
    private int startRound;

    public Linear(Shooter shooter){
       super(shooter);
       myConfiguration = new Configuration(this);
   }
    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public void update(double ms, Updatable parent) {
     shoot(ms, ((Shooter)parent).getMyShootable().getActiveWeapon().getMapFeature().getDirection());
    }
}
