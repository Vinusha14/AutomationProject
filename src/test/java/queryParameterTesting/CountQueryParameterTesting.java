package queryParameterTesting;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class CountQueryParameterTesting {
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
    public void testResponseForAValidCountParameterValue() throws ParseException {
        int countValue = 30;
        String countParamExtension="&count="+countValue;
        response = restTemplate.getForObject(templateUrl + apiKey + countParamExtension, String.class);
        JSONParser parser = new JSONParser();
        JSONArray object = (JSONArray) parser.parse(response);
        Assert.assertEquals("Response should return "+countValue+"images",countValue,object.size());
    }

    @Test
    public void testResponseForANegativeCountParameterValue(){
        int countValue = -28;
        String countParamExtension="&count="+countValue;
        try {
            response = restTemplate.getForObject(templateUrl + apiKey + countParamExtension, String.class);
        } catch(HttpClientErrorException ex){
            Assert.assertEquals("Bad request response code should be returned from server",400,ex.getRawStatusCode());
            System.out.println(ex.getMessage());
        }
    }
    @Test
    public void testResponseForADecimalCountParameterValue(){
        double countValue = 2.5;
        String countParamExtension="&count="+countValue;
        try {
            response = restTemplate.getForObject(templateUrl + apiKey + countParamExtension, String.class);
        } catch(HttpClientErrorException ex){
            Assert.assertEquals("Bad request response code should be returned from server",400,ex.getRawStatusCode());
            System.out.println(ex.getMessage());
        }
    }
    @Test
    public void testResponseWithCountParamValueGreaterThanMaxLimit(){
        int countValue = 238;
        String countParamExtension="&count="+countValue;
        try {
            response = restTemplate.getForObject(templateUrl + apiKey + countParamExtension, String.class);
        } catch(HttpClientErrorException ex){
            Assert.assertEquals("Bad request response code should be returned from server",400,ex.getRawStatusCode());
            System.out.println(ex.getMessage());
        }
    }
    @Test
    public void testResponseWithCountParamValueLessThanMinLimit(){
        int countValue = 0;
        String countParamExtension="&count="+countValue;
        try {
            response = restTemplate.getForObject(templateUrl + apiKey + countParamExtension, String.class);
        } catch(HttpClientErrorException ex){
            Assert.assertEquals("Bad request response code should be returned from server",400,ex.getRawStatusCode());
            System.out.println(ex.getMessage());
        }
    }
    @Test
    public void testResponseWithEmptyCountParamValueReturnsDefaultImage() throws ParseException {
        String countParamExtension="&count=";
        try {
            response = restTemplate.getForObject(templateUrl + apiKey + countParamExtension, String.class);
        } catch(HttpClientErrorException ex){
            JSONParser parser = new JSONParser();
            JSONArray object = (JSONArray) parser.parse(response);
            Assert.assertEquals("By Default if count param values is passed as empty response should return today's information",1,object.size());
        }
    }
}
