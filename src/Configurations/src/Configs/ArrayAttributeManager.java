package Configs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayAttributeManager {

    public static void updateArray(Updatable[] updatables, long ms) {
        List<Updatable> myUpdatables = new ArrayList<>(Arrays.asList(updatables));
        myUpdatables.stream().forEach(obj -> obj.update(ms));
    }

    public static void updateList(List<Updatable>updatables, long ms){
        updatables.stream().forEach(obj -> obj.update(ms));
    }



}
