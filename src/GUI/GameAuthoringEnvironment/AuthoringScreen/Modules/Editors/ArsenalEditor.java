package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Editors;

import GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Module;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

//TODO This can depends on the arsenal interface(backend). This class assumes that arsenal class has following methods
//
public class ArsenalEditor extends Module {

    ListView<Arsenal> sourceView = new ListView<Arsenal>();
    ListView<Arsenal> targetView = new ListView<Arsenal>();
    TextArea loggingArea = new TextArea("");
    static final DataFormat Arsenal_LIST = new DataFormat("Arsenal List");

    public ArsenalEditor(Group myRoot, int width, int height, String moduleName){
        super(myRoot, width, height, moduleName, true);
        String cssLayout = "-fx-border-color: red;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: dashed;\n";

        getContent().setStyle(cssLayout);
    }

    public void setContent(){
        Label sourceListLbl = new Label("Source List: ");
        Label targetListLbl = new Label("Target List: ");
        Label messageLbl = new Label("Select arsenals from the given list, drag and drop them to another list");

        sourceView.setPrefSize(200, 200);
        targetView.setPrefSize(200, 200);
        loggingArea.setMaxSize(410, 200);

        sourceView.getItems().addAll(getArsenalList());

        sourceView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        targetView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Create the GridPane
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);

        // Add the Labels and Views to the Pane
        pane.add(messageLbl, 0, 0, 3, 1);
        pane.addRow(1, sourceListLbl, targetListLbl);
        pane.addRow(2, sourceView, targetView);


        sourceView.setOnDragDetected(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                writelog("Event on Source: drag detected");
                dragDetected(event, sourceView);
            }
        });

        sourceView.setOnDragOver(new EventHandler <DragEvent>()
        {
            public void handle(DragEvent event)
            {
                writelog("Event on Source: drag over");
                dragOver(event, sourceView);
            }
        });

        sourceView.setOnDragDropped(new EventHandler <DragEvent>()
        {
            public void handle(DragEvent event)
            {
                writelog("Event on Source: drag dropped");
                dragDropped(event, sourceView);
            }
        });

        sourceView.setOnDragDone(new EventHandler <DragEvent>()
        {
            public void handle(DragEvent event)
            {
                writelog("Event on Source: drag done");
                dragDone(event, sourceView);
            }
        });

        // Add mouse event handlers for the target
        targetView.setOnDragDetected(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                writelog("Event on Target: drag detected");
                dragDetected(event, targetView);
            }
        });

        targetView.setOnDragOver(new EventHandler <DragEvent>()
        {
            public void handle(DragEvent event)
            {
                writelog("Event on Target: drag over");
                dragOver(event, targetView);
            }
        });

        targetView.setOnDragDropped(new EventHandler <DragEvent>()
        {
            public void handle(DragEvent event)
            {
                writelog("Event on Target: drag dropped");
                dragDropped(event, targetView);
            }
        });

        targetView.setOnDragDone(new EventHandler <DragEvent>()
        {
            public void handle(DragEvent event)
            {
                writelog("Event on Target: drag done");
                dragDone(event, targetView);
            }
        });


        VBox root = new VBox();
        // Add the Pane and The LoggingArea to the VBox
        root.getChildren().addAll(pane,loggingArea);
        getContent().getChildren().add(root);

    }

    private ObservableList<Arsenal> getArsenalList()
    {
        ObservableList<Arsenal> arsenalList = FXCollections.<Arsenal>observableArrayList();

        Arsenal iceTower = new Arsenal("iceTower");
        Arsenal fireTower = new Arsenal("fireTower");


        arsenalList.addAll(iceTower, fireTower);

        return arsenalList;
    }


    private void dragDetected(MouseEvent event, ListView<Arsenal> listView)
    {
        // Make sure at least one item is selected
        int selectedCount = listView.getSelectionModel().getSelectedIndices().size();

        if (selectedCount == 0)
        {
            event.consume();
            return;
        }

        // Initiate a drag-and-drop gesture
        Dragboard dragboard = listView.startDragAndDrop(TransferMode.COPY_OR_MOVE);

        // Put the the selected items to the dragboard
        ArrayList<Arsenal> selectedItems = this.getSelectedArsenal(listView);

        ClipboardContent content = new ClipboardContent();
        content.put(Arsenal_LIST, selectedItems);

        dragboard.setContent(content);
        event.consume();
    }

    private void dragOver(DragEvent event, ListView<Arsenal> listView)
    {
        // If drag board has an ITEM_LIST and it is not being dragged
        // over itself, we accept the MOVE transfer mode
        Dragboard dragboard = event.getDragboard();

        if (event.getGestureSource() != listView && dragboard.hasContent(Arsenal_LIST))
        {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
    }

    @SuppressWarnings("unchecked")
    private void dragDropped(DragEvent event, ListView<Arsenal> listView)
    {
        boolean dragCompleted = false;

        // Transfer the data to the target
        Dragboard dragboard = event.getDragboard();

        if(dragboard.hasContent(Arsenal_LIST))
        {
            ArrayList<Arsenal> list = (ArrayList<Arsenal>)dragboard.getContent(Arsenal_LIST);
            listView.getItems().addAll(list);
            // Data transfer is successful
            dragCompleted = true;
        }

        // Data transfer is not successful
        event.setDropCompleted(dragCompleted);
        event.consume();
    }

    private void dragDone(DragEvent event, ListView<Arsenal> listView)
    {
        // Check how data was transfered to the target
        // If it was moved, clear the selected items
        TransferMode tm = event.getTransferMode();

        if (tm == TransferMode.MOVE)
        {
            removeSelectedArsenals(listView);
        }

        event.consume();
    }


    private ArrayList<Arsenal> getSelectedArsenal(ListView<Arsenal> listView)
    {
        // Return the list of selected Arsenal in an ArratyList, so it is
        // serializable and can be stored in a Dragboard.
        ArrayList<Arsenal> list = new ArrayList<>(listView.getSelectionModel().getSelectedItems());

        return list;
    }

    private void removeSelectedArsenals(ListView<Arsenal> listView)
    {
        // Get all selected Arsenal in a separate list to avoid the shared list issue
        List<Arsenal> selectedList = new ArrayList<>();

        for(Arsenal Arsenal : listView.getSelectionModel().getSelectedItems())
        {
            selectedList.add(Arsenal);
        }

        // Clear the selection
        listView.getSelectionModel().clearSelection();
        // Remove items from the selected list
        listView.getItems().removeAll(selectedList);
    }

    // Helper Method for Logging
    private void writelog(String text)
    {
        this.loggingArea.appendText(text + "\n");
    }


}
