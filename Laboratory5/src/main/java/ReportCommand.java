import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.Template;

public class ReportCommand implements Command {
    private String command;
    private String path;
    private String option;
    private String catalogId;
    private Catalog catalog;
    private List<Catalog> catalogs;
    private final String reportName = "report.html";

    public ReportCommand(){
        this.command = null;
        this.path = null;
        this.option = null;
        this.catalogId = null;
        this.catalog = new Catalog();
        this.catalogs = new ArrayList<Catalog>();
    }

    public ReportCommand(String command, String catalogId, List<Catalog> catalogs){
        this.command = command;
        this.path = null;
        this.option = null;
        this.catalogId = catalogId;
        this.catalog = new Catalog();
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
        if(!this.command.equals("report")){
            return false;
        }

        if(this.path != null)
            return false;

        if(this.option != null)
            return false;

        return true;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.command + " " + this.path);
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

    public void report(){
        this.catalog = searchCatalog();
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
}
