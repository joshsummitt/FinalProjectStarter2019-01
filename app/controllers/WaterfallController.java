package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.MapInfo;
import models.Waterfall;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Html;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class WaterfallController extends Controller
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

        return ok(views.html.waterfall.render(waterfall));
    }

    @Transactional(readOnly = true)
    public Result getWaterfalls()
    {
        TypedQuery<Waterfall> query = db.em().createQuery("SELECT w FROM Waterfall w", Waterfall.class);
        List<Waterfall> waterfalls = query.getResultList();

        return ok(views.html.waterfalls.render(waterfalls));
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

        return ok(views.html.waterfallsearch.render(waterfalls));
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
        TypedQuery<MapInfo> query = db.em().createQuery("SELECT w FROM MapInfo w", MapInfo.class);
        List<MapInfo> waterfallMapInfo = query.getResultList();

        JsonNode json = Json.toJson(waterfallMapInfo);

        return ok(views.html.home.render(new Html(Json.stringify(json))));
    }

}
