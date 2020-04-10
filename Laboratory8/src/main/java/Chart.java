import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Chart {
    public Artist findArtistById(String id) throws SQLException;
    public Album findAlbumById(String id) throws SQLException;
    public List<TopElement> queryTopTable() throws SQLException;
    public String toString();
    public void setConnection(Connection connection);
}
