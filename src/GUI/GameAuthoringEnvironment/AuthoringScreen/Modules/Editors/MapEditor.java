package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Editors;

import GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Module;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class MapEditor extends Module {
    private final int tileViewWidth = 200;
    private final int tileViewHeight = 500;
    private final int viewGap = 10;
    private ListView<Arsenal> tileView = new ListView<>();

    public MapEditor(Group myRoot, int width, int height, String moduleName){
        super(myRoot, width, height, moduleName, true);
        setLayout(300, 100);
        setContentColor(Color.LIGHTBLUE);
        setContent();
    }

    public void setContent(){
        Label mapLbl = new Label("Map");
        Label tileListLbl = new Label("Tiles");
        Label messageLbl = new Label("Select tiles from the given list, drag and drop them to the map");

        tileView.setPrefSize(tileViewWidth, tileViewHeight);
        GridPane pane = new GridPane();
        pane.setHgap(200);
        pane.setVgap(20);

        // Add the Labels and Views to the Pane
        pane.add(messageLbl, 0, 0, 3, 1);
        pane.addRow(1, mapLbl, tileListLbl);
        pane.addRow(2, mapLbl, tileListLbl);
        VBox root = new VBox();
        // Add the Pane and The LoggingArea to the VBox
        root.getChildren().addAll(pane);
        getContent().getChildren().add(root);
    }

}
