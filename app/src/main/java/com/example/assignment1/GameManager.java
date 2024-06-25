package com.example.assignment1;

import java.util.Random;

public class GameManager {
    private int[][] matrix;
    private int rows;
    private int cols;
    private Random random = new Random();
    private int[] currentStoneRow;
    private boolean firstIteration;

    public GameManager(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new int[rows][cols];
        currentStoneRow = new int[cols];
        firstIteration = true;
        resetStones();
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void updateMatrix() {
        // Clear the matrix
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = 0;
            }
        }

        // Move stones down one row in each column if not at the bottom
        for (int col = 0; col < cols; col++) {
            if (currentStoneRow[col] >= 0) {
                if (currentStoneRow[col] < rows - 1) {
                    currentStoneRow[col]++;
                } else {
                    currentStoneRow[col] = -1; // Stone has reached the bottom
                }
            }
        }

        // Set stones at current positions
        for (int col = 0; col < cols; col++) {
            if (currentStoneRow[col] >= 0) {
                matrix[currentStoneRow[col]][col] = 1;
            }
        }


        boolean fallingDown = true;
        for (int col = 0; col < cols; col++) {
            if (currentStoneRow[col] != -1) {
                fallingDown = false;
                break;
            }
        }

        // falling down
        if (fallingDown) {
            resetStones();
        }
    }

    private void resetStones() {
        if (firstIteration) {
            firstIteration = false;
            // Initialize currentStoneRow to -1
            for (int i = 0; i < cols; i++) {
                currentStoneRow[i] = -1;
            }
        }

        // Select 2 out of 3 columns
        int firstColumn = random.nextInt(cols);
        int secondColumn;
        do {
            secondColumn = random.nextInt(cols);
        } while (secondColumn == firstColumn);

        currentStoneRow[firstColumn] = 0;
        currentStoneRow[secondColumn] = 0;
    }



}