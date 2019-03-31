package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules;

import java.util.ArrayList;
import javafx.scene.control.TreeItem;

public class TreeViewHelper
{
    public TreeViewHelper() {

    }

    public ArrayList<TreeItem> getLevels()
    {
        ArrayList<TreeItem> levels = new ArrayList<>();

        TreeItem startScreen = new TreeItem("Start Screen");
        startScreen.getChildren().addAll(getStartScreenComponents());

        TreeItem level1 = new TreeItem("Level 1");
        level1.getChildren().addAll(getLevelComponents());

        TreeItem level2 = new TreeItem("Level 2");
        level2.getChildren().addAll(getLevelComponents());

        TreeItem endScreen = new TreeItem("EndScreen");
        endScreen.getChildren().addAll(getEndScreenComponents());

        levels.add(startScreen);
        levels.add(level1);
        levels.add(level2);
        levels.add(endScreen);

        return levels;
    }

    private ArrayList<TreeItem> getStartScreenComponents(){

        ArrayList<TreeItem> startScreenComponents = new ArrayList<>();


        return startScreenComponents;

    }

    private ArrayList<TreeItem> getEndScreenComponents(){

        ArrayList<TreeItem> endScreenComponents = new ArrayList<>();
        return endScreenComponents;
    }

    private ArrayList<TreeItem> getLevelComponents()
    {
        ArrayList<TreeItem> LevelComponents = new ArrayList<>();

        TreeItem map = new TreeItem("Map");
        TreeItem turrets = new TreeItem("Turrets");
        TreeItem enemies = new TreeItem("Enemies");

        LevelComponents.add(map);
        LevelComponents.add(turrets);
        LevelComponents.add(enemies);

        return LevelComponents;
    }


}
