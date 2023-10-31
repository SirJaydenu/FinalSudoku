package com.example.finalsudoku;

public class Solve {
    //private static final int[][] board = new int[9][9];

    public static void main(String[] args) {

        generateSudokuBoard();
    }
    public static void generateSudokuBoard() {
//        initializeBoard();
//        Fill cells randomly with valid values
//        fillCells(numFilledCells);
    }

    // Method to initialize the Sudoku board with zeros
//    private static void initializeBoard() {
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                board[i][j] = 0;
//            }
//        }
//    }

    // Method to fill a specified number of cells with valid values
//    private static void fillCells(int numFilledCells) {
//        // Code to fill cells with valid Sudoku values
//        // (You need to implement this part based on Sudoku rules)
//        // This is a simplified example that fills cells randomly.
//
//        for (int count = 0; count < numFilledCells; count++) {
//            int row = (int) (Math.random() * 9);
//            int col = (int) (Math.random() * 9);
//            int value = (int) (Math.random() * 9) + 1; // 1 to 9
//
//            // Check if the selected cell is empty
//            if (board[row][col] == 0 && isValidMove(row, col, value)) {
//                board[row][col] = value;
//            } else {
//                // Try again with a different cell
//                count--;
//            }
//        }
//    }

    // Method to check if a move is valid in the Sudoku board
//    private static boolean isValidMove(int row, int col, int value) {
//        // Check the row, column, and 3x3 subgrid for the same value
//        return !usedInRow(row, value) && !usedInCol(col, value) && !usedInSubgrid(row - row % 3, col - col % 3, value);
//    }

    // Helper method to check if a value is used in the given row
//    private static boolean usedInRow(int row, int value) {
//        for (int col = 0; col < 9; col++) {
//            if (board[row][col] == value) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // Helper method to check if a value is used in the given column
//    private static boolean usedInCol(int col, int value) {
//        for (int row = 0; row < 9; row++) {
//            if (board[row][col] == value) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // Helper method to check if a value is used in the 3x3 subgrid
//    private static boolean usedInSubgrid(int startRow, int startCol, int value) {
//        for (int row = 0; row < 3; row++) {
//            for (int col = 0; col < 3; col++) {
//                if (board[startRow + row][startCol + col] == value) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

}
