package GameAuthoringEnvironment.AuthoringScreen;

import Configs.Configurable;
import Configs.Configuration;
import Configs.LevelPackage.Level;
import Configs.MapPackage.MapConfig;
import Configs.MapPackage.Terrain;
import GameAuthoringEnvironment.AuthoringScreen.TerrainTile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.TabExpander;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurableMap {


    public static final int GRID_WIDTH = 32;
    public static final int GRID_HEIGHT = 20;
//public static final int GRID_WIDTH = 32;
//    public static final int GRID_HEIGHT = 20;
    Map<String,String> typeToImagePathMap;
    Map<String, Object> passedMap;
    List<TerrainTile> terrainTileList;
    GridPane map;
    private List<TerrainTile> myTerrainTileList;
    private List<Point> exitPointsList = new ArrayList<>();
    private List<Point> enterPointsList = new ArrayList<>();
    private ListView<String> tileView = new ListView<>();
    private ListView<Point> enterPosView, exitPosView;
    private String currentTile = "Grass";
    private String dirtTileImage = "dirt.jpg";
    private String waterTileImage="water.jpg";
    private String grassTileImage="grass.jpg";
    private final int tileViewWidth = 400;
    private final int tileViewHeight = 400;
    private Map<String, Object> myAttributesMap;
    private Stage popUpWindow;
    private String mapName;
    private TextField nameTf;
    private Configurable myLevel;
    private Button nameButton, chooseTileImageButton;
    private MapConfig myAttributesMapConfig;
    private Scene scene;

    public ConfigurableMap(Map<String, Object> attributeMap, Configurable level){

        myAttributesMap = attributeMap;
        myLevel = level;
    }

    //Constructor for editing the map
    public ConfigurableMap(MapConfig mapConfig, Map<String, Object> myAttributeMap, Configurable level){
        myAttributesMap = myAttributeMap;
        myLevel = level;
        myAttributesMapConfig = mapConfig;
    }

    public void setConfigurations(){
        initMap();
        addComponentToScreen();
    }

    public void resetConfigurations(){
        reinitMap();
        addComponentToScreen();
    }

    private void addComponentToScreen() {

        popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Map Editor");

        Group allLayout = new Group();
        VBox tileViewBox = createTileView();
        VBox nameBox = createNameBox();
        VBox enterViewBox = createEnterView();
        VBox exitViewBox = createExitView();
        VBox otherLayout = new VBox();
        //otherLayout.setLayoutX();
        //otherLayout.setLayoutY();
        VBox mapLayout = new VBox();

        HBox mapHBox = new HBox();
        mapHBox.getChildren().add(map);

        mapHBox.setLayoutX(450);
        mapLayout.getChildren().add(mapHBox);



        otherLayout.getChildren().addAll(nameBox, tileViewBox, enterViewBox, exitViewBox);
        Button submitButton = addSubmit();
        allLayout.getChildren().addAll(mapHBox, otherLayout, submitButton);



        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        double screenHeight = primaryScreenBounds.getHeight();
        double screenWidth = primaryScreenBounds.getWidth();

        scene= new Scene(allLayout, screenWidth, screenHeight );
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();
    }

    private VBox createEnterView(){
        VBox myVbox = new VBox(10);
        Label enterPosLabel = new Label("Enter Positions");
        enterPosView = new ListView();
        enterPosView.getItems().addAll(enterPointsList);
        myVbox.getChildren().addAll(enterPosLabel, enterPosView);
        return myVbox;
    }

    private VBox createExitView(){
        VBox myVbox = new VBox(10);
        Label exitPosLabel = new Label("Exit Positions");
        exitPosView = new ListView();
        exitPosView.getItems().addAll(exitPointsList);
        myVbox.getChildren().addAll(exitPosLabel, exitPosView);
        return myVbox;
    }

    private VBox createNameBox(){
        VBox myNameBox = new VBox(10);
        Label mapLbl = new Label("Map Name");
        TextField nameTf = new TextField();
        if(myAttributesMapConfig != null){
            nameTf.setText(myAttributesMapConfig.getName());}
        Button nameButton = new Button("Confirm");
        nameButton.setOnMouseClicked(this::handleConfirmButton);
        myNameBox.getChildren().addAll(mapLbl, nameTf, nameButton);

        return myNameBox;
    }
    private void reinitMap(){
        List<Terrain> existingTerrainList = myAttributesMapConfig.getTerrain();
        map = new GridPane();

        for(int r=0; r< GRID_WIDTH; r++){
            for(int c=0; c<GRID_HEIGHT; c++){
                Terrain myTerrain = existingTerrainList.get(r*GRID_WIDTH + c);
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream("resources/" + myTerrain.getView().getImage());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image = new Image(fis);
                TerrainTile myTile = new TerrainTile(r, c, image,myTerrain.getView().getImage() );
                map.add(myTile, r, c);
            }
        }
        addGridEvent();

        //map.setLayoutX();
        //map.setLayoutY();
    }


    public void handleConfirmButton(MouseEvent event){
        mapName = nameTf.getText();
    }

    public void initMap() {
        try {
            java.io.FileInputStream fis = new FileInputStream("resources/" + grassTileImage);
            Image image = new Image(fis);
            map = new GridPane();
            for (int r = 0; r < GRID_WIDTH; r++) {
                for (int c = 0; c < GRID_HEIGHT; c++) {
                    TerrainTile myTile = new TerrainTile(r, c, image, currentTile);
//                    Tooltip tooltip = new Tooltip(myTile.getTileImString());
//                    Tooltip.install(myTile,tooltip);
                    map.setStyle("-fx-background-color: white;");
                    map.add(myTile, r, c);
                    map.setGridLinesVisible(false);
                    //map.add(tBuild.getTile("Grass",r,c,20,20),r,c);
                }

            }
            addGridEvent();
        }catch (FileNotFoundException e){

        }


        //map.setLayoutX();
        //map.setLayoutY();
    }

    public VBox createTileView(){
        typeToImagePathMap = new HashMap<>();
        typeToImagePathMap.put("Grass","resources/grass.jpg");
        typeToImagePathMap.put("Water","resources/water.jpg");
        typeToImagePathMap.put("Dirt","resources/dirt.jpg");
        //tileView.setPrefSize(tileViewWidth, tileViewHeight);
        VBox myBox = new VBox(10);

        Label messageLbl = new Label("Select tiles from the given list, click tile on map to change to selected tile type");
        //TODO Change this so that no specific tiles are made(and definitely not just my images)
        tileView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        tileView.getItems().add(0,"Grass");
        tileView.getItems().add(1,"Water");//        this.setOnMouseClicked(new EventHandler<MouseEvent>() {

        tileView.getItems().add(2,"Dirt");
        tileView.setCellFactory(param->new ListCell<String>(){
            private ImageView image = new ImageView();
            @Override
            public void updateItem(String name, boolean empty){
                super.updateItem(name,empty);
                if(empty){
                    setText(null);
                    setGraphic(null);
                }
                else{
//                    for(String s : typeToImagePathMap.keySet()) {
//                        try {
//                            image.setFitHeight(20);
//                            image.setFitWidth(20);
//                            image.setImage(new Image(new FileInputStream(typeToImagePathMap.get(s))));
//                        }
//                        catch(FileNotFoundException f){
//                            System.out.println(f);
//                        }
//                    }
                    try {
                        if (name.equals("Grass"))
                            image.setImage(new Image(new FileInputStream("resources/grass.jpg")));
                        else if (name.equals("Water"))
                            image.setImage(new Image(new FileInputStream("resources/water.jpg")));
                        else if (name.equals("Dirt"))
                            image.setImage(new Image(new FileInputStream("resources/dirt.jpg")));

                    }
                    catch(FileNotFoundException f){
                        System.out.println(f);
                    }
                    setText(name);
                    setGraphic(image);
                }
            }
        });

        tileView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                currentTile=tileView.getSelectionModel().getSelectedItem();
            }
        });


        Button addTileImageButton = new Button("Add New Tile");
        addTileImageButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //TODO Create Pop up screen that can configure Tile and add that tile to the list of tiles
                ConfigureTile configureTile = new ConfigureTile(tileView,terrainTileList);

            }
        });

        myBox.getChildren().addAll(messageLbl, tileView, addTileImageButton);
        return  myBox;
    }
    private Button addSubmit(){
        //TODO Refactor
        Button subButton = new Button("Submit Map");
        subButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nameButton.fireEvent(mouseEvent);
                terrainTileList=new ArrayList<>();
                List<Terrain> tileList = new ArrayList<>();
                for(Node child: map.getChildren()){
                    terrainTileList.add((TerrainTile) child);
                }
                MapConfig m = new MapConfig((Level) myLevel);
                for(TerrainTile t : terrainTileList){
                    Terrain tile = new Terrain(m, t.getTileImString(),(int) t.getY(), (int) t.getX(),t.getIsPath());

                    tileList.add(tile);
                }


                enterPointsList = enterPosView.getItems();
                exitPointsList = exitPosView.getItems();
                //TODO Need to clean this up
                passedMap=new HashMap<>();
                passedMap.put("myName",mapName);
                passedMap.put("myTerrain",tileList);
                passedMap.put("enemyEnteringGridPosList", enterPointsList);
                passedMap.put("enemyEnteringDirection",90);
                passedMap.put("enemyExitGridYPos",exitPointsList);


                m.getConfiguration().setAllAttributes(passedMap);


                myAttributesMap.put("myMap", m);

                popUpWindow.close();
            }
        });
       return subButton;
    }


    private void addGridEvent(){

        map.getChildren().forEach(item-> {
            item.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Tooltip toolTip = new Tooltip();
                    toolTip.setText(((TerrainTile)item).getType()+" "+((TerrainTile)item).getPathString());
                    Tooltip.install(item,toolTip);
                }
            });
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    MouseButton button = mouseEvent.getButton();
                    if(button == MouseButton.PRIMARY){
                        updateCellMouse(mouseEvent);

                    }

                    else if(button == MouseButton.SECONDARY){
                        item.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
                            @Override
                            public void handle(ContextMenuEvent event) {
                                ContextMenu contextMenu = new ContextMenu();
                                MenuItem menuItem1 = new MenuItem("Set as Enter Pos");
                                menuItem1.setOnAction(new EventHandler<ActionEvent>() {
                                    public void handle(ActionEvent t) {
                                        TerrainTile terrainTile = (TerrainTile) item;
                                        terrainTile.setPath();
                                        Point enterPoint = new Point((int)terrainTile.getX(), (int)terrainTile.getY());
                                        enterPosView.getItems().add(enterPoint);
                                        try{
                                            terrainTile.setImage(new Image(new FileInputStream("resources/enter.jpg")));
                                        }
                                        catch(FileNotFoundException f){
                                            System.out.println(f);
                                        }

                                    }
                                });

                                MenuItem menuItem2 = new MenuItem("Set as Exit Pos");
                                menuItem2.setOnAction(new EventHandler<ActionEvent>() {
                                    public void handle(ActionEvent t) {
                                        TerrainTile terrainTile = (TerrainTile) item;
                                        Point exitPoint = new Point((int)terrainTile.getX(), (int)terrainTile.getY());
                                        exitPosView.getItems().add(exitPoint);
                                        terrainTile.setPath();

                                        try{
                                            terrainTile.setImage(new Image(new FileInputStream("resources/exit.jpg")));
                                        }
                                        catch(FileNotFoundException f){
                                            System.out.println(f);

                                        }
                                    }
                                });
                                contextMenu.getItems().addAll(menuItem1, menuItem2);
                                contextMenu.show(map, event.getScreenX(), event.getScreenY());

                            }
                        });

                    }
                }
            });
            item.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("DRAGDETECTEDDDD");
                    Dragboard db = item.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(currentTile);
                    db.setContent(content);
                    mouseEvent.consume();
                }
            });
            item.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    dragEvent.acceptTransferModes(TransferMode.ANY);
                    updateCell(dragEvent);
                    System.out.println("DRAGGINGGGGGG");
                }
            });

        });

    }

    public void updateCell(DragEvent mouseEvent){
        TerrainTile source = (TerrainTile) mouseEvent.getSource();
        source.changeImage(currentTile);

    }

    public void updateCellMouse(MouseEvent mouseEvent){
        TerrainTile source = (TerrainTile) mouseEvent.getSource();
        source.changeImage(currentTile);

    }
}