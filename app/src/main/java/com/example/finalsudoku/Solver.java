package com.example.finalsudoku;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class Solver {
    private int selected_Row;
    private int selected_Col;

    private static int[][]  board;
    private static int[][]  key;
    private static Stack<Integer> stack;

    ArrayList<ArrayList<Object>> emptyBoxIndex;
    Solver(){
        selected_Row = -1;
        selected_Col = -1;
        stack = new Stack<>();

        board = new int[9][9];
        key = new int [9][9];
        for (int i = 0; i < 9; i++) {
            Arrays.fill(board[i], 0);

        }
        generateBoard();
        emptyBoxIndex = new ArrayList<>();
    }
    public void generateBoard(){
        solveSudoku();
        removeNumbers();
    }
    private static boolean isValidMove(int row, int col, int value) {
        return !usedInRow(row, value) && !usedInCol(col, value) && !usedInSubgrid(row - row % 3, col - col % 3, value);
    }
    private static boolean usedInRow(int row, int value) {
        for (int col = 0; col < 9; col++) {
            if (board[row][col] == value) {
                return true;
            }
        }
        return false;
    }
    private static boolean usedInCol(int col, int value) {
        for (int row = 0; row < 9; row++) {
            if (board[row][col] == value) {
                return true;
            }
        }
        return false;
    }
    private static boolean usedInSubgrid(int startRow, int startCol, int value) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[startRow + row][startCol + col] == value) {
                    return true;
                }
            }
        }
        return false;
    }
    public void setNumberPos(int num){
        if(this.selected_Row != -1 && this.selected_Col != -1){
            if(board[this.selected_Row-1][this.selected_Col-1] == num){
                board[this.selected_Row-1][this.selected_Col-1] = 0;
            }
            else{
                if(isValidMove(selected_Row, selected_Col, num)){
                    board[this.selected_Row-1][this.selected_Col-1] = num;
                    stack.push(this.selected_Col-1);
                    stack.push(this.selected_Row-1);
                }
            }
        }
    }
        private static void solveSudoku() {
            solveCell(0, 0);
            hasUniqueSolution();
        }
        private static boolean solveCell(int row, int col) {
            if (row == 9) {
                row = 0;
                if (++col == 9) {return true;}
            }

            if (board[row][col] != 0) {
                return solveCell(row + 1, col);
            }

            for (int num = 1; num <= 9; num++) {
                if (isValidMove(row, col, num)) {
                    board[row][col] = num;

                    if (solveCell(row + 1, col)) {
                        return true;
                    }

                    board[row][col] = 0;
                }
            }

            return false;
        }
    private static void removeNumbers() {
        int cellsToRemove = 40;
        for (int i = 0; i < 9; i++) {
            System.arraycopy(board[i], 0, key[i], 0, 9);
        }

        while (cellsToRemove > 0) {
            int row = (int) (Math.random() * 9);
            int col = (int) (Math.random() * 9);

            if (board[row][col] != 0) {
                board[row][col] = 0;
                cellsToRemove--;
            }
        }
    }
    static void revealHint() {
        int row = (int) (Math.random() * 9);
        int col = (int) (Math.random() * 9);
        while(board[row][col] != 0){
            row = (int) (Math.random() * 9);
            col = (int) (Math.random() * 9);
        }
        board[row][col] = key[row][col];
    }
    public static void undoNumber(){
        if(!stack.isEmpty()) {
            board[stack.pop()][stack.pop()] = 0;
        }
    }
    private static void hasUniqueSolution() {
            int[][] copy = new int[9][9];
            for (int i = 0; i < 9; i++) {
                System.arraycopy(board[i], 0, copy[i], 0, 9);
            }
        if (!solveCell(0, 0)) {
            isSudokuSolved();
        }
    }
        public static boolean isSudokuSolved() {
            for (int[] row : board) {
                for (int cell : row) {
                    if (cell == 0) {
                        return false;
                    }
                }
            }
            return true;
        }
    public int[][] getBoard(){
        return board;
    }
    public ArrayList<ArrayList<Object>> getEmptyBoxIndex(){
        return this.emptyBoxIndex;
    }
    public int getSelected_Col() {
        return selected_Col;
    }

    public int getSelected_Row() {
        return selected_Row;
    }

    public void setSelected_Row(int r) {
        selected_Row = r;
    }

    public void setSelected_Col(int c) {
        selected_Col = c;
    }
}
