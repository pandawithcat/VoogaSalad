package Configs.Waves.WaveBehaviors;

import ActiveConfigs.ActiveEnemy;
import Configs.Configuration;
import Configs.Updatable;
import Configs.Waves.Wave;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.Arrays;

public class Faster extends WaveBehavior{
    public static final String DISPLAY_LABEL = "Accelerating";
    @Configure
    private double speedAcceleration;


    @XStreamOmitField
    private transient Configuration myConfiguration;

    public Faster(Wave wave) {
        super(wave);
        myConfiguration = new Configuration(this);
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }

    @Override
    public void update(double ms, Updatable parent) {
        Arrays.asList(((Wave) parent).getEnemies()).stream().forEach(enemy -> enemy.setSpeedModifier(speedAcceleration));
    }
}
