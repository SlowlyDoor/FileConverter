package ru.vyatsu.service.structure;

import lombok.Data;
import ru.vyatsu.service.structure.TeamTranslation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Data
public class ManhwaXML {
    private String id;
    private String title;
    private String author;
    private Integer publicationYear;
    private Integer chapters;
    private TeamTranslation translationTeam;

    @XmlAttribute(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @XmlElement
    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    @XmlElement
    public Integer getChapters() {
        return chapters;
    }

    public void setChapters(Integer chapters) {
        this.chapters = chapters;
    }

    @XmlElement
    public TeamTranslation getTranslationTeam() {
        return translationTeam;
    }

    public void setTranslationTeam(TeamTranslation translationTeam) {
        this.translationTeam = translationTeam;
    }
}

