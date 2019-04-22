package Player.GamePlay.GamePlayLeft;

import BackendExternal.Logic;
import Configs.Info;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class GamePlayArsenal extends VBox {

    public static final double ARSENAL_RATIO = 0.90;
    public static final double SELECTOR_RATIO = 0.10;

    private Logic myLogic;
    private GamePlayArsenalSelector myArsenalSelector;
    private boolean isWeapon;
    private ArrayList<Pair<ImageView, String>> viewList;
    private ListView arsenalDisplay;
    private double myArsenalWidth;
    private HBox arsenalSelector;
    private ImageView selectedImage;
    private ImageView movingImage;
    private GamePlayMap myMap;
    private Group myRoot;
    private Map <String, Integer> weaponMap;


//    private Map <Integer, Info> myTestWeapons ;
    private Map <Integer, Info> myTestObstacles ;


    //list of WeaponInfo objects which has ID and an imageview
    private Map<Integer, Info> myArsenal;

    public GamePlayArsenal(double arsenalWidth, double arsenalHeight, Logic logic, GamePlayMap map, Group root) throws FileNotFoundException {
        myArsenalWidth = arsenalWidth;
        isWeapon = true;
        myLogic = logic;
        myMap = map;
        myArsenal = logic.getMyArsenal();

        System.out.println(weaponMap);
        myRoot = root;
        arsenalDisplay = new ListView();
        arsenalDisplay.setPrefHeight(arsenalHeight * ARSENAL_RATIO);
        arsenalDisplay.setPrefWidth(arsenalWidth);

        //START TEST STUFF
//        createTestWeaponArsenal();
//        createTestObstacleArsenal();
        viewList = new ArrayList<>();
        setArsenalDisplay(myArsenal,arsenalWidth);

        arsenalDisplay.setPrefHeight(arsenalHeight * ARSENAL_RATIO);
        arsenalDisplay.setPrefWidth(arsenalWidth);
        getChildren().addAll(arsenalDisplay);

        myArsenalSelector = new GamePlayArsenalSelector(arsenalWidth,arsenalHeight * SELECTOR_RATIO);
        getChildren().add(myArsenalSelector);
    }

    private void setArsenalDisplay(Map<Integer, Info> arsenal, double arsenalWidth) {
        try {
            //creates internal mapping of weapon and id
            arsenalDisplay.setCellFactory(viewList -> new ImageCell());
            weaponMap = new HashMap<>();
            for (Integer id: arsenal.keySet()) {
//                Image image = new Image(new FileInputStream("resources/" + myArsenal.get(id).getImage()));
//                ImageView imageView = new ImageView(image);
//                weaponMap.put(imageView.toString(), id);
//                System.out.println(imageView);
//                System.out.println("  ID: " + weaponMap.get(imageView.toString()));
//                imageView.setFitWidth(arsenalWidth / 2);
//                imageView.setFitHeight(arsenalWidth / 2);
//                Tooltip t = new Tooltip("A Square");
//                Tooltip.install(imageView, t);
//                viewList.add(new Pair(imageView,myArsenal.get(id).getName()));
                arsenalDisplay.getItems().add(loadImageWithCaption(myArsenal.get(id).getImage(),
                        myArsenal.get(id).getName()));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        arsenalDisplay.setOnDragDetected(mouseEvent -> dragDetected(mouseEvent));
        myMap.setOnDragOver(event -> dragOver(event));
        myMap.setOnDragEntered(event -> dragEntered(event));
        myMap.setOnDragExited(event -> dragExited(event));
        myMap.setOnDragDropped(event -> dragDropped(event));

    }

//    private void switchWeaponDisplay(){
//        if (!isWeapon) {
//            //TODO: implement display switch
//            viewList.clear();
//            setArsenalDisplay(myTestWeapons, myArsenalWidth);
//            isWeapon = true;
//        }
//    }
//
//    private void switchObstacleDisplay(){
//        if (isWeapon) {
//            //TODO: implement display switch
//            viewList.clear();
//            setArsenalDisplay(myTestObstacles, myArsenalWidth);
//            isWeapon = false;
//        }
//    }


//    private ArrayList<TreeItem> getWeapons(List arsenal){
//        ArrayList<TreeItem> weapons = new ArrayList<>();
//        for (int i = 0; i < arsenal.size(); i++){
////            String weaponName = arsenal.get(i).get;
////            TreeItem tower = new TreeItem(weaponName);
////            weapons.add(tower);
//        }
//        return weapons;
//    }
//
//    private ArrayList<TreeItem> getObstacles(){
//        ArrayList<TreeItem> obstacles = new ArrayList<>();
//        TreeItem obstacle1 = new TreeItem("barrier");
//        TreeItem obstacle2 = new TreeItem("yikes");
//        TreeItem obstacle3 = new TreeItem("gang");
//        obstacles.add(obstacle1);
//        obstacles.add(obstacle2);
//        obstacles.add(obstacle3);
//        return obstacles;
//    }

    private void dragDropped(DragEvent event){
        System.out.println("inside drop");
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            myRoot.getChildren().remove(movingImage);
            System.out.println("drag dropped");
            myLogic.instantiateWeapon(weaponMap.get(selectedImage.toString()), event.getX(), event.getY(),0);
            success = true;
        }
        event.setDropCompleted(success);
        event.consume();
    }

    private void dragExited(DragEvent event){
        System.out.println("drag exited");
        event.consume();
    }

    private void dragEntered(DragEvent event){
        if (event.getGestureSource() != myMap &&
                event.getDragboard().hasString()) {
            System.out.println("drag entered");
        }
        event.consume();
    }

    private void dragOver(DragEvent event){
        movingImage.setTranslateX(event.getX());
        movingImage.setTranslateY(event.getY());
        if (event.getGestureSource() != myMap ) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }

    private void dragDetected(MouseEvent mouseEvent){
        selectedImage = (ImageView)((Pair) arsenalDisplay.getSelectionModel().getSelectedItem()).getKey();
        Dragboard db = selectedImage.startDragAndDrop(TransferMode.ANY);

        //creates deepcopy of imageview
        var imageCopy = selectedImage.getImage();
        PixelReader pixelReader = imageCopy.getPixelReader();

        int width = (int)imageCopy.getWidth();
        int height = (int)imageCopy.getHeight();

        //Copy from source to destination pixel by pixel
        WritableImage writableImage
                = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                Color color = pixelReader.getColor(x, y);
                pixelWriter.setColor(x, y, color);
            }
        }

        movingImage = new ImageView();
        movingImage.setImage(writableImage);
        movingImage.setFitWidth(myMap.getGridSize());
        movingImage.setFitHeight(myMap.getGridSize());

        myRoot.getChildren().add(movingImage);
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        content.putString(selectedImage.toString());
//                content.put(DataFormat.IMAGE,selectedImage);
        db.setContent(content);
        mouseEvent.consume();
    }

    private static class ImageCell extends ListCell<Pair<ImageView, String>> {
        @Override
        public void updateItem(Pair<ImageView, String> item, boolean empty) {
            super.updateItem(item, empty);
            if(!empty) {
                setGraphic(item.getKey());
                Tooltip.install(this, new Tooltip(item.getValue()));
            }
        }
    }

    private static Pair<ImageView, String> loadImageWithCaption(String filename, String caption) {
        try {
            var image = new ImageView(new Image(new FileInputStream("resources/" + filename)));
            image.setFitWidth(100);
            image.setFitHeight(100);
            return new Pair<>(image, caption);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


//    //TEST DATA
//    private void createTestWeaponArsenal(){
//        Info testInfo = new Info("test", "weapon.png");
//        myTestWeapons = new HashMap<>();
//        for (int i = 0; i < 5; i++) {
//            myTestWeapons.put(i, testInfo);
//        }
//    }
//
//    private void createTestObstacleArsenal(){
//        Info testInfo = new Info("test", "obstacle.png");
//        myTestObstacles = new HashMap<>();
//        for (int i = 0; i < 5; i++) {
//            myTestObstacles.put(i, testInfo);
//        }
//    }
}
