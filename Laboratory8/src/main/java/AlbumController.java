import com.github.javafaker.Faker;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlbumController {
    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    public AlbumController(){}

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public Album create(String name, String artistId, Integer releaseYear) throws SQLException {
        String id = "ID";
        Random random = new Random();
        for(int i=0; i<3; i++){
            id += random.nextInt(10);
        }
        Album album;
        album = new Album(artistId, id, name, releaseYear);
        this.statement = this.connection.createStatement();
        this.statement.executeUpdate("insert into albums values('" + id + "','" + name + "'," + releaseYear + ",'" + artistId + "')");
        return album;
    }

    public List<Album> findByArtist(String artistId) throws SQLException {
        List<Album> albumList = new ArrayList<Album>();
        Album album;
        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery("select * from albums where artist_id='" + artistId + "'");
        while(resultSet.next() ){
            String name = resultSet.getString("name");
            String id = resultSet.getString("id");
            Integer releaseYear = resultSet.getInt("release_year");
            album = new Album(artistId, id, name, (Integer)releaseYear);
            albumList.add(album);
        }
        return albumList;
    }
}
