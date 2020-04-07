import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TopYear implements Chart {
    private Artist artist;
    private Album album;
    private Map<Artist, Album> artistAlbum = new HashMap<Artist, Album>();
    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    public TopYear(){ }

    public void findArtistById(String id) throws SQLException {
        this.artist = null;
        this.artist = new Artist();
        Statement artistStatement = this.connection.createStatement();
        ResultSet artists = artistStatement.executeQuery("select * from artists where id='" + id + "'");
        if(artists.next() != false){
            this.artist.setId(id);
            this.artist.setCountry(artists.getString("country"));
            this.artist.setName(artists.getString("name"));
        }
    }

    public void findAlbumById(String id) throws SQLException {
        this.album = null;
        this.album = new Album();
        Statement albumStatement = this.connection.createStatement();
        ResultSet albums = albumStatement.executeQuery("select * from albums where id='" + id + "'");
        if(albums.next() != false){
            this.album.setId(id);
            this.album.setName(albums.getString("name"));
            this.album.setReleaseYear(albums.getInt("release_year"));
            this.album.setArtistId(albums.getString("artist_id"));
        }
    }

    public void queryTopTable() throws SQLException {

        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery("select * from top2020");

        while(resultSet.next()){
            String artist_id = resultSet.getString("artist_id");
            String album_id = resultSet.getString("album_id");

            findArtistById(artist_id);
            findAlbumById(album_id);

            if(this.artist != null && this.album != null){
                this.artistAlbum.put(this.artist, this.album);
            }
        }

    }

    public Map<Artist, Album> getData(){
        return this.artistAlbum;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\n*****[ TOP 2020 ]*****\n\n");
        int index = 1;
        Set artistAlbumSet = this.artistAlbum.entrySet();
        Iterator iterator = artistAlbumSet.iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            stringBuilder.append("\n\n[=====> PLACE " + index + " <===== ]");
            stringBuilder.append("\n\n[=====> ARTIST <=====]\n" + entry.getKey().toString());
            stringBuilder.append("\n\n[=====> ALBUM <=====]\n" + entry.getValue().toString());
            index++;
        }
        return stringBuilder.toString();
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

}
