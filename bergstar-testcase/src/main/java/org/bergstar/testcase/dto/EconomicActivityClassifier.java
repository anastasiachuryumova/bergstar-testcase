package org.bergstar.testcase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EconomicActivityClassifier {
    @JsonProperty("code") private String code;

    @JsonProperty("name") private String name;

    @JsonProperty("items")
    List<Item> items;

    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Item {
        @JsonProperty("code") private String code;

        @JsonProperty("name") private String name;

        @JsonProperty("items")
        List<Item1> items;

        @NoArgsConstructor
        @Getter
        @Setter
        @ToString
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public static class Item1 {
            @JsonProperty("code") private String code;

            @JsonProperty("name") private String name;

            @JsonProperty("items")
            List<Item2> items;

            @NoArgsConstructor
            @Getter
            @Setter
            @ToString
            @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
            public static class Item2 {
                @JsonProperty("code") private String code;

                @JsonProperty("name") private String name;

                @JsonProperty("items")
                List<Item3> items;

                @NoArgsConstructor
                @Getter
                @Setter
                @ToString
                @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
                public static class Item3 {
                    @JsonProperty("code") private String code;

                    @JsonProperty("name") private String name;

                    @JsonProperty("items")
                    List<Item4> items;
                    @NoArgsConstructor
                    @Getter
                    @Setter
                    @ToString
                    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
                    public static class Item4 {
                        @JsonProperty("code") private String code;

                        @JsonProperty("name") private String name;
                    }
                }
            }
        }
    }
}
