package net.social;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import java.io.*;

public class GET_following {
	public static long NextCursor = -1;
	public static long Cursor = -1;
	
	public void start() {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(Global.CONSUMER_KEY, Global.CONSUMER_SECRET);
		
		AccessToken oathAccessToken = new AccessToken(Global.accessToken, Global.accessTokenSecret);
		twitter.setOAuthAccessToken(oathAccessToken);
		while (NextCursor != 0){
			try {
				IDs ids = twitter.getFriendsIDs(Global.currentID, Cursor);
				for (long id : ids.getIDs()){
					System.out.println(id);
					new EditFile().Write(Global.fileName,Long.toString(id),true);
					new EditFile().Write("queue.txt",Long.toString(id),true);
				}
				NextCursor = ids.getNextCursor();
				Cursor = NextCursor;
				System.out.println(NextCursor);
			} catch (TwitterException te) {
				te.printStackTrace();
				System.out.println("Failed to get following' ids: " + te.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GET_following().start();
	}

}
