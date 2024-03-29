
package com.mason.libgui.components.misc;

import com.mason.libgui.core.UIComponent;
import com.mason.libgui.utils.StyleInfo;

import java.awt.*;

/**
 * A progress bar
 * @author Adam Whittaker
 */
public class PointBar extends UIComponent{
    
    
    private Color color;
    private StyleInfo info;
    private double current, max;


    /**
     * Creates an instance.
     * @param info
     * @param col The color of the "progress"
     * @param cur the current value
     * @param _max the maximum value
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public PointBar(StyleInfo info, Color col, int cur, int _max, int x, int y, int w, int h){
        super(x, y, w, h);
        color = col;
        this.info = info;
        current = cur;
        max = _max;
    }
    
    
    public void setColor(Color col){
        color = col;
    }
    
    public void setPoints(double pts){
        current = pts;
    }
    
    public void setMax(double m){
        max = m;
    }
    
    
    @Override
    public void render(Graphics2D g){
        info.RENDER_UTILS.drawButton(g, info, x, y, width, height, false, false);
        g.setColor(color);
        g.fillRect(x+info.getLineWidth(), y+info.getLineWidth(),
                (int)((width-2*info.getLineWidth())*(current/max)), height-2*info.getLineWidth());
    }

    @Override
    public void tick(int mx, int my){}

}
