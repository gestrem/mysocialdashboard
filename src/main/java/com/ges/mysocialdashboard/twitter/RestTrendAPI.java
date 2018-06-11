package com.ges.mysocialdashboard.twitter;

import java.util.HashMap;
import java.util.Map;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import static com.ges.mysocialdashboard.twitter.TwitterService.ADDRESS;

public class RestTrendAPI extends AbstractVerticle {

	  private Map<String, JsonObject> trends = new HashMap<>();

	  @Override
	  public void start() throws Exception {
		  
		 System.out.println("REST Trend API Verticle started");
	    vertx.eventBus().<JsonObject>consumer(ADDRESS)
	        .handler(message -> {
	        	JsonObject trend = message.body();
	        	trends.put(trend.getString("name"), trend);
	        	  System.out.println("Trend collect ### " + trend.encode());
	        	
	  
	        });


	    vertx.createHttpServer()
	        .requestHandler(request -> {
	          HttpServerResponse response = request.response()
	              .putHeader("content-type", "application/json");

	          
	        	  response
	              .end(Json.encodePrettily(trends));
	        	  
	         
	        	    

	        })
	        .listen(config().getInteger("http.port"), ar -> {
	          if (ar.succeeded()) {
	            System.out.println("Server started");
	          } else {
	            System.out.println("Cannot start the server: " + ar.cause());
	          }
	        });
	  }
	}