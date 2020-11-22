package tss.sumdu.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.javalin.http.Context;
import tss.sumdu.UrlTesterApp;
import tss.sumdu.util.Service;
import tss.sumdu.util.ServiceHolder;

import java.util.HashMap;
import java.util.Map;

public class ServiceController {

    private final static ServiceHolder services = new ServiceHolder();

    public static void createOrUpdate(Context ctx) {
        Service service;
        try {
            String jsonString = ctx.body();
            JsonObject jsonObject;
            jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
            String url = jsonObject.get("url").getAsString();
            int code = jsonObject.get("code").getAsInt();
            String message = jsonObject.get("message").getAsString();
            service = new Service(url, code, message);
        } catch (Exception ex) {
            ViewHelper.userError(ctx, 400, "Bad request " + ex.getMessage());
            return;
        }

        services.putVal(service);
        ctx.redirect(service.getUrl());
    }

    public static void show(Context ctx) {
        Map<String, Object> model = new HashMap<>();
        model.put("current", services);
        model.put("host", UrlTesterApp.URL);
        model.put("csrf", ctx.sessionAttribute("csrf"));
        ctx.render("/velocity/show.vm", model);
    }

    public static void act(Context ctx) {
        String name = ctx.path();
        Service service = services.fetchVal(name);
        if (service == null) {
            ViewHelper.notFound(ctx);
            return;
        }
        ctx.status(service.getCode());
        ctx.result(service.getMessage().isEmpty() ? "" : service.getMessage());
    }

}
