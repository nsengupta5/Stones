/*
    The Stones class is the main class of the game. It initializes a game and
    the players in the program and executes the logic of the game.
*/
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

        //Initializes the game
        initialize();

        //Plays Stones as long as player doesn't quit
        while (!endStones) {
            int playerTurn = 0;
            //Continues game until heap is 0
            while (game.getHeap() != 0) {
                System.out.println("____________________________________________________________\n");
                System.out.println(game.getGameState());
                //Checks if player one's turn
                if (playerTurn % 2 == 0) {
                    //Plays player one's turn
                    turn(p1);
                    //Checks if player one is winner
                    if (!checkWinner(p1)) {
                        //Changes player's turn
                        playerTurn++;
                    }
                }
                else {
                    System.out.println("It's Omno's Turn!");
                    System.out.println("Omno is thinking...");
                    //Delay to simulate AI thinking
                    TimeUnit.SECONDS.sleep(3);
                    //Finds best play for AI
                    int bestPlay = p2.findBestPlay(game.getHeap());
                    //Plays AI's turn
                    game.playTurn(p2, bestPlay);
                    //Checks if AI is winner
                    if (!checkWinner(p2)) {
                        playerTurn++;
                    }
                }
            }
        }
    }

    //Initializes information for Stones
    public static void initialize() {

        //Adds player one to game
        System.out.print("Player 1: ");
        String p1Name = sc.nextLine();
        game = new Game();
        p1 = new Player(p1Name);
        game.addPlayer(p1);

        System.out.println("Player 2 is Omno\n");
        boolean validDifficulty = false;
        while (!validDifficulty) {
            //Sets difficulty level for AI
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
        //Adds player two to game
        game.addPlayer(p2);
    }

    //Plays turn for player
    public static void turn(Player p) {
        boolean validMove = false;
        //Asks for move until move is valid
        while (!validMove) {
            System.out.println("It's " + p.getName() + "'s turn!");
            System.out.println("How many stones do you want to remove from the heap?");
            int stones = sc.nextInt();
            //Plays turn based on input
            if (!game.playTurn(p, stones)) {
                System.out.println("Invalid # of stones. Please try again\n");
            } else {
                validMove = true;
            }
        }
    }

    //Checks if player is winner and prints winner
    public static boolean checkWinner(Player p) {
        //Player wins when heap is 0
        if (game.getHeap() == 0) {
            System.out.println(p.getName() + " wins!\n");
            //Checks if player wants to play again
            p.incrementScore();
            game.printPlayerAndScore();
            checkIfPlayAgain();
            return true;
        }
        else {
            return false;
        }
    }

    //Checks if player wants to play another round
    public static void checkIfPlayAgain() {
        System.out.print("Do you want to play again? (Y/N): ");
        String response = sc.next();
        if (response.toUpperCase().equals("N")) {
            System.out.println("Thanks for playing!");
            endStones = true;
        }
        else {
            //Generates new heap for new round
            game.generateNewHeap();
            endStones = false;
        }
    }
}
