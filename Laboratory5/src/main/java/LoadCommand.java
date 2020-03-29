import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class LoadCommand implements Command {

    private String command;
    private String path;
    private String option;
    private Catalog catalog;

    public LoadCommand(){
        this.command = null;
        this.path = null;
        this.option = null;
        this.catalog = new Catalog();
    }

    public LoadCommand(String command, String path, String option){
        this.command = command;
        this.path = path;
        this.option = option;
        this.catalog = new Catalog();
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
        if(!this.command.equals("load")){
            return false;
        }

        try{
            Paths.get(path);
        }catch(InvalidPathException | NullPointerException ex){
            return false;
        }

        if(!this.option.equals("plain") && !this.option.equals("binary"))
            return false;

        return true;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.command + " " + this.path + " " + this.option);
        return sb.toString();
    }


    public Catalog load() throws IOException {
        if(this.option.equals("binary")){
            System.out.println("\n[ LOADING BINARY FILE ]\n");
            this.catalog = this.loadBinary(this.path);
        }else if(this.option.equals("plain")){
            System.out.println("\n[ LOADING PLAIN TEXT FILE ]\n");
            this.catalog = this.loadPlainText(this.path);
        }
       return this.catalog;
    }

    private Catalog loadBinary(String filename){
        try{
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream input = new ObjectInputStream(file);

            Catalog catalog = new Catalog();

            String id = (String)input.readObject();
            String name = (String)input.readObject();
            int numberOfDocuments = (int)input.readObject();

            catalog.setId(id);
            catalog.setName(name);

            for(int i=0;i<numberOfDocuments;i++) {
                id = (String)input.readObject();
                name = (String)input.readObject();
                String pathString = (String)input.readObject();
                Path path = Paths.get(pathString);
                Map<String, String> tags = (Map<String, String>)input.readObject();
                String type = (String)input.readObject();

                Document document = new Document(id, type, name, path, tags);
                catalog.addDocument(document);

            }

            file.close();
            return catalog;
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return null;
    }

    private Catalog loadPlainText(String filename){
        int data;
        int numberOfDocuments;

        StringBuilder  representation = new StringBuilder();
        String[] pieces;

        String id = null;
        String name = null;
        String type = null;
        String path = null;
        String tags = null;

        try(Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))){

            data = reader.read();
            while(data != -1){
                representation.append((char)data);
                data = reader.read();
            }

        }catch(Exception exception){
            exception.printStackTrace();
        }

        pieces = representation.toString().split("\n");
        for(int index = 0; index < 2; index ++) {
            String[] partOfPiece = pieces[index].split(":");
            if(partOfPiece[0].equals("ID ")) {
                id = partOfPiece[1];
            }else if(partOfPiece[0].equals("Name ")) {
                name = partOfPiece[1];
            }
        }

        String[] parseLine = pieces[2].split(" ");
        numberOfDocuments = Integer.parseInt(parseLine[2]);

        this.catalog.setId(id);
        this.catalog.setName(name);

        id = name = null;

        for(int index = 3;index < pieces.length; index++){
            String[] partOfPiece = pieces[index].split(":");
            if(partOfPiece[0].equals("ID ")) {
                id = partOfPiece[1].replace(" ", "");
            }
            else if(partOfPiece[0].equals("Name ")) {
                name = partOfPiece[1];
                name = name.substring(1);
            }
            else if(partOfPiece[0].equals("Path ")) {
                path = partOfPiece[1].replace(" ", "") + partOfPiece[2].replace(" ", "");
            }
            else if(partOfPiece[0].equals("Type ")) {
                type = partOfPiece[1];
                type = type.substring(1);
            }
            else if(partOfPiece[0].equals("Tags ")) {

                tags = partOfPiece[1];
                tags = tags.substring(1);
            }
            if(id != null && name != null && path != null && type != null && tags != null){
                Map<String, String> newTags = new HashMap<String, String>();

                tags = tags.substring(1,tags.length()-1);
                tags = tags.replace(", ", "=");
                String[] partOfTags = tags.split("=");
                for(int i=0;i<partOfTags.length-1; i+=2) {
                    String key = partOfTags[i];
                    String value = partOfTags[i+1];
                    newTags.put(key,value);


                }

                Document document = new Document(id, type, name, Paths.get(path),newTags);
                this.catalog.addDocument(document);

                id = name = path = type = tags = null;
            }
        }
        return this.catalog;
    }

}
