
import BackendExternal.Logic;

public class View {
    public View() {

        Logic myLogic = new Logic();
    }


    // Depicts the static features of the game
    // Input: List of Constant Object Interface Instances (Immutable)
    void renderConstantFeatures(){

    }


    // Depicts the variable features of the game
    // Input: List of Variable Object Interface Instances (Immutable)
    void renderVariableFeatures(){

    }

    // Gets the user changes to the game
    // No Input to this Function
    // Return Value: List of Placement Config Objects, Empty if no changes have been made since last call
    // TODO: View Group make a class that has a Config Object and an x and y coordinate
    void getNewChanges(){

    }
}
