package dz.aminegasa;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class HttpRequest {
    private  String url;
    public HttpRequest(String url) {
        this.url = url;
           }
    public String sendRequest(String graphQlQuery){
        String responseBody = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");

            // Create the GraphQL query
            JsonObject queryObject = new JsonObject();
            queryObject.addProperty("query", graphQlQuery);

            // Convert the query object to JSON
            Gson gson = new Gson();
            String json = gson.toJson(queryObject);

            // Set the request entity
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            post.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(post);
            responseBody= EntityUtils.toString(response.getEntity());
            //System.out.println("Response: " + responseBody);

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return responseBody;}

}
