import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board = new Board();
    private int noTokens;
    private int noPlayers;
    private int progressionSize;
    private int maxScore = -1;
    private List<Player> players = new ArrayList<Player>();

    public Game(int noTokens, int noPlayers,  int progressionSize){
        this.noTokens = noTokens;
        this.noPlayers = noPlayers;
        this.progressionSize = progressionSize;
        this.board = new Board(noTokens, noPlayers);
    }

    public void play() throws InterruptedException {

        this.board.createTokens();
        List<Thread> threads = new ArrayList<Thread>();


        for (Integer i = 0; i < noPlayers; i++) {
            Player player = new Player(this.board, i+1);
            this.players.add(player);
        }

        while(board.getTokens().size()!=0) {
            for (Integer i = 0; i < noPlayers; i++) {
                if(board.getTokens().size() == 0){
                    break;
                }else {
                    Thread thread = new Thread(players.get(i), i.toString()+1);
                    thread.start();
                    thread.join();
                }
            }
        }
        for (Integer i = 0; i < noPlayers; i++){
            List<Token> tokens = this.players.get(i).getTokens();
            if(isProgression(tokens)){
                if(tokens.size() == this.progressionSize){
                    this.players.get(i).setScore(this.noTokens);
                    if(this.players.get(i).getScore() > this.maxScore){
                        this.maxScore = players.get(i).getScore();
                    }
                }else {
                    this.players.get(i).setScore(tokens.size());
                    if(this.players.get(i).getScore() > this.maxScore){
                        this.maxScore = players.get(i).getScore();
                    }
                }
            }
        }
        System.out.println("\n\n*********\n" + board.toString());
    }

    private boolean isProgression(List<Token> tokens){
        if(tokens.size() < 2){
            return true;
        }
        Token first = tokens.get(0);
        Token second = tokens.get(1);
        int diff = second.getNumber() - first.getNumber();
        for(int index = 2; index < tokens.size(); index++){
            if(tokens.get(index).getNumber() != second.getNumber() + diff ){
                return false;
            }else{
                second = tokens.get(index);
            }
        }
        return true;
    }

    public void results(){
        List<Player> winners = getWinners();
        System.out.println("Winners : ");

        for(Player player : winners){
            System.out.println(player.getName());
        }
    }

    public List<Player> getWinners(){
        List<Player> winners = new ArrayList<Player>();
        for(Player player : this.players ){
            if(player.getScore() == this.maxScore){
                winners.add(player);
            }
        }
        return winners;
    }

}
