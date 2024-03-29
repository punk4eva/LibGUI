
package com.mason.libgui.utils;

import com.mason.libgui.core.UIComponent;

/**
 *
 * @author Adam Whittaker
 */
public class UIAligner{
    
    
    public enum Position{
        START, MIDDLE, END
    }

    public enum Direction{

        LEFT(false), UP(true), DOWN(true), RIGHT(false);


        public final boolean vertical;

        Direction(boolean vert){
            vertical = vert;
        }

    }
    
    
    private int topMargin, bottomMargin, leftMargin, rightMargin;
    
    
    public UIAligner(int tm, int bm, int lm, int rm){
        topMargin = tm;
        bottomMargin = bm;
        leftMargin = lm;
        rightMargin = rm;
    }
    
    
    public int getTopMargin(){
        return topMargin;
    }
    
    public int getBottomMargin(){
        return bottomMargin;
    }
    
    public int getLeftMargin(){
        return leftMargin;
    }
    
    public int getRightMargin(){
        return rightMargin;
    }
    
    
    private int getHorizontalMod(){
        return (leftMargin-rightMargin)/2;
    }
    
    private int getVerticalMod(){
        return (topMargin-bottomMargin)/2;
    }
    
    
    public void align(UIComponent comp, int width, int height, Position hor, Position vert){
        switch(hor){
            case START -> comp.setX(leftMargin);
            case MIDDLE -> comp.setX(width/2 - comp.getWidth()/2 + getHorizontalMod());
            case END -> comp.setX(width - rightMargin - comp.getWidth());
        }
        
        switch(vert){
            case START -> comp.setY(topMargin);
            case MIDDLE -> comp.setY(height/2 - comp.getHeight()/2 + getVerticalMod());
            case END -> comp.setY(height - bottomMargin - comp.getHeight());
        }
    }
    
    
    public static UIAligner DEFAULT_ALIGNER = new UIAligner(2*6, 2*6, 2*6, 2*6);
    
}
