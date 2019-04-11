package GameAuthoringEnvironment.AuthoringScreen;

import Configs.Behaviors.Behavior;
import Configs.Configurable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfigureBehavior {

    List<Class> myList;
    Map<String, Object> myMap;
    Stage popUpWindow;
    VBox layout;
    Configurable myConfigurable;
    private static final DataFormat Behavior_LIST = new DataFormat("Behavior List");
    private ListView<Class> sourceView = new ListView<>();
    private ListView<Class> targetView = new ListView<>();
    int sourceViewWidth = 250;
    int sourceViewHeight = 250;
    int viewGap = 10;
    private final BooleanProperty dragModeActiveProperty =
            new SimpleBooleanProperty(this, "dragModeActive", true);
    GameController myGameController;

    public ConfigureBehavior(GameController gameController, Configurable configurable, Map<String, Object> attributesMap, List<Class> behaviorList) {
        myGameController = gameController;
        myConfigurable = configurable;
        myList = behaviorList;
        myMap = attributesMap;
        setContent();
    }

    private void setContent() {
        popUpWindow = new Stage();
        //popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Behavior Editor");
        layout = new VBox(10.00);

        Label sourceListLbl = new Label("Available Behaviors: ");
        Label targetListLbl = new Label("Selected Behaviors: ");
        Label messageLbl = new Label("Drag and drop behaviors. Some behaviors require further configuration");

        sourceView.setPrefSize(sourceViewWidth, sourceViewHeight);
        targetView.setPrefSize(sourceViewWidth, sourceViewHeight);


        //TODO Change the listview so that only the simple name shows up
        sourceView.getItems().addAll(myList);
        System.out.println(sourceView.getItems().get(0).getClass() + "adjfhdalkfahdsjfk");
        sourceView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        targetView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //When clicked, calls createconfigurable again
        targetView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                var selected = targetView.getSelectionModel().getSelectedItem();
                if (selected.getClass().isInstance(Configurable.class)) {
                    try {
                        Class<?> cl = Class.forName(selected.getComponentType().getName());
                        Constructor<?> cons = cl.getConstructor(myConfigurable.getClass());
                        var object = cons.newInstance(myConfigurable);
                        myGameController.createConfigurable((Configurable) object);
                    } catch (Exception e) {

                    }

                }
            }
        });;

        // Create the GridPane
        GridPane pane = new GridPane();
        pane.setHgap(viewGap);
        pane.setVgap(viewGap);

        pane.addRow(0, messageLbl);
        pane.addRow(1, sourceListLbl, targetListLbl);
        pane.addRow(2, sourceView, targetView);

        //setDragAndDrop();
        VBox root = new VBox();
        root.getChildren().addAll(pane);

        Button setButton = new Button("This config completed");
        setButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!myConfigurable.getConfiguration().isConfigurationComplete()){
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setContentText("Atrributtes not all filled out");
                    alert.showAndWait();
                }
                else {
                    //TODO Add THE TARGETVIEW LIST TO THE ATTRIBUTES BUT HOW?
                    myConfigurable.getConfiguration().setAllAttributes(myMap);
                    popUpWindow.close();
                }
            }
        }));

        setDragAndDrop();
        layout.getChildren().addAll(root, setButton);
        Scene scene= new Scene(layout, 800, 800);
        popUpWindow.setScene(scene);
        popUpWindow.show();

    }

    public ListView<Class> getTargetView() {
        return targetView;
    }

    public ListView<Class> getSourceView() {
        return sourceView;
    }

    //TODO This can be refactord to a separate class
    private void setDragAndDrop() {

        sourceView.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                dragDetected(event, sourceView);
            }
        });

        sourceView.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                dragOver(event, sourceView);
            }
        });

        sourceView.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                dragDone(event, targetView, sourceView);
            }
        });

        // Add mouse event handlers for the target
        targetView.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                dragDetected(event, targetView);
            }
        });

        targetView.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                dragOver(event, targetView);
            }
        });

        targetView.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                dragDone(event,sourceView, targetView);
            }
        });
    }



    private void dragDetected(MouseEvent event, ListView listView) {
        // Make sure at least one item is selected
        int selectedCount = listView.getSelectionModel().getSelectedIndices().size();

        if (selectedCount == 0) {
            event.consume();
            return;
        }

        // Initiate a drag-and-drop gesture
        Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY_OR_MOVE);

        // Put the the selected items to the dragboard
        ArrayList<Class> selectedItems = getSelectedBehavior(listView);

        ClipboardContent content = new ClipboardContent();
        content.put(Behavior_LIST, selectedItems);

        dragboard.setContent(content);
        //Stops any further handling of the event
        event.consume();
    }

    private void dragOver(DragEvent event, ListView listView) {
        // If drag board has an ITEM_LIST and it is not being dragged
        // over itself, we accept the MOVE transfer mode
        Dragboard dragboard = event.getDragboard();

        if (event.getGestureSource() != listView && dragboard.hasContent(Behavior_LIST)) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    @SuppressWarnings("unchecked")
    private void dragDone(DragEvent event, ListView source, ListView target) {

        // Transfer the data to the target
        Dragboard dragboard = event.getDragboard();
        boolean dragCompleted = false;

        if (dragboard.hasContent(Behavior_LIST)) {
            ArrayList<Behavior> list = (ArrayList<Behavior>) dragboard.getContent(Behavior_LIST);
            source.getItems().addAll(list);

        }

        TransferMode tm = event.getTransferMode();

        if (tm == TransferMode.MOVE) {
            removeSelectedBehaviors(target);
        }

        event.consume();
    }


    private ArrayList<Class> getSelectedBehavior(ListView listView) {

        ArrayList<Class> list = new ArrayList<>(listView.getSelectionModel().getSelectedItems());

        return list;
    }

    private void removeSelectedBehaviors(ListView listView) {

        List<Class> selectedList = new ArrayList<>();

        for (Object object : listView.getSelectionModel().getSelectedItems()) {
            selectedList.add((Class) object);
        }

        // Clear the selection
        listView.getSelectionModel().clearSelection();
        // Remove items from the selected list
        listView.getItems().removeAll(selectedList);
    }

}