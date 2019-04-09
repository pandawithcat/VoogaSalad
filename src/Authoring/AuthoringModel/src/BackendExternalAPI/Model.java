package BackendExternalAPI;

import Configs.GamePackage.Game;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Model {

    private final String PROPERTIES_FILE_PATH = "resources/GameInfo.properties";

    Properties myGameDetails = new Properties();

    public Model(){

    }

    public void saveToXML(Game newGame) {
        try {
            XStream mySerializer = new XStream(new DomDriver());
            String gameString = mySerializer.toXML(newGame);
            FileInputStream propertiesIS = new FileInputStream(PROPERTIES_FILE_PATH);
            Properties myGameDetails = new Properties();
            myGameDetails.load(propertiesIS);
           // String gameName = newGame

        } catch (Exception e) {
            // TODO: For Testing Purposes
            e.printStackTrace();
        }
    }
}
