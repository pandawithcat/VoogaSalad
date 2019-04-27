package BackendExternalAPI;

import Configs.GamePackage.Game;
import ExternalAPIs.AuthoringData;
import ExternalAPIs.GameInfo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.scene.image.Image;

import javax.print.DocFlavor;
import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Model {

    private final String PROPERTIES_FILE_PATH = "games/GameInfo.properties";
    private final String XML_FILE_PATH = "games/GameXMLs/";
    private final String REGEX = "~";
    private final String XML_TAG = "XML.xml";
    private final int MAX_FILE_SIZE = 16 * (10 ^ 6);

    private Game myGame;
    private String myXMLFileName;
    private AuthoringData myAuthoringData;


    public Model(){
        myAuthoringData = new AuthoringData();
    }

    // Do Not Call Yet !!!!!!!!!!!!!!!
    public void saveToXML2(Game newGame){

        XStream mySerializer = new XStream(new DomDriver());
        String gameXMLString = mySerializer.toXML(newGame);
        myAuthoringData.storeXML(gameXMLString);
    }

    // Do Not Call Yet !!!!!!!!!!!!!!!!!
    private void saveBasicInfo(Game savingGame){
        // TODO: Uncomment when game class thumbnail is changed to integer
        //GameInfo savingInfo = new GameInfo(savingGame.getTitle(), savingGame.getThumbnail(), savingGame.getDescription());
        // TODO: have a get method for the image ids used in the specific game
        ArrayList<Integer> imageIDs = new ArrayList<>();
        //myAuthoringData.storeBasicInfo(savingInfo, imageIDs);
    }

    public void saveToXML(Game newGame) {
        myGame = newGame;
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

    // Do Not Call Yet !!!!!!!!!!!!!!!
    public Game loadGameObject(GameInfo selectedGame){
        XStream serializer = new XStream(new DomDriver());
        String gameXMLString = myAuthoringData.getGameString(selectedGame);
        return (Game)serializer.fromXML(gameXMLString);
    }

    // Do Not Call Yet !!!!!!!!!!!!!!!
    public List<Integer> getImageOptions(AuthoringData.ImageType type){
        return myAuthoringData.getImages(type);
    }

    // Do Not Call Yet !!!!!!!!!!!!!!!
    // Use file chooser and pass selected File object into this method
    public int uploadImage(File newImageFile, AuthoringData.ImageType imageType) throws java.io.IOException{
        // TODO: Check length of image file and throw exception if too large
        int fileSize = (int) newImageFile.length();
        checkFileSize(fileSize);
        byte[] fileBytes = new byte[fileSize];
        InputStream imageIS = new FileInputStream(newImageFile);
        imageIS.read(fileBytes);
        return myAuthoringData.storeImage(fileBytes, imageType);
    }

    private void checkFileSize(int size){
        if (size > MAX_FILE_SIZE){
            throw new IllegalArgumentException("Image file size exceeds 16 MB.  Please choose a smaller file");
        }
    }

    // Do Not Call Yet !!!!!!!!!!!!!!!!
    public Image getImage(int imageID){
        byte[] imageBytes = myAuthoringData.getImage(imageID);
        InputStream byteIS = new ByteArrayInputStream(imageBytes);
        return new Image(byteIS);
    }

    private void updatePropertiesFile() throws IOException{
        FileInputStream propertiesIS = new FileInputStream(PROPERTIES_FILE_PATH);
        Properties myGameDetails = new Properties();
        myGameDetails.load(propertiesIS);
        myXMLFileName = myGame.getTitle() + XML_TAG;
        String propertyValue = myGame.getThumbnail() + REGEX + myGame.getDescription() + REGEX + myXMLFileName;
        System.out.println(propertyValue);
        myGameDetails.setProperty(myGame.getTitle(),propertyValue);
        FileOutputStream propertiesOS = new FileOutputStream(PROPERTIES_FILE_PATH);
        myGameDetails.store(propertiesOS, null);
    }

    private void writeToXMLFile() throws IOException {
        XStream mySerializer = new XStream(new DomDriver());
        String gameString = mySerializer.toXML(myGame);
        FileWriter xmlFW = new FileWriter(XML_FILE_PATH + myXMLFileName);
        xmlFW.write(gameString);
        xmlFW.close();

    }


}
