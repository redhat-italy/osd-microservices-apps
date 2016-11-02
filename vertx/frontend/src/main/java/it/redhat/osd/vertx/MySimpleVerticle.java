package it.redhat.osd.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.impl.StaticHandlerImpl;

public class MySimpleVerticle extends AbstractVerticle {


    private Integer remotePort;
    private String remoteUrl;

  @Override
  public void start() throws Exception {
      Router router = Router.router(vertx);

      StaticHandler staticHandler = new StaticHandlerImpl();

      router.route().handler(CorsHandler.create("*")
                      .allowedMethod(HttpMethod.GET)
                      .allowedMethod(HttpMethod.POST)
                      .allowedMethod(HttpMethod.OPTIONS));

      router.get("/api/shipping/:id").handler(this::callCamel);

      router.route("/*").handler(staticHandler);

      remotePort = Integer.parseInt(System.getProperty("remote.service.port","80"));
      remoteUrl = System.getProperty("remote.service.url","localhost");


      vertx.createHttpServer()
              .requestHandler(router::accept)
              .listen(Integer.parseInt(System.getProperty("http.port","8080")));

      System.out.println("Remote url: "+remoteUrl+":"+remotePort);

  }


  private void callCamel(RoutingContext routingContext) {

      HttpClient httpClient = vertx.createHttpClient();

      String id = routingContext.request().getParam("id");

      String composeURI = "/api/shipping/"+id;

      httpClient.getNow(remotePort, remoteUrl,composeURI , new Handler<HttpClientResponse>() {

          @Override
          public void handle(HttpClientResponse httpClientResponse) {

              httpClientResponse.bodyHandler(new Handler<Buffer>() {
                  @Override
                  public void handle(Buffer buffer) {
                      System.out.println("Response (" + buffer.length() + "): ");
                      System.out.println(buffer.getString(0, buffer.length()));

                      String data = buffer.getString(0, buffer.length());

                      routingContext.response().end(data);
                  }
              });
          }
      });

  }
}
