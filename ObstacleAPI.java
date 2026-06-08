import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import java.util.ArrayList;

public class ObstacleAPI {
    public void addObstacle(double x, double y) {
        try {
          OkHttpClient client = new OkHttpClient().newBuilder()
          .build();
          MediaType mediaType = MediaType.parse("application/json");
          RequestBody body = RequestBody.create(mediaType, "{\r\n    \"x\": " +x + ",\r\n    \"y\": " +y +"}");
          Request request = new Request.Builder()
            .url("http://127.0.0.1:5000/obstacle")
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .build();
          Response response = client.newCall(request).execute();
        }
        catch(Exception ex) {
          System.out.println("There was an error.");
        }
    }
    public void deleteObstacle(int index) {
        try {
          OkHttpClient client = new OkHttpClient().newBuilder()
          .build();
          MediaType mediaType = MediaType.parse("application/json");
          RequestBody body = RequestBody.create(mediaType, "" + index);
          Request request = new Request.Builder()
            .url("http://127.0.0.1:5000/obstacle")
            .method("DELETE", body)
            .addHeader("Content-Type", "application/json")
            .build();
          Response response = client.newCall(request).execute();
        }
        catch(Exception ex) {
          System.out.println("There was an error.");
        }
    }
    public ArrayList<Obstacle> getObstacles() {
        ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
        try {
          OkHttpClient client = new OkHttpClient().newBuilder()
          .build();
          MediaType mediaType = MediaType.parse("text/plain");
          RequestBody body = null; //RequestBody.create(mediaType, "");
          Request request = new Request.Builder()
            .url("http://127.0.0.1:5000/obstacles")
            .method("GET", body)
            .build();
          Response response = client.newCall(request).execute();
          String json = response.body().string();
          ObjectMapper mapper = new ObjectMapper();
          JsonNode jsonNode = mapper.readTree(json);
          
          if (jsonNode.isArray()) {
            for (int i = 0; i <jsonNode.size(); i++){
              JsonNode obstacle = jsonNode.get(i);
              float obsX = obstacle.get(0).floatValue();
              float obsY = obstacle.get(1).floatValue();
              Obstacle obs = new Obstacle((1.0 * obsX),(1.0 * obsY), 0.075);
              obstacles.add(obs);
            }
          }
        }
        catch(Exception ex) {
          System.out.println("There was an error.");
        }
        return obstacles;
    }
}
  
