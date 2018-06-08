package com.ges.mysocialdashboard.mysocialdashboard;


import static com.ges.mysocialdashboard.twitter.TwitterService.ADDRESS;

import com.ges.mysocialdashboard.twitter.TwitterService;

import io.vertx.core.AbstractVerticle;
import io.vertx.serviceproxy.ProxyHelper;

public class MainVerticle extends AbstractVerticle {
	
	private TwitterService twitterService;

    @Override
    public void start() throws Exception {
    	
    	twitterService = TwitterService.create(vertx);
    	
		ProxyHelper.registerService(TwitterService.class, vertx, twitterService, ADDRESS);

    	
        vertx.createHttpServer().requestHandler(req -> {
              req.response()
                .putHeader("content-type", "text/plain")
                .end("Hello from Vert.x!");
            }).listen(8080);
        System.out.println("HTTP server started on port 8080");
    }
}
