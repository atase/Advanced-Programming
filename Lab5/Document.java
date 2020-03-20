import java.io.Serializable;
import java.nio.file.*;
import java.util.*;

public interface Document  {

    public void setId(String id);
    public void setPath(Path path);
    public void setName(String name);
    public void setTags(Map<String, String> tags);

    public String getId();
    public String getPath();
    public String getName();
    public Map getTags();
    public String getType();



}
