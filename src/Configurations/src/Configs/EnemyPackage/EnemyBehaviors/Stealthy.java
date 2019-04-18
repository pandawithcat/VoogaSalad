package Configs.EnemyPackage.EnemyBehaviors;



import Configs.Configuration;
import Configs.EnemyPackage.EnemyConfig;
import Configs.ProjectilePackage.ProjectileConfig;

public class Stealthy extends EnemyBehavior{
    @Configure
    private String myLabel;
    @Configure
    ProjectileConfig[] projectilesThatCanAttackMe;
    private Configuration myConfiguration;

    Stealthy(EnemyConfig enemyConfig){
        super(enemyConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public String getLabel() {
        return myLabel;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
