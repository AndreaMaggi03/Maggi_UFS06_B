package org.example;

import com.google.gson.Gson;

import java.util.*;

public class Cantina {
    private static Cantina INSTANCE;
    List<Vini> listaVini = new ArrayList<>();
    private static final Gson gson = new Gson();

    private Cantina() {
        listaVini.add(new Vini(13,"Dom Perignon Vintage Moet e Chandon 2008",300.0, "white"));
        listaVini.add(new Vini(19,"Pignoli Radikon Radikon 2009",45.0, "red"));
        listaVini.add(new Vini(172,"Pinot Nero Elena Walch Elena Walch 2018",200.0,"red"));
    }
    public static Cantina getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Cantina();
        }

        return INSTANCE;
    }
    public void add(Vini vini) {
        listaVini.add(vini);
    }

    private String toJSON() {
        return gson.toJson(listaVini);
    }

    private String sorted_by_price() {
        String jsonVino = "";
        List<Vini> sortedList = new ArrayList<>(listaVini);

        sortedList.sort(Comparator.comparingDouble(Vini::getPrezzo).reversed());

        if (!sortedList.isEmpty()) {
            Vini vino = sortedList.get(0);
            double maxPrice = vino.getPrezzo();
            jsonVino = gson.toJson(vino);

            for (int i = 1; i < sortedList.size(); i++) {
                vino = sortedList.get(i);
                jsonVino += gson.toJson(vino);
            }
        }
        return jsonVino;
    }
    private String SortedViniRossi() {
        List<Vini> filteredList = new ArrayList<>();

        for (Vini vino : listaVini) {
            if (vino != null && vino.getTipo() != null && vino.getTipo().equalsIgnoreCase("red")) {
                filteredList.add(vino);
            }
        }
        return gson.toJson(filteredList);
    }
    private String SortedViniBianchi() {
        List<Vini> filteredList = new ArrayList<>();

        for (Vini vino : listaVini) {
            if (vino != null && vino.getTipo() != null && vino.getTipo().equalsIgnoreCase("white")) {
                filteredList.add(vino);
            }
        }
        return gson.toJson(filteredList);
    }
    private String sorted_by_name() {
        String jsonVino = "";
        List<Vini> sortedList = new ArrayList<>(listaVini);

        sortedList.sort(Comparator.comparing(Vini::getNome));

        if (!sortedList.isEmpty()) {
            Vini vino = sortedList.get(0);
            jsonVino = gson.toJson(vino);

            for (int i = 1; i < sortedList.size(); i++) {
                vino = sortedList.get(i);
                jsonVino += gson.toJson(vino);
            }
        }
        return jsonVino;
    }
    String cantinaActions(String command) {
        return switch (command.toLowerCase()) {
            case "all" -> toJSON();
            case "sorted_by_price" -> sorted_by_price();
            case "red" -> SortedViniRossi();
            case "white" -> SortedViniBianchi();
            case "sorted_by_name" -> sorted_by_name();
            default -> "Comando Errato, prova ad inserire un comando tra: all, sorted_by_price, sorted_by_name, red, white";
        };
    }
}
