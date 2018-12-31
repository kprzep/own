/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.kp.util2;

/**
 * Klasa typu POJO na obiekty typu Samochod, ktore bedą wczytywane z pliku XML
 *
 * @author kprzep
 */
public class Samochod {

    private Integer idSamochodu;
    private String marka;
    private String model;
    private Double pojemnosc;
    private String numerRejestracyjny;

    public Samochod() {
    }

    public Samochod(Integer idSamochodu, String marka, String model, Double pojemnosc, String numerRejestracyjny) {
        this.idSamochodu = idSamochodu;
        this.marka = marka;
        this.model = model;
        this.pojemnosc = pojemnosc;
        this.numerRejestracyjny = numerRejestracyjny;
    }

    public Integer getIdSamochodu() {
        return idSamochodu;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public Double getPojemnosc() {
        return pojemnosc;
    }

    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    public void setIdSamochodu(Integer idSamochodu) {
        this.idSamochodu = idSamochodu;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPojemnosc(Double pojemnosc) {
        this.pojemnosc = pojemnosc;
    }

    public void setNumerRejestracyjny(String numerRejestracyjny) {
        this.numerRejestracyjny = numerRejestracyjny;
    }

    @Override
    public String toString() {
        return "Samochody{" + "idSamochodu=" + idSamochodu + ", marka=" + marka + ", model=" + model + ", pojemnosc=" + pojemnosc + ", numerRejestracyjny=" + numerRejestracyjny + '}';
    }

    
    
    

}
