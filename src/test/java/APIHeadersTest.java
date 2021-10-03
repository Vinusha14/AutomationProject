import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.http.HTTPException;


public class APIHeadersTest {
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
    Test to validate content-type in headers is in JSON format
    test also includes validation of the connection type as "keep-alive"
     */
    @Test
    public void testHeadersInformationFromResponse() throws HTTPException, HttpClientErrorException, HttpServerErrorException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(templateUrl + apiKey,
                HttpMethod.GET,
                requestEntity,
                String.class);
        headers = responseEntity.getHeaders();
        Assert.assertEquals("Response content type is expected in JSON",MediaType.APPLICATION_JSON_VALUE,headers.getContentType().toString());
        Assert.assertEquals("Response connection should be keep-alive","keep-alive",headers.getConnection().get(0));
    }

}
