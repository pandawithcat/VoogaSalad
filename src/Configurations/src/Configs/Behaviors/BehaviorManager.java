//package Configs.Behaviors;
//
//import Configs.Updatable;
//import Configs.View;
//import Configs.Viewable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BehaviorManager<T extends Behavior> implements Updatable, Viewable {
//
//    List<T> myBehaviors;
//
//
//    public BehaviorManager(List<T> behaviors) {
//        myBehaviors = behaviors;
//    }
//
//    public void addBehavior(T behavior) {
//        myBehaviors.add(behavior);
//    }
//
//    @Override
//    public void update(long ms) {
//        myBehaviors.stream().forEach(behavior -> behavior.update(ms));
//    }
//
//    @Override
//    public List<View> getViews() {
//        List<View> viewList = new ArrayList<>();
//        myBehaviors.stream().forEach(behavior -> viewList.addAll(behavior.getViews()));
//        return viewList;
//    }
//
//
//}
