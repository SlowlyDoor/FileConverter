package ru.vyatsu.fileconverter.core.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManhwaJson {
    private String title;
    private Integer publicationYear;
    private Integer chapters;
    private TeamTranslationJson translationTeam;
}
