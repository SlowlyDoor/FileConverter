package ru.vyatsu.service.structure;

import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlType(propOrder = {"name", "publicationProjects", "adminName"})
public class TeamTranslation {
    private String name;
    private Integer publicationProjects;
    private String adminName;

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public Integer getPublicationProjects() {
        return publicationProjects;
    }

    public void setPublicationProjects(Integer publicationProjects) {
        this.publicationProjects = publicationProjects;
    }

    @XmlElement
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}
