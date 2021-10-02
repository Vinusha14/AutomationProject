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
    public String date = "2001-04-12";

    @BeforeClass
    public static void setUp() {
    }

    @Test
    public void test() throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(templateUrl + apiKey + "&date=" + date, String.class);
        System.out.println(response);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(response);
        String array = (String) object.get("date");
        Assert.assertEquals(date,array);
    }
    //date, count, start date, end date, thumbs
    //invalid url
    //valid url no data returned
}



