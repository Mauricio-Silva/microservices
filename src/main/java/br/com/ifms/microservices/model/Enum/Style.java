package br.com.ifms.microservices.model.Enum;

public enum Style {

    MANGA("Manga"),
    NOVEL("Novel"),
    WEBTOON("Webtoon");

    public final String value;

    private Style(String value) {
        this.value = value;
    }    
}
