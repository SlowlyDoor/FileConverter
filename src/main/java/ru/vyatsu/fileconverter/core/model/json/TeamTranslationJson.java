package ru.vyatsu.fileconverter.core.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamTranslationJson {
    private Integer publicationProjects;
    private String adminName;
    private String name;
}