/**
 * @author Nastasa Petru-Alexandru, II, B3
 */


import com.github.javafaker.Faker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Problem {
    /***
     * username : musicuser
     * password : musicpassword
     */
    static List<Album> albums = new ArrayList<Album>();
    static List<Artist> artists = new ArrayList<Artist>();
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Faker faker = new Faker();
        Database database = Database.getInstance();
        TopYear top = new TopYear();

        AlbumController albumController = new AlbumController();
        albumController.setConnection(database.getConnection());
        /*albumController.create(faker.name().fullName(), "ID001", 1978);*/
        albums = albumController.findByArtist("ID001");

        ArtistController artistController = new ArtistController();
        artistController.setConnection(database.getConnection());
        /*artistController.create(faker.artist().name(), faker.address().country());*/
        artists = artistController.findByName("NimeniAltu");
        artists = artistController.findByName("NimeniAltu");


        System.out.println("\n*****[ ALBUM ]*****\n");

        for(Album album : albums){
            System.out.println(album.toString());
        }

        System.out.println("\n*****[ ARTIST ]*****\n");

        for(Artist artist : artists){
            System.out.println(artist.toString());
        }


        top.setConnection(database.getConnection());
        /*top.create("ID001", "ID071", 123);
        top.create("ID001", "ID928", 356);*/
        top.queryTopTable();
        System.out.println(top.toString());

        database.closeConnection();

    }
}
