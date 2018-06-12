package com.ges.mysocialdashboard.twitter;

import java.util.List;



import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

@ProxyGen
public interface TwitterService {
	String ADDRESS = "twitter-service";
	
	
	  /**
	   * The address on which the successful action are sent.
	   */
	String EVENT_ADDRESS = "twitter";
	
	
  public void getTrends(Handler<AsyncResult<JsonObject>>  resultHandler);
  
  
  public static TwitterService create(Vertx vertx,Handler<AsyncResult
			<TwitterService>> readyHandler) {
      return new TwitterAPI(vertx,readyHandler);
  }
  
  static TwitterService createProxy(Vertx vertx, String address) {
	  
	   return new TwitterServiceVertxEBProxy(vertx, address);
	  }
  
 
  
  
	

}
