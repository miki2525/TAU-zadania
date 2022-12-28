package org.tau;

import org.tau.components.Board;
import org.tau.components.Player;
import org.tau.components.boards.SimpleBoard;
import org.tau.utils.EndOfMapException;
import org.tau.utils.InvalidInputsException;
import org.tau.utils.ObstacleException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    public static void main(String[] args) throws IOException {
        Board board = new SimpleBoard(5);
        Player player = new Player("playa", board);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String operatingSystem = System.getProperty("os.name");
        while (!player.isFinished()) {
//                if (operatingSystem.contains("Windows")) {
//            Runtime.getRuntime().exec("cls");
//                }
            board.printLegend();
            board.printBoard();
            int currentRow = player.currentPosition().getRow() + 1;
            int currentCol = player.currentPosition().getCol() + 1;
            System.out.println("Current position: Row[" + currentRow + "], " +
                    "Column[" + currentCol + "]");
            System.out.println();
            System.out.println("Pick the direction using CONTROLS and hit [ENTER]");
            char direction = reader.readLine().charAt(0);
            if (Character.toUpperCase(direction) == 'Q'){
                System.exit(-1);
            }
            try {
                player.move(direction);
            } catch (EndOfMapException | ObstacleException | InvalidInputsException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
        System.out.println("FINISH LINE! CONGRATS!");
    }
}

