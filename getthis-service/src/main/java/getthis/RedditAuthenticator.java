package getthis;

import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Verb;
import org.scribe.services.Base64Encoder;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class RedditAuthenticator {
    public static final String REDDIT_FETCH_POSTS_URL =
            "https://www.reddit.com/r/java/search.json?q=wordswithfriends&sort=new";

    private static String getAccessToken() {
        try {
            String redditAccessTokenUrl = "https://www.reddit.com/api/v1/access_token";
            OAuthRequest request = new OAuthRequest(Verb.POST, redditAccessTokenUrl);
            request.setCharset("UTF-8");
            request.addBodyParameter("grant_type", "client_credentials");
            request.addHeader("User-Agent", "Fatima");
            request.addHeader("Authorization",
                    "Basic " + Base64Encoder.getInstance().encode("EPrIeLugL-xdYw:2RstrsS8jR36gO6Qr74hcerXx6g".getBytes()));

            JSONObject response = new JSONObject(request.send().getBody());
            return response.get("access_token").toString();
        } catch (JSONException e) {
            throw new IllegalStateException(
                    "Failed to obtain token for Reddit API with exception: " + e.getLocalizedMessage());
        }
    }

    public static JSONObject fetchPostsBySubreddit() throws IOException {
        URL url = new URL(REDDIT_FETCH_POSTS_URL);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        httpsURLConnection.setRequestProperty("User-Agent", "Fatima");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = bufferedReader.readLine()) != null) {
            content.append(inputLine);
        }
        bufferedReader.close();
        return new JSONObject(content.toString());
    }

    public static void main(String[] args) throws Exception {
        System.out.println((((JSONObject) RedditAuthenticator.fetchPostsBySubreddit().get("data")).get("children")));
    }
}
