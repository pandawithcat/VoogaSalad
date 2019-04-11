package Player;

import BackendExternal.Logic;
import Configs.ImmutableImageView;
import Configs.Info;
import Configs.TransferImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.lang.reflect.Array;
import java.util.*;

public class GamePlayArsenal extends VBox {

    public static final double ARSENAL_RATIO = 0.90;
    public static final double SELECTOR_RATIO = 0.10;

    private Logic myLogic;
    private GamePlayArsenalSelector myArsenalSelector;
    public static final String WEAPON_IMAGE = "weapon.png";
    public static final String OBSTACLE_IMAGE = "obstacle.png";
    private Image weaponImage;
    private Image obstacleImage;
    private ImageView weaponImageView;
    private ImageView obstacleImageView;
    private boolean isWeapon;
    private ArrayList<ImageView> viewList;
    private ListView arsenalDisplay;
    private double myArsenalWidth;


    private Map <Integer, Info> myTestWeapons ;
    private Map <Integer, Info> myTestObstacles ;


    //list of WeaponInfo objects which has ID and an imageview
    private Map<Integer, Info> myArsenal;

    public GamePlayArsenal(double arsenalWidth, double arsenalHeight, Logic logic){
        myArsenalWidth = arsenalWidth;
        //initialize weapon display first
        isWeapon = true;
        myLogic = logic;
//        myArsenal = logic.getMyArsenal();
        arsenalDisplay = new ListView();
        arsenalDisplay.setPrefHeight(arsenalHeight * ARSENAL_RATIO);
        arsenalDisplay.setPrefWidth(arsenalWidth);

        //START TEST STUFF
        createTestWeaponArsenal();
        createTestObstacleArsenal();
        viewList = new ArrayList<>();
        setArsenalDisplay(myTestWeapons,arsenalWidth);


        //TODO: implement the hover shit when we set content
//        rootItem.getChildren().addAll(myArsenal);
//        arsenalView.setRoot(rootItem);

        arsenalDisplay.setPrefHeight(arsenalHeight * ARSENAL_RATIO);
        arsenalDisplay.setPrefWidth(arsenalWidth);

        getChildren().addAll(arsenalDisplay);

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
        obstacleImageView = new ImageView(OBSTACLE_IMAGE);
        obstacleImageView.setFitHeight(height);
        obstacleImageView.setFitWidth(width /2);
        Button obstacleButton = new Button("", obstacleImageView);
        obstacleButton.setOnAction(e -> switchObstacleDisplay());
        arsenalSelector.getChildren().add(obstacleButton);
        return arsenalSelector;
    }

    private void setArsenalDisplay(Map<Integer, Info> currArsenal, double arsenalWidth){
        currArsenal.values().stream().forEach(info -> { try {
        ImageView image = new ImageView(new Image(info.getImage()));
        image.setFitWidth(arsenalWidth/2);
        image.setFitHeight(arsenalWidth/2);
        viewList.add(image);
        } catch (Exception e){
            e.printStackTrace();
        }
        });
        ObservableList<ImageView> items = FXCollections.observableArrayList(viewList);
        arsenalDisplay.setItems(items);
        arsenalDisplay.setOnMouseEntered(e -> System.out.println(arsenalDisplay.getSelectionModel().getSelectedItem()));
    }

    private void switchWeaponDisplay(){
        if (!isWeapon) {
            //TODO: implement display switch
            viewList.clear();
            setArsenalDisplay(myTestWeapons, myArsenalWidth/2);
            isWeapon = true;
        }
    }

    private void switchObstacleDisplay(){
        if (isWeapon) {
            //TODO: implement display switch
            viewList.clear();
            System.out.println("switch to obstacle");
            setArsenalDisplay(myTestObstacles, myArsenalWidth/2);
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

    //TEST DATA
    private void createTestWeaponArsenal(){
        Info testInfo = new Info("test", "weapon.png");
        myTestWeapons = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            myTestWeapons.put(i, testInfo);
        }
    }

    private void createTestObstacleArsenal(){
        Info testInfo = new Info("test", "obstacle.png");
        myTestObstacles = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            myTestObstacles.put(i, testInfo);
        }
    }
}
