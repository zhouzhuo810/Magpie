package me.zhouzhuo810.appx.event;

public class ChangeTextEvent {
    
    private int index;
    
    public ChangeTextEvent(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
}
