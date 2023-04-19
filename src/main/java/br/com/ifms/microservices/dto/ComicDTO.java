package br.com.ifms.microservices.dto;

import br.com.ifms.microservices.model.Enum.Style;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComicDTO {
    
    private long id;

    private String name;

    private Style style;

    private String releaseYear;

    private Integer chapters;

    private Integer volumes; 
}
