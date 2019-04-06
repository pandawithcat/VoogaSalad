package Configs.EnemyPackage;

import Configs.Behaviors.Behavior;
import Configs.Configurable;
import Configs.Updatable;
import Configs.View;
import Configs.Viewable;

import java.util.List;

public class Enemy implements Configurable, Viewable, Updatable {
    @Configure
    List<Behavior<Enemy>> myBehaviors;

    public void setMyBehaviors(List<Behavior<Enemy>> behavior) {
        myBehaviors = behavior;
    }

    public List<Behavior<Enemy>> getMyBehaviors() {
        return myBehaviors;
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<View> getViews() {
        return null;
    }
}
