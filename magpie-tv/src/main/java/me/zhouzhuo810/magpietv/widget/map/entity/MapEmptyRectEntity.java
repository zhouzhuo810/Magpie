package me.zhouzhuo810.magpietv.widget.map.entity;

import java.util.List;

/**
 * 地图数据
 */
public class MapEmptyRectEntity {

    public int mapWidth;//地图宽度
    public int mapHigh;//地图高度
    private int lineWidthPx;

    private List<MapTextRectEntity> machines;

    public MapEmptyRectEntity(int mapHigh, int mapWidth, int lineWidthPx, List<MapTextRectEntity> machines) {
        this.machines = machines;
        this.mapHigh = mapHigh;
        this.mapWidth = mapWidth;
        this.lineWidthPx = lineWidthPx;
    }

    public List<MapTextRectEntity> getMachines() {
        return machines;
    }

    public void setMachines(List<MapTextRectEntity> machines) {
        this.machines = machines;
    }

    public int getMapHigh() {
        return mapHigh;
    }

    public void setMapHigh(int mapHigh) {
        this.mapHigh = mapHigh;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }
    
    
    public int getLineWidthPx() {
        return lineWidthPx;
    }
    
    public void setLineWidthPx(int lineWidthPx) {
        this.lineWidthPx = lineWidthPx;
    }
    
    public void clear() {
        mapWidth = 0;
        mapHigh = 0;
        lineWidthPx = 0;
        machines.clear();
    }
}
