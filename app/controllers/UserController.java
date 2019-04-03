package controllers;

import models.CompletedWaterfallDetail;
import models.UserAccount;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserController extends BaseController
{
    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public UserController(JPAApi db, FormFactory formFactory)
    {
        this.db = db;
        this.formFactory = formFactory;
    }

    @Transactional(readOnly = true)
    public Result getUserDetail(int userAccountId)
    {
        TypedQuery<UserAccount> query = db.em().createQuery("SELECT u FROM UserAccount u WHERE userAccountId = :userAccountId", UserAccount.class);
        query.setParameter("userAccountId", userAccountId);
        UserAccount user = query.getSingleResult();

        TypedQuery<CompletedWaterfallDetail> query2 = db.em().createQuery("SELECT NEW CompletedWaterfallDetail(c.completedWaterfallId, c.userAccountId, w.waterfallName, c.ranking, c.comment, u.username) FROM Waterfall w JOIN CompletedWaterfall c ON w.waterfallId = c.waterfallId JOIN UserAccount u ON c.userAccountId = u.userAccountId WHERE c.userAccountId = :userAccountId", CompletedWaterfallDetail.class);
        query2.setParameter("userAccountId", userAccountId);
        List<CompletedWaterfallDetail> completedWaterfalls = query2.getResultList();

        return ok(views.html.userdetail.render(user, completedWaterfalls, isLoggedIn()));
    }

    public Result getUserAdd()
    {
        return ok(views.html.useradd.render());
    }

    @Transactional
    public Result postUserAdd()
    {
        UserAccount userAccount = new UserAccount();

        DynamicForm form = formFactory.form().bindFromRequest();

        String firstName = form.get("firstName");
        String lastName = form.get("lastName");
        String username = form.get("username");
        String password = form.get("password");

        userAccount.setUserFirstName(firstName);
        userAccount.setUserLastName(lastName);
        userAccount.setUsername(username);
        userAccount.setPassword(password);

        db.em().persist(userAccount);

        return redirect("/home");
    }
}


































