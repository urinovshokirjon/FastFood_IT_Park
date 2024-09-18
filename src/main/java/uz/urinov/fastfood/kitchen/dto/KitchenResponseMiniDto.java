package uz.urinov.fastfood.kitchen.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KitchenResponseMiniDto {

    private Integer id;

    private String name;

    private Double lat;

    private Double lon;

}
