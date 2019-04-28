package Player.GamePlay.GamePlayRight;

import Player.GamePlay.SelectionInterface;
import Player.SetUp.GameSelection;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class QuitConfirmation extends Stage{

    public QuitConfirmation(SelectionInterface quit){
        Group root = new Group();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("style.css");
        //TODO: CHANGE FONT OF THIS
        Text text = new Text("Are you sure you want to quit?");
        text.setTextAlignment(TextAlignment.CENTER);
        setScene(scene);
        Button yes = createButton("shiny-yelow","Yes", 0, 100);
        yes.setOnAction(e -> displayChoices(quit));
        Button no = createButton("shiny-yelow", "No", 0, 100);
        no.setOnAction(e -> close());
        HBox buttons = new HBox(no,yes);
        VBox box = new VBox(text,buttons);
        box.setAlignment(Pos.CENTER);
        root.getChildren().add(box);
        show();
    }

    private Button createButton(String style, String title, int x, int y){
        Button button = new Button(title);
        button.setId(style);
        button.setTranslateX(x);
        button.setTranslateY(y);
        return button;
    }

    private void displayChoices(SelectionInterface quit){
        quit.closeStage();
        GameSelection gameSelection = new GameSelection();
        gameSelection.start(new Stage());
    }
}
