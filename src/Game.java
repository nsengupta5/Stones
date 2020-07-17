import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private int heap;

    public Game() {
        players = new ArrayList<>();
        heap = (int)(Math.random() * 27) + 4;
    }

    public boolean playTurn(Player p, int stones) {
        if (stones > 3 || stones < 0) {
            return false;
        }
        else if ((heap - stones) < 0) {
            return false;
        }
        else {
            heap -= stones;
            System.out.println(p.getName() + " removed " + stones + " stones from the heap");
            return true;
        }
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void generateNewHeap() {
        heap = (int)(Math.random() * 27) + 4;
    }

    public void printPlayerAndScore() {
        System.out.println(String.format("%-15s %-15s", "PLAYER", "SCORE"));
        System.out.println(String.format("%-15s %-15s", "------", "-----"));
        for (Player p : players) {
            System.out.println(String.format("%-15s %-15s", p.getName(), p.getScore()));
        }
    }

    public int getHeap() {
        return heap;
    }

    public String getGameState() {
        printHeap();
        System.out.println("");
        return "* Current # of stones in heap: " + heap + " *\n";
    }

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
                    System.out.print("O");
                    heapCount--;
                }
            }
            System.out.println("");
        }
    }
}
