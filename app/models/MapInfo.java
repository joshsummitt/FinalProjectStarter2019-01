package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "Waterfall")
public class MapInfo
{
    @Id
    private int waterfallId;

    private BigDecimal fallLatitude;
    private BigDecimal fallLongitude;
    private String waterfallName;

    public BigDecimal getFallLatitude()
    {
        return fallLatitude;
    }

    public void setFallLatitude(BigDecimal fallLatitude)
    {
        this.fallLatitude = fallLatitude;
    }

    public BigDecimal getFallLongitude()
    {
        return fallLongitude;
    }

    public void setFallLongitude(BigDecimal fallLongitude)
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
}
