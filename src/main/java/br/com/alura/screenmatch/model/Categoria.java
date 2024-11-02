package br.com.alura.screenmatch.model;

public enum Categoria {
    ACAO("Action"),
    AVENTURA("Adventure"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    FANTASIA("Fantasy"),
    FICCAO("Sci-Fi"),
    MISTERIO("Mystery"),
    SUSPENSE("Thriller"),
    TERROR("Horror");

    private String categoriaOmdb;

    Categoria (String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Categoria fromString (String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria não encontrada");
    }


}
