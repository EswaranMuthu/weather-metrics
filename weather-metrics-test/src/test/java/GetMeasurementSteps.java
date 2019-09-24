import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GetMeasurementSteps {
    private String saveMetricsURI = WeatherMetricsConfig.BASE_API_URI + "/measurements/timeStamp/";
    private CloseableHttpResponse response;
    private String measurementResponse;

    @When("^I get a measurement for \"2015-09-01T16:20Z\"$")
    public void whenStatement()throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(saveMetricsURI+ "2015-09-01T16:20Z");
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");
        CloseableHttpResponse response = client.execute(httpGet);
        String value = convertStreamToString(response.getEntity().getContent());
        this.measurementResponse = value;
        this.response = response;
        client.close();
    }

    @Then("^the response has a status code of 200$")
    public void getMeasurement(){
        assertEquals(200, this.response.getStatusLine().getStatusCode());
    }

    @Then("the response has a status code of 404$")
    public void invalidInput(){
        assertEquals(204, this.response.getStatusLine().getStatusCode() );
    }

    @And("^the response body is:$")
    public void checkResponseJson(DataTable dt)throws IOException{
        List<String> dtList = dt.asList(String.class);

        Assert.assertTrue(this.measurementResponse.contains(dtList.get(4)));
        Assert.assertTrue(this.measurementResponse.contains(dtList.get(5)));
        Assert.assertTrue(this.measurementResponse.contains(dtList.get(6)));
        Assert.assertTrue(this.measurementResponse.contains(dtList.get(7)));
    }


    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
