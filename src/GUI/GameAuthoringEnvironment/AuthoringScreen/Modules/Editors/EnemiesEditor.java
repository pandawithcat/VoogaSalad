package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Editors;

import GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Module;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class EnemiesEditor extends Module {

    public EnemiesEditor(Group myRoot, int width, int height, String moduleName){
        super(myRoot, width, height, moduleName, true);

        setLayout(1000, 400);
        setContentColor(Color.LIGHTBLUE);
    }
}
