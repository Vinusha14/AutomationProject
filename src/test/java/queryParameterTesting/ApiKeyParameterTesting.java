package queryParameterTesting;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


/*
TODO
1. Handle timeout?
 */
public class ApiKeyParameterTesting {
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
             String response = restTemplate.getForObject(templateUrl + apiKey, String.class);
        } catch(HttpClientErrorException exception){
            Assert.assertEquals("Without API key server is supposed to return 403 error",403,exception.getRawStatusCode());
            System.out.println(exception.getMessage());
        }
    }
    /*
   Test the API response with 403 Forbidden error when invalid api key is provided in the request
    */
    @Test
    public void testResponseWithInvalidKeyInURI(){
        apiKey="cvxjev456MzhfJbw9JqcbmFM";
        try {
           String response = restTemplate.getForObject(templateUrl + apiKey, String.class);
        } catch(HttpClientErrorException exception){
            Assert.assertEquals("Without API key server is supposed to return 403 error",403,exception.getRawStatusCode());
            System.out.println(exception.getMessage());
        }
    }
    /*
    Test to send request to the API with Demo key to return a successful response
     */
    @Test
    public void testResponseWithDemoKeyInURI(){
        apiKey="DEMO_KEY";
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(templateUrl + apiKey, String.class);
            Assert.assertEquals("Given API key is expected to return a successful API response(200)",200,responseEntity.getStatusCodeValue());
        } catch(HttpClientErrorException exception){
            System.out.println(exception.getMessage());
        }
    }
    /*
    Security Testing:
    test the API cannot accept information of the resource requested in the request
    and sends the bad request HTTP error
    */
    @Test
    public void testResponseWithReturnFieldInformationInURI(){
        String returnFieldInUrlExtension = "&description=The Holographic Principle and a Teapot";
        try {
            restTemplate.getForEntity(templateUrl + apiKey + returnFieldInUrlExtension, String.class);
        } catch(HttpClientErrorException exception){
            Assert.assertEquals("Without API key server is supposed to return 400 Bad Request error",400,exception.getRawStatusCode());
            System.out.println(exception.getMessage());        }
    }
    /*
    Security Testing:
    test the API cannot accept HTTP urls (they need to be in HTTPs)
    as a result sends the bad request HTTP error
    */
    @Test
    public void testResponseWithHttpUrl(){
        baseUrl="http://api.nasa.gov";
        templateUrl = baseUrl + "/planetary/apod?api_key=";
        try {
            restTemplate.getForEntity(templateUrl + apiKey, String.class);
        } catch(HttpClientErrorException exception){
            Assert.assertEquals("With HTTP method in URL server is supposed to return 400 Bad Request error",400,exception.getRawStatusCode());
            System.out.println(exception.getMessage());
        }
    }
}
