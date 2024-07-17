package pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Resource {
    private int id;
    private String name;
    private int year;
    private String color;
    private String pantoneValue;

    @JsonCreator
    public Resource(@JsonProperty("id") int id,
                    @JsonProperty("name") String name,
                    @JsonProperty("year") int year,
                    @JsonProperty("color") String color,
                    @JsonProperty("pantone_value") String pantoneValue) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.color = color;
        this.pantoneValue = pantoneValue;
    }
}

