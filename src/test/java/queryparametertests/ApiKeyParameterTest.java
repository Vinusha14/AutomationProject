package queryparametertests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.http.HTTPException;
/*
APIKeyParameterTest includes basic Security and authorization testing methods
 */
public class ApiKeyParameterTest {
    public static RestTemplate restTemplate;
    public String baseUrl = "https://api.nasa.gov";
    public String apiKey = "";
    public String templateUrl = baseUrl + "/planetary/apod?api_key=";

    @BeforeClass
    public static void setUpRestTemplate(){
        restTemplate = new RestTemplate();
    }
    /*
    Test the API response with 403 Forbidden error when no api key is provided in the request
     */
    @Test
    public void testResponseWithNoKeyInURI(){
        try {
              restTemplate.getForObject(templateUrl + apiKey, String.class);
        } catch(HttpClientErrorException exception){
            Assert.assertEquals("Without API key server is supposed to return 403 error",403,exception.getRawStatusCode());
        } catch(HTTPException ex){
            Assert.fail("HTTP Exception is not expected"+ex.getMessage());
        } catch(HttpServerErrorException ex){
            Assert.fail("HTTP Server Error Exception is not expected"+ex.getMessage());
        }
    }
    /*
   Test the API response with 403 Forbidden error when invalid api key is provided in the request
    */
    @Test
    public void testResponseWithInvalidKeyInURI(){
        apiKey="cvxjev456MzhfJbw9JqcbmFM";
        try {
           restTemplate.getForObject(templateUrl + apiKey, String.class);
        } catch(HttpClientErrorException exception){
            Assert.assertEquals("Without API key server is supposed to return 403 error",403,exception.getRawStatusCode());
        } catch(HTTPException ex){
            Assert.fail("HTTP Exception is not expected"+ex.getMessage());
        } catch(HttpServerErrorException ex){
            Assert.fail("HTTP Server Error Exception is not expected"+ex.getMessage());
        }
    }
    /*
    Security Testing:
    test the API cannot accept information of the resource requested in the request
    and sends the bad request HTTP error
    */
    @Test
    public void testResponseWithReturnFieldInformationInURI(){
        apiKey="cvxjev456MzhfJbw9JFrZOJIvMRByegGqqcbmFMI";
        String returnFieldInUrlExtension = "&description=The Holographic Principle and a Teapot";
        try {
            restTemplate.getForEntity(templateUrl + apiKey + returnFieldInUrlExtension, String.class);
        } catch(HttpClientErrorException exception){
            Assert.assertEquals("Without API key server is supposed to return 400 Bad Request error",400,exception.getRawStatusCode());
        } catch(HTTPException ex){
            Assert.fail("HTTP Exception is not expected"+ex.getMessage());
        } catch(HttpServerErrorException ex){
            Assert.fail("HTTP Server Error Exception is not expected"+ex.getMessage());
        }
    }
    /*
    Security Testing:
    test the API cannot accept HTTP urls (they need to be in HTTPs)
    as a result sends the bad request HTTP error
    */
    @Test
    public void testResponseWithHttpUrl(){
        apiKey="cvxjev456MzhfJbw9JFrZOJIvMRByegGqqcbmFMI";
        baseUrl="http://api.nasa.gov";
        templateUrl = baseUrl + "/planetary/apod?api_key=";
        try {
            restTemplate.getForEntity(templateUrl + apiKey, String.class);
        } catch(HttpClientErrorException exception){
            Assert.assertEquals("With HTTP method in URL server is supposed to return 400 Bad Request error",400,exception.getRawStatusCode());
        } catch(HTTPException ex){
            Assert.fail("HTTP Exception is not expected"+ex.getMessage());
        } catch(HttpServerErrorException ex){
            Assert.fail("HTTP Server Error Exception is not expected"+ex.getMessage());
        }
    }
}
