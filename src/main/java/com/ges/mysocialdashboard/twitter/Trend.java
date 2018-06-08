package com.ges.mysocialdashboard.twitter;



import io.vertx.core.json.JsonObject;
import io.vertx.codegen.annotations.DataObject;

@DataObject
public class Trend {
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String name;
	private String url;
	
	  public Trend() {

	    }
	    
	 // mandatory to be used a bus event service

	    public Trend(JsonObject json) {
	        this.url = json.getString("url");
	        this.name = json.getString("name");
	    
	    }
	    
	    public JsonObject toJson() {

	        final JsonObject json = new JsonObject();
	 
	        json.put("name", this.name);
	        json.put("url", this.url);
	        return json;
	    }

}
