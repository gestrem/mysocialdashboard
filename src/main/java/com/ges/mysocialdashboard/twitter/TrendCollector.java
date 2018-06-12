package com.ges.mysocialdashboard.twitter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import static com.ges.mysocialdashboard.twitter.TwitterService.ADDRESS;
public class TrendCollector extends AbstractVerticle {
	
	private Twitter twitter = TwitterFactory.getSingleton();

	 @Override
	  public void start() throws Exception {
	    super.start();
	   
	    TwitterService service = TwitterService.create(vertx, ready ->{
	    	if (ready.succeeded()) {
	    		
	    		System.out.println("Twitter Service Ready");
	    		
	    		
	    		 //ProxyHelper.registerService(TwitterService.class, vertx, service, ADDRESS);
	    		 
	    	}
	    	
	    	else {
	    		
	    		System.out.println("error "+ready.result());
	    	}
	    });
	    
		 

		  
	    // Read the configuration, and deploy a MarketDataVerticle for each company listed in the configuration.
	
	    
	      System.out.println("Trend collector");
	      vertx.setPeriodic(10000, l -> {
	    	  
    	      send();
    	    });
	      
	      // Read the configuration, and deploy a MarketDataVerticle for each company listed in the configuration.
	    
	    }
	 
	 private void send() {
		 Trend trend = new Trend();
	        
		    

			try {
				Trends myTrends = twitter.getPlaceTrends(615702);

		    	 
		        for (int i = 0; i < myTrends.getTrends().length; i++) {
		        	
		        	trend.setName(myTrends.getTrends()[i].getName());
		        	trend.setUrl(myTrends.getTrends()[i].getURL());
		        	
		        	 System.out.println("Sending");
		        	 System.out.println(trend.toJson());
		        	 vertx.eventBus().publish(ADDRESS, trend.toJson());
		        
		           
		        }
				
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
			
			
				 
				
			}
		   
		  }


}
