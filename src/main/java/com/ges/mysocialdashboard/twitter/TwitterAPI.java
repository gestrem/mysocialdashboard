package com.ges.mysocialdashboard.twitter;

import static com.ges.mysocialdashboard.twitter.TwitterService.ADDRESS;

import java.util.List;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;


public class TwitterAPI implements TwitterService {
	
	
	
	private Twitter twitter = TwitterFactory.getSingleton();
	private final Vertx vertx;
	
	public TwitterAPI(Vertx vertx,Handler<AsyncResult
			<TwitterService>> readyHandler) {
	    this.vertx = vertx;
	    readyHandler.handle(Future.succeededFuture(this));
	
	  }

	public void getTrends(Handler<AsyncResult<JsonObject>> resultHandler) {
		
		vertx.eventBus().<JsonObject>consumer(ADDRESS).handler( message ->{
			JsonObject trend = message.body();
			 resultHandler.handle((AsyncResult<JsonObject>) Future.succeededFuture(trend));	
		});
		
		
		/*
		   vertx.eventBus().<JsonObject>consumer(ADDRESS)
	        .handler(message -> {
	        	JsonObject trend = message.body();
	        	trends.put(trend.getString("name"), trend);
	        	  System.out.println("Trend collect ### " + trend.encode());
	        	
	  
	        });

		*/
		// TODO Auto-generated method stub
		
	}

	






}
