package com.swradioafrica.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UrlShortener {

	@Inject private PropertiesRepository propertiesRepository;
	
	public String shorten(String url) throws Exception {
		String API_STRING = "http://api.j.mp/v3/shorten?longUrl=%s&login=%s&apiKey=%s&format=json";
		String USERNAME = propertiesRepository.loadProperties().JMPUsername;
		String API_KEY = propertiesRepository.loadProperties().JMPKey;
			
		String api_url = String.format(API_STRING, url, USERNAME, API_KEY);
		StringBuilder response = new StringBuilder();
		
        try {
            URL jmpUrl = new URL(api_url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(jmpUrl.openStream()));
            String line;
        	
            while ((line = reader.readLine()) != null) {
            	response.append(line);
            }
            reader.close();
    		
            JSONObject jsonObject = new JSONObject(response.toString());
    		if (jsonObject.getString("status_txt").equals("OK")) {
    			return jsonObject.getJSONObject("data").getString("url");
    		}
        } catch (Exception e) {
            return "";
        }

		return "";
	}
}
