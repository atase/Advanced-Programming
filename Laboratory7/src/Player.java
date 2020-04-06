import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Player implements Runnable {
    private String name;
    private Board board;
    private int playerIndex;
    private int score;
    private List<Token> playerTokens = new ArrayList<Token>();
    ReentrantLock lock = new ReentrantLock();

    private boolean reading=false;

    public Player(Board board, int playerIndex){
        this.board = board;
        this.name = "Player " + playerIndex;
        this.playerIndex = playerIndex;
    }

    public String getName(){
        return this.name;
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public Board getBoard(){
        return this.board;
    }

    public List<Token> getTokens(){
        return this.playerTokens;
    }

    public void  run() {
        synchronized (this) {
            while (board.getPlayerIndex() != this.playerIndex) {
                try {
                    this.wait();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            if(board.getTokens().size() == 0) {
                return;
            }
            this.playerTokens.add(board.getToken(board.getTokens().get(0).getNumber()));
            board.incrementPlayerIndex();
            this.notifyAll();
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name : " + this.name + "\n");
        sb.append("Tokens : ");
        for(Token token : playerTokens){
            sb.append(token.toString() + " ");
        }
        return sb.toString();
    }

    public void setScore(int score){
        this.score = score;
    }


    public int getScore(){
        return this.score;
    }

}
