package net.social;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.RateLimitStatus;

import java.io.*;

public class CheckLimit {
	public static String IDLimit  = "/friends/ids";
	
	public static int start(String endpoint) {
		int r = 0;
		
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(Global.CONSUMER_KEY, Global.CONSUMER_SECRET);
		AccessToken oathAccessToken = new AccessToken(Global.accessToken, Global.accessTokenSecret);
		twitter.setOAuthAccessToken(oathAccessToken);
		
		try {
			RateLimitStatus rate = twitter.getRateLimitStatus().get("/friends/ids");
			System.out.println(rate);
			System.out.println(rate.getLimit());
			System.out.println(rate.getRemaining());
			System.out.println(rate.getSecondsUntilReset());
			r = Integer.valueOf(rate.getRemaining());
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get followers' ids: " + te.getMessage());
		}
		return r;
	}
	
	public static void main(String[] args) {
		new CheckLimit().start("/friends/ids");
	}

}
