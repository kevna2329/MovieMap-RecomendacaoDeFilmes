package model;
import enums.TipoAresta;

public class Aresta {
    private final Filme destino;
    private final TipoAresta tipo;
    private final int peso;

    public Aresta(Filme Destino, TipoAresta tipo, int peso) {
        this.destino = Destino;
        this.tipo = tipo;
        this.peso = peso;
    }

    public TipoAresta getTipo() {
        return tipo;
    }
    public Filme getDestino() {
        return destino;
    }
    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() { //Impressão de Aresta
        return " " + getDestino().getNome() + " " +
                "[" + getPeso() + "]" ;
    }
}