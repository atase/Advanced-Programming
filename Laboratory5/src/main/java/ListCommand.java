import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ListCommand implements Command {
    private String command;
    private String path;
    private String option;

    public ListCommand(){
        this.command = null;
        this.path = null;
        this.option = null;
    }

    public ListCommand(String command, String path){
        this.command = command;
        this.path = path;
        this.option = null;
    }

    public void setCommand(String command){
        this.command = command;
    }

    public void setPath(String path){
        this.path = path;
    }

    public void setOption(String option){
        this.option = option;
    }

    public String getCommand(){
        return this.command;
    }

    public String getPath(){
        return this.path;
    }

    public String getOption(){
        return this.option;
    }

    public boolean validateCommand(){
        if(!this.command.equals("list")){
            return false;
        }
        try{
            Paths.get(path);
        }catch(InvalidPathException | NullPointerException ex){
            return false;
        }

        if(this.option != null)
            return false;

        return true;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.command + " " + this.path);
        return sb.toString();
    }

    public void list() {
        Path listPath = Paths.get(this.path);
        try(Stream<Path> paths = Files.walk(listPath)){
            paths
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        }catch(IOException exception){
            exception.printStackTrace();
        }
    }
}
