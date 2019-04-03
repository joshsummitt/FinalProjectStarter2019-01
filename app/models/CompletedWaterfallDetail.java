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

    public CompletedWaterfallDetail(int completedWaterfallId, int userAccountId, String waterfallName, int ranking, String comment, String username)
    {
        this.completedWaterfallId = completedWaterfallId;
        this.userAccountId = userAccountId;
        this.waterfallName = waterfallName;
        this.ranking = ranking;
        this.comment = comment;
        this.username = username;
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
}
