import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.StopWatch;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.http.HTTPException;


public class PerformanceTest {
    public static RestTemplate restTemplate;
    public static String baseUrl = "https://api.nasa.gov";
    public static String apiKey = "cvxjev456MzhfJbw9JFrZOJIvMRByegGqqcbmFMI";
    public static String templateUrl = baseUrl + "/planetary/apod?api_key=";

    @BeforeClass
    public static void setUpRestTemplate() {
        restTemplate = new RestTemplate();
    }

    /*
    API accepts only GET method
    Test to validate API throws the Method not found 405 exception with POST
     */
    @Test
    public void testPerformanceOfAPIForTodaysImageInfoRequest() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity(headers);
        final StopWatch stopWatch = new StopWatch();
        //Measure response execution time
        stopWatch.start();
        restTemplate.exchange(templateUrl + apiKey,
                HttpMethod.GET,
                requestEntity,
                String.class);
        stopWatch.stop();
        Assert.assertTrue("Response time for today's image information should take less than 3 seconds", stopWatch.getTotalTimeSeconds() < 3);
    }
    /*
    Test for performance of API with max limit of count parameter (100)
     */
    @Test
    public void testPerformanceOfAPIForMaxCountParameterRequest(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity(headers);
        int countOfDates = 100;
        String countUrlExtension = "&count="+countOfDates;
        final StopWatch stopWatch = new StopWatch();
        //Measure response execution time
        stopWatch.start();
        restTemplate.exchange(templateUrl + apiKey + countUrlExtension,
                HttpMethod.GET,
                requestEntity,
                String.class);
        stopWatch.stop();
        Assert.assertTrue("Response time for"+countOfDates+" image information should take less than 13 seconds",stopWatch.getTotalTimeSeconds()<13);
    }
    /*
    Test for performance of API with max limit of count parameter (100) & with thumbnail information
   */
    @Test
    public void testPerformanceOfAPIForMaxCountAndThumbnailParameterRequest() throws HttpServerErrorException{
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity(headers);
        int countOfDates = 100;
        String countUrlExtension = "&count="+countOfDates;
        String thumbnailExtension = "&thumbs="+true;
        final StopWatch stopWatch = new StopWatch();
        //Measure response execution time
        stopWatch.start();
        try {
            restTemplate.exchange(templateUrl + apiKey + countUrlExtension + thumbnailExtension,
                    HttpMethod.GET,
                    requestEntity,
                    String.class);
        } catch(HTTPException| HttpServerErrorException| HttpClientErrorException ex){
            Assert.fail(ex.getMessage());
        }
        stopWatch.stop();
        Assert.assertTrue("Response time for"+countOfDates+" image information with thumbnail url information request " +
                " should take less than 15 seconds",stopWatch.getTotalTimeSeconds()<15);
    }
    /*
    Stress test the API to test the robustness of the system (API) by passing count query parameter
    value more than the max limit set
     */
    @Test
    public void testResponseWithCountParamValueGreaterThanMaxLimit(){
        int countValue = 238;
        String countParamExtension="&count="+countValue;
        try {
            restTemplate.getForObject(templateUrl + apiKey + countParamExtension, String.class);
        } catch(HttpClientErrorException ex){
            Assert.assertEquals("Bad request response code should be returned from server",400,ex.getRawStatusCode());
            System.out.println(ex.getMessage());
        } catch(HTTPException ex){
            Assert.fail("HTTP Exception is not expected");
        } catch(HttpServerErrorException ex){
            Assert.fail("HTTP Server Error Exception is not expected");
        }
    }
}


