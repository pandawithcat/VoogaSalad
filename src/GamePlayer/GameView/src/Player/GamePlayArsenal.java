package Player;

import BackendExternal.Logic;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class GamePlayArsenal extends VBox {

    public static final double ARSENAL_RATIO = 0.90;
    public static final double SELECTOR_RATIO = 0.10;

    private Logic myLogic;
    private GamePlayArsenalSelector myArsenalSelector;
    public static final String WEAPON_IMAGE = "weapon.png";
    private Image weaponImage;
    private Image obstacleImage;
    private ImageView weaponImageView;
    private ImageView obstacleImageView;
    private boolean isWeapon;

    //list of WeaponInfo objects which has ID and an imageview
    private List myArsenal;

    public GamePlayArsenal(double arsenalWidth, double arsenalHeight, Logic logic){
        //initialize weapon display first
        isWeapon = true;
        myLogic = logic;
//        myArsenal = myLogic.getArsenal();
        TreeView arsenalView = new TreeView();
        TreeItem rootItem = new TreeItem("Arsenal");

//        rootItem.getChildren().addAll(myArsenal);
//        arsenalView.setRoot(rootItem);
        arsenalView.setPrefHeight(arsenalHeight * ARSENAL_RATIO);
        arsenalView.setPrefWidth(arsenalWidth);
        getChildren().addAll(arsenalView);

        //arsenal selector part
//        myArsenalSelector = new GamePlayArsenalSelector(arsenalWidth,arsenalHeight * SELECTOR_RATIO);
        getChildren().add(createArsenalSelector(arsenalWidth,arsenalHeight * SELECTOR_RATIO));
    }

    private HBox createArsenalSelector(double width, double height){
        HBox arsenalSelector = new HBox();
        weaponImage = new Image(WEAPON_IMAGE);
        weaponImageView = new ImageView(weaponImage);
        weaponImageView.setFitHeight(height);
        weaponImageView.setFitWidth(width /2);
        Button weaponButton = new Button("", weaponImageView);
        weaponButton.setOnAction(e -> switchWeaponDisplay());
        arsenalSelector.getChildren().add(weaponButton);

        //obstacles
        obstacleImage = new Image(WEAPON_IMAGE);
        obstacleImageView = new ImageView(WEAPON_IMAGE);
        obstacleImageView.setFitHeight(height);
        obstacleImageView.setFitWidth(width /2);
        Button obstacleButton = new Button("", obstacleImageView);
        obstacleButton.setOnAction(e -> switchObstacleDisplay());
        arsenalSelector.getChildren().add(obstacleButton);
        return arsenalSelector;
    }

    private void switchWeaponDisplay(){
        if (!isWeapon) {
            //TODO: implement display switch
            System.out.println("switch to weapon");
            isWeapon = true;
        }
    }

    private void switchObstacleDisplay(){
        if (isWeapon) {
            //TODO: implement display switch
            System.out.println("switch to obstacle");
            isWeapon = false;
        }
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
//            String weaponName = arsenal.get(i).get;
//            TreeItem tower = new TreeItem(weaponName);
//            weapons.add(tower);
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
