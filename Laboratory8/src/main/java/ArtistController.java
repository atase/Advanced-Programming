import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArtistController {

    private Artist artist;
    private List<Artist> artistList = new ArrayList<Artist>();
    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    public ArtistController(){}

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public void create(String name, String country) throws SQLException {
        String id = "ID";
        Random random = new Random();
        for(int i=0; i<3; i++){
            id += random.nextInt(10);
        }
        this.artist = new Artist( id, name, country);
        this.statement = this.connection.createStatement();
        this.statement.executeUpdate("insert into artists values('" + id + "','" + name + "','" + country + "')");
        /*this.artist = new Artist(id, name, country);*/
    }

    public List<Artist> findByName(String name) throws SQLException {
        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery("select * from artists where name='" + name + "'");
        while(resultSet.next() ){
            String artistName = resultSet.getString("name");
            String artistId = resultSet.getString("id");
            String artistCountry = resultSet.getString("country");
            this.artist = new Artist(artistId, artistName, artistCountry);
            this.artistList.add(this.artist);
        }
        return this.artistList;
    }
}
