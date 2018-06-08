package com.ges.mysocialdashboard.twitter;

import java.util.List;



import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

@ProxyGen
public interface TwitterService {
	String ADDRESS = "twitter-service";
	
	
	  /**
	   * The address on which the successful action are sent.
	   */
	String EVENT_ADDRESS = "twitter";
	
	
  public void getTrends(Handler<AsyncResult<List<Trend>>> resulthandler);
  
  public void sendTrends(Trend trend);
  
  public static TwitterService create(Vertx vertx) {
      return new TwitterAPI(vertx);
  }
  
  public static TwitterService createProxy(Vertx vertx) {
      return new TwitterServiceVertxEBProxy(vertx, ADDRESS);
  }
  
  
	

}
