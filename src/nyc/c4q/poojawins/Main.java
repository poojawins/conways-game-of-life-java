package nyc.c4q.poojawins;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static final int ROWS = 25;
    static final int COLUMNS = 75;
    static char[][] grid = new char[ROWS][COLUMNS];

    public static void main(String[] args) {
        File seed = new File("./life1.dat");
//        File seed = new File("./life2.dat");
//        File seed = new File("./life3.dat");
//        File seed = new File("./life4.dat");
//        File seed = new File("./life5.dat");

        seedBoard(seed);
        printBoard();

        int generations = 10;

        for (int i = 0; i < generations; i++) {
              resetGrid();
              printBoard();
        }

    }

    public static void seedBoard(File file) {
        String line;
        int currentRow = 0;

        try{
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()){
                line = sc.nextLine();
                int currentCol = 0;
                for(char myCharacter : line.toCharArray()) {
                    grid[currentRow][currentCol] = myCharacter;
                    currentCol++;
                }
                currentRow++;
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLUMNS; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    public static void resetGrid() {
        for (int i = 1; i < ROWS - 1; i++) {
            for (int j = 1; j < COLUMNS - 1; j++) {
                char currentCell = grid[i][j];
                boolean alive = ('X' == currentCell);
                int neighborCount = countNeighbors(i, j);

                if (alive && (2 == neighborCount || 3 == neighborCount)) {
                    grid[i][j] = 'X';
                } else if (!alive && 3 == neighborCount) {
                    grid[i][j] = 'X';
                } else {
                    grid[i][j] = '.';
                }
            }
        }
    }

    public static int countNeighbors(int i, int j) {
        int count = 0;

        // row above
        if('X' == grid[i + 1][j - 1]) {
            count++;
        }

        if('X' == grid[i + 1][j]) {
            count++;
        }

        if('X' == grid[i + 1][j + 1]) {
            count++;
        }

        // same row
        if('X' == grid[i][j - 1]) {
            count++;
        }

        if('X' == grid[i][j + 1]) {
            count++;
        }

        // row below
        if('X' == grid[i - 1][j - 1]) {
            count++;
        }

        if('X' == grid[i - 1][j]) {
            count++;
        }

        if('X' == grid[i - 1][j + 1]) {
            count++;
        }

        return count;
    }
}
