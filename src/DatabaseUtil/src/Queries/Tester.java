package Queries;

import java.io.File;

public class Tester {
    public static void main(String[] args){
       new Tester().test();
    }

    public void test(){

        //DBUtil util = new DBUtil();
        //System.out.println(new UserData().addUser("stacy", "barker", "minimouse"));
        //UserData users = new UserData();
        //System.out.println(users.login("stacy","barker"));

        File file = new File(this.getClass().getClassLoader().getResource("ReactionTime5.png").getFile());
        ImageData data = new ImageData();
        int id = data.addImage(file, ""+ (int)(Math.random()*10));
        System.out.println(data.fetchImage(id));

        //System.out.println(data.fetchImageIDs("2"));
        //util.addSession("stacy",1,2,3);
        //util.addTest(file, "tree");
        //util.addImage(file);
    }
}
