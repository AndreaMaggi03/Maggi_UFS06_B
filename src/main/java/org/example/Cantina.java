package org.example;

import com.google.gson.Gson;

import java.util.*;

public class Cantina {
    private static Cantina INSTANCE;
    List<Vini> ListaVini = new ArrayList<>();
    private static final Gson gson = new Gson();

    private Cantina() {
        ListaVini.add(new Vini(13,"Dom Perignon Vintage Moet & Chandon 2008",225.94, "white"));
        ListaVini.add(new Vini(14,"Pignoli Radikon Radikon 2009",133.0, "red"));
        ListaVini.add(new Vini(124,"Pinot Nero Elena Walch Elena Walch 2018",43.0,"red"));
    }

    public static Cantina getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Cantina();
        }

        return INSTANCE;
    }

    public void add(Vini vino) {
        ListaVini.add(vino);
    }

    private String toJSON() {
        return gson.toJson( ListaVini);
    }

    private List<String> getViniBianchi() {
        List<String> jsonVini = new ArrayList<>();

        for (Vini vino : ListaVini) {
            if (vino.getTipo().equals("white")) {
                String jsonVino = gson.toJson(vino);
                jsonVini.add(jsonVino);
            }
        }

        return jsonVini;
    }


    String garageActions(String command) {
        return switch (command.toLowerCase()) {
            case "red" -> toJSON();
            case "white" -> getViniBianchi();
            case "sorted_by_name" -> allSorted();
            case "sorted_by_price" ->
            default -> "Comando Errato";
        };
    }
}
