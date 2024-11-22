package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=43e78112";
    private List<DadosSerie> dadosSeries = new ArrayList<>();
    private SerieRepository serieRepository;
    private List<Serie> series = new ArrayList<>();

    public Principal(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                1 - Buscar séries
                2 - Buscar episódios
                3 - Lista series buscadas
                
                0 - Sair
                """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarSerieWeb() {
        DadosSerie dadosSerie = getDadosSerieFromApi();
        if (dadosSerie != null) {
            Serie serie = new Serie(dadosSerie);
            serieRepository.save(serie);
        }

        System.out.println(dadosSerie);
    }

    private void listarSeriesBuscadas() {
        // Anteriormente estávamos instanciando um ArrayList vazio
        // Mas agora utilizando o repositorio + o banco de dados, vamos consumir do que já existe
        series = serieRepository.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                    .forEach(System.out::println);
    }

    private void listarSeriesBuscadasOne() {
        List<Serie> series = new ArrayList<>();

        series = dadosSeries.stream()
                .map(d -> new Serie(d))
                .collect(Collectors.toList());

        series.stream()
            .sorted(Comparator.comparing(Serie::getGenero))
            .forEach(System.out::println);
    }

    private void listarSeriesBuscadasTwo() {
        List<Serie> series = new ArrayList<>();

        series = dadosSeries.stream()
            .map(Serie::new)
            .toList();

        series.stream()
            .sorted(Comparator.comparing(Serie::getGenero))
            .forEach(System.out::println);
    }

    private Optional<Serie> getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        String nomeSerie = leitura.nextLine();

      return series.stream()
          .filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase()))
          .findFirst();
    }

    private DadosSerie getDadosSerieFromApi() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
      return conversor.obterDados(json, DadosSerie.class);
    }

    private void buscarEpisodioPorSerie(){
        listarSeriesBuscadas();

        Optional<Serie> serie = getDadosSerie();
        if (serie.isPresent()) {
            Serie dadosSerie = serie.get();
            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= dadosSerie.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO +
                    dadosSerie.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                .flatMap(d -> d.episodios().stream()
                    .map(e -> new Episodio(d.numero(), e)))
                .toList();

            dadosSerie.setEpisodios(episodios);
            serieRepository.save(dadosSerie);
        } else {
            System.out.println("Série não encontrada");
        }
    }
}
