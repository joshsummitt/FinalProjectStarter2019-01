package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WaterfallDetail
{
    @Id
    private int waterfallId;

    private double fallLatitude;
    private double fallLongitude;
    private String waterfallName;
    private int height;
    private Integer userAccountId;

    public WaterfallDetail(int waterfallId, double fallLatitude, double fallLongitude, String waterfallName, int height, Integer userAccountId)
    {
        this.waterfallId = waterfallId;
        this.fallLatitude = fallLatitude;
        this.fallLongitude = fallLongitude;
        this.waterfallName = waterfallName;
        this.height = height;
        this.userAccountId = userAccountId;
    }

    public int getWaterfallId()
    {
        return waterfallId;
    }


    public double getFallLatitude()
    {
        return fallLatitude;
    }


    public double getFallLongitude()
    {
        return fallLongitude;
    }

    public String getWaterfallName()
    {
        return waterfallName;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public Integer getUserAccountId()
    {
        return userAccountId;
    }
}
