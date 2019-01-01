/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.kp.util2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Klasa na utilsy zwiazane z xml
 *
 * @author kprzep
 */
public class XUtils {

    /**
     * Metoda do zamiany numerow typow zdarzen na zrozumiale stringi opisujace
     * typ zdarzenia.
     *
     * @param eventType
     * @return
     */
    public final static String getEventTypeString(int eventType) {
        switch (eventType) {
            case XMLEvent.START_ELEMENT:
                return "START_ELEMENT";

            case XMLEvent.END_ELEMENT:
                return "END_ELEMENT";

            case XMLEvent.PROCESSING_INSTRUCTION:
                return "PROCESSING_INSTRUCTION";

            case XMLEvent.CHARACTERS:
                return "CHARACTERS";

            case XMLEvent.COMMENT:
                return "COMMENT";

            case XMLEvent.START_DOCUMENT:
                return "START_DOCUMENT";

            case XMLEvent.END_DOCUMENT:
                return "END_DOCUMENT";

            case XMLEvent.ENTITY_REFERENCE:
                return "ENTITY_REFERENCE";

            case XMLEvent.ATTRIBUTE:
                return "ATTRIBUTE";

            case XMLEvent.DTD:
                return "DTD";

            case XMLEvent.CDATA:
                return "CDATA";

            case XMLEvent.SPACE:
                return "SPACE";
        }
        return "UNKNOWN_EVENT_TYPE," + eventType;
    }

    /**
     * Metoda diagnostyczna na wczytanie pliku XML
     *
     * @param filename nazwa pliku xml
     * @throws java.io.FileNotFoundException
     */
    public static void readXmlStaxFileDiag(String filename) throws FileNotFoundException, XMLStreamException {
        //tworzymy instancje parsera do wczytywania XMLi
        XMLInputFactory factory = XMLInputFactory.newInstance();
        //tworzymy event readera
        //aby uzyc filename jako zrodlo XMLa
        XMLEventReader r = null;
        try {
            r = factory.createXMLEventReader(filename, new FileInputStream(filename));
            //tworzymy iterator
            while (r.hasNext()) {
                XMLEvent e = r.nextEvent();
                System.out.println("EVENT TYPE: " + getEventTypeString(e.getEventType()) + ", EVENT: " + e.toString());

            }
        } finally {
            r.close();
        }
    }

    /**
     * Metoda diagnostyczna na wczytanie XML z podanego uri
     *
     * @param uri adres strony z plikiem xml
     * @throws java.net.MalformedURLException
     * @throws javax.xml.stream.XMLStreamException
     */
    public static void readXmlStaxUrlDiag(String uri) throws MalformedURLException, IOException, XMLStreamException {
        //tworzymy instancje parsera do wczytywania XMLi
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader r = null;
        try {
            //tworzymy event readera
            //aby uzyc URL jako zrodlo XMLa gdzie uri = "www.cos.pl/plik.xml";
            URL url = new URL(uri);
            r = factory.createXMLEventReader(uri, url.openStream());
            //tworzymy iterator
            while (r.hasNext()) {
                XMLEvent e = r.nextEvent();
                System.out.println("EVENT TYPE: " + getEventTypeString(e.getEventType()) + ", EVENT: " + e.toString());
            }
        } finally {
            r.close();
        }
    }

    /**
     * Metoda na wczytanie pliku XML z uzyciem parsera STAX
     *
     * @param plik
     * @return
     */
    public static List<Samochod> readXmlStaxFile(String plik) {
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
                    //jesli to startElement to stworzmy ten startElement
                    StartElement startElement = xmlEvent.asStartElement();
                    //jesli to start element samochod
                    if (startElement.getName().getLocalPart().equals("samochod")) {
                        samochod = new Samochod();
                        //ten element ma atrybut
                        //przyklad przypisanie atrybutu po nazwie:
                        //Attribute attribute = startElement.getAttributeByName(QName.valueOf("idSamochodu"));
                        //jak mamy nieokreslona liczbe atrybutów to mozemy zrobic tak
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            if (attribute.getName().toString().equals("idSamochodu")) {
                                //przypisz id do obiektu samochod
                                samochod.setIdSamochodu(new Integer(attribute.getValue()));
                            }
                        }

                    }
                    //teraz mozemy szukac innych startElement w tym elemencie
                    if (xmlEvent.isStartElement()) {
                        //tutaj sprawdzam kolejne startElement
                        if (xmlEvent.asStartElement().getName().getLocalPart().equals("marka")) {
                            //wczytaj kolejny element , to bedzie tekst
                            xmlEvent = xmlEventReader.nextEvent();
                            samochod.setMarka(xmlEvent.asCharacters().getData());
                            continue;
                        }
                    }
                    //kolejne elementy
                    if (xmlEvent.asStartElement().getName().getLocalPart().equals("model")) {
                        //wczytaj kolejny element, to bedzie tekst
                        xmlEvent = xmlEventReader.nextEvent();
                        samochod.setModel(xmlEvent.asCharacters().getData());
                        continue;
                    }
                    if (xmlEvent.asStartElement().getName().getLocalPart().equals("pojemnosc")) {
                        //wczytaj kolejny element, to bedzie tekst
                        xmlEvent = xmlEventReader.nextEvent();
                        samochod.setPojemnosc(Double.valueOf(xmlEvent.asCharacters().getData()));
                        continue;
                    }
                    if (xmlEvent.asStartElement().getName().getLocalPart().equals("numerRejestracyjny")) {
                        //wczytaj kolejny element, to bedzie tekst
                        xmlEvent = xmlEventReader.nextEvent();
                        samochod.setNumerRejestracyjny(xmlEvent.asCharacters().getData());
                        continue;
                    }
                }
                //teraz szukamy endElementow
                if(xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if(endElement.getName().getLocalPart().equals("samochod")) {
                        auta.add(samochod);
                    }
                }

            }

        } catch (FileNotFoundException | XMLStreamException ex) {
            Logger.getLogger(XUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return auta;
    }

}
