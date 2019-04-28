package Queries;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class Tester{

    private UserData userData = new UserData();
    private GameData gameData = new GameData();
    private ImageData imageData = new ImageData();
    private int validUserID = 7;
    private int validImageID = 7;
    private int validGameID = 3;
    private String gameString = "test";
    private String description = "Description";
    private String title = "title";
    private File file = new File(this.getClass().getClassLoader().getResource("water.jpg").getFile());


    public void test(){
        //DBUtil util = new DBUtil();
       // System.out.println(new UserData().addUser("stacy2", "barker", "minimouse"));
        //UserData users = new UserData();
        //System.out.println(users.login("stacy","barker"));

//        File file = new File(this.getClass().getClassLoader().getResource("water.jpg").getFile());
//        ImageData data = new ImageData();
//        int id = data.addImage(file, ""+ (int)(Math.random()*10));
//        System.out.println(data.fetchImage(id));

        //System.out.println(data.fetchImageIDs("2"));
        //util.addSession("stacy",1,2,3);
        //util.addTest(file, "tree");
        //util.addImage(file);
    }

    @Test
    @Disabled
    public void AddGame(){
        gameData.addGame(validUserID, description, gameString.getBytes(), validImageID, title);
    }

    @Test
    public void fetchGame(){
        String input = new String(gameData.fetchGame(3).getBinary());
        String output = new String(gameString.getBytes());
        assertEquals(input, output);
    }

    @Test
    public void addImage(){

    }

    @Test
    @Disabled
    public void AddUser() {
        userData.addUser("stacy2", "barker", "minimouse");
    }

    @Test
    public void add_TwoPlusTwo_ReturnsFour() {
        assertEquals(4,4);
    }
}
