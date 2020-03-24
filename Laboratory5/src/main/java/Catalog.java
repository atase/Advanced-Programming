import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private String id;
    private String name;
    private List<Document> documents;

    public Catalog(){
        this.id = null;
        this.name = null;
        this.documents = new ArrayList<Document>();
    }

    public Catalog(String id, String name){
        this.id = id;
        this.name = name;

        this.documents = new ArrayList<Document>();
    }

    public Catalog(String id, String name, List<Document> documents){
        this.id = id;
        this.name = name;

        this.documents = new ArrayList<Document>();
        this.documents.addAll(documents);
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDocuments(List<Document> documents){
        if(this.documents != null){
            this.documents.clear();
            this.documents.addAll(documents);
        }else{
            addDocuments(documents);
        }
    }

    public void addDocument(Document document){
        if(!this.documents.contains(document)) {
            this.documents.add(document);
        }
    }

    public void addDocuments(List<Document> documents){
        for(Document document : documents)
            if(!this.documents.contains(document)) {
                this.documents.add(document);
            }
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public List<Document> getDocuments(){
        return this.documents;
    }

    public int getNumberOfDocuments(){
        return this.documents.size();
    }

    public String toString(){
        StringBuilder str = new StringBuilder();

        str.append("ID : ");
        str.append(this.id);
        str.append("\n");

        str.append("Name : ");
        str.append(this.name);
        str.append("\n");

        str.append("Catalog contains " + this.getNumberOfDocuments() + " elements : \n");
        for(Document document : this.documents) {
            str.append(document.toString());
            str.append("\n");
        }
        return str.toString();
    }

}
