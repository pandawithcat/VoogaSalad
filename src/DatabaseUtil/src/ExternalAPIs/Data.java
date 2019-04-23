package ExternalAPIs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.regex.Pattern;

public abstract class Data {

    private final int MAX_LOGIN_ATTEMPTS = 3;
    private final String HASHING_ALGORITHM = "MD5";
    private final int MINIMUM_PASSWORD_LENGTH = 6;
    private final String NUMBER_CHECKER = "[0-9]";

    // TODO: Might store this in backend
    protected String currentUserID;
    protected String currentGameID;
    private int numberOfLoginAttempts;
    private Random saltGenerator;

    public Data(){
        numberOfLoginAttempts = 0;
        saltGenerator = new SecureRandom();
        // TODO: Set up connection with server here
    }

    /**
     * Reads in the provided information from the user to cross check with the user data in the database
     * @param username - Unique string to identify user
     * @param password - chosen string to verify user identity
     * @return boolean indicating if existing user credentials were matched
     */
    public boolean authenticateUser(String username, String password) throws IllegalArgumentException, IllegalAccessError{
        checkArgumentLengths(username, password);
        numberOfLoginAttempts++;
        if (numberOfLoginAttempts > MAX_LOGIN_ATTEMPTS){
            throw new IllegalAccessError("You have used up all of your login attempts");
        }
        // TODO: Check if username is in database and change if statement
        if (username.length() > 5){
            // TODO: Get password salt byte array
            byte[] salt = new byte[0];
            byte[] hashedPassword = hashPassword(password, salt);
            // TODO: Get database password
            byte[] savedPasswordHash = new byte[0];
            if (MessageDigest.isEqual(hashedPassword, savedPasswordHash)){
                // TODO: Get User ID
                currentUserID = "me";
                numberOfLoginAttempts = 0;
                return true;
            }
            else{
                return false;
            }
        }
        else {
            throw new IllegalArgumentException("Account with this username has not yet been created");
        }
    }

    private void checkArgumentLengths(String username, String password){
        if (username.length() == 0){
            throw new IllegalArgumentException("You must enter a username in the field provided");
        }
        if (password.length() == 0){
            throw new IllegalArgumentException("You must enter a password in the field provided");
        }
    }

    private byte[] hashPassword(String password, byte[] salt){
        byte[] passwordBytes = password.getBytes();
        byte[] plainTextBytes = new byte[passwordBytes.length + salt.length];
        System.arraycopy(passwordBytes, 0, plainTextBytes, 0, passwordBytes.length);
        System.arraycopy(salt, 0, plainTextBytes, passwordBytes.length, salt.length);
        try {
            MessageDigest passwordDigest = MessageDigest.getInstance(HASHING_ALGORITHM);
            passwordDigest.update(plainTextBytes);
            return passwordDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Takes in new user information error checks it and stores it to the database
     * @param username - Unique string to identify user
     * @param password - chosen string to verify user identity
     * @param passwordRepeated - repeated chosen string
     */
    public void createNewUser(String username, String password, String passwordRepeated){
        checkArgumentLengths(username, password, passwordRepeated);
        // TODO: Check if username already exists in database and change if statement
        if (username.length() > 5){
            throw new IllegalArgumentException("This username is already being used please try another");
        }
        // TODO: Enter Regex check
        passwordErrorChecking(password, passwordRepeated);
        byte[] salt = new byte[16];
        saltGenerator.nextBytes(salt);
        byte[] hashedPassword = hashPassword(password, salt);
        // TODO: add user info to database
        currentUserID = "me";
    }

    private void checkArgumentLengths(String username, String password, String passwordRepeated){
        checkArgumentLengths(username, password);
        if (passwordRepeated.length() == 0){
            throw new IllegalArgumentException("You must re-enter a password in the field provided");
        }
    }

    private void passwordErrorChecking(String password, String passwordRepeated){
        if (password.length() < MINIMUM_PASSWORD_LENGTH || !Pattern.compile(NUMBER_CHECKER).matcher(password).find()){
            throw new IllegalArgumentException("Password must be at least 6 characters and contain a number");
        }
        else if (! password.equals(passwordRepeated)){
            throw new IllegalArgumentException("Password and repeated password do not match");
        }
    }

    /**
     * Provides basic information about each game to be displayed as the game library
     * @return - List of GameInfo Objects containing basic information about created games
     */
    public abstract List<GameInfo> getAuthoredGames();

    /**
     * Retrieves specified game XML from the database and returns it as a Java File Object
     * @param chosenGameInfo - One of the game info objects selected from the provided list
     * @return - Java File object containing the XML file of the selected game
     */

    public File getGameFile(GameInfo chosenGameInfo){
        // TODO: Potentially create interface for GameInfo so only Database module can edit LocalKey of game
        currentGameID = chosenGameInfo.getGameTitle();
        // TODO: Retrieve String of specific Games XML Bytes from the Database
        byte[] gameBytes = new byte[0];
        File gameXML = new File("");
        try {
            FileOutputStream fileOS = new FileOutputStream(gameXML);
            fileOS.write(gameBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameXML;
    }

    // TODO: Method call that downloads the images of a game




}
