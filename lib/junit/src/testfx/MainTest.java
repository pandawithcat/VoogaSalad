package testfx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.Slider;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import static org.junit.jupiter.api.Assertions.*;


class MainTest extends ApplicationTest {
    // allow easy access within tests to elements of GUI
    private Labeled myLabel;
    private Button myButton;
    private TextInputControl myTextField;
    private Slider mySlider;
    private ColorPicker myPicker;
    private ComboBox<String> myCombo;


    @BeforeAll
    public static void setUpClass () {
        // explicitly use the most stable robot implementation to avoid some older versions
        //   https://stackoverflow.com/questions/52605298/simple-testfx-example-fails
        System.getProperties().put("testfx.robot", "glass");
    }

    @BeforeEach
    public void setUp () throws Exception {
        // start GUI new for each test
        launch(Main.class);
        // various ways to find elements in GUI
        // by ID
        myLabel = lookup("#label").queryLabeled();
        myPicker = lookup("#picker").queryAs(ColorPicker.class);
        // by path of IDs
        myTextField = lookup("#pane #inputField").queryTextInputControl();
        // by being the only one of its kind
        mySlider = lookup(".slider").queryAs(Slider.class);
        // by being the only one of its kind within another element
        myCombo = lookup(".grid-pane .combo-box").queryComboBox();
        // by complete text in button
        myButton = lookup("Apply").queryButton();

        // clear text field, just in case
        myTextField.clear();
    }

    @AfterEach
    public void tearDown () throws Exception {
        // remove stage of running app
        FxToolkit.cleanupStages();
        // clear any key or mouse presses left unreleased
        release(new KeyCode[] {});
        release(new MouseButton[] {});
    }


    @Test
    public void testTextFieldAction () {
        var expected = "ENTER test!";
        clickOn(myTextField).write(expected).write(KeyCode.ENTER.getChar());
        assertEquals(expected, myLabel.getText());
    }

    @Test
    public void testButtonAction () {
        var expected = "CLICK test!";
        clickOn(myTextField).write(expected);
        clickOn(myButton);
        assertEquals(expected, myLabel.getText());
    }

    @Test
    public void testComboBoxAction () {
        var expected = "b";
        select(myCombo, expected);
        assertEquals(expected, myLabel.getText());
    }

    @Test
    public void testSliderAction () {
        var expected = "50.0";
        setValue(mySlider, 50);
        assertEquals(expected, myLabel.getText());
    }

    @Test
    public void testColorPickerAction () {
        var expected = Color.RED;
        setValue(myPicker, expected);
        assertEquals(expected.toString(), myLabel.getText());
    }


    // utility methods for different elements
    private void clickOn (Button b) {
        simulateAction(b, () -> b.fire());
    }

    private void select (ComboBox<String> cb, String value) {
        simulateAction(cb, () -> cb.getSelectionModel().select(value));
    }

    private void setValue (Slider s, double value) {
        simulateAction(s, () -> s.setValue(value));
    }

    private void setValue (ColorPicker cp, Color value) {
        simulateAction(cp, () -> { cp.setValue(value); cp.fireEvent(new ActionEvent()); });
    }

    // simulate an UI action
    private void simulateAction (Node n, Runnable action) {
        // simulate robot motion
        moveTo(n);
        // fire event using given action on the given node
        Platform.runLater(action);
        // make it "later" so the requested event has time to run
        WaitForAsyncUtils.waitForFxEvents();
    }
}
