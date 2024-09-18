package uz.urinov.fastfood.kitchen.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KitchenCreateDto {

    @NotBlank(message = "Name bo'sh bo'lishi mumkin emas")
    @Size(min = 3, max = 50, message = "Berilgan Stadium (Name) ning uzunligi 3 va 50 orasida bo'lishi kerak")
    private String name;

    @NotNull(message = "Lat bo'sh bo'lishi mumkin emas")
    private Double lat;

    @NotNull(message = "Lon bo'sh bo'lishi mumkin emas")
    private Double lon;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @NotNull(message = " District id bo'sh bo'lishi mumkin emas")
    @Min(value = 1, message = "District id ning qiymati minimal 1 bo'lsin")
    private Integer districtId;

    @NotNull
    private List<String> photoList;

}
