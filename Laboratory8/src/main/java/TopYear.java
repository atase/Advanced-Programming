import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class TopYear implements Chart {
    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;
    private List<TopElement> topElementList;

    public TopYear(){ }

    public void create(String artistId, String albumId, Integer score) throws SQLException {
        this.statement = this.connection.createStatement();
        this.statement.executeUpdate("insert into top2020 values('" + artistId + "','" + albumId + "','" + score + "')");
    }


    public Artist findArtistById(String id) throws SQLException {
        Artist artist = new Artist();
        Statement artistStatement = this.connection.createStatement();
        ResultSet artists = artistStatement.executeQuery("select * from artists where id='" + id + "'");
        if(artists.next() != false){
            artist.setId(id);
            artist.setCountry(artists.getString("country"));
            artist.setName(artists.getString("name"));
        }
        return artist;
    }

    public Album findAlbumById(String id) throws SQLException {
        Album album = new Album();
        Statement albumStatement = this.connection.createStatement();
        ResultSet albums = albumStatement.executeQuery("select * from albums where id='" + id + "'");
        if(albums.next() != false){
            album.setId(id);
            album.setName(albums.getString("name"));
            album.setReleaseYear(albums.getInt("release_year"));
            album.setArtistId(albums.getString("artist_id"));
        }
        return album;
    }

    public List<TopElement> queryTopTable() throws SQLException {
        this.topElementList = new ArrayList<TopElement>();
        Artist artist = new Artist();
        Album album = new Album();

        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery("select * from top2020 order by score desc");

        while(resultSet.next()){
            String artist_id = resultSet.getString("artist_id");
            String album_id = resultSet.getString("album_id");
            Integer score = resultSet.getInt("score");
            artist = findArtistById(artist_id);
            album = findAlbumById(album_id);

            if(artist != null && album != null){
                TopElement element = new TopElement(artist, album, score);
                this.topElementList.add(element);
            }
        }
        return this.topElementList;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\n*****[ TOP 2020 ]*****\n\n");
        int index = 1;
        for(TopElement element : topElementList){
            stringBuilder.append("\n\n===> Place " + index + " <===\n\n");
            stringBuilder.append("\n*** ARTIST ***\n");
            stringBuilder.append(element.getArtist().toString());
            stringBuilder.append("\n*** ALBUM ***\n");
            stringBuilder.append(element.getAlbum().toString());
            stringBuilder.append("\n*** SCORE ***\n");
            stringBuilder.append(element.getScore().toString());
            index++;
        }
        return stringBuilder.toString();
    }
    public void setConnection(Connection connection){
        this.connection = connection;
    }
}
