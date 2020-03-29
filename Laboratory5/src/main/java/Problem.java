import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem {

    public static void main(String[] args){

        String binaryFilename = "binaryData";
        String plainTextFilename = "plainData";
        String id;
        String name;
        Path path;
        String type;
        Map<String, String> tags = new HashMap<String, String>();
        List<Catalog> catalogs = new ArrayList<Catalog>();


        // First object - code file
        path = Paths.get("C:/Users/Alexandru/Documents/PHP/hello.php");
        id = "123987";
        name = "PHP - example";
        type = "Code file";


        tags.put("author","Nastasa Petru-Alexandru");
        tags.put("year","2020");
        tags.put("scope","TW-laboratory");

        Document document1 = new Document(id, type, name, path, tags);

        // Second object - book
        path = Paths.get("C:/Users/Alexandru/Documents/Books/RealWorldHaskell.pdf");
        id = "129735";
        name = "RealWorldHaskell.pdf";
        type = "Book";

        tags.clear();
        tags.put("author", "Bryan O'Sullivan");
        tags.put("year", "2005");
        tags.put("description", "Haskell language");

        Document document2 = new Document(id, type, name, path, tags);


        //Third object - Article
        path = Paths.get("C:/Users/Alexandru/Documents/Cursuri/IP01.pdf");
        id = "983667";
        name = "Ingineria Programarii - curs 1";
        type = "PDF";

        tags.clear();
        tags.put("author", "Adrian Iftene");
        tags.put("faculty", "Univ. Alexandru Ioan Cuza - Facultatea de Informatica");
        tags.put("year", "2020");


        Document document3 = new Document(id, type,  name, path, tags);

        // Catalog
        id = "129032";
        name = "Catalog";

        List<Document> documents = new ArrayList<Document>();
        documents.add(document1);
        documents.add(document2);
        documents.add(document3);

        Catalog catalog = new Catalog(id, name, documents);
        catalogs.add(catalog);

        Shell shell = new Shell(catalogs);

        while(true) {
            try {
                String command = shell.getInput();
                System.out.println(command);
                if (command.equals("exit")) {
                    break;
                }
            }catch(Exception exception){
                exception.printStackTrace();
            }
        }


    }
}
