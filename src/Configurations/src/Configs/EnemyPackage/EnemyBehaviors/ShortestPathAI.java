package Configs.EnemyPackage.EnemyBehaviors;

import Configs.Configuration;
import Configs.EnemyPackage.EnemyConfig;

public class ShortestPathAI extends EnemyBehavior {
    public static final String myLabel = "ShortestPathAI";
    @Configure
    private int speed;

    private Configuration myConfiguration;

    public ShortestPathAI(EnemyConfig enemyConfig){
        super(enemyConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public String getName() {
        return myLabel;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public void update(double ms) {

    }
}
