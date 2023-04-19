package br.com.ifms.microservices.model.Enum;

public enum Station {

    NETFLIX("Netflix"),
    AMAZON_PRIME_VIDEO("Amazon Prime Video"),
    HBO_MAX("HBO Max"),
    APPLE_TV("Apple TV"),
    DISNEY_PLUS("Disney+"),
    STAR_PLUS("Star+"),
    PARAMONT_PLUS("Paramont+"),
    MOVIE_THEATER("Movie Theater");

    public final String value;

    private Station(String value) {
        this.value = value;
    }    
}
