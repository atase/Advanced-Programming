import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArtistController {

    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    public ArtistController(){}

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public Artist create(String name, String country) throws SQLException {
        String id = "ID";
        Random random = new Random();
        for(int i=0; i<3; i++){
            id += random.nextInt(10);
        }
        Artist artist;
        artist = new Artist( id, name, country);
        this.statement = this.connection.createStatement();
        this.statement.executeUpdate("insert into artists values('" + id + "','" + name + "','" + country + "')");

        return artist;
    }

    public List<Artist> findByName(String name) throws SQLException {
        List<Artist> artistList = new ArrayList<Artist>();
        Artist artist;
        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery("select * from artists where name='" + name + "'");
        while(resultSet.next() ){
            String artistName = resultSet.getString("name");
            String artistId = resultSet.getString("id");
            String artistCountry = resultSet.getString("country");
            artist = new Artist(artistId, artistName, artistCountry);
            artistList.add(artist);
        }
        return artistList;
    }
}
