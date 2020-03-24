import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Shell {

    private Catalog catalog;
    private String command;
    private String path;
    private String filetype;
    private String catalogId;
    private List<Catalog> catalogs;
    private final String reportName = "report.html";

    public Shell(List<Catalog> catalogs){
        this.catalog = new Catalog();
        this.command = null;
        this.path = null;
        this.filetype = null;
        this.catalogId = null;
        this.catalogs = new ArrayList<Catalog>();
        this.catalogs.addAll(catalogs);
    }

    private void load() throws IOException {
        Operation operation = new Operation();
        if(this.filetype.equals("binary")){
            this.catalog = operation.loadBinary(path);
        }else if(this.filetype.equals("plain")){
            this.catalog = operation.loadPlainText(path);
        }

        System.out.println(this.catalog.toString());
    }

    private void list() throws IOException {
        try(Stream<Path> paths = Files.walk(Paths.get(this.path))){
            paths
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        }
    }

    private void view(){
        try{
            Desktop desktop = null;
            if(Desktop.isDesktopSupported()){
                desktop = Desktop.getDesktop();
            }
            desktop.open(new File(this.path));
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    private void report(){
        Velocity.init();
        Template template = Velocity.getTemplate("./src/main/java/template.vm");
        VelocityContext context = new VelocityContext();
        context.put("ID", this.catalog.getId());
        context.put("NAME", this.catalog.getName());
        context.put("NUMBER", this.catalog.getNumberOfDocuments());

        List<Document> documents = new ArrayList<Document>();
        documents = this.catalog.getDocuments();
        context.put("documents", documents);

        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        System.out.println(writer);

        try(Writer writerbuf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.reportName), "UTF-8"))){
            writerbuf.write(String.valueOf(writer));
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public String getInput() throws IOException {
        this.catalog = new Catalog();
        this.command = null;
        this.path = null;
        this.filetype = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Syntax : command [ path - if save/view/load/list ] [ plain/binary - if load/save] [ catalogID - if save/report] ");
        String input = scanner.nextLine();

        if(isCommandValid(input)) {
            try {
                //executeOperations();
                executeClases();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this.command;
        }else{
            return "Invalid command";
        }
    }

    public boolean isCommandValid(String input){
        String[] parts = input.split(" ");

        if(parts.length < 1 || parts.length > 4) {
            return false;
        }

        if(!parts[0].equals("list") && !parts[0].equals("load") && !parts[0].equals("view") && !parts[0].equals("exit") && !parts[0].equals("report") && !parts[0].equals("save")){
            return false;
        }

        if(parts[0].equals("load") && (parts.length != 3 || (!parts[2].equals("plain") || !parts[2].equals("binary")))) {
            return false;
        }

        if((parts[0].equals("list") || parts[0].equals("view") || parts[0].equals("report"))  && parts.length != 2){
            return false;
        }

        if(parts[0].equals("save") && parts.length != 4){
            return false;
        }
        this.command = parts[0];
        if(parts.length > 1) {
            if(!this.command.equals("report")) {
                this.path = parts[1];
            }
            if(this.command.equals("report")){
                this.catalogId = parts[1];
            }
        }
        if(parts.length > 2){
            this.filetype = parts[2];
        }
        if(parts.length > 3){
            this.catalogId = parts[3];
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

    private void executeOperations() throws IOException {
        if(this.command.equals("list")){
            this.list();
        }else if(this.command.equals("view")){
            this.view();
        }else if(this.command.equals("load")){
            this.load();
        }else if(this.command.equals("report")){
            this.report();
        }else if(this.command.equals("exit")){
            return ;
        }

    }

    private void executeClases() throws IOException{

        if(this.command.equals("list")){
            ListCommand list = new ListCommand(this.command, this.path);
            if(list.validateCommand()) {
                list.list();
            }
        }else if(this.command.equals("view")){
            ViewCommand view = new ViewCommand(this.command, this.path);
            if(view.validateCommand()) {
                view.view();
            }
        }else if(this.command.equals("load")){
            LoadCommand load = new LoadCommand(this.command, this.path, this.filetype);
            if(load.validateCommand()) {
                this.catalog = load.load();
                this.catalog.toString();
            }
        }else if(this.command.equals("report")){
            ReportCommand report = new ReportCommand(this.command, this.catalogId, this.catalogs);
            if(report.validateCommand()) {
                report.report();
            }
        }else if(this.command.equals("save")){
            SaveCommand save = new SaveCommand(this.command, this.path, this.filetype, this.catalogId, this.catalogs);
            if(save.validateCommand()) {
                save.save();
            }
        }else if(this.command.equals("exit")){
            ExitCommand exit = new ExitCommand(this.command);
            if(exit.validateCommand()) {
                exit.exit();
            }
        }
    }
}
