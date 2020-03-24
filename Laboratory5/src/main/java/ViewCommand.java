import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class ViewCommand implements Command {
    private String command;
    private String path;
    private String option;

    public ViewCommand(){
        this.command = null;
        this.path = null;
        this.option = null;
    }

    public ViewCommand(String command, String path){
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
        if(!this.command.equals("view")){
            return false;
        }
        if(this.path.startsWith("http") || this.path.startsWith("https")){
            try{
                new URL(this.path).toURI();
            }catch(MalformedURLException | URISyntaxException exception){
                exception.printStackTrace();
            }
        }else{
            try{
                Paths.get(this.path);
            }catch(InvalidPathException | NullPointerException ex){
                return false;
            }
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

    public void view(){
        if(this.path.startsWith("http") || this.path.startsWith("https")){
            this.viewURL(this.path);
        }else{
            this.viewDocument(this.path);
        }
    }

   private void viewDocument(String path){
        try{
            Desktop desktop = null;
            if(Desktop.isDesktopSupported()){
                desktop = Desktop.getDesktop();
            }
            desktop.open(new File(path));
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    private void viewURL(String url){
        try{
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI newUrl = new URI(url);
            desktop.browse(newUrl);
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }
}
