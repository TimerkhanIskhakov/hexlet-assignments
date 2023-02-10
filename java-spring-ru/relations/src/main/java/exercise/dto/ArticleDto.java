package exercise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDto {

    String name;
    String body;

    CategoryDto category;
}
