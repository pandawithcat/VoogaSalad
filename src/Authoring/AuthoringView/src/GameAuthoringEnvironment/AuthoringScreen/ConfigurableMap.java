package GameAuthoringEnvironment.AuthoringScreen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.util.Map;

public class ConfigurableMap {
    private GridPane map;
    private ListView<String> tileView = new ListView<>();
    private String currentTile = "Grass";
    private String dirtTileImage = "dirt.jpg";
    private String waterTileImage="water.jpg";
    private String grassTileImage="grass.jpg";
    private VBox layout;
    private final int tileViewWidth = 150;
    private final int tileViewHeight = 400;
    private Map<String, Object> myAttributesMap;

    public ConfigurableMap(Map<String, Object> myMap){
        myAttributesMap = myMap;
    }



    public void setConfigurations(){
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Map Editor");
        layout = new VBox(10.00);
        layout.autosize();
        Scene scene= new Scene(layout, 500, 500);
        popUpWindow.setScene(scene);
        popUpWindow.showAndWait();
        Label mapLbl = new Label("Map");
        Label tileListLbl = new Label("Tiles");
        Label messageLbl = new Label("Select tiles from the given list, click tile on map to change to selected tile type");
        initMap();
        initTileView();



        // Add the Labels and Views to the Pane
        layout.getChildren().add(messageLbl);
        layout.getChildren().addAll(mapLbl, tileListLbl);
        layout.getChildren().addAll(map, tileView);
        addSubmit();
        //pane.add(tileView,2,1);

        // Add the Pane and The LoggingArea to the VBox
    }
    public void initMap(){

        map=new GridPane();
        for(int r = 0; r<20; r++) {
            for(int c = 0; c<20; c++){

                map.add(new TerrainTile(r,c,new Image(this.getClass().getClassLoader().getResourceAsStream(grassTileImage)),currentTile),r,c);
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

            }
        });
        layout.getChildren().add(subButton);

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

