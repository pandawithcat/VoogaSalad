package Data;

import java.util.*;

/**
 * Project 4: VoogaSalad
 * Duke CompSci 308 Spring 2019 - Duvall
 * Date Created: 4/4/2019
 * Date Last Modified: 4/4/2019
 * @author Brian Jordan
 * @author Feroze Mohideen
 */

public class GameLibrary {

    private final String GAME_INFO_FILE = "GameInfo";
    private final String REGEX = ",";

    private ResourceBundle myGameStrings;
    private List<GameInfo> myGames;
    private Map<String,String> myXMLFiles;
    
    public GameLibrary(){
        myGameStrings = ResourceBundle.getBundle(GAME_INFO_FILE);
        myGames = new ArrayList<>();
        myXMLFiles = new HashMap<>();
        populateLibrary();
    }

    private void populateLibrary(){
        for (String s : myGameStrings.keySet()){
            String[] gameDetails = myGameStrings.getString(s).split(REGEX);
            GameInfo newGameInfo = new GameInfo(s, gameDetails[0]);
            myXMLFiles.put(s,gameDetails[1]);
            myGames.add(newGameInfo);
        }
    }

    public List<GameInfo> getImmutableGameList(){
        return Collections.unmodifiableList(myGames);
    }

    public Game getGame(GameInfo chosenGameInfo){
        String gameXMLFile = myXMLFiles.get(chosenGameInfo.getGameTitle());

        return myXMLFiles;
    }
}
