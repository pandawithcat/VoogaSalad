package GameAuthoringEnvironment.AuthoringScreen;

import Configs.Configurable;
import Configs.Configuration;
import Configs.LevelPackage.Level;
import Configs.MapPackage.MapConfig;
import Configs.MapPackage.Terrain;
import GameAuthoringEnvironment.AuthoringScreen.TerrainTile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurableMap {
    public static final int GRID_WIDTH = 20;
    public static final int GRID_HEIGHT = 20;
    Map<String, Object> passedMap;
    List<TerrainTile> terrainTileList;
    GridPane map;
    private ListView<String> tileView = new ListView<>();
    private String currentTile = "Grass";
    private String dirtTileImage = "dirt.jpg";
    private String waterTileImage="water.jpg";
    private String grassTileImage="grass.jpg";
    private VBox layout;
    private final int tileViewWidth = 400;
    private final int tileViewHeight = 400;
    private Map<String, Object> myMap;
    private Stage popUpWindow;
    private String mapName;
    private TextField nameTf;
    private Configurable myLevel;
    private Button nameButton;
    private MapConfig myMapConfig;

    public ConfigurableMap(Map<String, Object> myAttributeMap, Configurable level){

        myMap = myAttributeMap;
        myLevel = level;
    }

    //Constructor for editing the map
    //TODO Add a way to display the existing map
    public ConfigurableMap(MapConfig mapConfig, Map<String, Object> myAttributeMap, Configurable level){
        myMap = myAttributeMap;
        myLevel = level;
        myMapConfig = mapConfig;
    }

    public void resetConfigurations(){
        popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Map Editor");

        layout = new VBox(10.00);

        VBox nameBox = new VBox(10);
        Label mapLbl = new Label("Map");
        nameTf = new TextField();
        nameTf.setText(myMapConfig.getName());
        nameButton = new Button("Confirm");
        nameButton.setOnMouseClicked(this::handleConfirmButton);
        nameBox.getChildren().addAll(mapLbl, nameTf, nameButton);

        Label tileListLbl = new Label("Tiles");
        Label messageLbl = new Label("Select tiles from the given list, click tile on map to change to selected tile type");
        reinitMap();
        initTileView();



        // Add the Labels and Views to the Pane
        layout.getChildren().addAll(messageLbl, nameBox, tileListLbl, map, tileView);
        addSubmit();

        Scene scene= new Scene(layout, 800, 800);
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();
    }

    private void reinitMap(){
        List<Terrain> existingTerrainList = myMapConfig.getTerrain();
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

    }

    public void setConfigurations(){
        popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Map Editor");

        layout = new VBox(10.00);

        VBox nameBox = new VBox(10);
        Label mapLbl = new Label("Map");
        nameTf = new TextField();
        nameButton = new Button("Confirm");
        nameButton.setOnMouseClicked(this::handleConfirmButton);
        nameBox.getChildren().addAll(mapLbl, nameTf, nameButton);

        Label tileListLbl = new Label("Tiles");
        Label messageLbl = new Label("Select tiles from the given list, click tile on map to change to selected tile type");
        initMap();
        initTileView();



        // Add the Labels and Views to the Pane
        layout.getChildren().addAll(messageLbl, nameBox, tileListLbl, map, tileView);
        addSubmit();

        Scene scene= new Scene(layout, 800, 800);
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();

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
                    map.add(myTile, r, c);
                    //map.add(tBuild.getTile("Grass",r,c,20,20),r,c);
                }

            }
            addGridEvent();
        }catch (FileNotFoundException e){

        }
    }

    public void initTileView(){
        tileView.setPrefSize(tileViewWidth, tileViewHeight);
        tileView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tileView.getItems().add(0,"Grass");
        tileView.getItems().add(1,"Water");
        tileView.getItems().add(2,"Dirt");
        tileView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                currentTile=tileView.getSelectionModel().getSelectedItem();
                //System.out.println(currentTile);
            }
        });
    }
    private void addSubmit(){
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
                    Terrain tile = new Terrain(m, t.getTileImString(),(int) t.getY(), (int) t.getX(),Terrain.TERRAIN_SIZE,Terrain.TERRAIN_SIZE,t.getIsPath());

                    tileList.add(tile);
                }

                passedMap=new HashMap<>();
                passedMap.put("myName",mapName);
                passedMap.put("myTerrain",tileList);
                passedMap.put("enemyEnteringGridXPos", 0);
                passedMap.put("enemyEnteringGridYPos", 0);
                passedMap.put("enemyEnteringDirection",90);
                passedMap.put("enemyExitGridXPos",19);
                passedMap.put("enemyExitGridYPos",19);
                passedMap.put("gridHeight",GRID_HEIGHT);
                passedMap.put("gridWidth",GRID_WIDTH);

                m.getConfiguration().setAllAttributes(passedMap);

                myMap.put("myMap", m);

                popUpWindow.close();
            }
        });
        layout.getChildren().add(subButton);

    }

    //    private void addSizeLabel(){
//
//        TextField txt = new TextField();
//        txt.setPromptText("Size of Tile to Modify");
//        Button sub = new Button("Submit");
//        Label lab = new Label();
//
//        sub.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                if(txt.getText()!=null&&!txt.getText().isEmpty()&&Integer.parseInt(txt.getText())<6){
//                    modTileSize=Integer.parseInt(txt.getText());
//                    lab.setText("");
//                    System.out.println(modTileSize);
//                }
//                else{
//                    lab.setText("Invalid Input");
//                }
//
//            }
//        });
//        .addRow(3,txt,sub);
//
//    }
    private void addEnemEnterPosButton(){
        TextField enemEnterX = new TextField();
        Button confirmEnemEnterX = new Button("Confirm");
        TextField enemEnterY = new TextField();
        Button confirmEnemEnterY = new Button("Confirm");
        TextField enemExitX = new TextField();
        Button confirmEnemExitX = new Button("Confirm");
        TextField enemExitY = new TextField();
        Button confirmEnemExitY = new Button("Confirm ");

    }

    private void addGridEvent(){
        map.getChildren().forEach(item-> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    updateCell(mouseEvent);
                }
            });

        });
    }
    public void updateCell(MouseEvent mouseEvent){
        //System.out.println("HELLO");
        TerrainTile source = (TerrainTile) mouseEvent.getSource();

        Integer col = GridPane.getColumnIndex(source);
        Integer row = GridPane.getRowIndex(source);

        source.changeImage(currentTile);






    }

}