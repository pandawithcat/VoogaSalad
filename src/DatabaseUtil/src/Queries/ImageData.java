package Queries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageData extends DBUtil{

    /**
     * @param file The file associated with the image to be uploaded
     * @param tag A tag for file that can be queried for later
     * @return the imageID from insertion
     */
    public int addImage(File file, String tag){
        String insertionQuery =
                " insert into images (name, image, tag)"
                        + " values (?, ?, ?);";
        try {
            FileInputStream fis = new FileInputStream(file);
            PreparedStatement statement = getConnection().prepareStatement(insertionQuery, RETURN_GENERATED_KEYS);
            statement.setString(1, file.getName());
            statement.setBinaryStream(2, fis, (int) file.length());
            statement.setString(3, tag);
            statement.executeUpdate();
            int ID = getGeneratedAutoIndex(statement);
            statement.close();
            return ID;
        }
        catch (SQLException e){
            closeConnection();
            throw new ConnectionException(e.toString());
        }
        catch (FileNotFoundException e){
            throw new RuntimeException("File not found\n"+e.toString());
        }
    }


    /**
     * @param tag The tag associated with the desired list of images, null if querying all tags
     * @return an ArrayList of imageIDs
     */
    public ArrayList<Integer> fetchImageIDs(String tag){
        String selectionQuery =
                "select imageID from images";

        if(tag != null){
            selectionQuery += " where tag = (?)";
        }
        try {
            PreparedStatement statement = getConnection().prepareStatement(selectionQuery);
            if(tag != null){
                statement.setString(1, tag);
            }
            ResultSet results = statement.executeQuery();
            ArrayList<Integer> IDList = new ArrayList<>();

            while(results.next()){
                IDList.add(results.getInt("imageID"));
            }
            results.close();
            statement.close();
            return IDList;
        }
        catch (SQLException e){
            closeConnection();
            throw new ConnectionException(e.toString());
        }
    }

    /**
     * @param imageID The imageID corresponding to the desired image
     * @return an InputStream corresponding to the image's binary
     */
    public InputStream fetchImage(int imageID){
        String selectionQuery =
                "select image " +
                        "from images where imageID = (?)";
        try {
            PreparedStatement statement = getConnection().prepareStatement(selectionQuery);
            statement.setInt(1,imageID);
            ResultSet results = statement.executeQuery();
            InputStream image = null;
            if(results.next()){
                image = results.getBinaryStream("image");
            }
            results.close();
            statement.close();
            return image;
        }
        catch (SQLException e){
            closeConnection();
            throw new ConnectionException(e.toString());
        }
    }

    /**
     * @param imageIDs The the list of imageIDs corresponding to the desired images
     * @return an ArrayList of InputStreams corresponding to the images' binaries
     */
    public ArrayList<InputStream> fetchImages(int... imageIDs){
        ArrayList<InputStream> images = new ArrayList<>();
        Arrays.stream(imageIDs).forEach(id -> images.add(fetchImage(id)));
        return images;
    }



}
