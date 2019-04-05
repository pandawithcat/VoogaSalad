package GUI.GameAuthoringEnvironment.AuthoringConfig;

import java.io.Serializable;

public class Game implements Serializable {

    private String myName;
    private String myMode;
    private int myNumberOfLevels;
    private int myScreenSize;


    public Game(String name, String gameMode, String levels, String screenSize)
    {
        myNumberOfLevels = Integer.parseInt(levels);
        myName = name;
        myMode = gameMode;
        myScreenSize = Integer.parseInt(screenSize);
        //System.out.println(myName + " " + myNumberOfLevels + " " + myScreenSize);
    }

    public int getMyNumberOfLevels(){
        return myNumberOfLevels;
    }

    public String getMyMode(){
        return  myMode;
    }

    public int getMyScreenSize(){
        return myScreenSize;
    }

    public String getMyName()
    {
        return myName;
    }

    public void setMyName(String name)
    {
        myName = name;
    }

    @Override
    public String toString()
    {
        return myName;
    }
}
