package Configs.EnemyPackage.EnemyBehaviors;



import Configs.Configuration;
import Configs.EnemyPackage.EnemyConfig;
import Configs.ProjectilePackage.ProjectileConfig;
import Configs.Updatable;

public class Stealthy extends EnemyBehavior{
    public static final String DISPLAY_LABEL = "Stealthy";
    @Configure
    ProjectileConfig[] projectilesThatCanAttackMe;
    private transient Configuration myConfiguration;

    Stealthy(EnemyConfig enemyConfig){
        super(enemyConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(double ms, Updatable parent) {
        //TODO
    }
    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
