package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Editors;

import GUI.GameAuthoringEnvironment.AuthoringConfig.Arsenal;
import GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Module;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class MapEditor extends Module {
    private final String currentTile = "GRASS";
    private final int tileViewWidth = 150;
    private final int tileViewHeight = 400;
    private final int viewGap = 10;
    private ListView<String> tileView = new ListView<>();
    private GridPane map;
    private GridPane pane;
    private VBox myVBox;
    private Pane myToolBar;
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
        Label messageLbl = new Label("Select tiles from the given list, drag and drop them to the map");
        initMap();
        initTileView();
        pane = new GridPane();

        pane.setHgap(10);
        pane.setVgap(10);

        // Add the Labels and Views to the Pane
        pane.add(messageLbl, 0, 0, 3, 1);
        pane.addRow(1, mapLbl, tileListLbl);
        pane.addRow(2, map, tileView);
        //pane.add(tileView,2,1);

        VBox root = new VBox();
        // Add the Pane and The LoggingArea to the VBox
        root.getChildren().addAll(pane);
        getContent().getChildren().add(root);

    }

    public void initMap(){
        map=new GridPane();
        for(int r = 0; r<20; r++) {
            for(int c = 0; c<20; c++){
                map.add(new GrassTile(r*1,c*1,20,20),r*1,c*1);
            }

        }
    }

    public void initTileView(){
        tileView.setPrefSize(tileViewWidth, tileViewHeight);
        tileView.getItems().add(0,"Grass");
        tileView.getItems().add(1,"Water");
        tileView.getItems().add(2,"Dirt");
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

}
