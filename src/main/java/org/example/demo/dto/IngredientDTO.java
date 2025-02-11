package org.example.demo.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IngredientDTO {
    private String ingredient_id;
    private String name;
    private String description;
    private String unit_g_ml;

}
