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
import java.time.LocalDate;
/*
TODO -
 3. add comments on the test
 */
public class DateQueryParameterTests {
    public static RestTemplate restTemplate;
    public String baseUrl = "https://api.nasa.gov";
    public String apiKey = "cvxjev456MzhfJbw9JFrZOJIvMRByegGqqcbmFMI";
    public String templateUrl = baseUrl + "/planetary/apod?api_key=";

    @BeforeClass
    public static void setUpRestTemplate(){
        restTemplate = new RestTemplate();
    }

    @Test
    public void testDefaultDateResponse() throws ParseException {
        try {
            String response = restTemplate.getForObject(templateUrl + apiKey, String.class);
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(response);
            String dateValue = (String) object.get("date");
            Assert.assertEquals("Date should be equal to today's date by default", dateValue, LocalDate.now().toString());
        } catch(HTTPException ex){
            throw ex;
        } catch(HttpServerErrorException ex){
            throw ex;
        } catch(HttpClientErrorException ex){
            throw ex;
        }
    }

    @Test
    public void testDateBefore1995(){
        String date = "1994-02-26";
        String urlExtensionWithDate = "&date="+date;
        try {
            restTemplate.getForObject(templateUrl + apiKey + urlExtensionWithDate, String.class);
        } catch(HttpClientErrorException ex){
            Assert.assertEquals("Bad Request status code - 400 is returned",400,ex.getRawStatusCode());
            System.out.println(ex.getMessage());
        } catch(HTTPException ex){
            throw ex;
        } catch(HttpServerErrorException ex){
            throw ex;
        }
    }
    @Test
    public void testDateAfterToday(){
        String date = LocalDate.now().plusDays(1).toString();
        String urlExtensionWithDate = "&date="+date;
        try {
            restTemplate.getForObject(templateUrl + apiKey + urlExtensionWithDate, String.class);
        } catch(HttpClientErrorException ex){
            Assert.assertEquals("Bad Request status code - 400 is returned",400,ex.getRawStatusCode());
            System.out.println(ex.getMessage());
        } catch(HTTPException ex){
            throw ex;
        } catch(HttpServerErrorException ex){
            throw ex;
        }
    }
    @Test
    public void testDateWithNull(){
        String date = null;
        String urlExtensionWithDate = "&date="+date;
        try {
            restTemplate.getForObject(templateUrl + apiKey + urlExtensionWithDate, String.class);
        } catch(HttpClientErrorException ex){
            Assert.assertEquals("Bad Request status code - 400 is returned",400,ex.getRawStatusCode());
            System.out.println(ex.getMessage());
        } catch(HTTPException ex){
            throw ex;
        } catch(HttpServerErrorException ex){
            throw ex;
        }
    }
    @Test
    public void testWithEmptyDate() throws ParseException {
        String date = "";
        String urlExtensionWithDate = "&date=" + date;
        try {
            String response = restTemplate.getForObject(templateUrl + apiKey + urlExtensionWithDate, String.class);
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(response);
            String dateValue = (String) object.get("date");
            Assert.assertEquals("Date should be equal to today's date by default", dateValue, LocalDate.now().toString());
        } catch(HTTPException ex){
            throw ex;
        } catch(HttpServerErrorException ex){
            throw ex;
        } catch(HttpClientErrorException ex){
            throw ex;
        }
    }
    @Test
    public void testWithInvalidDateFormat(){
        String date = "21-12-01";
        String urlExtensionWithDate = "&date="+date;
        try {
            restTemplate.getForObject(templateUrl + apiKey + urlExtensionWithDate, String.class);
        } catch(HttpClientErrorException ex){
            Assert.assertEquals("Bad Request status code - 400 is returned",400,ex.getRawStatusCode());
            System.out.println(ex.getMessage());
        } catch(HTTPException ex){
            throw ex;
        } catch(HttpServerErrorException ex){
            throw ex;
        }
    }
    @Test
    public void testWithInvalidDateFormatInSeperators(){
        String date = "2021.01.01";
        String urlExtensionWithDate = "&date="+date;
        try {
            String response = restTemplate.getForObject(templateUrl + apiKey + urlExtensionWithDate, String.class);
        } catch(HttpClientErrorException ex){
            Assert.assertEquals("Bad Request status code - 400 is returned",400,ex.getRawStatusCode());
            System.out.println(ex.getMessage());
        } catch(HTTPException ex){
            throw ex;
        } catch(HttpServerErrorException ex){
            throw ex;
        }
    }
    @Test
    public void testWithInvalidDateFormatInSeperatorsVariation(){
        String date = "2021/01/01";
        String urlExtensionWithDate = "&date="+date;
        try {
            restTemplate.getForObject(templateUrl + apiKey + urlExtensionWithDate, String.class);
        } catch(HttpClientErrorException ex){
            Assert.assertEquals("Bad Request status code - 400 is returned",400,ex.getRawStatusCode());
            System.out.println(ex.getMessage());
        } catch(HTTPException ex){
            throw ex;
        } catch(HttpServerErrorException ex){
            throw ex;
        }
    }
    /*
    Test to validate the response returns the specific (valid) date information requested accurately
     */
    @Test
    public void testResponseWithValidDateQueryParam() throws ParseException {
        String date = "2001-04-12";
        try {
            String response = restTemplate.getForObject(templateUrl + apiKey + "&date=" + date, String.class);
            System.out.println(response);
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(response);
            String array = (String) object.get("date");
            Assert.assertEquals(date, array);
        } catch(HTTPException ex){
            throw ex;
        } catch(HttpServerErrorException ex){
            throw ex;
        } catch(HttpClientErrorException ex){
            throw ex;
        }
    }
}
