package BackendAPI;

public interface IModel {


    // Creates Instance of the Game being played
    // Input 1: Game config object
    // Input 2: User data Object
    void createGameInstance();


    // Begins game loop
    // No Input to this function
    void runGame();
}
