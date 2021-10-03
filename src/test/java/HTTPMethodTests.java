import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class HTTPMethodTests {
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
    public void testAPIResponseWithPOSTMethod(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity(headers);
        try {
            restTemplate.exchange(templateUrl + apiKey,
                    HttpMethod.POST,
                    requestEntity,
                    String.class);
        } catch(HttpClientErrorException ex){
            Assert.assertNotNull("POST operation should not be allowed",ex.getMessage());
            Assert.assertEquals("Method not allowed - 405 status code must be returned",405,ex.getRawStatusCode());
        }
    }
    /*
   API accepts only GET method
   Test to validate API throws the Method not found 405 exception with DELETE
    */
    @Test
    public void testAPIResponseWithDeleteMethod(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity(headers);
        try {
            restTemplate.exchange(templateUrl + apiKey,
                    HttpMethod.DELETE,
                    requestEntity,
                    String.class);
        } catch(HttpClientErrorException ex){
            Assert.assertNotNull("DELETE operation should not be allowed",ex.getMessage());
            Assert.assertEquals("Method not allowed - 405 status code must be returned",405,ex.getRawStatusCode());
        }
    }
    /*
 API accepts only GET method
 Test to validate API throws the Method not found 405 exception with PUT
  */
    @Test
    public void testAPIResponseWithPutMethod(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity(headers);
        try {
             restTemplate.exchange(templateUrl + apiKey,
                    HttpMethod.PUT,
                    requestEntity,
                    String.class);
        } catch(HttpClientErrorException ex){
            Assert.assertNotNull("PUT operation should not be allowed",ex.getMessage());
            Assert.assertEquals("Method not allowed - 405 status code must be returned",405,ex.getRawStatusCode());
        }
    }
}
