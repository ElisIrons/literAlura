package br.com.alura.literAlura.model;

import br.com.alura.literAlura.dto.LivroDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Autor autor;
    private String idiomas;
    private Integer numero_downloads;

    public Livro() {}

    public Livro(LivroDTO livroDTO) {
        this.titulo = livroDTO.titulo();
        if (!livroDTO.autores().isEmpty()) {
            this.autor = new Autor(livroDTO.autores().get(0));
        }
        if (!livroDTO.idioma().isEmpty()) {
            this.idiomas = livroDTO.idioma().get(0);
        }
        this.numero_downloads = livroDTO.numeroDownloads();
    }


    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public Integer getNumeroDownloads() {
        return numero_downloads;
    }

    @Override
    public String toString() {
        return "********** Livro **********" +
                "\nTítulo: " + titulo +
                "\nAutor: " + (autor != null ? autor.getNome() : "Desconhecido") +
                "\nIdioma: " + idiomas +
                "\nNumero de Downloads: " + numero_downloads;
    }
}
