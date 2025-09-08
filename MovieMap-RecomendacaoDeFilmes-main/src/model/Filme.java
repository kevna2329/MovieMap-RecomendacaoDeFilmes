package model;
import java.util.ArrayList;

public class Filme {
    private final String nome;
    private final int ano;
    private final String genero;
    private final String descricao;
    private final ArrayList<String> atores;

    public Filme(String nome, int ano, String genero, String descricao, ArrayList<String> atores) {
        this.atores = atores;
        this.nome = nome;
        this.ano = ano;
        this.genero = genero;
        this.descricao = descricao;
    }

    public String getNome() {return nome;}
    public int getAno() {return ano;}
    public String getGenero() {return genero;}
    public String getDescricao() {return descricao;}
    public ArrayList<String> getAtores() {return atores;}

    @Override
    public String toString() { //impressão de Filme
        return  "Filme: " + getNome() + " (" + getAno() + ")\n"+
                "Gênero: " + getGenero() + "\n" +
                "Descrição: " + getDescricao() + "\n" +
                "Principais atores: " + getAtores() + "\n";
    }

    //isso é pra o has set de busca em largura funcionar
    @Override
    public boolean equals(Object obj) {//recebe Object porque tá sobrecrevendo Objcet.equals
        if(this == obj){//se obj é a mesma instância que this(mesmo endereço de memória)
            return true;
        }
        if(!(obj instanceof Filme)){//se o obj não for da classe filme
            return false;
        }
        Filme filme = (Filme) obj;//faz o cast de object para filme
        return this.ano == filme.ano && this.nome.equals(filme.nome);//serão iguais se tiverem mesmo ano e mesmo nome
    }

    @Override
    public int hashCode() {
        return 31 * nome.hashCode() + ano;
        //clacula um valor hash único a partir do nome e do ano
        //nome.hascode gera um número inteiro baseado na string nome
        //multiplica por 31(primo) e soma a ano para ficar mais indintificavel no has
    }
}


