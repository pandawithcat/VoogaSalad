package BackendExternalAPI;

import Configs.GamePackage.Game;
import ExternalAPIs.AuthoringData;
import ExternalAPIs.GameInfo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Model {

    private final String PROPERTIES_FILE_PATH = "games/GameInfo.properties";
    private final String XML_FILE_PATH = "games/GameXMLs/";
    private final String REGEX = "~";
    private final String XML_TAG = "XML.xml";

    private Game myNewGame;
    private String myXMLFileName;
    private AuthoringData myAuthoringData;


    public Model(){
        myAuthoringData = new AuthoringData();
    }

    public void saveToXML(Game newGame) {
        myNewGame = newGame;
        try {
            updatePropertiesFile();
            writeToXMLFile();

        } catch (Exception e) {
            // TODO: For Testing Purposes
            e.printStackTrace();
        }
    }

    // Do Not Call Yet !!!!!!!!!!!!!!!
    public boolean authenticateUser(String username, String password){
        return myAuthoringData.authenticateUser(username, password);
    }

    // Do Not Call Yet !!!!!!!!!!!!!!!
    public void createNewUser(String username, String password, String passwordRepeated){
        myAuthoringData.createNewUser(username, password, passwordRepeated);
    }

    // Do Not Call Yet !!!!!!!!!!!!!!!
    public List<GameInfo> getAuthoredGameLibrary(){
        return myAuthoringData.getAuthoredGames();
    }

    private void updatePropertiesFile() throws IOException{
        FileInputStream propertiesIS = new FileInputStream(PROPERTIES_FILE_PATH);
        Properties myGameDetails = new Properties();
        myGameDetails.load(propertiesIS);
        myXMLFileName = myNewGame.getTitle() + XML_TAG;
        String propertyValue = myNewGame.getThumbnail() + REGEX + myNewGame.getDescription() + REGEX + myXMLFileName;
        System.out.println(propertyValue);
        myGameDetails.setProperty(myNewGame.getTitle(),propertyValue);
        FileOutputStream propertiesOS = new FileOutputStream(PROPERTIES_FILE_PATH);
        myGameDetails.store(propertiesOS, null);
    }

    private void writeToXMLFile() throws IOException {
        XStream mySerializer = new XStream(new DomDriver());
        String gameString = mySerializer.toXML(myNewGame);
        FileWriter xmlFW = new FileWriter(XML_FILE_PATH + myXMLFileName);
        xmlFW.write(gameString);
        xmlFW.close();


        //for testingvv
        String gameXMLFileName = "wtfXML.xml";
        System.out.println("Loading Game XML File: " + gameXMLFileName);
        File xmlFile = new File("games/GameXMLs/" + gameXMLFileName);
        System.out.println("Un-Serializing Game XML File");
        Game g = (Game)(mySerializer.fromXML(xmlFile));
        System.out.println("LEVEL LIST:" + Arrays.asList(g.getLevelList()));
        System.out.println("LEVEL LABEL" + g.getLevelList()[0].getLabel());
        System.out.println("LEVEL MAP" + g.getLevelList()[0].getMyMapConfig());



        //for testing^^


    }

//    public static void main (String[] args) {
//        Model m = new Model();
//        Game g = new Game();
//        g.setName("name");
//        g.setMyDescription("description");
//        g.setThumbnail("thumbnail.gif");
//        m.saveToXML(g);
//
//
//
//    }


}
