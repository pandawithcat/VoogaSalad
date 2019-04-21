package ExternalAPIs;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.regex.Pattern;

public abstract class Data {

    private final int MAX_LOGIN_ATTEMPTS = 3;
    private final String HASHING_ALGORITHM = "MD5";
    private final int MINIMUM_PASSWORD_LENGTH = 6;
    private final String NUMBER_CHECKER = "[0-9]";

    // TODO: Might store this in backend
    private String currentUserID;
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

    public void createNewUser(String username, String password, String passwordRepeated){
        checkArgumentLengths(username, password, passwordRepeated);
        // TODO: Check if username already exists in database and change if statement
        if (username.length() > 5){
            throw new IllegalArgumentException("This username is already being used please try another");
        }
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
}
