package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserAccount
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userAccountId;

    private String username;
    private String userFirstName;
    private String userLastName;
    private String password;
    private String passwordSalt;

    public int getUserAccountId()
    {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId)
    {
        this.userAccountId = userAccountId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUserFirstName()
    {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName)
    {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName()
    {
        return userLastName;
    }

    public void setUserLastName(String userLastName)
    {
        this.userLastName = userLastName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPasswordSalt()
    {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt)
    {
        this.passwordSalt = passwordSalt;
    }
}
