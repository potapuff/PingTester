package tss.sumdu;

import io.javalin.Javalin;
import tss.sumdu.controllers.ServiceController;
import tss.sumdu.controllers.ViewHelper;

import static io.javalin.apibuilder.ApiBuilder.*;

public class UrlTesterApp {
    public static Integer PORT = 7000;
    public static String URL = "http://127.0.0.1:" + PORT;

    private final Javalin app = Javalin.create(
            config -> {
                config.addStaticFiles("/public");
            })
            .routes(() -> {
                get("/", ServiceController::show);
                post("/", ServiceController::createOrUpdate);
                get("*", ServiceController::act);
                post("*", ServiceController::act);
            });

    public void start(int port) {
        this.app.start(port);
    }

    public void stop() {
        this.app.stop();
    }

    public static void main(String[] args) {
        new UrlTesterApp().start(UrlTesterApp.PORT);
    }

}
