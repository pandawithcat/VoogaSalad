package Configs.GamePackage;

public enum GameStatus {
    OVER(0),LOST(1),WON(2),PLAYING(3);

    private final int myState;

    GameStatus(int state) {
        myState=state;
    }

    public int getMyState() {
        return myState;
    }
}
