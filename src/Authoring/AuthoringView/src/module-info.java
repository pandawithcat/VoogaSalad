module AuthoringView {
    requires Configurations;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.media;

    exports GameAuthoringEnvironment.AuthoringComponents;
    exports GameAuthoringEnvironment.AuthoringScreen;
    exports Main;
}