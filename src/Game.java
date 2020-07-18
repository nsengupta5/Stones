/*
    The Game class defines a heap of stones that is a random number within a
    given range. It also defines a valid turn made by a player and executes when
    appropriate. It also prints all appropriate information such as game state,
    player names and scores.
*/
import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private int heap;
    private int max_range;
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    //Game constructor
    public Game(int maxRange) {
        players = new ArrayList<>();
        max_range = maxRange;
        //Heap of stones initialized as random number between 4 & 30
        heap = (int)(Math.random() * (max_range * 10 - max_range)) + (max_range + 1);
    }

    //Checks valid moves and plays valid move
    public boolean playTurn(Player p, int stones) {
        //Checks if input within range
        if (stones > max_range || stones < 0) {
            return false;
        }

        //Checks if input exceeds heap amount
        else if ((heap - stones) < 0) {
            return false;
        }

        //Subtracts input from heap and prints successful action
        else {
            heap -= stones;
            System.out.println(p.getName() + " removed " + stones + " stones from the heap");
            return true;
        }
    }

    //Adds player to game
    public void addPlayer(Player p) {
        players.add(p);
    }

    //Generates new heap between 4 & 30 stones
    public void generateNewHeap() {
        heap = (int)(Math.random() * (max_range * 10 - max_range)) + (max_range + 1);
    }

    //Prints player and score
    public void printPlayerAndScore() {
        System.out.println(String.format("%-15s %-15s", "PLAYER", "SCORE"));
        System.out.println(String.format("%-15s %-15s", "------", "-----"));
        for (Player p : players) {
            System.out.println(String.format("%-15s %-15s", p.getName(), p.getScore()));
        }
    }

    //Returns heap
    public int getHeap() {
        return heap;
    }

    //Prints heap representation and current heap number
    public String getGameState() {
        printHeap();
        System.out.println("");
        return "* Current # of stones in heap: " + heap + " *\n";
    }

    public void setMaxRange(int newRange) {
        max_range = newRange;
    }

    public int getMaxRange() {
        return max_range;
    }

    //Prints graphical representation of heap
    public void printHeap() {
        int row = (int)(Math.sqrt(heap)) + 1;
        int col;
        if (row * row == heap) {
            col = row;
        }
        else {
            col = row + 1;
        }

        int heapCount = heap;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (heapCount == 0) {
                    i = row;
                    j = col;
                }
                else {
                    int randColor = (int)(Math.random() * 3);
                    switch (randColor) {
                        case 0:
                            System.out.print(ANSI_PURPLE + "O" + ANSI_RESET);
                            break;
                        case 1:
                            System.out.print(ANSI_RED + "O" + ANSI_RESET);
                            break;
                        case 2:
                            System.out.print(ANSI_WHITE + "O" + ANSI_RESET);
                            break;
                        default:
                            System.out.println("O");
                            break;
                    }

                    heapCount--;
                }
            }
            System.out.println("");
        }
    }
}
