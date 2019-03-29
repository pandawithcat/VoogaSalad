package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules;

import GUI.GameAuthoringEnvironment.AuthoringScreen.AuthoringVisualization;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class Module {

    private final VBox module;
    private Pane content;
    private int moduleWidth;
    private int moduleHeight;
    private AuthoringVisualization context;


    public Module(int width, int height, String moduleName, AuthoringVisualization context) {

        this.module = new VBox();
        this.content = new Pane();
        this.moduleWidth = width;
        this.moduleHeight = height;
        this.context = context;
        module.setMinSize(moduleWidth, moduleHeight);
        module.setMaxSize(moduleWidth, moduleHeight);
        module.setId("module");
        content.prefHeightProperty().bind(module.heightProperty());
        content.prefWidthProperty().bind(module.widthProperty());
        module.getChildren().addAll(content);
    }

    public VBox getContent() {
        return module;
    }

    protected Pane getPane() {
        return content;
    }

    protected int getModuleWidth() {
        return moduleWidth;
    }

    protected int getModuleHeight() { return moduleHeight; }

    public void close(Class clazz) {context.close(clazz);}

}
