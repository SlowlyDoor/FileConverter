package ru.vyatsu.fileconverter.core.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlType(propOrder = {"id", "title", "author", "publicationYear", "chapters", "translationTeam"})
public class Manhwa {
    @JacksonXmlProperty(isAttribute = true)
    private String id;
    private String title;
    private String author;
    private Integer publicationYear;
    private Integer chapters;
    private TeamTranslation translationTeam;
}
