import org.json.simple.JSONArray;
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

public class ReturnedFieldsTest {
    public static RestTemplate restTemplate;
    public static String baseUrl = "https://api.nasa.gov";
    public static String apiKey = "cvxjev456MzhfJbw9JFrZOJIvMRByegGqqcbmFMI";
    public static String templateUrl = baseUrl + "/planetary/apod?api_key=";
    public static String response;

    @BeforeClass
    public static void setUpRestTemplate() {
        restTemplate = new RestTemplate();
    }
    /*
    Test for not nullable fields in API response are not null
     */
    @Test
    public void testMandatoryReturnFieldsAreReturnedFromResponse() throws ParseException {
        String dateValue = "2021-01-01";
        String dateParamExtension = "&start_date=" + dateValue;
        try {
            response = restTemplate.getForObject(templateUrl + apiKey + dateParamExtension, String.class);
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(response);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(0);
                Assert.assertNotNull("title field in response should not be null", object.get("title"));
                Assert.assertNotNull("date field in response should not be null", object.get("date"));
                Assert.assertNotNull("url field in response should not be null", object.get("url"));
                Assert.assertNotNull("hdurl field in response should not be null", object.get("hdurl"));
                Assert.assertNotNull("media_type field in response should not be null", object.get("media_type"));
                Assert.assertNotNull("explanation field in response should not be null", object.get("explanation"));
                Assert.assertNotNull("service_version field in response should not be null", object.get("service_version"));
            }
        } catch(HTTPException ex){
            Assert.fail("HTTP Exception is not expected "+ex.getMessage());
        } catch(HttpServerErrorException ex){
            Assert.fail("HTTPServerError Exception is not expected "+ex.getMessage());
        } catch(HttpClientErrorException ex){
            Assert.fail("HTTPClientError Exception is not expected "+ex.getMessage());
        }
    }
}
