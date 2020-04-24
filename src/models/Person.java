package models;

import java.util.ArrayList;

public class Person {
    public String ime;
    public String prezime;
    public String gender;
    public ArrayList<Predmet> predmeti;

    public Person(String ime, String prezime, String gender, ArrayList<Predmet> predmeti) {
        this.ime = ime;
        this.prezime = prezime;
        this.gender = gender;
        this.predmeti = predmeti;
    }


    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<Predmet> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(ArrayList<Predmet> predmeti) {
        this.predmeti = predmeti;
    }
}
