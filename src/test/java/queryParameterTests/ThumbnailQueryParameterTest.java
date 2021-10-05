package queryParameterTests;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.http.HTTPException;


public class ThumbnailQueryParameterTest {
    public static RestTemplate restTemplate;
    public static String baseUrl = "https://api.nasa.gov";
    public static String apiKey = "cvxjev456MzhfJbw9JFrZOJIvMRByegGqqcbmFMI";
    public static String templateUrl = baseUrl + "/planetary/apod?api_key=";
    public static String response;

    @BeforeClass
    public static void setUpRestTemplate() {
        restTemplate = new RestTemplate();
    }
    @Test
    public void testThumbnailQueryParameterTrue() throws HTTPException, HttpClientErrorException, HttpServerErrorException,ParseException {
        String dateValue = "2018-02-26";
        String dateParamExtension = "&date=" + dateValue;
        String thumbnailExtension = "&thumbs="+true;
        response = restTemplate.getForObject(templateUrl + apiKey + dateParamExtension + thumbnailExtension, String.class);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(response);
        String thumbnailUrlValue = (String)object.get("thumbnail_url");
        Assert.assertNotNull("Thumbnail url should not be null",thumbnailUrlValue);
    }
    @Test
    public void testThumbnailQueryParameterTrueForNonVideoFiles() throws HTTPException, HttpClientErrorException, HttpServerErrorException,ParseException {
        String dateValue = "2018-01-26";
        String dateParamExtension = "&date=" + dateValue;
        String thumbnailExtension = "&thumbs="+true;
        response = restTemplate.getForObject(templateUrl + apiKey + dateParamExtension + thumbnailExtension, String.class);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(response);
        String thumbnailUrlValue = (String)object.get("thumbnail_url");
        Assert.assertNull("Thumbnail url should not be null",thumbnailUrlValue);
    }
    @Test
    public void testThumbnailQueryParameterFalseForVideoFiles() throws HTTPException, HttpClientErrorException, HttpServerErrorException,ParseException {
        String dateValue = "2018-02-26";
        String dateParamExtension = "&date=" + dateValue;
        String thumbnailExtension = "&thumbs="+false;
        response = restTemplate.getForObject(templateUrl + apiKey + dateParamExtension + thumbnailExtension, String.class);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(response);
        String thumbnailUrlValue = (String)object.get("thumbnail_url");
        Assert.assertNull("Thumbnail url should not be null",thumbnailUrlValue);
    }
    @Test
    public void testThumbnailQueryParameterFalseForNonVideoFiles() throws HTTPException, HttpClientErrorException, HttpServerErrorException,ParseException {
        String dateValue = "2018-12-26";
        String dateParamExtension = "&date=" + dateValue;
        String thumbnailExtension = "&thumbs="+false;
        response = restTemplate.getForObject(templateUrl + apiKey + dateParamExtension + thumbnailExtension, String.class);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(response);
        String thumbnailUrlValue = (String)object.get("thumbnail_url");
        Assert.assertNull("Thumbnail url should not be null",thumbnailUrlValue);
    }
}
