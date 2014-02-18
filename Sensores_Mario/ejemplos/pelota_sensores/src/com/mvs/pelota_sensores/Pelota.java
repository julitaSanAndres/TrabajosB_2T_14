package com.mvs.pelota_sensores;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Pelota  { 
 
    // Vista que controlara la marble. 
    private View mView; 
 
    // atributos de la marble
    // x e y son privados porque necesitamos compararlos con los nuevos valores para estar seguro de que son validos
    
    private int mX = 0; 
    private int mY = 0;    
    private int mRadius = 100;
    private int mColor = Color.RED; 
    private double gravedad = 25;
 
    /** 
     * Marble constructor. 
     *  
     * @param view 
     *            View controlling the marble 
     */ 
    public Pelota(View view) { 
        this.mView = view; 
        init(); 
    } 
 
    /** 
     * Setup marble starting co-ords. 
     */ 
    public void init() { 
        mX = 160; 
        mY = 240; 
    } 
 
    /** 
     * Draw the marble. 
     *  
     * @param canvas 
     *            Canvas object to draw too. 
     * @param paint 
     *            Paint object used to draw with. 
     */ 
    public void draw(Canvas canvas, Paint paint) { 
        paint.setColor(mColor); 
        canvas.drawCircle(mX, mY, mRadius, paint); 
    } 
 
    /** 
     * Attempt to update the marble with a new x value, boundary checking 
     * enabled to make sure the new co-ordinate is valid. 
     *  
     * @param newX 
     *            Incremental value to add onto current x co-ordinate. 
     */ 
    public void updateX(float newX) { 
        mX += (newX*gravedad)*6; // Es necesario la multiplicaion x6 ya que sino no conseguimos llegar a los extremos
 
        // controlar coordenadas ya que no queremos que se salga de la pantalla. 
        if (mX + mRadius >= mView.getWidth()) 
            mX = mView.getWidth() - mRadius; 
        else if (mX - mRadius < 0) 
            mX = mRadius; 
    } 
 
    /** 
     * Attempt to update the marble with a new y value, boundary checking 
     * enabled to make sure the new co-ordinate is valid. 
     *  
     * @param newY 
     *            Incremental value to add onto current y co-ordinate. 
     */ 
    public void updateY(float newY) { 
        mY -= (newY*gravedad)*6; 
 
     // controlar coordenadas ya que no queremos que se salga de la pantalla. 
        if (mY + mRadius >= mView.getHeight()) 
            mY = mView.getHeight() - mRadius; 
        else if (mY - mRadius < 0) 
            mY = mRadius; 
    } 
    
    /** 
     * @return Current x co-ordinate. 
     */ 
    public int getX() { 
        return mX; 
    } 
 
    /** 
     * @return Current y co-ordinate. 
     */ 
    public int getY() { 
        return mY; 
    } 
} 