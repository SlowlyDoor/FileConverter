package ru.vyatsu.fileconverter.core.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlType;

@Data
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