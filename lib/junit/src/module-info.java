module example_testfx {
    requires java.desktop;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.web;

    // for testing
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.params;
    requires org.testfx;
    requires org.testfx.junit5;

    // for javafx and testing
    exports debug;
    exports tdd;
    exports testfx;
}
