package com.example.finalsudoku;

import java.util.ArrayList;

public class Solver {
    private static int selected_Row;
    private static int selected_Col;
    int[][] board;
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
        emptyBoxIndex = new ArrayList<>();

    }
    private void getEmptyBoxIndexes(){
        for(int r = 0; r < 9; r++){
            for (int c = 0; c < 9; c++){
                if(this.board[r][c] == 0 ){
                    this.emptyBoxIndex.add(new ArrayList<>());
                    this.emptyBoxIndex.get(this.emptyBoxIndex.size()-1).add(r);
                    this.emptyBoxIndex.get(this.emptyBoxIndex.size()-1).add(c);
                }
            }
        }
    }
    public void setNumberPos(int num){
        if(selected_Row != -1 && selected_Col != -1){
            if(this.board[selected_Row-1][selected_Col-1] == num){
                this.board[selected_Row-1][selected_Col-1] = 0;
            }
            else{
                this.board[selected_Row-1][selected_Col-1] = num;
            }
        }
    }
    public int[][] getBoard(){
        return this.board;
    }
    public ArrayList<ArrayList<Object>> getEmptyBoxIndex(){
        return this.emptyBoxIndex;
    }
    public static int getSelected_Col() {
        return selected_Col;
    }

    public static int getSelected_Row() {
        return selected_Row;
    }

    public static void setSelected_Row(int selected_Row) {
        Solver.selected_Row = selected_Row;
    }

    public static void setSelected_Col(int selected_Col) {
        Solver.selected_Col = selected_Col;
    }
}
