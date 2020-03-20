import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Problem {

    public static void main(String[] args){

        String binaryFilename = "binaryData";
        String plainTextFilename = "plainData";
        String id;
        String name;
        Path path;
        Map<String, String> tags = new HashMap<String, String>();


        // First object - code file
        path = Paths.get("C:/Users/Alexandru/Documents/PHP/hello.php");
        id = "123987";
        name = "PHP - example";


        tags.put("author","Nastasa Petru-Alexandru");
        tags.put("year","2020");
        tags.put("scope","TW-laboratory");

        Code code = new Code(id, name, path, tags);

        // Second object - book
        path = Paths.get("C:/Users/Alexandru/Documents/Books/RealWorldHaskell.pdf");
        id = "129735";
        name = "RealWorldHaskell.pdf";

        tags.clear();
        tags.put("author", "Bryan O'Sullivan");
        tags.put("year", "2005");
        tags.put("description", "Haskell language");

        Book book = new Book(id, name, path, tags);


        //Third object - Article
        path = Paths.get("C:/Users/Alexandru/Documents/Cursuri/IP01.pdf");
        id = "983667";
        name = "Ingineria Programarii - curs 1";

        tags.clear();
        tags.put("author", "Adrian Iftene");
        tags.put("faculty", "Univ. Alexandru Ioan Cuza - Facultatea de Informatica");
        tags.put("year", "2020");


        Article article = new Article(id, name, path, tags);

        // Catalog
        id = "129032";
        name = "Catalog";

        List<Document> documents = new ArrayList<Document>();
        documents.add(code);
        documents.add(book);
        documents.add(article);

        Catalog catalog = new Catalog(id, name, documents);


        // Save catalog
        /*Operation op = new Operation();
        op.savePlainText(catalog, plainTextFilename);
        //op.saveBinary(catalog, binaryFilename);

        Catalog loadedCatalog = new Catalog();
        loadedCatalog = op.loadPlainText(plainTextFilename);
        System.out.println(loadedCatalog.toString());
        /*loadedCatalog = op.loadBinary(binaryFilename);
        System.out.println(loadedCatalog.toString());*/

        //op.view("C:/Users/Alexandru/Documents/PHP/hello.php");


        Shell shell = new Shell();

        while(true) {
            String command = shell.getInput();
            System.out.println(command);
        }


    }
}
