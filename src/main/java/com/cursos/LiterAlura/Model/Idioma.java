package com.cursos.LiterAlura.Model;

public enum Idioma
{
    ESPAÑOL("es","español"),
    INGLES ("en","ingles"),
    FRANCES("fr", "frances"),
    PORTUGUES("pt","portugues"),
    LATIN("la", "latin"),
    ALEMAN("de", "aleman"),
    ITALIANO("it", "italiano");

    private String idiomaApi;
    private String idiomaLiter;

    Idioma(String idiomaApi, String idiomaLiter) {
        this.idiomaApi = idiomaApi;
        this.idiomaLiter = idiomaLiter;
    }
    public static Idioma fromString(String text) {
        for (Idioma idioma: Idioma.values()) {
            if (idioma.idiomaLiter.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ninguna lenguaje encontrado: " + text);
    }
    public static Idioma fromTotalString(String text) {
        for (Idioma idioma: Idioma.values()) {
            if (idioma.idiomaLiter.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ninguna lenguaje encontrado: " + text);
    }
}
