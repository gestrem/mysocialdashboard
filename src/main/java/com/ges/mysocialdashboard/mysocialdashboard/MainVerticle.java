package com.ges.mysocialdashboard.mysocialdashboard;


import static com.ges.mysocialdashboard.twitter.TwitterService.ADDRESS;

import com.ges.mysocialdashboard.twitter.RestTrendAPI;
import com.ges.mysocialdashboard.twitter.TrendCollector;
import com.ges.mysocialdashboard.twitter.TwitterService;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.serviceproxy.ProxyHelper;

public class MainVerticle extends AbstractVerticle {
	

    @Override
    public void start() {
    	
    	try {
			super.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	/*
    	twitterService = TwitterService.create(vertx);
    	
		ProxyHelper.registerService(TwitterService.class, vertx, twitterService, ADDRESS);
		
		
*/
    	 vertx.deployVerticle(TrendCollector.class.getName(), new DeploymentOptions());
    	 vertx.deployVerticle(RestTrendAPI.class.getName(),new DeploymentOptions());
    	 
    	 /*
    	
        vertx.createHttpServer().requestHandler(req -> {
              req.response()
                .putHeader("content-type", "text/plain")
                .end("Hello from Vert.x!");
            }).listen(8080);
        System.out.println("HTTP server started on port 8080");
    }
    */
    }
}
