package Configs.EnemyPackage.EnemyBehaviors;



import Configs.Behaviors.Behavior;
import Configs.Configuration;
import Configs.EnemyPackage.EnemyConfig;
import Configs.ProjectilePackage.ProjectileConfig;
import Configs.Updatable;

public class InvulnerableTo extends EnemyBehavior{
    public static final String DISPLAY_LABEL = "InvulnerableTo";
    @Configure
    ProjectileConfig[] projectilesThatCanAttackMe;
    private transient Configuration myConfiguration;

    InvulnerableTo(EnemyConfig enemyConfig){
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

    @Override
    public Behavior copy() {
        InvulnerableTo ret = new InvulnerableTo(getMyEnemyConfig());
        ret.projectilesThatCanAttackMe = projectilesThatCanAttackMe;
        return ret;
    }
}
