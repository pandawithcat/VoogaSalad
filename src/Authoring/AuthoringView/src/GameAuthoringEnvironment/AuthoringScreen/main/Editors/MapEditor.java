package GameAuthoringEnvironment.AuthoringScreen.main.Editors;
import GameAuthoringEnvironment.AuthoringScreen.main.Screen;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class MapEditor extends Screen {
    private String currentTile = "Grass";
    private final int tileViewWidth = 150;
    private final int tileViewHeight = 400;
    private final int viewGap = 10;
    private ListView<String> tileView = new ListView<>();
    private GridPane map;
    private GridPane pane;
    private VBox myVBox;
    private Pane myToolBar;
    private String dirtTileImage = "dirt.jpg";
    private String waterTileImage="water.jpg";
    private String grassTileImage="grass.jpg";
    private ArrayList<ArrayList<TerrainTile>> tileList;

    private final BooleanProperty dragActiveProperty =
            new SimpleBooleanProperty(this, "dragModeActive", true);

    public MapEditor(Group myRoot, int width, int height, String moduleName){
        super(myRoot, width, height, moduleName, true);
        myVBox=getVBox();
        myToolBar=getToolbarPane();
        setLayout(300, 100);
        setContentColor(Color.LIGHTBLUE);
        setContent();
        makeDraggable(myToolBar);
    }

    public void setContent(){
        Label mapLbl = new Label("Map");
        Label tileListLbl = new Label("Tiles");
        Label messageLbl = new Label("Select tiles from the given list, click tile on map to change to selected tile type");
        initMap();
        initTileView();
        pane = new GridPane();

        pane.setHgap(10);
        pane.setVgap(10);

        // Add the Labels and Views to the Pane
        pane.add(messageLbl, 0, 0, 3, 1);
        pane.addRow(1, mapLbl, tileListLbl);
        pane.addRow(2, map, tileView);
        addSubmit();
        //pane.add(tileView,2,1);

        VBox root = new VBox();
        // Add the Pane and The LoggingArea to the VBox
        root.getChildren().addAll(pane);
        getContent().getChildren().add(root);

    }

    public void initMap(){
        tileList=new ArrayList<ArrayList<TerrainTile>>();

        map=new GridPane();
        TileBuilder tBuild = new TileBuilder();
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

    private void makeDraggable(Node top){
        DragContext dragContext = new DragContext();
        top.addEventFilter(
                javafx.scene.input.MouseEvent.ANY,
                new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(final MouseEvent mouseEvent) {
                        if(dragActiveProperty.get()){
                            mouseEvent.consume();
                        }
                    }
                }
        );
        top.addEventFilter(
                javafx.scene.input.MouseEvent.MOUSE_PRESSED,
                new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(final javafx.scene.input.MouseEvent mouseEvent) {
                        if (dragActiveProperty.get()) {
                            // remember initial mouse cursor coordinates
                            // and node position
                            dragContext.mouseAnchorX = mouseEvent.getX();
                            dragContext.mouseAnchorY = mouseEvent.getY();
                            dragContext.initialTranslateX =
                                    myVBox.getTranslateX();
                            dragContext.initialTranslateY =
                                    myVBox.getTranslateY();
                        }
                    }
                });

        top.addEventFilter(
                javafx.scene.input.MouseEvent.MOUSE_DRAGGED,
                new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(final javafx.scene.input.MouseEvent mouseEvent) {
                        if (dragActiveProperty.get()) {
                            // shift node from its initial position by delta
                            // calculated from mouse cursor movement
                            myVBox.setTranslateX(
                                    dragContext.initialTranslateX
                                            + mouseEvent.getX()
                                            - dragContext.mouseAnchorX);
                            myVBox.setTranslateY(
                                    dragContext.initialTranslateY
                                            + mouseEvent.getY()
                                            - dragContext.mouseAnchorY);
                        }
                    }
                });
    }

    private void addSubmit(){
        Button subButton = new Button("Submit");
        subButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        pane.addRow(3,subButton);

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
        TileBuilder tb = new TileBuilder();
        //SquareCell sq = tb.getTile(currentTile,row,col,20,20);
        source.changeImage(currentTile);






    }


}

