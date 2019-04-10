package Configs.EnemyPackage.EnemyBehaviors;



import Configs.Configuration;
import Configs.EnemyPackage.EnemyConfig;
import Configs.ProjectilePackage.ProjectileConfig;

public class Stealthy extends EnemyBehavior{
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
<<<<<<< HEAD
    public List<View> getViews() {
        return null;
    }

    @Override
=======
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
