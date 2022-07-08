
package com.mason.libgui.components;

import com.mason.libgui.core.ComponentManager;
import com.mason.libgui.utils.ColorScheme;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;

import static com.mason.libgui.utils.RenderUtils.drawBorder;

/**
 *
 * @author Adam Whittaker
 */
public class Pane extends ComponentManager{
    
    
    public Pane(int x, int y, int w, int h){
        super(x, y, w, h);
    }
    
    
    @Override
    public void render(Graphics2D g){
        renderBorder(g);
        AffineTransform saved = g.getTransform();
        g.transform(AffineTransform.getTranslateInstance(x, y));
        super.render(g);
        g.setTransform(saved);
    }
    
    protected void renderBorder(Graphics2D g){
        drawBorder(g, ColorScheme.DEFAULT_COLOR_SCHEME, x, y, width, height);
    }
    
    protected MouseEvent relativeMouseCoords(MouseEvent e){
        return new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiersEx(), e.getX() - x, e.getY() - y, 
                e.getClickCount(), e.isPopupTrigger());
    }
    
    protected MouseWheelEvent relativeMouseCoords(MouseWheelEvent e){
        return new MouseWheelEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiersEx(), e.getX() - x, 
                e.getY() - y, e.getClickCount(), e.isPopupTrigger(), e.getScrollType(), e.getScrollAmount(), 
                e.getWheelRotation());
    }

    
    @Override
    public void mouseClicked(MouseEvent e){
        e = relativeMouseCoords(e);
        super.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e){
        e = relativeMouseCoords(e);
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e){
        e = relativeMouseCoords(e);
        super.mouseReleased(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e){
        e = relativeMouseCoords(e);
        super.mouseWheelMoved(e);
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        e = relativeMouseCoords(e);
        super.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e){
        e = relativeMouseCoords(e);
        super.mouseMoved(e);
    }
    
}