package GUI.GamePlay.GameScreen;

import javafx.geometry.Insets;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class GamePlayArsenal extends VBox {

    public GamePlayArsenal(double arsenalWidth, double arsenalHeight){
        ArrayList<TreeItem> arsenal = getArsenal();
        TreeView arsenalView = new TreeView();
        TreeItem rootItem = new TreeItem("Arsenal");
        rootItem.getChildren().addAll(arsenal);
        arsenalView.setRoot(rootItem);
        arsenalView.setPrefHeight(arsenalHeight);
        arsenalView.setPrefWidth(arsenalWidth);
        getChildren().addAll(arsenalView);
    }

    public ArrayList<TreeItem> getArsenal(){
        ArrayList<TreeItem> myArsenal = new ArrayList<TreeItem>();
        //TODO: Iterate through a list of things users can implement
        TreeItem myWeapons = new TreeItem("Weapons");
        myWeapons.getChildren().addAll(getWeapons());
        TreeItem myObstacles = new TreeItem("Obstacles");
        myObstacles.getChildren().addAll(getObstacles());
        myArsenal.add(myWeapons);
        myArsenal.add(myObstacles);
        return myArsenal;
    }

    private ArrayList<TreeItem> getWeapons(){
        ArrayList<TreeItem> weapons = new ArrayList<TreeItem>();
        //TODO: also should iterate through list
        TreeItem tower1 = new TreeItem("gang");
        TreeItem tower2 = new TreeItem("boom");
        TreeItem tower3 = new TreeItem("pop");
        weapons.add(tower1);
        weapons.add(tower2);
        weapons.add(tower3);
        return weapons;
    }

    private ArrayList<TreeItem> getObstacles(){
        ArrayList<TreeItem> obstacles = new ArrayList<TreeItem>();
        //TODO: also should iterate through list
        TreeItem obstacle1 = new TreeItem("barrier");
        TreeItem obstacle2 = new TreeItem("yikes");
        TreeItem obstacle3 = new TreeItem("gang");
        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        return obstacles;
    }

}
