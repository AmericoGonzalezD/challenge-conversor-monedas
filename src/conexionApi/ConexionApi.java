package conexionApi;

import modelos.DivisasApi;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionApi {

    public String respuetaDatos(String divisaSeleccionada)throws IOException, InterruptedException{
        String direccion="https://v6.exchangerate-api.com/v6/ac7472d3d9f7ec0972d99dea/latest/"+divisaSeleccionada.toUpperCase();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        HttpResponse<String> response= client.
                send(request,HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
