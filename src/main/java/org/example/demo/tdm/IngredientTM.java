package org.example.demo.tdm;

import lombok.*;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public class IngredientTM {
        private String ingredient_id;
        private String name;
        private String description;
        private String unit_of_measurein_gram_or_miligram;

    }
