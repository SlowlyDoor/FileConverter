package ru.vyatsu.fileconverter.core.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "mangalib")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mangalib {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "manhwa")
    private List<Manhwa> mangalib;

}