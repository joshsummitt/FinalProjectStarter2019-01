package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Waterfall")
public class MapInfo
{
    @Id
    private int waterfallId;

    private double fallLatitude;
    private double fallLongitude;
    private String waterfallName;
    private int height;

    public double getFallLatitude()
    {
        return fallLatitude;
    }

    public void setFallLatitude(double fallLatitude)
    {
        this.fallLatitude = fallLatitude;
    }

    public double getFallLongitude()
    {
        return fallLongitude;
    }

    public void setFallLongitude(double fallLongitude)
    {
        this.fallLongitude = fallLongitude;
    }

    public int getWaterfallId()
    {
        return waterfallId;
    }

    public void setWaterfallId(int waterfallId)
    {
        this.waterfallId = waterfallId;
    }

    public String getWaterfallName()
    {
        return waterfallName;
    }

    public void setWaterfallName(String waterfallName)
    {
        this.waterfallName = waterfallName;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
}
