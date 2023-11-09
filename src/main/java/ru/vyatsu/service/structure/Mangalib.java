package ru.vyatsu.service.structure;

import ru.vyatsu.service.structure.Manhwa;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Mangalib {
    private List<Manhwa> mangalib;

    public void setMangalib(List<Manhwa> mangalib) {this.mangalib = mangalib;}
    @XmlElement(name = "manhwa")
    public List<Manhwa> getMangalib() {return this.mangalib;}
}