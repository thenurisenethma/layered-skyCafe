package org.example.demo.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ingredient {
    private String ingredient_id;
    private String name;
    private String description;
    private String unit_g_ml;

}
