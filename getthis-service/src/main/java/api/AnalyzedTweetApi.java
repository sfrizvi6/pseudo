package api;


import data.AnalyzedTweet;
import getthis.TwitterAuthenticator;
import response.AnalyzedTweetResponse;
import twitter4j.TwitterException;
import utils.AnalyzedTweetsUtil;

import java.util.List;

public class AnalyzedTweetApi {

    public AnalyzedTweetApi() {

    }

    public AnalyzedTweetResponse getAnalyzedTweetsByHashtag(String hashtag) throws TwitterException {
        List<AnalyzedTweet> analyzedTweetList = TwitterAuthenticator.fetchAnalyzedTweetsByHashtag("Pakistan");

        float averageSentimentScoreOfTweets = AnalyzedTweetsUtil.averageSentimentScoreOfTweets(analyzedTweetList);
        float averageSentimentMagnitudeOfTweets = AnalyzedTweetsUtil.averageSentimentMagnitudeOfTweets(analyzedTweetList);
        return new AnalyzedTweetResponse(analyzedTweetList, averageSentimentScoreOfTweets, averageSentimentMagnitudeOfTweets);
    }
}
