/*
    The AI Player class defines the best play for an AI. The best play is defined as
    the heap mod 4, and is chosen based on a weight determined by the game. When it
    does not execute the best play, it chooses a random number that is within the range
    of stones of the game.
*/
public class AIPlayer extends Player{
    private double weight;

    //AIPlayer constructor
    public AIPlayer(double weight) {
        super("Omno");
        //Weight initialized to determine difficulty of AI
        this.weight = weight;
    }

    //Finds the best play of AI
    public int findBestPlay(int heap) {
        //Returns whatever left from heap if heap is less than 4
        if (heap < 4) {
            return heap;
        }

        //Returns set number if heap divisible by 4
        else if (heap % 4 == 0) {
            return (heap % 3) + 1;
        }

        //Returns best play based on weight
        else {
            double rand = Math.random();
            //Best play defined as mod 4; chosen based on weight
            if (rand < weight) {
                return heap % 4;
            }
            //Random play
            else {
                return (int)(Math.random() * 3) + 1;
            }
        }
    }
}
