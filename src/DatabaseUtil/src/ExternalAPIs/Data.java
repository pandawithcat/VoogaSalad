package ExternalAPIs;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public abstract class Data {

    private final int MAX_LOGIN_ATTEMPTS = 3;
    private final String HASHING_ALGORITHM = "MD5";

    private String currentUserID;
    private int numberOfLoginAttempts;

    public Data(){
        numberOfLoginAttempts = 0;
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
}
