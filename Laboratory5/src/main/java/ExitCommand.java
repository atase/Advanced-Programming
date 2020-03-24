public class ExitCommand implements Command {
    private String command;
    private String path;
    private String option;

    public ExitCommand(){
        this.command = null;
        this.path = null;
        this.option = null;
    }

    public ExitCommand(String command){
        this.command = command;
        this.path = null;
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
        if(!this.command.equals("exit")){
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
        sb.append(this.command);
        return sb.toString();
    }

    public boolean exit(){
        return true;
    }
}
