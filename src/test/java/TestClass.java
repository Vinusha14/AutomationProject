import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import org.json.simple.JSONObject;

public class TestClass {
    public String baseUrl = "https://api.nasa.gov";
    public String apiKey = "cvxjev456MzhfJbw9JFrZOJIvMRByegGqqcbmFMI";
    public String templateUrl = baseUrl + "/planetary/apod?api_key=";
    public static RestTemplate restTemplate;

    @BeforeClass
    public static void setUpRestTemplate() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void test() throws ParseException {
        String date = "2001-04-12";
        String response = restTemplate.getForObject(templateUrl + apiKey + "&date=" + date, String.class);
        System.out.println(response);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(response);
        String array = (String) object.get("date");
        Assert.assertEquals(date,array);
       }
}



