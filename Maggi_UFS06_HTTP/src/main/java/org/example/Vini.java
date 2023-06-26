package org.example;

public class Vini {
    private static int id;

    private static String nome;
    private static String tipo;

    private static double prezzo;

    public Vini(int id, String nome, double prezzo, String tipo) {
        setId(id);
        setNome(nome);
        setPrezzo(prezzo);
        setTipo(tipo);
    }
    public static int getId() {
        return id;
    }
    public void setId(int id) {

        this.id = id;
    }
    public static String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo < 0.0 ? 1 : prezzo;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}

