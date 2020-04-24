package main;

import com.google.gson.Gson;
import models.Data;
import models.Person;
import models.Predmet;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mozilla.javascript.Context.exit;
import static spark.Spark.*;

public class Launcher {
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(5000);
        String path="Projekat.json";
        ArrayList<Person> person = Data.readFromJson(path);
        HashMap<String,Object> polja=new HashMap<>();

        get("/",(request, response) -> {
            HashMap<String,Object> mapa=new HashMap<>();
            mapa.put("ja","Projekat iz predmeta Internet Programiranje");
            return new ModelAndView(mapa,"index.hbs");
        },new HandlebarsTemplateEngine());


        get("/osobe",(request, response) -> {
            polja.put("osobe",person);
            return new ModelAndView(polja,"index.hbs");
        },new HandlebarsTemplateEngine());
        post("/izmeni2",(request, response) -> {
            response.type("application/json");
            String min1 = request.queryParams("min");
            ArrayList<Person> filtrirano=new ArrayList<>();
                for (Person p : person) {
                    if (p.getIme().equals(min1)) {
                        filtrirano.add(p);
                    }
                }
            Gson gson=new Gson();
            return gson.toJson(filtrirano);
        });


        post("/filter",(request, response) -> {
            response.type("application/json");
            String min1 = request.queryParams("min");
            String max1 = request.queryParams("max");
            ArrayList<Person> filtrirano=new ArrayList<>();
            if(min1.equals("")|| max1.equals("")){
            }
            else {
                int min = Integer.parseInt(request.queryParams("min"));
                int max = Integer.parseInt(request.queryParams("max"));
                if (min > max) {
                    int pom;
                    pom = min;
                    min = max;
                    max = pom;
                }

                for (Person p : person) {
                    ArrayList<Predmet> pom = p.getPredmeti();
                    ArrayList<Predmet> predmeti = new ArrayList<>();
                    for (Predmet predmet : pom) {
                        int OcenaZaProveru = predmet.getOcena();
                        if (OcenaZaProveru >= min && OcenaZaProveru <= max) {
                            predmeti.add(predmet);
                        }
                    }
                    if (predmeti.size() > 0) {
                        Person Pomoc = new Person(p.ime, p.prezime, p.gender, predmeti);
                        filtrirano.add(Pomoc);
                    }
                }
            }
            Gson gson=new Gson();
            return gson.toJson(filtrirano);
        });

        get("/dodaj",(request, response) -> {
            polja.put("obavestenje",null);
            return new ModelAndView(polja,"dodaj.hbs");
            },new HandlebarsTemplateEngine());
        post("/Kreiraj",(request, response) -> {
            String ime=request.queryParams("ime");
        String prezime=request.queryParams("prezime");
        String gender=request.queryParams("gender");
        Person p = new Person(ime,prezime,gender,null);
        person.add(p);
        polja.put("obavestenje","Uspesno ste dodali studenta");
       return new ModelAndView(polja,"dodaj.hbs");
         },new HandlebarsTemplateEngine());

        get("/izmeni/:ime",(request, response) -> {
            String id= request.params("ime");
            for (Person s:person) {
                if (s.getIme().equals(id)){
                    polja.put("student",s);
                }
            }
            polja.put("izmena",null);
            polja.put("potvrda",null);
            polja.put("poziv","Poziv");
            return new ModelAndView(polja,"update.hbs");
        },new HandlebarsTemplateEngine());

        post("/snimiIzmene",(request, response) -> {
            String ime=request.queryParams("ime");
            String prezime=request.queryParams("prezime");
            String gender=request.queryParams("gender");
            for (Person s:person) {
                if (s.getIme().trim().equals(ime.trim())) {
                    s.setPrezime(prezime);
                    s.setGender(gender);
                }
            }
            polja.put("izmena",null);
            polja.put("potvrda",null);
            polja.put("poruka","Uspesno ste izmenili podatke");
            return new ModelAndView(polja,"update.hbs");
        },new HandlebarsTemplateEngine());

        get("/DodajPredmet/:ime",(request, response) -> {
            String id= request.params("ime");
            for (Person s:person) {
                if (s.getIme().equals(id)){
                    polja.put("student",s);
                }
            }
            polja.put("poziv",null);
            polja.put("poruka",null);
            polja.put("izmena","Izmena");
            return new ModelAndView(polja,"update.hbs");
        },new HandlebarsTemplateEngine());

        post("/snimiPredmet",(request, response) -> {
            String ime=request.queryParams("ime");
            int ocena=Integer.parseInt(request.queryParams("ocena"));
            int semestar=Integer.parseInt(request.queryParams("semestar"));
            int espb=Integer.parseInt(request.queryParams("espb"));
            for (Person s:person) {
                if (s.getIme().trim().equals(ime.trim())) {
                    ArrayList<Predmet> lista = s.getPredmeti();
                    Predmet novi = new Predmet(1,1,1,1);
                    int id;
                    if(lista==null){
                        ArrayList<Predmet> novalista = new ArrayList<>();
                        id = 0;
                        id++;
                        novi.setOcena(ocena);
                        novi.setId(id);
                        novi.setSemestar(semestar);
                        novi.setEspb(espb);
                        novalista.add(novi);
                        s.setPredmeti(novalista);
                    }
                    else {
                        id = lista.size();
                        id++;
                        novi.setOcena(ocena);
                        novi.setId(id);
                        novi.setSemestar(semestar);
                        novi.setEspb(espb);
                        lista.add(novi);
                        s.setPredmeti(lista);
                    }
                }
            }
            polja.put("poziv",null);
            polja.put("poruka",null);
            polja.put("potvrda","Uspesno ste dodali polozeni predmet predmet");
            return new ModelAndView(polja,"update.hbs");
        },new HandlebarsTemplateEngine());
    }
}
