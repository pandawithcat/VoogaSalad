package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class TreeViewHelper
{
    public TreeViewHelper() {

    }

    public ArrayList<TreeItem> getLevels()
    {
        ArrayList<TreeItem> levels = new ArrayList<>();

        TreeItem startScreen = new TreeItem("Start Screen");
        startScreen.getChildren().addAll(getStartScreenComponents());

        // TODO i should be replaced with the number of levels
        /*for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<> ("Level" + i);
            rootItem.getChildren().add(item);
        }*/

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

        TreeItem music = new TreeItem("Music");

        startScreenComponents.add(music);


        return startScreenComponents;

    }

    private ArrayList<TreeItem> getEndScreenComponents(){

        ArrayList<TreeItem> endScreenComponents = new ArrayList<>();
        return endScreenComponents;
    }

    private ArrayList<TreeItem> getLevelComponents()
    {
        ArrayList<TreeItem> levelComponents = new ArrayList<>();

        TreeItem map = new TreeItem("Map");
        TreeItem turrets = new TreeItem("Arsenals");
        TreeItem enemies = new TreeItem("Enemies");
        TreeItem music = new TreeItem("Music");

        levelComponents.add(map);
        levelComponents.add(turrets);
        levelComponents.add(enemies);
        levelComponents.add(music);

        return levelComponents;
    }



}
