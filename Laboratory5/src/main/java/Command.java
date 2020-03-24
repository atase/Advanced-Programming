public interface Command {
    public void setCommand(String command);
    public void setPath(String path);
    public void setOption(String option);

    public String getCommand();
    public String getPath();
    public String getOption();

    public boolean validateCommand();

}
