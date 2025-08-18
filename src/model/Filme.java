package model;
import enums.TipoAresta;
import java.util.ArrayList;

public class Filme {
    private final String nome;
    private final int ano;
    private final String genero;
    private final String descricao;
    private final ArrayList<String> atores;

    private ArrayList<Aresta> relacionadosPorGenero = new ArrayList<>();
    private ArrayList<Aresta> relacionadosPorAtor = new ArrayList<>();

    public Filme(String nome, int ano, String genero, String descricao, ArrayList<String> atores) {
        this.atores = atores;
        this.nome = nome;
        this.ano = ano;
        this.genero = genero;
        this.descricao = descricao;
    }

    public void addAresta(Aresta aresta, TipoAresta tipo) {
        if (tipo.equals(TipoAresta.ARESTA_GENERO))
            relacionadosPorGenero.add(aresta);
        else
            relacionadosPorAtor.add(aresta);
    }

    public ArrayList<Aresta> getRelacionados(TipoAresta tipo) {
        if(tipo.equals(TipoAresta.ARESTA_GENERO)) {
            return relacionadosPorGenero;
        }else{
            return relacionadosPorAtor;
        }
    }

    //Getters: --------------------
    public String getNome() {return nome;}
    public int getAno() {return ano;}
    public String getGenero() {return genero;}
    public String getDescricao() {return descricao;}
    public ArrayList<String> getAtores() {return atores;}
    //--------------------

    @Override
    public String toString() { //impressão de Filme
        return "Filme: \n"+
                "Nome: " + getNome() + "\n"+
                "Ano de lançamento: " + getAno() + "\n"+
                "Gênero: " + getGenero() + "\n" +
                "Descrição: " + getDescricao() + "\n" +
                "Atores: " + getAtores() + "\n";
    }
}
