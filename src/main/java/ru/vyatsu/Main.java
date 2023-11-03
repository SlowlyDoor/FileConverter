package ru.vyatsu;


import ru.vyatsu.service.converters.DesirealizeXMLtoClass;
import ru.vyatsu.service.structure.MangalibXML;

public class Main
{
    public static void main(String[] args)
    {
        DesirealizeXMLtoClass test = new DesirealizeXMLtoClass();

        MangalibXML ma = test.deserialize("src\\main\\resources\\data.xml");
        System.out.println(ma.getManhwa());
    }
}