import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import data.TestDatas;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.DataInput;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AddMeasurementSteps
{

    private String saveMetricsURI = WeatherMetricsConfig.BASE_API_URI + "/measurements";
    private CloseableHttpResponse response;

  @When("^I have submitted new measurements as follows:$")
  public void givenStatement(DataTable dt)throws IOException {
      List<String> dtList = dt.asList(String.class);
      List<String> requestJSON = TestDatas.multiSaveMeasurement(dtList);
      requestJSON.size();
      for(String json : requestJSON){
          saveMeasurement(json);
      }
  }

  private void saveMeasurement(String json)throws  IOException{
      CloseableHttpClient client = HttpClients.createDefault();
      HttpPost httpPost = new HttpPost(saveMetricsURI);
      StringEntity entity = new StringEntity(json);
      httpPost.setEntity(entity);
      httpPost.setHeader("Accept", "application/json");
      httpPost.setHeader("Content-type", "application/json");
      CloseableHttpResponse response = client.execute(httpPost);
      this.response = response;
      client.close();

  }

  @When("^I submit a new measurement as follows:$")
  public void whenStatement(DataTable dt)throws IOException {
      List<String> dtList = dt.asList(String.class);
      int r = dtList.size();
      String json = TestDatas.validSaveMeasurement(dtList);
      saveMeasurement(json);
  }

    @Then("^the response has a status code of 201$")
    public void thenStatement(){
      assertEquals(201, this.response.getStatusLine().getStatusCode());
    }

    @Then("^the response has a status code of 400$")
    public void thenNegativeSenario(){
        assertEquals(400, this.response.getStatusLine().getStatusCode());
    }
}
