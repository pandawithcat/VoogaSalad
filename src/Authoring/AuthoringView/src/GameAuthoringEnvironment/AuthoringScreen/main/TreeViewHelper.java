package GameAuthoringEnvironment.AuthoringScreen.main;

import javafx.scene.control.TreeItem;

import java.util.ArrayList;

public class TreeViewHelper
{
    private int myNumberOfLevels;
    public TreeViewHelper(int numberOfLevels) {
        myNumberOfLevels = numberOfLevels;
    }

    public ArrayList<TreeItem> getLevels()
    {
        ArrayList<TreeItem> levels = new ArrayList<>();

        TreeItem startScreen = new TreeItem("Start Screen");
        startScreen.getChildren().addAll(getStartScreenComponents());
        levels.add(startScreen);

        for(int i = 1; i <= myNumberOfLevels; i++){
            TreeItem myLevel = new TreeItem("Level " + i);
            myLevel.getChildren().addAll(getLevelComponents());
            levels.add(myLevel);
        }

        TreeItem endScreen = new TreeItem("EndScreen");
        endScreen.getChildren().addAll(getEndScreenComponents());

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
