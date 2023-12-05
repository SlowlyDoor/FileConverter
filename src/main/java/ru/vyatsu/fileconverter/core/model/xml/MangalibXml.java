package ru.vyatsu.fileconverter.core.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.util.List;

@Getter
@Setter
@JacksonXmlRootElement(localName = "mangalib")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MangalibXml {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "manhwa")
    private List<Manhwa> mangalib;
}