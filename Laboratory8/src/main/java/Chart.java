import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface Chart {
    public void findArtistById(String id) throws SQLException;
    public void findAlbumById(String id) throws SQLException;
    public void queryTopTable() throws SQLException;
    public Map<Artist, Album> getData();
    public String toString();
    public void setConnection(Connection connection);
}
