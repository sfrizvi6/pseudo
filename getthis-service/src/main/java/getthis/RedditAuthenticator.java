package main.java.getthis;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.services.Base64Encoder;


public class RedditAuthenticator {
  public static void getAccessToken() throws ClientProtocolException, IOException {
    String redditAccessTokenUrl = "https://www.reddit.com/api/v1/access_token";
    OAuthRequest request = new OAuthRequest(Verb.POST, redditAccessTokenUrl);
    request.setCharset("UTF-8");
    request.addBodyParameter("grant_type", "client_credentials");
    request.addHeader("User-Agent", "Fatima");
    request.addHeader("Authorization",
        "Basic " + Base64Encoder.getInstance().encode("M-n2Kjs5dQuBbQ:cvH4fUwvJUOGZRs5JtbCRC_pUnc".getBytes()));

    Response json_response = request.send();
    JSONObject jsonResp = new JSONObject(json_response.getBody());
    System.out.println("RESPONSE: " + jsonResp);
  }

  public static void main(String[] args) throws Exception {
    RedditAuthenticator.getAccessToken();
  }
}
