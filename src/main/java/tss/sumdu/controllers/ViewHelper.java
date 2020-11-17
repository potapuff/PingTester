package tss.sumdu.controllers;

import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class ViewHelper {

    public static void notFound(Context ctx){
       userError(ctx, 404, "Service or path not found");
    }

    public static void userError(Context ctx, Integer code, String message){
        Map<String, String> model = new HashMap<>();
        model.put("code", code.toString());
        model.put("message", message);
        ctx.status(code);
        ctx.render("/velocity/error.vm", model);
    }



}
