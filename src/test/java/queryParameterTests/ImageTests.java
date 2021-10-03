package queryParameterTests;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ImageTests {
    public static RestTemplate restTemplate;
    public static String baseUrl = "https://api.nasa.gov";
    public static String apiKey = "cvxjev456MzhfJbw9JFrZOJIvMRByegGqqcbmFMI";
    public static String templateUrl = baseUrl + "/planetary/apod?api_key=";
    public static String response;

    @BeforeClass
    public static void setUpRestTemplate(){
        restTemplate = new RestTemplate();
        response = restTemplate.getForObject(templateUrl+apiKey,String.class);

    }
    /*
    Test If the URI returned from API response is a valid url which returns the image
     */
    @Test
    public void testImageUriIsSuccessfullyNavigated() throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(response);
        String urlValue = (String) object.get("url");
        String hdUrlValue = (String) object.get("hdurl");

        Image image = ImageIO.read(new URL(urlValue));
        Assert.assertNotEquals("Image is not returned",-1,image.getWidth(null));
        image = ImageIO.read(new URL(hdUrlValue));
        Assert.assertNotEquals("Image is not returned from hd url",-1,image.getWidth(null));
    }

    /*
    Verify if the private domain image has copy right information
     */
    @Test
    public void testForCopyrightInformationOnAPrivateDomainImage() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(response);
        String copyrightInformation = (String) object.get("copyright");

        Assert.assertNotNull("Copyright information should be displayed for a private domain image",copyrightInformation);
    }

    /*
    Test irrespective of the "hd" parameter indicating whether or not high-resolution images
    should be returned service always ignores this and and high-resolution urls are returned
    regardless
     */
    @Test
    public void testForHighResolutionImagesWithHdParamFalse() throws ParseException {
        String hdQueryParameterExtension = "&hd="+false;
        response = restTemplate.getForObject(templateUrl+apiKey+hdQueryParameterExtension,String.class);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(response);
        String hdUrlInformation = (String) object.get("hdurl");

        Assert.assertNotNull("Irrespective of the hd parameter value request should always return a hd url if present",hdUrlInformation);
    }
    /*
       Test irrespective of the "hd" parameter indicating whether or not high-resolution images
       should be returned service always ignores this and and high-resolution urls are returned
       regardless
        */
    @Test
    public void testForHighResolutionImagesWithHdParamTrue() throws ParseException {
        String hdQueryParameterExtension = "&hd="+true;
        response = restTemplate.getForObject(templateUrl+apiKey+hdQueryParameterExtension,String.class);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(response);
        String hdUrlInformation = (String) object.get("hdurl");

        Assert.assertNotNull("Irrespective of the hd parameter value request should always return a hd url if present",hdUrlInformation);
    }
}
