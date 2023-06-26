package org.example;

import com.google.gson.Gson;

import java.util.*;

public class Cantina {
    private static Cantina INSTANCE;
    List<Vini> listaVini = new ArrayList<>();
    private static final Gson gson = new Gson();

    private Cantina() {
        listaVini.add(new Vini(13,"Dom Perignon Vintage Moet & Chandon 2008",300.0, "white"));
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
            Vini car = sortedList.get(0);
            double maxPrice = car.getPrezzo();
            jsonVino = gson.toJson(car);

            for (int i = 1; i < sortedList.size(); i++) {
                car = sortedList.get(i);
                jsonVino += gson.toJson(car);
            }
        }
        return jsonVino;
    }

    private String filteredSorted() {
        List<Vini> filteredList = new ArrayList<>();

        for (Vini vino : listaVini) {
            if (vino != null && vino.getTipo() != null && vino.getTipo().equals("rosso")) {
                filteredList.add(vino);
            }
        }

        return gson.toJson(filteredList);
    }


    String cantinaActions(String command) {
        return switch (command.toLowerCase()) {
            case "all" -> toJSON();
            case "sorted_by_price" -> sorted_by_price();
             case "all_sorted" -> filteredSorted();
            default -> "Comando Errato";
        };
    }
}
