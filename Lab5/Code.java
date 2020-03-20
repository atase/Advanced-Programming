import java.io.Serializable;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Code implements Document{

    private String id;
    private String name;
    private Path path;
    private final String type = "Code file";
    private Map<String, String> tags;
    private StringBuilder str;


    public Code(){
        this.id = null;
        this.name = null;
        this.path = null;
        this.str = new StringBuilder();
        this.tags = new HashMap<String, String>();
    }

    public Code(String id, String name, Path path, Map<String, String> tags){
        this.id = id;
        this.name = name;
        this.path = path;

        this.tags = new HashMap<String, String>();
        this.tags.putAll(tags);

        this.str = new StringBuilder();
    }


    public void setId(String Id){
        this.id = id;
    }

    public void setPath(Path path){
        this.path = path;
    }

    public void setName(String name){
        this.name = name;
    }


    public void setTags(Map<String, String> tags){
        this.tags.forEach(tags::putIfAbsent);
    }


    public String getId(){
        return this.id;
    }

    public String getPath(){
        return this.path.toString();
    }

    public String getName(){
        return this.name;
    }

    public Map<String, String> getTags(){
        return this.tags;
    }

    public String getType() { return this.type; }

    public String toString(){
        this.str.append("ID : ");
        this.str.append(this.id);
        this.str.append("\n");

        this.str.append("Name : ");
        this.str.append(this.name);
        this.str.append("\n");

        this.str.append("Path : ");
        this.str.append(this.path.toString());
        this.str.append("\n");

        this.str.append("Type : ");
        this.str.append(this.type);
        this.str.append("\n");

        String tagsAsString = this.tagsToString();
        this.str.append("Tags : ");
        this.str.append(tagsAsString);
        this.str.append("\n");


        return str.toString();

    }

    private String tagsToString(){
        String tagsAsString = this.tags.keySet().stream()
                .map(key -> key + "=" + this.tags.get(key))
                .collect(Collectors.joining(", ", "{", "}"));

        return tagsAsString;
    }

}
