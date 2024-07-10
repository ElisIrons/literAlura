package br.com.alura.literAlura.principal;

import br.com.alura.literAlura.dto.LivroDTO;
import br.com.alura.literAlura.dto.ResultsDTO;
import br.com.alura.literAlura.model.Autor;
import br.com.alura.literAlura.model.Livro;
import br.com.alura.literAlura.repository.AutorRepository;
import br.com.alura.literAlura.repository.LivroRepository;
import br.com.alura.literAlura.service.ConsumoAPI;
import br.com.alura.literAlura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;
    private List<Autor> autoresList = new ArrayList<>();
    private List<Livro> livroList = new ArrayList<>();

    public Menu(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    public void exibeMenu() {
        int opcao = -1;
        while (opcao != 0) {
            var menu = """
                    ***** Opções *****
                    1 - Buscar livros pelo título
                    2 - Lista de livros registrados
                    3 - Lista de autores registrados
                    4 - Lista de autores registrados vivos em determinado ano
                    5 - Lista de livros por idioma
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivrosPorTitulo();
                    break;
                case 2:
                    listaDeLivrosRegistrados();
                    break;
                case 3:
                    listaDeAutoresRegistrados();
                    break;
                case 4:
                    listaDeAutoresVivosEmDeterminadoAno();
                    break;
                case 5:
                    listaLivrosEmDeterminadoIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
        leitura.close();
    }

    private void buscarLivrosPorTitulo() {
        ResultsDTO dadosLivros = getDadosLivro();

        if (dadosLivros != null && dadosLivros.livros() != null && !dadosLivros.livros().isEmpty()) {
            for (LivroDTO livroBuscado : dadosLivros.livros()) {
                Livro livro = new Livro(livroBuscado);
                System.out.println(livro);
                livroRepository.save(livro);
            }
        } else {
            System.out.println("Nenhum livro encontrado.");
        }
    }

    private void listaDeLivrosRegistrados() {
        livroList = livroRepository.findAll();

        if (livroList.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
        } else {
            livroList.forEach(System.out::println);
        }
    }

    private void listaDeAutoresRegistrados() {
        autoresList = autorRepository.findAll();

        if (autoresList.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            autoresList.forEach(System.out::println);
        }
    }

    private void listaDeAutoresVivosEmDeterminadoAno() {
        System.out.println("Insira o ano que deseja pesquisar:");
        Integer ano = leitura.nextInt();
        autoresList = autorRepository.autoresVivosNaqueleAno(ano);

        if (autoresList.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            autoresList.forEach(System.out::println);
        }
    }

    private void listaLivrosEmDeterminadoIdioma() {
        System.out.println("""
                Digite o idioma que deseja:
                en - Inglês
                es - Espanhol
                fr - Francês
                it - Italiano
                pt - Português
                """);

        String idiomaEscolhido = leitura.nextLine();
        livroList = livroRepository.findByIdiomasContaining(idiomaEscolhido);

        if (livroList.isEmpty()) {
            System.out.println("Nenhum livro encontrado para este idioma.");
        } else {
            livroList.forEach(System.out::println);
        }
    }

    private ResultsDTO getDadosLivro() {
        System.out.println("Digite o nome do livro que deseja buscar: ");
        String nomeLivro = leitura.nextLine();
        String json = consumoApi.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));
        return converteDados.obterDados(json, ResultsDTO.class);
    }
}
