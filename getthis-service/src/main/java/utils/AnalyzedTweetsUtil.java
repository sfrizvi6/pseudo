package utils;

import data.AnalyzedTweet;
import org.gradle.internal.impldep.org.eclipse.jgit.annotations.NonNull;

import java.util.List;

public class AnalyzedTweetsUtil {

    public static float averageSentimentScoreOfTweets(@NonNull List<AnalyzedTweet> analyzedTweets) {
        if (analyzedTweets.size() <= 0) {
            return Float.MIN_VALUE;
        }
        float sum = analyzedTweets.stream().map(analyzedTweet -> analyzedTweet.sentimentScore).reduce(0f, Float::sum);
        return sum / analyzedTweets.size();
    }

    public static float averageSentimentMagnitudeOfTweets(@NonNull List<AnalyzedTweet> analyzedTweets) {
        if (analyzedTweets.size() <= 0) {
            return Float.MIN_VALUE;
        }
        float sum = analyzedTweets.stream().map(analyzedTweet -> analyzedTweet.sentimentMagnitude).reduce(0f, Float::sum);
        return sum / analyzedTweets.size();
    }
}
