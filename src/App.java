import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    private static String body;
    public static void main(String[] args) throws Exception {
       
        //  1-  fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbtop250moviesdata.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String>  response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        
        //    2-  parsear apenas os dados mais relevantes( titulo, poster, classificacao)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //    3-  exibir e manipular os dados

        for (int i = 0; i < 3; i++) {
            Map<String, String> filme = listaDeFilmes.get(i);
                       
            System.out.println(i+1 + "º "); 
            System.out.println("Título do filme: " + filme.get("title"));
            System.out.println("Poster: " + filme.get("image"));
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int stars = (int) classificacao;
            for ( int n = 1; n <= stars; n++){
                System.out.println("⭐");
            }
            System.out.println("Nota: " + filme.get("imDbRating") + " /10");
            System.out.println();
        }

    }
}
