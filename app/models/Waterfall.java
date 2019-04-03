package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Waterfall
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int waterfallId;

    private String waterfallName;
    private int height;
    private double fallLatitude;
    private double fallLongitude;
    private BigDecimal parkingLatitude;
    private BigDecimal parkingLongitude;
    private boolean GPSRequired;
    private String description;
    private byte[] picture;

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

    public BigDecimal getParkingLatitude()
    {
        return parkingLatitude;
    }

    public void setParkingLatitude(BigDecimal parkingLatitude)
    {
        this.parkingLatitude = parkingLatitude;
    }

    public BigDecimal getParkingLongitude()
    {
        return parkingLongitude;
    }

    public void setParkingLongitude(BigDecimal parkingLongitude)
    {
        this.parkingLongitude = parkingLongitude;
    }

    public boolean isGPSRequired()
    {
        return GPSRequired;
    }

    public void setGPSRequired(boolean GPSRequired)
    {
        this.GPSRequired = GPSRequired;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public byte[] getPicture()
    {
        return picture;
    }

    public void setPicture(byte[] picture)
    {
        this.picture = picture;
    }
}
