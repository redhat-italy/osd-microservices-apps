package it.redhat.osd.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.impl.StaticHandlerImpl;

public class MySimpleVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
      Router router = Router.router(vertx);

      StaticHandler staticHandler = new StaticHandlerImpl();

      router.get("/api").handler(rc -> {
          rc.response().end(new JsonObject().put("name", "my-awesome-api").put("version", 1).encode());
      });

      router.route("/*").handler(staticHandler);

      vertx.createHttpServer()
              .requestHandler(router::accept)
              .listen(8080);

  }
}
