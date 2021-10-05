package queryparametertests;

import org.json.simple.JSONArray;
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

public class StartAndEndDateQueryParameterTest {
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
    Test API response given a valid start date
     */
    @Test
    public void testResponseGivenAStartDate() throws HTTPException, HttpClientErrorException, HttpServerErrorException,ParseException {
        String dateValue = LocalDate.now().minusDays(1).toString();
        String startDateParamExtension = "&start_date=" + dateValue;
        response = restTemplate.getForObject(templateUrl + apiKey + startDateParamExtension, String.class);
        JSONParser parser = new JSONParser();
        JSONArray object = (JSONArray) parser.parse(response);
        Assert.assertEquals("Response should return " + 2 + "images information", 2, object.size());
    }

    /*
    Test the API response with valid start and end date range
     */
    @Test
    public void testResponseGivenARangeOfDates() throws HTTPException, HttpClientErrorException, HttpServerErrorException,ParseException {
        int rangeOfDays = 9;
        LocalDate startDate = LocalDate.now().minusDays(10);
        String endDateValue = startDate.plusDays(rangeOfDays).toString();
        String dateParamExtension = "&start_date=" + startDate + "&end_date=" + endDateValue;
        response = restTemplate.getForObject(templateUrl + apiKey + dateParamExtension, String.class);
        JSONParser parser = new JSONParser();
        JSONArray object = (JSONArray) parser.parse(response);
        Assert.assertEquals("Response should return " + rangeOfDays + 1 + "images information", rangeOfDays + 1, object.size());
    }

    /*
  Test the API response with invalid start and end date range
   */
    @Test
    public void testResponseGivenARangeOfInvalidDates(){
        String startDate = "1990-02-20";
        String endDate = "2030-01-09";
        String dateParamExtension = "&start_date=" + startDate + "&end_date=" + endDate;
        try {
            response = restTemplate.getForObject(templateUrl + apiKey + dateParamExtension, String.class);
        } catch (HttpClientErrorException ex) {
            Assert.assertEquals("Bad request response code should be returned from server", 400, ex.getRawStatusCode());
        }
    }
    /*
     Test the API response with end date later than the start date
    */
    @Test
    public void testResponseStartDateGreaterThanEndDate() throws HTTPException, HttpServerErrorException{
        String startDate = "2021-01-09";
        String endDate = "1997-02-20";
        String dateParamExtension = "&start_date=" + startDate + "&end_date=" + endDate;
        try {
            response = restTemplate.getForObject(templateUrl + apiKey + dateParamExtension, String.class);
        } catch (HttpClientErrorException ex) {
            Assert.assertEquals("Bad request response code should be returned from server", 400, ex.getRawStatusCode());
        }
    }

    /*
    Test the API response with a combination of date, start date and end date
    */
    @Test
    public void testResponseWithDateStartDateAndEndDate() throws HTTPException,  HttpServerErrorException{
        String startDate = "1997-02-20";
        String endDate = "2021-01-09";
        String date = LocalDate.now().toString();
        String dateParamExtension = "&start_date=" + startDate + "&end_date=" + endDate + "&date=" + date;
        try {
            response = restTemplate.getForObject(templateUrl + apiKey + dateParamExtension, String.class);
        } catch (HttpClientErrorException ex) {
            Assert.assertEquals("Bad request response code should be returned from server", 400, ex.getRawStatusCode());
        }
    }

}
