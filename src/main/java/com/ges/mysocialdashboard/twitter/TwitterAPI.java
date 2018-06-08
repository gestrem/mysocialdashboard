package com.ges.mysocialdashboard.twitter;

import java.util.List;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
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
	
	public TwitterAPI(Vertx vertx) {
	    this.vertx = vertx;
	   
	
	  }

	public void getTrends(Handler<AsyncResult<List<Trend>>> resulthandler) {
		
	
        List<Trend> trends = null;
        Trend trend = new Trend();
        
    

		try {
			Trends myTrends = twitter.getPlaceTrends(615702);

	    	 
	        for (int i = 0; i < myTrends.getTrends().length; i++) {
	        	
	        	trend.setName(myTrends.getTrends()[i].getName());
	        	trend.setUrl(myTrends.getTrends()[i].getURL());
	        	 System.out.println(trend.toJson());
	        	trends.add(trend);
	           
	        }
			resulthandler.handle(Future.succeededFuture(trends));
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
		
			 resulthandler.handle(Future.failedFuture(e.getCause()));
			 
			
		}
		
		// TODO Auto-generated method stub
		
	}

	public void sendTrendsToBus(Trend trend) {
		
		vertx.eventBus().publish(EVENT_ADDRESS, new JsonObject()
		        .put("name", trend.getName())
		        .put("url", trend.getUrl())
		        .put("date", System.currentTimeMillis())
		    );
		
	}

	@Override
	public void sendTrends(Trend trend) {
		// TODO Auto-generated method stub
		
	}





}
