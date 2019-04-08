package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;
import play.twirl.api.Html;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class WaterfallController extends BaseController
{
    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public WaterfallController(JPAApi db, FormFactory formFactory)
    {
        this.db = db;
        this.formFactory = formFactory;
    }

    @Transactional(readOnly = true)
    public Result getWaterfall(int waterfallId)
    {
        TypedQuery<Waterfall> query = db.em().createQuery("SELECT w FROM Waterfall w WHERE waterfallId = :waterfallId", Waterfall.class);
        query.setParameter("waterfallId", waterfallId);
        Waterfall waterfall = query.getSingleResult();

        TypedQuery<CompletedWaterfallDetail> query2 = db.em().createQuery("SELECT NEW CompletedWaterfallDetail(c.completedWaterfallId, c.userAccountId, w.waterfallName, c.ranking, c.comment, u.username) FROM Waterfall w JOIN CompletedWaterfall c ON w.waterfallId = c.waterfallId JOIN UserAccount u ON c.userAccountId = u.userAccountId WHERE w.waterfallId = :waterfallId", CompletedWaterfallDetail.class);
        query2.setParameter("waterfallId", waterfallId);
        List<CompletedWaterfallDetail> reviews = query2.getResultList();

        boolean canReview = false;
        int reviewCount = 0;

        for(CompletedWaterfallDetail review : reviews)
        {
            if(isLoggedIn() && loggedInUserId() == review.getUserAccountId())
            {
                reviewCount++;
            }
        }

        if(reviewCount == 0 && isLoggedIn())
        {
            canReview = true;
        }

        return ok(views.html.waterfall.render(waterfall, reviews, isLoggedIn(), loggedInUserId(), canReview));
    }

    @Transactional(readOnly = true)
    public Result getWaterfalls()
    {
        TypedQuery<Waterfall> query = db.em().createQuery("SELECT w FROM Waterfall w", Waterfall.class);
        List<Waterfall> waterfalls = query.getResultList();

        return ok(views.html.waterfalls.render(waterfalls, isLoggedIn(), loggedInUserId()));
    }

    @Transactional(readOnly = true)
    public Result getWaterfallSearch()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String name = form.get("name");
        Logger.debug("name:" + name);

        if (name == null)
        {
            name = "";
        }
        name = "%" + name + "%";

        TypedQuery<Waterfall> query = db.em().createQuery("SELECT w FROM Waterfall w WHERE waterfallName LIKE :name", Waterfall.class);
        query.setParameter("name", name);
        List<Waterfall> waterfalls = query.getResultList();

        return ok(views.html.waterfallsearch.render(waterfalls, isLoggedIn(), loggedInUserId()));
    }

    @Transactional(readOnly = true)
    public Result getPicture(int waterfallId)
    {
        TypedQuery<Waterfall> query = db.em().createQuery("SELECT w FROM Waterfall w WHERE waterfallId = :waterfallId", Waterfall.class);
        query.setParameter("waterfallId", waterfallId);
        Waterfall waterfall = query.getSingleResult();

        return ok(waterfall.getPicture()).as("image/jpeg");
    }

    @Transactional(readOnly = true)
    public Result getHome()
    {

        TypedQuery<WaterfallDetail> query = db.em().createQuery("SELECT NEW WaterfallDetail(w.waterfallId, w.fallLatitude, w.fallLongitude, w.waterfallName, w.height, c.userAccountId) FROM Waterfall w LEFT OUTER JOIN CompletedWaterfall c ON w.waterfallId = c.waterfallId AND c.userAccountId = :userAccountId", WaterfallDetail.class);
        query.setParameter("userAccountId", loggedInUserId());
        List<WaterfallDetail> notCompleted = query.getResultList();

        ArrayList<WaterfallDetail> completed = new ArrayList<>();

        if(isLoggedIn())
        {
            for (int i = notCompleted.size() - 1; i >= 0; i--)
            {
                if(notCompleted.get(i).getUserAccountId() != null)
                {
                    completed.add(notCompleted.remove(i));
                }
            }
        }

        JsonNode notCompletedFalls = Json.toJson(notCompleted);
        JsonNode completedFalls = Json.toJson(completed);

        return ok(views.html.home.render(new Html(Json.stringify(notCompletedFalls)), new Html(Json.stringify(completedFalls)), isLoggedIn(), loggedInUserId()));
    }

    @Transactional(readOnly = true)
    public Result getWaterfallsInRange()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        int n = Integer.parseInt(form.get("distance"));
        int currentWaterfallId = Integer.parseInt(form.get("id"));

        ArrayList<MapInfo> waterfallsInRange = new ArrayList<>();

        TypedQuery<Waterfall> query = db.em().createQuery("SELECT w FROM Waterfall w WHERE waterfallId = :waterfallId", Waterfall.class);
        query.setParameter("waterfallId", currentWaterfallId);
        Waterfall currentWaterfall = query.getSingleResult();

        TypedQuery<MapInfo> query2 = db.em().createQuery("SELECT w FROM MapInfo w", MapInfo.class);
        List<MapInfo> waterfalls = query2.getResultList();

        TypedQuery<CompletedWaterfallDetail> query3 = db.em().createQuery("SELECT NEW CompletedWaterfallDetail(c.completedWaterfallId, c.userAccountId, w.waterfallName, c.ranking, c.comment, u.username) FROM Waterfall w JOIN CompletedWaterfall c ON w.waterfallId = c.waterfallId JOIN UserAccount u ON c.userAccountId = u.userAccountId WHERE w.waterfallId = :waterfallId", CompletedWaterfallDetail.class);
        query3.setParameter("waterfallId", currentWaterfallId);
        List<CompletedWaterfallDetail> reviews = query3.getResultList();

        boolean canReview = false;
        int reviewCount = 0;

        for(CompletedWaterfallDetail review : reviews)
        {
            if(isLoggedIn() && loggedInUserId() == review.getUserAccountId())
            {
                reviewCount++;
            }
        }

        if(reviewCount == 0 && isLoggedIn())
        {
            canReview = true;
        }

        for(MapInfo waterfall : waterfalls)
        {
            if(waterfall.getWaterfallId() != currentWaterfallId && distance(currentWaterfall.getFallLatitude(), currentWaterfall.getFallLongitude(), waterfall.getFallLatitude(), waterfall.getFallLongitude()) <= n)
            {
                waterfallsInRange.add(waterfall);
            }
        }

        JsonNode json = Json.toJson(waterfallsInRange);
        return ok(views.html.waterfallsinrange.render(currentWaterfall, reviews, isLoggedIn(), loggedInUserId(), canReview, new Html(Json.stringify(json))));
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2)
    {
        if ((lat1 == lat2) && (lon1 == lon2))
        {
            return 0;
        }
        else
        {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;

            return (dist);
        }
    }

    public Result getCommentAdd()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        int waterfallId = Integer.parseInt(form.get("waterfallId"));


        return ok(views.html.commentadd.render(waterfallId));
    }

    @Transactional
    public Result postGetWaterfall(Integer waterfallId)
    {
        CompletedWaterfall completedWaterfall = new CompletedWaterfall();

        DynamicForm form = formFactory.form().bindFromRequest();

        int userAccountId = loggedInUserId();
        int ranking = Integer.parseInt(form.get("ranking"));
        String comment = form.get("comment");

        completedWaterfall.setUserAccountId(userAccountId);
        completedWaterfall.setWaterfallId(waterfallId);
        completedWaterfall.setRanking(ranking);
        completedWaterfall.setComment(comment);

        db.em().persist(completedWaterfall);

        return redirect("/waterfall/" + waterfallId);
    }


}
