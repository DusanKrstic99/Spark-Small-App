package models;

public class Predmet {
    public int id;
    public int ocena;
    public int semestar;
    public int espb;

    public Predmet(int id, int ocena, int semestar, int espb) {
        this.id = id;
        this.ocena = ocena;
        this.semestar = semestar;
        this.espb = espb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public int getSemestar() {
        return semestar;
    }

    public void setSemestar(int semestar) {
        this.semestar = semestar;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }
}
