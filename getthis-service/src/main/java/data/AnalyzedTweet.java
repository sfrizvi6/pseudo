package data;

import com.google.cloud.language.v1.Sentiment;

public class AnalyzedTweet {

    public final String text;
    public final float sentimentScore;
    public final float sentimentMagnitude;

    public AnalyzedTweet(String text, Sentiment sentiment) {
        this.text = text;
        this.sentimentScore = sentiment.getScore();
        this.sentimentMagnitude = sentiment.getMagnitude();
    }

    @Override
    public String toString() {
        return "AnalyzedTweet{" +
                "text='" + text + '\'' +
                ", sentimentScore=" + sentimentScore +
                ", sentimentMagnitude=" + sentimentMagnitude +
                '}';
    }
}
