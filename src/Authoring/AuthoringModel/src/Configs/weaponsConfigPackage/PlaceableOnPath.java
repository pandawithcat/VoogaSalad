package Configs.weaponsConfigPackage;

import java.util.Optional;

public class PlaceableOnPath implements Behavior {
    int rangeOnPath;
    public PlaceableOnPath(Optional<Integer> rangeOnPath){
        this.rangeOnPath = rangeOnPath.get();
    }
}
