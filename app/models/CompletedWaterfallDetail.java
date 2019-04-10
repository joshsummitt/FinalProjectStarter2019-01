package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CompletedWaterfallDetail
{
    @Id
    private int completedWaterfallId;

    private int userAccountId;
    private String waterfallName;
    private int ranking;
    private String comment;
    private String username;
    private int waterfallId;

    public CompletedWaterfallDetail(int completedWaterfallId, int userAccountId, String waterfallName, int ranking, String comment, String username, int waterfallId)
    {
        this.completedWaterfallId = completedWaterfallId;
        this.userAccountId = userAccountId;
        this.waterfallName = waterfallName;
        this.ranking = ranking;
        this.comment = comment;
        this.username = username;
        this.waterfallId = waterfallId;
    }

    public int getCompletedWaterfallId()
    {
        return completedWaterfallId;
    }

    public int getUserAccountId()
    {
        return userAccountId;
    }

    public String getWaterfallName()
    {
        return waterfallName;
    }

    public int getRanking()
    {
        return ranking;
    }

    public String getComment()
    {
        return comment;
    }

    public String getUsername()
    {
        return username;
    }

    public int getWaterfallId()
    {
        return waterfallId;
    }
}
