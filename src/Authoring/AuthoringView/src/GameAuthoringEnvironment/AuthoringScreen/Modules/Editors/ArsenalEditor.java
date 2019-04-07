package GameAuthoringEnvironment.AuthoringScreen.Modules.Editors;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

//TODO This can depends on the arsenal interface(backend). This class assumes that arsenal class has following methods
//
public class ArsenalEditor extends Module {

    private final int sourceViewWidth = 200;
    private final int sourceViewHeight = 200;
    private final int viewGap = 10;
    private ObservableList<Arsenal> arsenalList = FXCollections.observableArrayList();
    private ListView<Arsenal> sourceView = new ListView<>();
    private ListView<Arsenal> targetView = new ListView<>();
    //TODO change the logging area to where user can change the property of each arsenal
    private TextArea loggingArea = new TextArea("");
    private static final DataFormat Arsenal_LIST = new DataFormat("Arsenal List");
    private final BooleanProperty dragModeActiveProperty =
            new SimpleBooleanProperty(this, "dragModeActive", true);
    private VBox myVBox;
    private Pane myToolBar;

    public ArsenalEditor(Group myRoot, int width, int height, String moduleName) {
        super(myRoot, width, height, moduleName, true);
        setLayout(600, 600);
        setContentColor(Color.LIGHTBLUE);
        myVBox = getVBox();
        myToolBar = getToolbarPane();
        setContent();
        addSaveButton();
        addMakeNewArsenalButton();
        makeDraggable(myToolBar);
    }

    //TODO MAKE NEW ARSENAL BUTTON
    private void addMakeNewArsenalButton(){
        Button createArsenal = new Button("Create Arsenal");


    }


    private void makeDraggable(Node topBar) {
        DragContext dragContext = new DragContext();
        topBar.addEventFilter(
                MouseEvent.ANY,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(final MouseEvent mouseEvent) {
                        if (dragModeActiveProperty.get()) {
                            // disable mouse events for all children
                            mouseEvent.consume();
                        }
                    }
                });

        topBar.addEventFilter(
                MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(final MouseEvent mouseEvent) {
                        if (dragModeActiveProperty.get()) {
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

        topBar.addEventFilter(
                MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(final MouseEvent mouseEvent) {
                        if (dragModeActiveProperty.get()) {
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

    //TODO make this into a separate class
    private void addSaveButton(){
        Button submitButton = new Button("Submit");
        submitButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                //TODO Export the list of arsenals
                targetView.getItems();
            }
        });


        submitButton.setLayoutX(100);
        submitButton.setLayoutY(100);
        getContent().getChildren().add(submitButton);
    }

    public void addArsenals(Arsenal arsenal) {
        arsenalList.addAll(arsenal);
    }

    private ObservableList<Arsenal> getArsenalList() {
        return arsenalList;
    }

    //TODO getter function
  /*  public ObservableList<Arsenal> getAllArsenalList(){

    }

    public ObservableList<Arsenal> getSelectedArsenalList(){

    }*/

    //TODO Instead of the loggging area, make a pop up screen for setting properties for the objects
    public void setContent() {
        Label sourceListLbl = new Label("Available Towers: ");
        Label targetListLbl = new Label("Selected Towers: ");
        Label messageLbl = new Label("Select arsenals from the given list, drag and drop them to another list");

        sourceView.setPrefSize(sourceViewWidth, sourceViewHeight);
        targetView.setPrefSize(sourceViewWidth, sourceViewHeight);
        loggingArea.setMaxSize(sourceViewWidth * 2 + viewGap, sourceViewHeight);

        //examples
        Arsenal iceTower = new Arsenal("iceTower");
        Arsenal fireTower = new Arsenal("fireTower");
        addArsenals(iceTower);
        addArsenals(fireTower);

        sourceView.getItems().addAll(getArsenalList());

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

    //TODO This can be refactord to a separate class
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
                dragDone(event,sourceView, targetView);
            }
        });
    }


    private void dragDetected(MouseEvent event, ListView<Arsenal> listView) {
        // Make sure at least one item is selected
        int selectedCount = listView.getSelectionModel().getSelectedIndices().size();

        if (selectedCount == 0) {
            event.consume();
            return;
        }

        // Initiate a drag-and-drop gesture
        Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY_OR_MOVE);

        // Put the the selected items to the dragboard
        ArrayList<Arsenal> selectedItems = getSelectedArsenal(listView);
        writelog(selectedItems.toString());

        ClipboardContent content = new ClipboardContent();
        content.put(Arsenal_LIST, selectedItems);

        dragboard.setContent(content);
        //Stops any further handling of the event
        event.consume();
    }

    private void dragOver(DragEvent event, ListView<Arsenal> listView) {
        // If drag board has an ITEM_LIST and it is not being dragged
        // over itself, we accept the MOVE transfer mode
        Dragboard dragboard = event.getDragboard();

        if (event.getGestureSource() != listView && dragboard.hasContent(Arsenal_LIST)) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    @SuppressWarnings("unchecked")
    private void dragDone(DragEvent event, ListView<Arsenal> source, ListView<Arsenal> target) {

        // Transfer the data to the target
        Dragboard dragboard = event.getDragboard();
        boolean dragCompleted = false;

        if (dragboard.hasContent(Arsenal_LIST)) {
            ArrayList<Arsenal> list = (ArrayList<Arsenal>) dragboard.getContent(Arsenal_LIST);
            source.getItems().addAll(list);

            // Data transfer is successful
            dragCompleted = true;
        }

        // Data transfer is not successful
        //event.setDropCompleted(dragCompleted);

        TransferMode tm = event.getTransferMode();

        if (tm == TransferMode.MOVE) {
            removeSelectedArsenals(target);
        }

        event.consume();
    }


    private ArrayList<Arsenal> getSelectedArsenal(ListView<Arsenal> listView) {

        ArrayList<Arsenal> list = new ArrayList<>(listView.getSelectionModel().getSelectedItems());

        return list;
    }

    private void removeSelectedArsenals(ListView<Arsenal> listView) {
        // Get all selected Arsenal in a separate list to avoid the shared list issue
        List<Arsenal> selectedList = new ArrayList<>();

        for (Arsenal Arsenal : listView.getSelectionModel().getSelectedItems()) {
            selectedList.add(Arsenal);
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
