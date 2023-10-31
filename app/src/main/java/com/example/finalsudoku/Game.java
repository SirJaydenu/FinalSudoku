package com.example.finalsudoku;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Game extends View {
    private final int boardColor;
    private final int cellFillColor;
    private final int cellHighlightColor;
    private final int letterColor;
    private final int letterColorSolve;
    private final Paint boardColorPaint = new Paint();
    private final Paint cellFillColorPaint = new Paint();
    private final Paint cellsHighlightColorPaint = new Paint();
    private final Paint letterPaint = new Paint();
    private final
    Rect letterPaintBounds = new Rect();
    private final Solver solver = new Solver();
    private int cellSize;
    public Game (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Game,
                0, 0);
        try {
            boardColor = a.getInteger(R.styleable.Game_boardColor, 0);
            cellFillColor = a.getInteger(R.styleable.Game_CellFillColor, 0);
            cellHighlightColor = a.getInteger(R.styleable.Game_cellHighlightColor, 0);
            letterColor = a.getInteger(R.styleable.Game_lettorColor, 0);
            letterColorSolve = a.getInteger(R.styleable.Game_lettorColorSolve, 0);

        } finally {
            a.recycle();
        }
    }


    @Override
    protected void onMeasure (int width, int height){
        super.onMeasure(width, height);

        int dimension = Math.min(this.getWidth(), this.getHeight());
        cellSize = dimension / 9;
        setMeasuredDimension(dimension, dimension);
    }
    @Override
    protected void onDraw(@NonNull Canvas canvas){
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(16);
        boardColorPaint.setColor(boardColor);
        boardColorPaint.setAntiAlias(true);

        cellFillColorPaint.setStyle(Paint.Style.FILL);
        cellFillColorPaint.setAntiAlias(true);
        cellFillColorPaint.setColor(cellFillColor);

        cellsHighlightColorPaint.setStyle(Paint.Style.FILL);
        cellsHighlightColorPaint.setAntiAlias(true);
        cellsHighlightColorPaint.setColor(cellHighlightColor);

        letterPaint.setStyle(Paint.Style.FILL);
        letterPaint.setAntiAlias(true);
        letterPaint.setColor(letterColor);
        colorCell(canvas, Solver.getSelected_Row(), Solver.getSelected_Col());
        canvas.drawRect(0, 0, getWidth(), getHeight(), boardColorPaint);
        drawBoard(canvas);
        drawNumbers(canvas);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        boolean isValid;

        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){
            Solver.setSelected_Row((int)Math.ceil(x/cellSize));
            Solver.setSelected_Col((int)Math.ceil(y/cellSize));
            isValid = true;
        }
        else{
            isValid = false;
        }
        return isValid;
    }
    private void drawNumbers(Canvas canvas){
        letterPaint.setTextSize(cellSize);
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                if(solver.getBoard()[r][c] != 0){
                    String text = Integer.toString(solver.getBoard()[r][c]);
                    float width, height;
                    letterPaint.getTextBounds(text, 0, text.length(), letterPaintBounds);
                    width = letterPaint.measureText(text);
                    height = letterPaintBounds.height();

                    canvas.drawText(text, (c*cellSize) + ((cellSize - width)/2), (r*cellSize+cellSize) - ((cellSize - height)/2), letterPaint);

                }
            }
        }
        letterPaint.setColor(letterColorSolve);
        for(ArrayList<Object> letter : solver.getEmptyBoxIndex()){
            int r = (int) letter.get(0);
            int c = (int) letter.get(1);
            String text = Integer.toString(solver.getBoard()[r][c]);
            float width, height;
            letterPaint.getTextBounds(text, 0, text.length(), letterPaintBounds);
            width = letterPaint.measureText(text);
            height = letterPaintBounds.height();
            canvas.drawText(text, (c*cellSize) + ((cellSize - width)/2), (r*cellSize+cellSize) - ((cellSize - height)/2), letterPaint);
        }
    }
    private void colorCell(Canvas canvas, int r, int c){
        if(Solver.getSelected_Col() != -1 && Solver.getSelected_Row() != 1){
            canvas.drawRect((c-1) * cellSize, 0, c*cellSize,
                    cellSize*9, cellsHighlightColorPaint);
            canvas.drawRect(0, (r-1)* cellSize, 9*cellSize,
                    cellSize*c, cellsHighlightColorPaint);
            canvas.drawRect((c-1) * cellSize, (r-1)*cellSize, c*cellSize,
                    cellSize*9, cellsHighlightColorPaint);

        }
        invalidate();
    }
    private void drawThickLines(){
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(10);
        boardColorPaint.setColor(boardColor);
    }
    private void drawThinLines(){
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(4);
        boardColorPaint.setColor(boardColor);
    }
    private void drawBoard(Canvas canvas){
        for(int x = 0; x < 10; x++){
            if ((x % 3 == 0)) {
                drawThickLines();
            } else {
                drawThinLines();
            }
            canvas.drawLine(cellSize * x, 0, cellSize * x,
                    getWidth(), boardColorPaint);
            drawBoard(canvas);
        }
        for(int y = 0; y < 10; y++){
            if ((y % 3 == 0)) {
            drawThickLines();
        } else {
            drawThinLines();
        }
            canvas.drawLine(0, cellSize * y, getWidth(),
                    cellSize * y,  boardColorPaint);
        }
    }

    public Solver getSolver(){
        return this.solver;
    }

}
