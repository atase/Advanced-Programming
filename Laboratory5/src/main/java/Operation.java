import java.awt.*;
import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Operation{

    private List<String> components;
    private Catalog catalog;

    public Operation(){
        this.components = new ArrayList<String>();
        this.catalog = new Catalog();
    }

    public void saveBinary(Catalog obj, String filename) throws IOException {

        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream output = new ObjectOutputStream(file);

            output.writeObject(obj.getId());
            output.writeObject(obj.getName());
            output.writeObject(obj.getNumberOfDocuments());

            List<Document> documents = new ArrayList<Document>();
            documents = obj.getDocuments();

            for(Document document : documents) {
                output.writeObject(document.getId());
                output.writeObject(document.getName());
                output.writeObject(document.getPath());
                output.writeObject(document.getTags());
                output.writeObject(document.getType());
            }

            output.flush();
            file.close();
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }

    public Catalog loadBinary(String filename) throws IOException{
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

    public void viewDocument(String path){
        try{
            Desktop  desktop = null;
            if(Desktop.isDesktopSupported()){
                desktop = Desktop.getDesktop();
            }
            desktop.open(new File(path));
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public void viewURL(String url){
        try{
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI newUrl = new URI(url);
            desktop.browse(newUrl);
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public void savePlainText(Catalog obj, String filename){
        try(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"))){
            writer.write(obj.toString());
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public Catalog loadPlainText(String filename){
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
