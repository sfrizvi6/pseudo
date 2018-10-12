package getthis;

import com.google.cloud.language.v1.Sentiment;
import data.AnalyzedTweet;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class TwitterAuthenticator {

    private static final Logger LOGGER = Logger.getLogger(TwitterAuthenticator.class.getSimpleName());

    private static final Twitter twitter = new TwitterFactory(new ConfigurationBuilder().setDebugEnabled(true)
            .setOAuthConsumerKey("EIUQBObVxHxYoWCI5kn4UpAgc")
            .setOAuthConsumerSecret("7ExHo5Cl4KKSgOW1IsRX6VBC3NcyiIL6xJshCCFCyeM87ekRAl")
            .setOAuthAccessToken("454215044-sg9yCKaQ6tI06H5oEs50GuoJV38p7REidHsAUQrm")
            .setOAuthAccessTokenSecret("R5gIS8xip9LY23pIGlSLhpXTCTTafk6MiEMQzpssXKZrG")
            .build()).getInstance();

    private static List<String> fetchTweetsByHashtag(String hashtag) throws TwitterException {
        LOGGER.info("Fetching tweets for " + hashtag);
        Query query = new Query(hashtag);
        query.resultType(Query.ResultType.popular);
        query.count(100);
        QueryResult result = twitter.search(query);
        List<Status> tweets = result.getTweets();
        return tweets.stream().map(Status::getText).distinct().collect(Collectors.toList());
    }

    private static List<AnalyzedTweet> fetchAnalyzedTweetsByHashtag(String hashtag) throws TwitterException {
        List<String> tweets = fetchTweetsByHashtag(hashtag);
        GoogleNLPAnalyzer googleNLPAnalyzer = new GoogleNLPAnalyzer();

        List<AnalyzedTweet> analyzedTweetList = new ArrayList<>();

        for (String tweet : tweets) {
            Sentiment sentiment = googleNLPAnalyzer.analyzeSentiment(tweet);
            analyzedTweetList.add(new AnalyzedTweet(tweet, sentiment));
        }
        return analyzedTweetList;
    }

    // TODO: set env variable GOOGLE_APPLICATION_CREDENTIALS=/Users/fsyeda/Desktop/keys/getthis-5c46fc1ae41e.json
    public static void main(String[] args) throws TwitterException {
        List<AnalyzedTweet> analyzedTweetList = fetchAnalyzedTweetsByHashtag("$NFLX");

        analyzedTweetList.stream().map(AnalyzedTweet::toString).map(foo -> "----").forEach(System.out::println);
    }

}
