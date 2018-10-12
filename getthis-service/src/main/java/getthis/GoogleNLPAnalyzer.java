package getthis;

import com.google.cloud.language.v1.*;

import java.io.IOException;
import java.util.logging.Logger;


public class GoogleNLPAnalyzer {

    private static final Logger LOGGER = Logger.getLogger(GoogleNLPAnalyzer.class.getSimpleName());
    private final LanguageServiceClient _language;

    public GoogleNLPAnalyzer() {
        try {
            _language = LanguageServiceClient.create();
        } catch (IOException e) {
            throw new IllegalStateException("Exception encountered while analyzing sentiment: " + e.getLocalizedMessage());
        }
    }

    public void analyzeEntities(String text) {
        System.out.printf("Text: %s%n", text);
        Document document = Document.newBuilder().setContent(text).setType(Document.Type.PLAIN_TEXT).build();
        AnalyzeEntitiesResponse analyzeEntitiesResponse = _language.analyzeEntities(document, EncodingType.UTF8);
        analyzeEntitiesResponse.getEntitiesList().forEach(System.out::println);
    }

    Sentiment analyzeSentiment(String text) {
        Document document = Document.newBuilder().setContent(text).setType(Document.Type.PLAIN_TEXT).build();
        return _language.analyzeSentiment(document).getDocumentSentiment();
    }
}
