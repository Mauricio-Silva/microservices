package br.com.ifms.microservices.dto;

import br.com.ifms.microservices.model.Enum.Station;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeriesDTO {
    
    private long id;

    private String name;

    private String releaseYear;

    private Station station;    

    private Integer episodes;

    private Integer seasons;
}
