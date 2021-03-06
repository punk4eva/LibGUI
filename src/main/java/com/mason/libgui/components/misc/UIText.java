
package com.mason.libgui.components.misc;

import com.mason.libgui.core.UIComponent;
import com.mason.libgui.utils.StyleInfo;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import static com.mason.libgui.utils.RenderUtils.LINE_WIDTH;

/**
 *
 * @author Adam Whittaker
 */
public class UIText extends UIComponent{
    
    
    private String text;
    private Font font;
    private Color color;
    private boolean dropShadow;
    private int dropShadowOffset;
    private Color shadowColor;
    private int padding;
    
    
    public UIText(String t, Font f, Color c, boolean dropS, int dropShadowOff, Color shadowC, int x, int y, int pad){
        super(x, y, 0, 0);
        padding = pad;
        text = t;
        font = f;
        color = c;
        dropShadow = dropS;
        dropShadowOffset = dropShadowOff;
        shadowColor = shadowC;
    }
    
    public UIText(String t, StyleInfo col, int x, int y){
        this(t, col.FONT, col.TEXT, true, 2, col.TEXT.darker().darker(), x, y, LINE_WIDTH);
    }
    
    public UIText(String t, int x, int y){
        this(t, StyleInfo.DEFAULT_STYLE_INFO, x, y);
    }
    
    
    @Override
    public void render(Graphics2D g){
        FontMetrics f = g.getFontMetrics();
        g.setFont(font);
        if(dropShadow){
            g.setColor(shadowColor);
            g.drawString(text, x + dropShadowOffset + padding, y + dropShadowOffset + height - f.getDescent() - padding);
        }
        g.setColor(color);
        g.drawString(text, x + padding, y + height - f.getDescent() - padding);
    }
    
    
}
