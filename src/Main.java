import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

/**
 * Demonstration class: First-to-one-hundred game.
 *
 * @author Hwansu Kim (Billy)
 * @version 05.14.2022
 */
public class Main {


    public static void main(String[] args) {
        CircularLinkedList<Player> playerList = new CircularLinkedList<>();
        Iterator<Player> playerIterator = playerList.iterator();
        Random numberGen = new Random();

        Player playerOne = new Player("Dick");
        Player playerTwo = new Player("Jason");
        Player playerThree = new Player("Tim");
        Player playerFour = new Player("Damian");

        playerList.add(playerOne);
        playerList.add(playerTwo);
        playerList.add(playerThree);
        playerList.add(playerFour);

        System.out.println("GAME START\n");
        SoundEffect.START.play();
        sleep(2200);

        int topScore = 0;
        int firstRoll;
        int secondRoll;
        Player firstPlace = null;

        while (topScore < 100) {
            System.out.println("New Round Starting");
            SoundEffect.NEW_ROUND.play();
            sleep(2000);

            for (int players = 0; players < 4; players++) {
                if (topScore < 100) {
                    SoundEffect.ROLL.play();
                    sleep(1200);

                    Player currentPlayer = playerIterator.next();
                    int startScore = currentPlayer.getScore();

                    currentPlayer.addToScore(numberGen.nextInt(1,7));
                    firstRoll = currentPlayer.getScore() - startScore;

                    currentPlayer.addToScore(numberGen.nextInt(1, 7));
                    secondRoll = currentPlayer.getScore() - (firstRoll + startScore);

                    if (currentPlayer.getScore() > topScore) {
                        topScore = currentPlayer.getScore();
                        System.out.println(currentPlayer + " rolls a " + firstRoll + " and a " + secondRoll +
                                " TOTAL SCORE: " + currentPlayer.getScore() + "... NEW HIGH SCORE");
                        firstPlace = currentPlayer;
                    } else {
                        System.out.println(currentPlayer + " rolls a " + firstRoll + " and a " + secondRoll +
                                " TOTAL SCORE: " + currentPlayer.getScore());
                    }
                    sleep(1000);
                }
            }
            System.out.println();
            sleep(1000);
        }
        SoundEffect.GAME_OVER.play();
        System.out.println("The winner is " + firstPlace + " with a score of " + topScore);
        sleep(2000);
    }


    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public enum SoundEffect {
        START("Bell.wav"),
        NEW_ROUND("Shake.wav"),
        ROLL("Dice.wav"),
        GAME_OVER("TaDa.wav");


        private Clip clip;


        SoundEffect(String soundFileName) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFileName));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }

        public void play() {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();

        }
    }
}
