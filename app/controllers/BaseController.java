package controllers;

import models.UserAccount;
import models.Waterfall;
import play.mvc.Controller;
import views.html.waterfall;

import javax.lang.model.type.IntersectionType;

public class BaseController extends Controller
{
    private String LOGGED_IN_USER = "LoggedInUserId";

    public void login(UserAccount user)
    {
        session().put(LOGGED_IN_USER, "" + user.getUserAccountId());
    }

    public void logout()
    {
        session().remove(LOGGED_IN_USER);
    }

    public boolean isLoggedIn()
    {
        return session().containsKey(LOGGED_IN_USER);
    }

    public Integer loggedInUserId()
    {
        String userIdText = session().get(LOGGED_IN_USER);
        Integer userId = null;

        if(userIdText != null)
        {
            userId = Integer.parseInt(userIdText);
        }

        return userId;
    }
}
