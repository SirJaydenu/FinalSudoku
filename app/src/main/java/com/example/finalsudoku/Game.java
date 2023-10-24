package com.example.finalsudoku;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Game extends View {
    private final int boardColor;
    private final Paint boardColorPaint = new Paint();
    public Game(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Game,
                0, 0);

        try{
            boardColor = a.getInteger(R.styleable.SudokuBoard_boardColor, 0)
        }finally{
            a.recycle();
        }
    }
    @Override
    protected void onMeasure (int width, int height){
        super.onMeasure(width, height);

        int dimension = Math.min(this.getWidth(), this.getHeight());

        setMeasuredDimension(dimension, dimension);
    }
    @Override
    protected void onDraw(Canvas canvas){
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(16);
        boardColorPaint.setColor(boardColor);
        boardColorPaint.setAntiAlias(true);

        canvas.drawRect(0, 0, getWidth(), getHeight(), boardColorPaint);
    }
}
