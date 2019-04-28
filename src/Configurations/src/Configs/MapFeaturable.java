package Configs;

import ActiveConfigs.ActiveLevel;
import Configs.MapFeature;

public interface MapFeaturable {

    MapFeature getMapFeature();

    ActiveLevel getActiveLevel();
    void setMyMapFeature(MapFeature mapFeature);

}
