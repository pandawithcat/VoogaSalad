package Configs.EnemyPackage.EnemyBehaviors;



import Configs.Configuration;
import Configs.EnemyPackage.EnemyConfig;
import Configs.ProjectilePackage.ProjectileConfig;

public class Stealthy extends EnemyBehavior{
    public static final String myLabel = "Stealthy";
    @Configure
    ProjectileConfig[] projectilesThatCanAttackMe;
    private Configuration myConfiguration;

    Stealthy(EnemyConfig enemyConfig){
        super(enemyConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(double ms) {
        //TODO
    }
    @Override
    public String getName() {
        return myLabel;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
