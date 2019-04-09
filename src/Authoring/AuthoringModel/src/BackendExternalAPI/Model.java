package BackendExternalAPI;

import Configs.GamePackage.Game;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.xml.sax.XMLFilter;

import java.io.*;
import java.util.Properties;

public class Model {

    private final String PROPERTIES_FILE_PATH = "resources/GameInfo.properties";
    private final String XML_FILE_PATH = "resources/GameXMLs/";
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
        FileInputStream propertiesIS = new FileInputStream(PROPERTIES_FILE_PATH);
        Properties myGameDetails = new Properties();
        myGameDetails.load(propertiesIS);
        myXMLFileName = myNewGame.getTitle() + XML_TAG;
        String propertyValue = myNewGame.getThumbnail() + REGEX + myNewGame.getDescription() + REGEX + myXMLFileName;
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
    }
}
