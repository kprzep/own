/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.kp.util2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Klasa na utilsy zwiazane z xml
 *
 * @author kprzep
 */
public class XUtils {

    /**
     * Metoda diagnostyczna na wczytanie pliku XML
     */
    public static void readXmlStaxDiag(String plik) {
        //tworzymy instancje parsera
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            //tworzymy event readera
            XMLEventReader r = factory.createXMLEventReader(plik, new FileInputStream(plik));
            //twrozymy iterator
            while (r.hasNext()) {
                XMLEvent e = r.nextEvent();
                System.out.println(e.toString());
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            Logger.getLogger(XUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda na wczytanie pliku XML z uzyciem parsera STAX kotry jest parserem
     * strumieniowym
     *
     * @param plik
     * @return
     */
    public static List<Samochod> readXmlStax(String plik) {
        //bedziemy przetwarzac obiekty klasy Samochod
        Samochod samochod = null;
        //potrzebujemy listy na obiekty typu samochod
        List<Samochod> auta = new ArrayList<>();
        //potrzebujemy tez instancje parsera z fabryki 
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            InputStream inputStream;
            XMLEventReader xmlEventReader;
            //mamy strumien wejsciowy
            inputStream = new FileInputStream(plik);
            //oraz xml event reader
            xmlEventReader = xmlInputFactory.createXMLEventReader(inputStream);
            while (xmlEventReader.hasNext()) {
                //gdy pojawi sie xml event reader to wczytuje xml event
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                //teraz moge juz sprawdzac jakie to zdarzenie xml
                if (xmlEvent.isStartElement()) {
                    //jesli to startElement to 
                    StartElement startElement = xmlEvent.asStartElement();
                    //jesli to start element samochod
                    if (startElement.getName().getLocalPart().equals("samochod")) {
                        samochod = new Samochod();
                        //poniewaz ten tag ma atrybuty to musze je odczytac
                        //wiec najpier je przepisuje 
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            if (attribute.getName().toString().equals("idSamochodu")) {
                                //przypisz id do obiektu samochod
                                samochod.setIdSamochodu(new Integer(attribute.getValue()));
                            }
                        }

                    }
                    //if(startElement.getName().getLocalPart().equals("marka")) {

                }
            }

        } catch (FileNotFoundException | XMLStreamException ex) {
            Logger.getLogger(XUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return auta;
    }

}
