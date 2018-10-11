package getthis;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
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

    private static List<String> fetchTweetsByHashtag(String hashtag)
            throws TwitterException, URISyntaxException, IOException {
        LOGGER.info("Fetching tweets for " + hashtag);
        Query query = new Query(hashtag);
        QueryResult result = twitter.search(query);
        List<Status> tweets = result.getTweets();
        return tweets.stream().map(Status::getText).distinct().collect(Collectors.toList());
    }

    // TODO: set env variable GOOGLE_APPLICATION_CREDENTIALS=/Users/fsyeda/Desktop/keys/getthis-5c46fc1ae41e.json
    public static void main(String[] args) throws TwitterException, IOException, URISyntaxException, ParseException {
        List<String> tweets = TwitterAuthenticator.fetchTweetsByHashtag("$NFLX");
        tweets.forEach(System.out::println);

        for (String tweet : tweets) {
            System.out.println(tweet);
            System.out.println("------");
        }
//    GoogleNLPAnalyzer googleNLPAnalyzer = new GoogleNLPAnalyzer();
//    tweets.forEach(googleNLPAnalyzer::analyzeSentiment);
//    tweets.forEach(googleNLPAnalyzer::analyzeEntities);
//    googleNLPAnalyzer.analyzeEntities("LinkedIn in acquired by Microsoft in Mountain View for $23B");
    }

}
