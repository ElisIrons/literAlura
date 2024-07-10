package br.com.alura.literAlura.service;

import br.com.alura.literAlura.model.Livro;
import br.com.alura.literAlura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;

    public Livro buscarLivroPorTitulo(String title) {
        Livro livro = repository.findByTitulo(title);
        if (livro != null) {
            repository.save(livro);
            System.out.println("Livro salvo: " + livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
        return livro;
    }

    public List<Livro> getLivros() {
        List<Livro> books = repository.findAll();
        books.forEach(System.out::println);
        return books;
    }

    public List<Livro> getPorIdioma(String idioma) {
        List<Livro> livros = repository.findByIdiomasContaining(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para este idioma");
        } else {
            livros.forEach(System.out::println);
        }
        return livros;
    }
}
