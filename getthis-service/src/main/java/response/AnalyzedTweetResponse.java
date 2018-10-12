package response;

import data.AnalyzedTweet;
import org.gradle.internal.impldep.org.eclipse.jgit.annotations.NonNull;

import java.util.List;

public class AnalyzedTweetResponse {

    public final List<AnalyzedTweet> analyzedTweetResponseList;
    public final float sentimentScore;
    public final float sentimentMagnitude;

    public AnalyzedTweetResponse(List<AnalyzedTweet> analyzedTweetResponseList, float sentimentScore, float sentimentMagnitude) {
        this.analyzedTweetResponseList = analyzedTweetResponseList;
        this.sentimentScore = sentimentScore;
        this.sentimentMagnitude = sentimentMagnitude;
    }
}
