package tss.sumdu;

import io.javalin.Javalin;
import tss.sumdu.controllers.ServiceController;
import tss.sumdu.controllers.ViewHelper;
import tss.sumdu.filter.CSRFFilter;
import tss.sumdu.util.CheckTokenException;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

public class UrlTesterApp {
    public static final Integer PORT = 7000;
    public static final String URL = "http://127.0.0.1:" + PORT;

    private final Javalin app = Javalin.create(
            config -> config.addStaticFiles("/public"))
            .before("/", CSRFFilter::check)
            .before("/", CSRFFilter::generate)
            .routes(() -> {
                get("/", ServiceController::show);
                post("/", ServiceController::createOrUpdate);
                get("*", ServiceController::act);
                post("*", ServiceController::act);
            })
            .exception(CheckTokenException.class, ViewHelper::noAuth);

    public static void main(String[] args) {
        new UrlTesterApp().start(UrlTesterApp.PORT);
    }

    public void start(int port) {
        this.app.start(port);
    }

    public void stop() {
        this.app.stop();
    }

}
