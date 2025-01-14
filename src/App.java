import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.io.InputStream;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar op top 250 filmes do IMDB
        String url = "https://imdb-api.com/en/API/Top250Movies/k_uu11hhri";
        URI endereco = URI.create(url); 
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (título, poster e classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImagem = filme.get("imagem");
            String titulo = filme.get("title");

            InputStream inputStream = new URL("urlImagem").openStream();
            String nomeArquivo = titulo + "png";

            var geradora = new GeradoraDeFigurinhas();
            geradora.cria(inputStream, nomeArquivo);
            
            System.out.println(filme.get("title"));
            System.out.println();
            
        }

    }

}
