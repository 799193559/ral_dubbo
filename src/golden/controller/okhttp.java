package golden.controller;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class okhttp {
  public String postRequest(String url, String json) throws IOException {
    MediaType mediaType = MediaType.get("application/json;charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    RequestBody body = RequestBody.create(mediaType, json);
    Request request = (new Request.Builder()).url(url).post(body).build();
    Response response = client.newCall(request).execute();
    return response.body().string();
  }
}
