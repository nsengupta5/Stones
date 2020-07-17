import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Stones {
    private static Scanner sc = new Scanner(System.in);
    private static Game game;
    private static boolean endGame = false;
    private static boolean endStones = false;
    private static Player p1;
    private static AIPlayer p2;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("STONES");
        System.out.println("____________________________________________________________\n");
        System.out.println("HOW TO PLAY: Remove 1, 2 or 3 stones from the heap. Whoever removes all the stones from the heap first wins!\n\n");

        initialize();

        while (!endStones) {
            int playerTurn = 0;
            while (game.getHeap() != 0) {
                System.out.println("____________________________________________________________\n");
                System.out.println(game.getGameState());
                if (playerTurn % 2 == 0) {
                    turn(p1);
                    if (checkWinner(p1)) {
                        p1.incrementScore();
                        game.printPlayerAndScore();
                        checkIfPlayAgain();
                    }
                    else {
                        playerTurn++;
                    }
                }
                else {
                    System.out.println("It's Omno's Turn!");
                    System.out.println("Omno is thinking...");
                    TimeUnit.SECONDS.sleep(3);
                    int bestPlay = p2.findBestPlay(game.getHeap());
                    game.playTurn(p2, bestPlay);
                    if (checkWinner(p2)) {
                        p2.incrementScore();
                        game.printPlayerAndScore();
                        checkIfPlayAgain();
                    }
                    else {
                        playerTurn++;
                    }
                }
            }
        }
    }

    public static void initialize() {
        System.out.print("Player 1: ");
        String p1Name = sc.nextLine();
        game = new Game();
        p1 = new Player(p1Name);
        game.addPlayer(p1);

        System.out.println("Player 2 is Omno\n");
        boolean validDifficulty = false;
        while (!validDifficulty) {
            System.out.print("Omno's Difficulty Level (EASY/MID/HARD): ");
            String difficulty = sc.nextLine();
            switch (difficulty.toUpperCase()) {
                case "EASY":
                    p2 = new AIPlayer(0.3);
                    validDifficulty = true;
                    break;
                case "MID":
                    p2 = new AIPlayer(0.6);
                    validDifficulty = true;
                    break;
                case "HARD":
                    p2 = new AIPlayer(0.9);
                    validDifficulty = true;
                    break;
                default:
                    System.out.println("Invalid difficulty. Please try again");
                    break;
            }
        }
        game.addPlayer(p2);
    }

    public static void turn(Player p) {
        boolean validMove = false;
        while (!validMove) {
            System.out.println("It's " + p.getName() + "'s turn!");
            System.out.println("How many stones do you want to remove from the heap?");
            int stones = sc.nextInt();
            if (!game.playTurn(p, stones)) {
                System.out.println("Invalid # of stones. Please try again\n");
            } else {
                validMove = true;
            }
        }
    }

    public static boolean checkWinner(Player p) {
        if (game.getHeap() == 0) {
            System.out.println(p.getName() + " wins!\n");
            return true;
        }
        else {
            return false;
        }
    }

    public static void checkIfPlayAgain() {
        System.out.print("Do you want to play again? (Y/N): ");
        String response = sc.next();
        if (response.toUpperCase().equals("N")) {
            System.out.println("Thanks for playing!");
            endStones = true;
        }
        else {
            game.generateNewHeap();
            endStones = false;
        }
    }
}
