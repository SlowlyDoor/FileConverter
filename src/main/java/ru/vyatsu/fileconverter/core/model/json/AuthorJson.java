package ru.vyatsu.fileconverter.core.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorJson {
    private String name;
    private List<ManhwaJson> manhws;
}
