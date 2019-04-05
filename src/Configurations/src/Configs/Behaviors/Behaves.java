package Configs.Behaviors;


import java.util.List;

public interface Behaves<T extends Behavior> {

    void addBehavior(T behavior);
    List<T> getMyBehaviors();

}
