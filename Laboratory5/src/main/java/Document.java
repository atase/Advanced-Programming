import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Document{


    private String id;
    private String name;
    private Path path;
    private String type;
    private Map<String, String> tags;

    public Document(){
        this.id = null;
        this.type = null;
        this.name = null;
        this.path = null;
        this.tags = new HashMap<String, String>();
    }

    public Document(String id, String type, String name, Path path, Map<String, String> tags){
        this.id = id;
        this.type = type;
        this.name = name;
        this.path = path;
        this.tags = tags;

        this.tags = new HashMap<String, String>();
        this.tags.putAll(tags);
    }


    public void setId(String Id){
        this.id = id;
    }

    public void setType(String type){
        this.type = type;
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
        StringBuilder str = new StringBuilder();

        str.append("ID : ");
        str.append(this.id);
        str.append("\n");

        str.append("Name : ");
        str.append(this.name);
        str.append("\n");

        str.append("Path : ");
        str.append(this.path.toString());
        str.append("\n");

        str.append("Type : ");
        str.append(this.type);
        str.append("\n");

        String tagsAsString = this.tagsToString();
        str.append("Tags : ");
        str.append(tagsAsString);
        str.append("\n");

        return str.toString();

    }

    private String tagsToString(){
        String tagsAsString = this.tags.keySet().stream()
                .map(key -> key + "=" + this.tags.get(key))
                .collect(Collectors.joining(", ", "{", "}"));

        return tagsAsString;
    }

}
