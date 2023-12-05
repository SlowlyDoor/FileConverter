package ru.vyatsu.fileconverter.core.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlType(propOrder = {"name", "publicationProjects", "adminName"})
public class TeamTranslation {
    @JacksonXmlProperty(isAttribute = true)
    private String name;
    private Integer publicationProjects;
    private String adminName;
}