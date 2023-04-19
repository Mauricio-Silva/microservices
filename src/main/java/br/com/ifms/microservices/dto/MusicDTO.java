package br.com.ifms.microservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicDTO {
    
    private long id;

    private String name;

    private Double duration;
}
