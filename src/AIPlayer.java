public class AIPlayer extends Player{
    private double weight;

    public AIPlayer(double weight) {
        super("Omno");
        this.weight = weight;
    }

    public int findBestPlay(int heap) {
        if (heap < 4) {
            return heap;
        }

        else if (heap % 4 == 0) {
            return (heap % 3) + 1;
        }

        else {
            double rand = Math.random();
            if (rand < weight) {
                return heap % 4;
            }
            else {
                return (int)(Math.random() * 3) + 1;
            }
        }
    }
}
