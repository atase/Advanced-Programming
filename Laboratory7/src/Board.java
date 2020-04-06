import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private int limit;
    private int noPlayers;
    private int playerIndex;
    private List<Token> tokensList;
    private List<Token> copyTokensList;

    public Board(){
        this.limit = 0;
        this.noPlayers = 0;
        this.playerIndex = 0;
    }

    public Board(int limit, int noPlayers){
        this.limit = limit;
        this.noPlayers = noPlayers;
        this.playerIndex = 1;
    }

    public void setLimit(int limit){
        this.limit = limit;
    }

    public int getLimit(){
        return this.limit;
    }

    public void createTokens(){
        if(this.tokensList == null) {
            this.tokensList = new ArrayList<Token>();
            this.copyTokensList = new ArrayList<Token>();
            for (Integer i = 1; i <= this.limit; i++) {
                Token token = new Token(i);
                this.tokensList.add(token);
                this.copyTokensList.add(token);
            }
        }
    }

    public void setTokens(List<Token> tokensList){
        if(tokensList.size() < this.limit){
            if(this.tokensList != null){
                this.tokensList.clear();
                this.tokensList.addAll(tokensList);
            }else{
                this.tokensList = new ArrayList<Token>();
                this.tokensList.addAll(tokensList);
            }
        }
    }

    public List<Token> getTokens(){
        return this.tokensList;
    }

    public Token getToken(int tokenValue){
        Token markedToken = new Token();
        for(int index = 0; index < tokensList.size(); index++){
            if(this.tokensList.get(index).getNumber() ==  tokenValue){
                markedToken = this.tokensList.get(index);
                break;
            }
        }
        if(markedToken.getNumber() != 0) {
            this.tokensList.remove(markedToken);
            return markedToken;
        }
        return null;
    }

    public int getPlayerIndex(){
        return this.playerIndex;
    }

    public void incrementPlayerIndex(){
        if(this.playerIndex == this.noPlayers){
            this.playerIndex = 1;
        }else{
            this.playerIndex++;
        }
    }

    public int getNoPlayers(){
        return this.noPlayers;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Limit : " + this.limit);
        sb.append("\nTokens : ");
        for(Token token : copyTokensList)
            sb.append(token.toString() + " ");
        return sb.toString();
    }

}
