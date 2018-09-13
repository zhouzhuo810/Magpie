package me.zhouzhuo810.magpietv.widget.map.entity;

import android.graphics.Rect;

import me.zhouzhuo810.magpietv.utils.ScreenAdapterUtil;
import me.zhouzhuo810.magpietv.widget.map.view.MapTextView;


/**
 */
public class MapTextRectEntity extends BaseMapEntity {
    
    public String id;
    private boolean isEmpty = false;
    private String name;
    public int orientation;
    private int textSize;
    private int rectColor;
    public int[] colors;
    
    public MapTextRectEntity(String name, int rectColor, Rect rim) {
        this.isEmpty = true;
        this.orientation = MapTextView.EMPTY;
        this.name = name;
        this.rectColor = rectColor;
        this.rim = rim;
        this.textSize = ScreenAdapterUtil.getInstance().getScaledValue(30);
    }
    
    public MapTextRectEntity(String id, int rectColor, String name, int orientation, Rect rim, int textSizePx, int[] colors) {
        this.colors = colors;
        this.id = id;
        this.rectColor = rectColor;
        this.textSize = ScreenAdapterUtil.getInstance().getScaledValue(textSizePx);
        this.name = name;
        this.orientation = orientation;
        this.rim = rim;
    }
    
    public int[] getColors() {
        return colors;
    }
    
    public void setColors(int[] colors) {
        this.colors = colors;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public int getOrientation() {
        return orientation;
    }
    
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isEmpty() {
        return isEmpty;
    }
    
    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
    
    public int getTextSize() {
        return textSize;
    }
    
    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }
    
    public int getRectColor() {
        return rectColor;
    }
    
    public void setRectColor(int rectColor) {
        this.rectColor = rectColor;
    }
}
