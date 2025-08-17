package model;

import java.util.ArrayList;

public class Usuario {
    private String nome;
    private ArrayList<Filme> historico;

    public Usuario(String nome, ArrayList<Filme> historico) {
        this.nome = nome;
    }
}
