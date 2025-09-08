package model;

import enums.TipoAresta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.System.exit;

public class LerArquivo {
    String caminhoArquivo;
    Grafo grafo = new Grafo();

    public LerArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public Grafo getDados(){
        InputStream inputStream = LerArquivo.class.getResourceAsStream(caminhoArquivo);
            if(inputStream == null){
                System.out.println("Erro ao ler arquivo");
                exit(1);
            }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            // Lendo número de filmes
            int nVertices = Integer.parseInt(br.readLine().trim());
            // Lista para armazenar os filmes

            // Lendo os filmes
            for (int i = 0; i < nVertices; i++) {
                String linha = br.readLine();
                String[] partes = linha.split(";");
                String nome = partes[0];
                int ano = Integer.parseInt(partes[1]);
                String genero = partes[2];
                String descricao = partes[3];
                ArrayList<String> atores = new ArrayList<>();
                atores.add(partes[4]);

                Filme f = new Filme(nome, ano, genero, descricao, atores);
                grafo.adicionarVertice(f);
            }

            // Lista para armazenar as arestas (ligações entre filmes)
            int nArestas = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < nArestas; i++)  {
                String linha = br.readLine();
                String[] partes = linha.split(";");
                String origem = partes[0];
                String destino = partes[1];
                TipoAresta tipo = TipoAresta.valueOf(partes[2]);//tipo aresta;
                int peso = Integer.parseInt(partes[3]);
                    grafo.adicionarAresta(grafo.buscaFilme(origem), grafo.buscaFilme(destino), tipo, peso);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return grafo;
    }
}