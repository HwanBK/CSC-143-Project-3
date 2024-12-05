/**
 * Class for Player objects.
 *
 * @author Hwansu Kim (Billy)
 * @version 05.14.2022
 */
public class Player {

    /** the name of the player. */
    private String name;
    /** the score of the player. */
    private int score;

    /**
     * Class constructor.
     *
     * @param name name of the player.
     */
    public Player(String name) {
        setName(name);
        this.score = 0;
    }

    /**
     * Accessor for the player's name.
     *
     * @return the player name.
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor for the player's score.
     *
     * @return the player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Mutator for the player's name.
     *
     * @param name the desired name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds the specified number to the player's score.
     *
     * @param num the amount to be added onto the score.
     */
    public void addToScore(int num) {
        this.score = score + num;
    }

    public String toString() {
        return this.name;
    }
}
