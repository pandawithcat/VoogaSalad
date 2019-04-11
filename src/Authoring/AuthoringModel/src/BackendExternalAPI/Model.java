package BackendExternalAPI;

import Configs.GamePackage.Game;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.xml.sax.XMLFilter;

import java.io.*;
import java.util.Properties;

public class Model {

    private final String PROPERTIES_FILE_PATH = "games/GameInfo.properties";
    private final String XML_FILE_PATH = "games/GameXMLs/";
    private final String REGEX = "~";
    private final String XML_TAG = "XML.xml";

    private Game myNewGame;
    private String myXMLFileName;


    public Model(){

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

    private void updatePropertiesFile() throws IOException{
        System.out.println("Reading Properties File");
        FileInputStream propertiesIS = new FileInputStream(PROPERTIES_FILE_PATH);
        Properties myGameDetails = new Properties();
        myGameDetails.load(propertiesIS);
        myXMLFileName = myNewGame.getTitle() + XML_TAG;
        String propertyValue = myNewGame.getThumbnail() + REGEX + myNewGame.getDescription() + REGEX + myXMLFileName;
        System.out.println("Adding: " + propertyValue);
        System.out.println(propertyValue);
        myGameDetails.setProperty(myNewGame.getTitle(),propertyValue);
        FileOutputStream propertiesOS = new FileOutputStream(PROPERTIES_FILE_PATH);
        myGameDetails.store(propertiesOS, null);
        System.out.println("Saved Properties File");
    }

    private void writeToXMLFile() throws IOException {
        XStream mySerializer = new XStream(new DomDriver());
        System.out.println("Serializing Game");
        String gameString = mySerializer.toXML(myNewGame);
        FileWriter xmlFW = new FileWriter(XML_FILE_PATH + myXMLFileName);
        xmlFW.write(gameString);
        System.out.println("Saved Game XML File");
        xmlFW.close();
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
