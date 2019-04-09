package Player;

import BackendExternal.Logic;
import javafx.geometry.Insets;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class GamePlayArsenal extends VBox {
    private Logic myLogic;
    //list of WeaponInfo objects which has ID and an imageview
    private List myArsenal;

    public GamePlayArsenal(double arsenalWidth, double arsenalHeight, Logic logic){
        myLogic = logic;
        myArsenal = myLogic.getArsenal();
        TreeView arsenalView = new TreeView();
        TreeItem rootItem = new TreeItem("Arsenal");

        rootItem.getChildren().addAll(myArsenal);
        arsenalView.setRoot(rootItem);
        arsenalView.setPrefHeight(arsenalHeight);
        arsenalView.setPrefWidth(arsenalWidth);
        getChildren().addAll(arsenalView);
    }

    public ArrayList<TreeItem> getArsenal(){
        ArrayList<TreeItem> myArsenal = new ArrayList<TreeItem>();
        //TODO: Iterate through a list of things users can implement
        TreeItem myWeapons = new TreeItem("Weapons");
        myWeapons.getChildren().addAll(getWeapons(myArsenal));

        TreeItem myObstacles = new TreeItem("Obstacles");
        myObstacles.getChildren().addAll(getObstacles());
        myArsenal.add(myWeapons);
        myArsenal.add(myObstacles);
        return myArsenal;
    }

    private ArrayList<TreeItem> getWeapons(List arsenal){
        ArrayList<TreeItem> weapons = new ArrayList<>();
        for (int i = 0; i < arsenal.size(); i++){
            String weaponName = arsenal.get(i).get;
            TreeItem tower = new TreeItem(weaponName);
            weapons.add(tower);
        }
        return weapons;
    }

    private ArrayList<TreeItem> getObstacles(){
        ArrayList<TreeItem> obstacles = new ArrayList<>();
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
