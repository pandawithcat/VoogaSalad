package Configs.EnemyPackage;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.Waves.Wave;

import java.util.List;

public class Enemy implements Configurable, Viewable, Updatable {
    private Wave myWave;
    @Configure
    List<Behavior<Enemy>> myBehaviors;

    public Enemy(Wave wave) {
        myWave = wave;
    }

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
    public List<ImmutableImageView> getViewsToBeAdded() {
        return null;
    }

    @Override
    public List<ImmutableImageView> getViewsToBeRemoved() {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
