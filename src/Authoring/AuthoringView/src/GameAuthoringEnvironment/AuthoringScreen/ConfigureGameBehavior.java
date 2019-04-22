package GameAuthoringEnvironment.AuthoringScreen;

import Configs.Configurable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class ConfigureGameBehavior {

    List<Class> myList;
    Map<String, Object> myMap;
    Stage popUpWindow;
    VBox layout;
    Configurable myConfigurable;
    GameController myGameController;

    public ConfigureGameBehavior(GameController gameController, Configurable configurable, Map<String, Object> attributesMap, List<Class> behaviorList){
        myGameController = gameController;
        myConfigurable = configurable;
        myList = behaviorList;
        myMap = attributesMap;
        setContent();

    }

    private void setContent(){
        popUpWindow = new Stage();
        //popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Behavior Editor");
        layout = new VBox(10.00);

        //TODO Give users options to choose different game types but provide explanations too. Maybe relevant images or example games

        layout.getChildren().addAll();
        Scene scene= new Scene(layout, 800, 800);
        popUpWindow.setScene(scene);
        popUpWindow.show();
    }
}
