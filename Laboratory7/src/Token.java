public class Token {

    private Integer number;
    public Token() { this.number = 0; }
    public Token(Integer number){
        this.number = number;
    }

    public void setNumber(Integer number){
        this.number = number;
    }

    public Integer getNumber(){
        return this.number;
    }

    public String toString(){
        return this.number.toString();
    }

}
