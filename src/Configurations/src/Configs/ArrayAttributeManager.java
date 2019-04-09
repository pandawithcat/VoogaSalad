package Configs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayAttributeManager {

    public static void updateList(Updatable[] updatables,long ms) {
        List<Updatable> myUpdatables = new ArrayList<>(Arrays.asList(updatables));
        myUpdatables.stream().forEach(obj -> obj.update(ms));
    }




}
