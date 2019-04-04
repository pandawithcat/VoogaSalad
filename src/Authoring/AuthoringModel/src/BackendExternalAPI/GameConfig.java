package BackendExternalAPI;

import APIs.IAuthoringModel;
import Configs.Config;
import Configs.LevelConfig;

import java.util.List;

public class GameConfig extends Config implements IAuthoringModel {
    private List<LevelConfig> myLevelList;
    private double myUserLives;
    private double myGridSize;

    public void setLevelList(List<LevelConfig> levelList) {
        myLevelList = levelList;
    }

    public void setMyUserLives(int lives) {
        myUserLives = lives;
    }

    public void setMyGridSize(int size) {
        myGridSize = size;
    }
}
