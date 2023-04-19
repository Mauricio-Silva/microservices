package br.com.ifms.microservices.model.Enum;

public enum Studio {
    
    UNIVERSAL_PICTURES("Universal Pictures"),
    PARAMOUNT_PICTURES("Paramount Pictures"),
    WALT_DISNEY_PICTURES("Walt Disney Pictures"),
    WARNER_BROS_PICTURES("Warner Bros. Pictures"),
    SONY_PICTURES("Sony Pictures"),
    TWENTY_CENTURY_FOX("20TH Century Fox"),
    LIONS_GATE_ENTERTAINMENT("Lions Gate Entertainment"),
    DREAMWORKS("Dreamworks"),
    METRO_GOLDWYN_MAYER("Metro Goldwyn Mayer");

    public final String value;

    private Studio(String value) {
        this.value = value;
    } 
}
