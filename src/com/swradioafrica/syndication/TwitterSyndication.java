package com.swradioafrica.syndication;

import java.net.URI;
import java.util.logging.Logger;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.http.AccessToken;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.swradioafrica.model.ContentItem;
import com.swradioafrica.utils.PropertiesRepository;
import com.swradioafrica.utils.UrlShortener;

@Singleton
public class TwitterSyndication implements Syndication {
	private static final Logger log = Logger.getLogger(TwitterSyndication.class.getName());
	@Inject private PropertiesRepository propertiesRepository;
	@Inject private UrlShortener urlShortener; 	
	
	public String syndicate(ContentItem item) {
	    String token = propertiesRepository.loadProperties().twitterAccessToken; 
	    String tokenSecret = propertiesRepository.loadProperties().twitterAccessTokenSecret;
	    AccessToken accessToken = new AccessToken(token, tokenSecret);
		String consumerKey = propertiesRepository.loadProperties().consumerKey;
		String consumerSecret = propertiesRepository.loadProperties().consumerSecret;

		
		TwitterFactory factory = new TwitterFactory();
	    Twitter twitter = factory.getInstance();
	    twitter.setOAuthConsumer(consumerKey, consumerSecret);
	    twitter.setOAuthAccessToken(accessToken);
	
		try {
			String shortUrl;
			try {
				shortUrl = item.url;
				//shortUrl = urlShortener.shorten(item.url);
			} catch (Exception e) {
				log.warning("Url shortening failed. Using full url. Error was: " + e.getMessage());
				shortUrl = item.url;
			}
			twitter.updateStatus(item.title + " " + shortUrl);
			return null;
		} catch (Exception e) {
			String error = "Failed to post to twitter: " + e.getMessage();
			log.warning(error);
			return error;
		}
	}
}
