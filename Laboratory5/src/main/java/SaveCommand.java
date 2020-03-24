import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveCommand implements Command {
    private String command;
    private String path;
    private String option;
    private Catalog catalog;
    private String catalogId;
    private List<Catalog> catalogs;

    public SaveCommand(){
        this.command = null;
        this.path = null;
        this.option = null;
        this.catalog = new Catalog();
        this.catalogId = null;
        this.catalogs = new ArrayList<Catalog>();
    }

    public SaveCommand(String command, String path, String option, String catalogId, List<Catalog> catalogs){
        this.command = command;
        this.path = path;
        this.option = option;
        this.catalog = new Catalog();
        this.catalogId = catalogId;
        this.catalogs = new ArrayList<Catalog>();
        this.catalogs.addAll(catalogs);
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
        if(!this.command.equals("save")){
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

    public Catalog searchCatalog(){
        for(Catalog catalog : this.catalogs){
            if(catalog.getId().equals(this.catalogId)){
                return catalog;
            }
        }
        return null;
    }

    public void save() throws IOException {
        if(this.option.equals("binary")){
            this.catalog = searchCatalog();
            this.saveBinary(this.catalog, this.path);
        }else if(this.option.equals("plain")){
            this.catalog = searchCatalog();
            this.savePlainText(this.catalog, this.path.toString());
        }
    }

    private void savePlainText(Catalog obj, String filename){
        try(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"))){
            writer.write(obj.toString());
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    private void saveBinary(Catalog obj, String filename) throws IOException {

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
}
