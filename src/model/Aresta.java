package model;
import enums.TipoAresta;
import java.util.ArrayList;

public class Aresta {
    private Filme destino;
    private TipoAresta tipo;
    private int peso;

    public Aresta(Filme Destino, TipoAresta tipo, int peso) {
        this.destino = Destino;
        this.tipo = tipo;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Aresta:" +
                "Destino: " + getDestino().getNome() + "\n" +
                "Tipo:" + getTipo() + "\n" +
                "Peso: " + getPeso() + "\n";
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
}
