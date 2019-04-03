package controllers;

import models.UserAccount;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class SessionController extends BaseController
{
    private FormFactory formFactory;
    private JPAApi db;

    @Inject
    public SessionController(JPAApi db, FormFactory formFactory)
    {
        this.db = db;
        this.formFactory = formFactory;
    }

    public Result getLogin()
    {
        return ok(views.html.login.render(""));
    }

    public Result getLogout()
    {
        logout();
        return redirect("/home");
    }

    @Transactional(readOnly = true)
    public Result postLogin()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String username = form.get("username");

        String sql = "SELECT u FROM UserAccount u WHERE username = :username";
        TypedQuery<UserAccount> query = db.em().createQuery(sql, UserAccount.class);
        query.setParameter("username", username);

        List<UserAccount> users = query.getResultList();

        Result result;

        if(users.size() == 1)
        {
            UserAccount user = users.get(0);
            result = redirect("/home");
            login(user);
        }
        else
        {
            String answer = "Incorrect Username or Password";
            result = ok(views.html.login.render(answer));
        }

        return result;
    }
}
