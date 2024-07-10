package br.com.alura.literAlura.controller;


import br.com.alura.literAlura.model.Livro;
import br.com.alura.literAlura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> listarLivros() {
        return livroService.getLivros();
    }

    @GetMapping("/titulo/{titulo}")
    public Livro buscarLivroPorTitulo(@PathVariable String titulo) {
        return livroService.buscarLivroPorTitulo(titulo);
    }

    @GetMapping("/idioma/{idioma}")
    public List<Livro> listarLivrosPorIdioma(@PathVariable String idioma) {
        return livroService.getPorIdioma(idioma);
    }

}
