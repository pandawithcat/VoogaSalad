package Data;

import BackendExternal.GameInfo;
import Configs.GamePackage.Game;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;
import java.util.*;

/**
 * Project 4: VoogaSalad
 * Duke CompSci 308 Spring 2019 - Duvall
 * Date Created: 4/4/2019
 * Date Last Modified: 4/4/2019
 * @author Brian Jordan
 */

public class GameLibrary {

    private final String GAME_INFO_FILE = "GameInfo";
    private final String REGEX = ",";
    private final String FILE_PATH = "resources/GameXMLs/";

    private List<GameInfo> myGames;
    private Map<String,String> myXMLFileNames;


    public GameLibrary(){
        myGames = new ArrayList<>();
        myXMLFileNames = new HashMap<>();
        populateLibrary();
    }



    private void populateLibrary(){
        ResourceBundle gameStrings = ResourceBundle.getBundle(GAME_INFO_FILE);
        for (String s : gameStrings.keySet()){
            String[] gameDetails = gameStrings.getString(s).split(REGEX);
            GameInfo newGameInfo = new GameInfo(s, gameDetails[0]);
            myXMLFileNames.put(s,gameDetails[1]);
            myGames.add(newGameInfo);
        }
    }

    public List<GameInfo> getImmutableGameList(){
        return Collections.unmodifiableList(myGames);
    }

    public Game getGame(GameInfo chosenGameInfo){
        XStream serializer = new XStream(new DomDriver());
        String gameXMLFileName = myXMLFileNames.get(chosenGameInfo.getGameTitle());
        File xmlFile = new File(FILE_PATH + gameXMLFileName);
        return (Game)serializer.fromXML(xmlFile);
    }

}
