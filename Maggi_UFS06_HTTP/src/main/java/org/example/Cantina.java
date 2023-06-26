package org.example;

import com.google.gson.Gson;

import java.util.*;

public class Cantina {
    private static Cantina INSTANCE;
    List<Vini> listaVini = new ArrayList<>();
    private static final Gson gson = new Gson();

    private Cantina() {
        listaVini.add(new Vini(13, "Dom Perignon Vintage Moet e Chandon 2008", 300.0, "white"));
        listaVini.add(new Vini(19, "Pignoli Radikon Radikon 2009", 45.0, "red"));
        listaVini.add(new Vini(172, "Pinot Nero Elena Walch Elena Walch 2018", 200.0, "red"));
    }

    public static Cantina getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Cantina();
        }

        return INSTANCE;
    }

    public void add(Vini vino) {
        listaVini.add(vino);
    }

    private String toHTMLTable(List<Vini> listaVini) {
        StringBuilder responseString = new StringBuilder();
        responseString.append("<!DOCTYPE html>")
                .append("<html>")
                .append("<style>")
                .append("table, th, td {")
                .append("  border:1px solid black;")
                .append("}")
                .append("</style>")
                .append("<body>")
                .append("<h2>HTML Response Table</h2>")
                .append("<table style=\"width:100%\">")
                .append("<tr>")
                .append("<th>ID</th>")
                .append("<th>Nome</th>")
                .append("<th>Prezzo</th>")
                .append("<th>Tipo</th>")
                .append("</tr>");

        for (Vini vino : listaVini) {
            responseString.append("<tr>")
                    .append("<td>").append(vino.getId()).append("</td>")
                    .append("<td>").append(vino.getNome()).append("</td>")
                    .append("<td>").append(vino.getPrezzo()).append("</td>")
                    .append("<td>").append(vino.getTipo()).append("</td>")
                    .append("</tr>");
        }

        responseString.append("</table>")
                .append("</body>")
                .append("</html>");

        return responseString.toString();
    }

    private List<Vini> filterByType(String type) {
        List<Vini> filteredList = new ArrayList<>();
        for (Vini vino : listaVini) {
            if (vino.getTipo().equalsIgnoreCase(type)) {
                filteredList.add(vino);
            }
        }
        return filteredList;
    }

    private List<Vini> sortByPriceDescending() {
        List<Vini> sortedList = new ArrayList<>(listaVini);
        sortedList.sort(Comparator.comparingDouble(Vini::getPrezzo).reversed());
        return sortedList;
    }

    private List<Vini> sortByName() {
        List<Vini> sortedList = new ArrayList<>(listaVini);
        sortedList.sort(Comparator.comparing(Vini::getNome));
        return sortedList;
    }

    String getCantinaData(String command) {
        switch (command.toLowerCase()) {
            case "all":
                return toHTMLTable(listaVini);
            case "red":
                List<Vini> redWines = filterByType("red");
                return toHTMLTable(redWines);
            case "white":
                List<Vini> whiteWines = filterByType("white");
                return toHTMLTable(whiteWines);
            case "desc_price":
                List<Vini> descPriceList = sortByPriceDescending();
                return toHTMLTable(descPriceList);
            case "alphabetical":
                List<Vini> alphabeticalList = sortByName();
                return toHTMLTable(alphabeticalList);
            default:
                return "Comando Errato";
        }
    }
}
