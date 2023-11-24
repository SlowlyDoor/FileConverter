package ru.vyatsu.service.structure.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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