package com.swradioafrica.syndication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.google.appengine.api.xmpp.SendResponse.Status;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.http.AccessToken;
import twitter4j.http.RequestToken;

public class SetupStub {

	/**
	 * @param args
	 */
	public static void main(String args[]) throws Exception{
	    // The factory instance is re-useable and thread safe.
	    Twitter twitter = new TwitterFactory().getInstance();
	    twitter.setOAuthConsumer("NXkJlZuQHOyVA2qzVwWcfg", "vd1b0vg7qSLU1AR66SyR9ASVRQaEdXVjeJqSKOqQ");
	    RequestToken requestToken = twitter.getOAuthRequestToken();
	    AccessToken accessToken = null;
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    while (null == accessToken) {
	      System.out.println("Open the following URL and grant access to your account:");
	      System.out.println(requestToken.getAuthorizationURL());
	      System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
	      String pin = br.readLine();
	      try{
	         if(pin.length() > 0){
	           accessToken = twitter.getOAuthAccessToken(requestToken, pin);
	         }else{
	           accessToken = twitter.getOAuthAccessToken();
	         }
	      } catch (TwitterException te) {
	        if(401 == te.getStatusCode()){
	          System.out.println("Unable to get the access token.");
	        }else{
	          te.printStackTrace();
	        }
	      }
	    }
	    //persist to the accessToken for future reference.
	    storeAccessToken(twitter.verifyCredentials().getId() , accessToken);
	    System.exit(0);
	  }
	  private static void storeAccessToken(int useId, AccessToken accessToken){
		System.out.println("accessToken.getToken() [" + accessToken.getToken() + "].");
		System.out.println("accessToken.getTokenSecret() [" + accessToken.getTokenSecret() + "].");
	  }


}
