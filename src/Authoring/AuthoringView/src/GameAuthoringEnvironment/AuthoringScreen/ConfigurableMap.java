package GameAuthoringEnvironment.AuthoringScreen;

import Configs.Configurable;
import Configs.Configuration;
import Configs.MapPackage.MapConfig;
import Configs.MapPackage.Terrain;
import GameAuthoringEnvironment.AuthoringScreen.TerrainTile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurableMap {
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

    public ConfigurableMap(Map<String, Object> myAttributeMap){
       myMap = myAttributeMap;
    }

    public void setConfigurations(){
        popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Map Editor");

        layout = new VBox(10.00);

        Label mapLbl = new Label("Map");
        Label tileListLbl = new Label("Tiles");
        Label messageLbl = new Label("Select tiles from the given list, click tile on map to change to selected tile type");
        initMap();
        initTileView();



        // Add the Labels and Views to the Pane
        layout.getChildren().addAll(messageLbl, mapLbl, tileListLbl, map, tileView);
        addSubmit();

        Scene scene= new Scene(layout, 800, 800);
        popUpWindow.setScene(scene);
        popUpWindow.show();
        //pane.add(tileView,2,1);

        // Add the Pane and The LoggingArea to the VBox
    }
    public void initMap(){

        map = new GridPane();
        for(int r = 0; r<20; r++) {
            for(int c = 0; c<20; c++){

                TerrainTile myTile = new TerrainTile(r,c,new Image(this.getClass().getClassLoader().getResourceAsStream(grassTileImage)),currentTile);

                map.add(myTile,r,c);
                //map.add(tBuild.getTile("Grass",r,c,20,20),r,c);
            }

        }
        addGridEvent();
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
                terrainTileList=new ArrayList<>();
                List<Terrain> tileList = new ArrayList<>();
                for(Node child: map.getChildren()){
                    terrainTileList.add((TerrainTile) child);
                }
                MapConfig m = new MapConfig();
                Configuration c = m.getConfiguration();
                for(TerrainTile t : terrainTileList){
                    Terrain tile = new Terrain(m,t.getImageView(),t.getTileImString(),(int) t.getY(), (int) t.getX(),20,20,map.getHeight(),map.getWidth(),t.getIsPath());
                    tileList.add(tile);
                }
                passedMap=new HashMap<>();
                passedMap.put("myLabel","Map");
                passedMap.put("myTerrain",tileList);
                passedMap.put("enemyEnteringGridXPos", 0);
                passedMap.put("enemyEnteringGridYPos", 0);
                passedMap.put("enemyEnteringDirection",90);
                passedMap.put("enemyExitGridXPos",20);
                passedMap.put("enemyExitGridYPos",20);


                passedMap.put("gridHeight",(int)map.getHeight());
                passedMap.put("gridWidth",(int)map.getWidth());
                c.getAttributes();
                c.setAllAttributes(passedMap);
                myMap.put("MapConfig", c);
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

        Integer col = map.getColumnIndex(source);
        Integer row = map.getRowIndex(source);

        System.out.println(col);
        System.out.println(row);

        source.changeImage(currentTile);






    }

}