package hu.nye.progtech.foxandhounds.service.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import hu.nye.progtech.foxandhounds.model.GameState;
import hu.nye.progtech.foxandhounds.service.util.MapUtil;

/**
 * Controls game cycles.
 */
public class GameController {


    private GameState gameState;
    private MapUtil mapUtil;

    private GameStepPerformer gameStepPerformer;

    public GameController(GameState gameState, MapUtil mapUtil, GameStepPerformer gameStepPerformer) {
        this.gameState = gameState;
        this.mapUtil = mapUtil;
        this.gameStepPerformer = gameStepPerformer;
    }

    /**
     * Commences a game loop.
     */

    public void gameLoop() throws IOException {

        while (isGameInProgress()) {
            gameStepPerformer.performGameStep();
            String name = "";
            while (mapUtil.isMapCompleted(gameState.getMapVo()) && Objects.equals(name, "")) {

                Scanner input = new Scanner(System.in);
                System.out.println("You won! Name:");
                name = input.nextLine();
                FileWriter myWriter = new FileWriter("score.txt", true);
                myWriter.write(name + "\r");
                myWriter.close();

                File f1 = new File("score.txt");
                String[] words;
                FileReader fr = new FileReader(f1);
                BufferedReader br = new BufferedReader(fr);
                String s;
                int count = 0;
                while ((s = br.readLine()) != null) {
                    words = s.split("\r");
                    for (String word : words) {
                        if (word.equals(name)) {
                            count++;
                        }
                    }
                }
                    System.out.println("You won " + count + " times so far!");

                fr.close();







            }
        }
    }

    private boolean isGameInProgress() {
        return !mapUtil.isMapCompleted(gameState.getMapVo()) && !gameState.isUserWantsToExit();

    }
}
