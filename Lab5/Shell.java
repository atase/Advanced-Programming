import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class Shell {

    private Catalog catalog;
    private String command;
    private Path path;
    private String filetype;

    public Shell(){
        this.catalog = new Catalog();
        this.command = null;
        this.path = null;
        this.filetype = null;
    }

    private void load(Path path) throws IOException {
        Operation operation = new Operation();
        if(this.filetype.equals("binary")){
            this.catalog = operation.loadBinary(path.toString());
        }else if(this.filetype.equals("plain")){
            this.catalog = operation.loadPlainText(path.toString());
        }

        System.out.println(this.catalog.toString());
    }

    private void list(Path path) throws IOException {
        System.out.println("oke");
        try(Stream<Path> paths = Files.walk(path)){
            paths
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        }
    }

    private void view(String path){
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

    public String getInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Syntax : command path [ plain/binary - if load]");
        String input = scanner.nextLine();

        if(isCommandValid(input)) {
            try {
                execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this.command + " " + this.path.toString();
        }else{
            return "Invalid command";
        }
    }

    public boolean isCommandValid(String input){
        String[] parts = input.split(" ");

        if(parts.length != 2 && parts.length != 3) {
            return false;
        }

        if(!parts[0].equals("list") && !parts[0].equals("load") && !parts[0].equals("view")){
            return false;
        }

        if(!this.isPathValid(parts[1])) {
            return false;
        }



        if(parts[0].equals("load") && parts.length != 3 && (!parts[2].equals("plain") || !parts[2].equals("binary"))) {
            return false;
        }




        if((parts[0].equals("list") || parts[0].equals("view")) && parts.length != 2){
            return false;
        }

        System.out.println("oke");

        this.command = parts[0];
        this.path = Paths.get(parts[1]);
        if(parts.length == 3){
            this.filetype = parts[2];
        }

        return true;
    }

    private static boolean isPathValid(String path){
        try{
            Paths.get(path);
        }catch(InvalidPathException | NullPointerException ex){
            return false;
        }
        return true;
    }

    private void execute() throws IOException {
        if(this.command.equals("list")){
            this.list(this.path);
            System.out.println("oke");
        }else if(this.command.equals("view")){
            view(this.path.toString());
        }else if(this.command.equals("load")){
            this.load(this.path);
        }

    }
}
