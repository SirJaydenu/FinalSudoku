package com.example.finalsudoku;

import java.util.ArrayList;

public class Solver {
    private int selected_Row;
    private int selected_Col;
    private static int[][]  board;
    ArrayList<ArrayList<Object>> emptyBoxIndex;
    Solver(){
        selected_Row = -1;
        selected_Col = -1;

        board = new int[9][9];

        for(int r = 0; r < 9; r++){
            for (int c = 0; c < 9; c++){
                board[r][c] = 0;
            }
        }
        for (int count = 0; count < 38; count++) {
            int row = (int) (Math.random() * 9);
            int col = (int) (Math.random() * 9);
            int value = (int) (Math.random() * 9) + 1; // 1 to 9

            // Check if the selected cell is empty
            if (board[row][col] == 0 && isValidMove(row, col, value)) {
                board[row][col] = value;
            } else {
                // Try again with a different cell
                count--;
            }
        }
        emptyBoxIndex = new ArrayList<>();

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
//    private boolean check (int row, int col){
//        if (this.board[row][col] > 0){
//            for (int i = 0; i < 9; i++){
//                if(this.board[i][col] == this.board[row][col] && row != i){
//                    return false;
//                }
//                if(this.board[row][i] == this.board[row][col] && col != i){
//                    return false;
//                }
//            }
//            int boxRow = row / 3;
//            int boxCol = col / 3;
//            for(int r = boxRow*3; r < boxRow*3 + 3; r++){
//                for(int c = boxCol*3; c < boxCol*3 +3; c++){
//                    if(this.board[r][c] == this.board[row][boxCol] && row != r && col != c){
//                        return false;
//                    }
//                }
//            }
//        }
//        return true;
//    }
//    public boolean solve(Game display){
//        int row = -1, col = -1;
//        for(int r = 0; r < 9; r ++){
//            for(int c = 0; c < 9; c ++){
//                if(this.board[r][c] == 0){
//                    row = r;
//                    col = c;
//                    break;
//                }
//            }
//        }
//        if(row == -1 || col == -1){
//            return true;
//        }
//        for(int i = 1; i < 10; i ++){
//            this.board[row][col] = i;
//            display.invalidate();
//            if(check(row, col)){
//                if(solve(display)){
//                    return true;
//                }
//            }
//            this.board[row][col] = 0;
//        }
//        return false;
//    }
//    public void resetBoard(){
//        for(int r = 0; r < 9; r++){
//            for (int c = 0; c < 9; c++){
//                board[r][c] = 0;
//            }
//        }
//        this.emptyBoxIndex = new ArrayList<>();
//    }
    public void setNumberPos(int num){
        if(this.selected_Row != -1 && this.selected_Col != -1){
            if(board[this.selected_Row-1][this.selected_Col-1] == num){
                board[this.selected_Row-1][this.selected_Col-1] = 0;
            }
            else{
                board[this.selected_Row-1][this.selected_Col-1] = num;
            }
        }
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
