package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CompletedWaterfall
{
    @Id
    private int completedWaterfallId;

    private int userAccountId;
    private int waterfallId;
    private int ranking;
    private String comment;

    public int getCompletedWaterfallId()
    {
        return completedWaterfallId;
    }

    public void setCompletedWaterfallId(int completedWaterfallId)
    {
        this.completedWaterfallId = completedWaterfallId;
    }

    public int getUserAccountId()
    {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId)
    {
        this.userAccountId = userAccountId;
    }

    public int getWaterfallId()
    {
        return waterfallId;
    }

    public void setWaterfallId(int waterfallId)
    {
        this.waterfallId = waterfallId;
    }

    public int getRanking()
    {
        return ranking;
    }

    public void setRanking(int ranking)
    {
        this.ranking = ranking;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
