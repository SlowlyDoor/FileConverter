package ru.vyatsu.service.structure;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "mangalib")
public class MangalibXML {
    private List<ManhwaXML> manhwa;

    @XmlElementWrapper(name = "manhwa")
    @XmlElement(name = "manhwa")
    public List<ManhwaXML> getManhwa() {
        return manhwa;
    }

    public void setManhwa(List<ManhwaXML> manhwa) {
        this.manhwa = manhwa;
    }
}

