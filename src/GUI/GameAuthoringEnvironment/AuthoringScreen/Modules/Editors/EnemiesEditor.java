package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Editors;

import GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Module;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class EnemiesEditor extends Module {

    private final int sourceViewWidth = 200;
    private final int sourceViewHeight = 200;
    private final int viewGap = 10;
    private ObservableList<Enemy> EnemyList = FXCollections.observableArrayList();
    private ListView<Enemy> sourceView = new ListView<>();
    private ListView<Enemy> targetView = new ListView<>();
    private TextArea loggingArea = new TextArea("");
    private static final DataFormat Enemy_LIST = new DataFormat("Enemy List");

    public EnemiesEditor(Group myRoot, int width, int height, String moduleName){
        super(myRoot, width, height, moduleName, true);
        setLayout(1000, 400);
        setContentColor(Color.LIGHTBLUE);
        setContent();
        addSaveButton();
    }

    //TODO Add a handler to the submit button so that it saves all the entered info in the right config file
    private void addSaveButton(){
        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(100);
        submitButton.setLayoutY(100);
        getContent().getChildren().add(submitButton);
    }


    //TODO put Enemy as an argument
    public void addEnemies(Enemy Enemy) {
        EnemyList.addAll(Enemy);
    }

    private ObservableList<Enemy> getEnemyList() {
        return EnemyList;
    }

    //TODO getter function
  /*  public ObservableList<Enemy> getAllEnemyList(){

    }

    public ObservableList<Enemy> getSelectedEnemyList(){

    }*/

    public void setContent() {
        Label sourceListLbl = new Label("Available enemies: ");
        Label targetListLbl = new Label("Selected enemies: ");
        Label messageLbl = new Label("Select Enemys from the given list, drag and drop them to another list");

        sourceView.setPrefSize(sourceViewWidth, sourceViewHeight);
        targetView.setPrefSize(sourceViewWidth, sourceViewHeight);
        loggingArea.setMaxSize(sourceViewWidth * 2 + viewGap, sourceViewHeight);

        //examples
        Enemy duvall = new Enemy("Prof.Duvall");
        Enemy tas = new Enemy("TAs");
        addEnemies(duvall);
        addEnemies(tas);

        sourceView.getItems().addAll(getEnemyList());

        sourceView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        targetView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Create the GridPane
        GridPane pane = new GridPane();
        pane.setHgap(viewGap);
        pane.setVgap(viewGap);

        // Add the Labels and Views to the Pane
        pane.add(messageLbl, 0, 0, 3, 1);
        pane.addRow(1, sourceListLbl, targetListLbl);
        pane.addRow(2, sourceView, targetView);

        setDragAndDrop();
        VBox root = new VBox();
        // Add the Pane and The LoggingArea to the VBox
        root.getChildren().addAll(pane, loggingArea);
        getContent().getChildren().add(root);

    }

    private void setDragAndDrop() {

        sourceView.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                writelog("sourceview dragdetected");
                dragDetected(event, sourceView);
            }
        });

        sourceView.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                writelog("sourceview draggedover");
                dragOver(event, sourceView);
            }
        });

        sourceView.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                writelog("sourceview dragdone");
                dragDone(event, targetView, sourceView);
            }
        });

        // Add mouse event handlers for the target
        targetView.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                writelog("targetview dragdetected");
                dragDetected(event, targetView);
            }
        });

        targetView.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                writelog("targetview draggedover");
                dragOver(event, targetView);
            }
        });

        targetView.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                writelog("Event on Target: drag done");

                dragDone(event, sourceView, targetView);
            }
        });
    }


    private void dragDetected(MouseEvent event, ListView<Enemy> listView) {
        // Make sure at least one item is selected
        int selectedCount = listView.getSelectionModel().getSelectedIndices().size();

        if (selectedCount == 0) {
            event.consume();
            return;
        }

        // Initiate a drag-and-drop gesture
        Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY_OR_MOVE);

        // Put the the selected items to the dragboard
        ArrayList<Enemy> selectedItems = getSelectedEnemy(listView);
        writelog(selectedItems.toString());

        ClipboardContent content = new ClipboardContent();
        content.put(Enemy_LIST, selectedItems);

        dragboard.setContent(content);
        //Stops any further handling of the event
        event.consume();
    }

    private void dragOver(DragEvent event, ListView<Enemy> listView) {
        // If drag board has an ITEM_LIST and it is not being dragged
        // over itself, we accept the MOVE transfer mode
        Dragboard dragboard = event.getDragboard();

        if (event.getGestureSource() != listView && dragboard.hasContent(Enemy_LIST)) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    private void dragDone(DragEvent event, ListView<Enemy> source, ListView<Enemy> target) {

        // Transfer the data to the target
        Dragboard dragboard = event.getDragboard();
        boolean dragCompleted = false;

        if (dragboard.hasContent(Enemy_LIST)) {
            ArrayList<Enemy> list = (ArrayList<Enemy>) dragboard.getContent(Enemy_LIST);
            source.getItems().addAll(list);

            // Data transfer is successful
            dragCompleted = true;
        }

        // Data transfer is not successful
        //event.setDropCompleted(dragCompleted);

        TransferMode tm = event.getTransferMode();

        if (tm == TransferMode.MOVE) {
            removeSelectedEnemies(target);
        }

        event.consume();
    }


    private ArrayList<Enemy> getSelectedEnemy(ListView<Enemy> listView) {

        ArrayList<Enemy> list = new ArrayList<>(listView.getSelectionModel().getSelectedItems());

        return list;
    }

    private void removeSelectedEnemies(ListView<Enemy> listView) {
        // Get all selected Enemy in a separate list to avoid the shared list issue
        List<Enemy> selectedList = new ArrayList<>();

        for (Enemy Enemy : listView.getSelectionModel().getSelectedItems()) {
            selectedList.add(Enemy);
        }

        // Clear the selection
        listView.getSelectionModel().clearSelection();
        // Remove items from the selected list
        listView.getItems().removeAll(selectedList);
    }

    // Helper Method for Logging
    private void writelog(String text) {
        this.loggingArea.appendText(text + "\n");
    }

}
