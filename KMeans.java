
import java.util.Random;

public class KMeans {
    
    // Número de grupos a ser obtido;
    private int k;
    // Matriz com os dados do problema;
    private double[][] dados;
    // Centroides
    private double[][] centroides;
    // Número de atributos;
    private int numAttr;
    // Métrica usada para o agrupamento
    private Metrica metrica;
    
    
    public KMeans(int k, double dados[][], Metrica metrica) {
        this.k = k;
        this.dados = dados;
        this.numAttr = dados[0].length;
        this.centroides = new double[k][];
        for(int i = 0; i < k; ++i) {
            centroides[i]= new double[numAttr];
        }
        this.metrica = metrica;
    }
    
    // Método para retornar as partições obtidas pelo Kmeans
    public int[] gerarParticao(double tolerancia) {
        int particao[] = new int[dados.length];
        
        double erro = Double.MAX_VALUE;
        
        gerarCentroides();
        while(erro > tolerancia) {
            erro = 0;
            // Determina para cada observação o grupo correspondente...
            for(int i = 0; i < dados.length; ++i) {
                int grupo = 0;
                double menorDistancia = Double.MAX_VALUE;
                for(int j = 0; j < k; ++j) {
                    double distancia = metrica.calcular(dados[i], centroides[j]);
                    if(distancia < menorDistancia) {
                        menorDistancia = distancia;
                        grupo = j;
                    }
                }
                particao[i] = grupo;
            }

            double novaPosicaoCentroide[][] = new double[k][numAttr];
            double quantidadeDeObservacoesPorCentroide[] = new double[k];
            for(int j = 0; j < particao.length; ++j) {
                for(int n = 0; n < numAttr; ++n) {
                    novaPosicaoCentroide[particao[j]][n] +=
                    dados[j][n];
                }
                quantidadeDeObservacoesPorCentroide[particao[j]]++;
            }

            for(int j = 0; j < k; ++j) {
                for(int n = 0; n < numAttr; ++n) {
                    if(quantidadeDeObservacoesPorCentroide[j] > 0)
                        novaPosicaoCentroide[j][n] /= 
                                quantidadeDeObservacoesPorCentroide[j];
                }
            }

            for(int i =0; i < k; ++i) {
                erro += metrica.calcular(centroides[i],
                        novaPosicaoCentroide[i]);
            }
            erro = erro/k;
        
            centroides = novaPosicaoCentroide;
            System.out.println("erro = "+erro);
        }
        
        return particao;
    }
    
    /*  Colocar aleatoriamente os centroides em alguma
        posição aleatória no espaço dos dados. */
    private void gerarCentroides() {
        Random r = new Random();
        for(int i =0; i < k; ++i) {
            centroides[i] = new double[numAttr];
            centroides[i] = dados[r.nextInt(dados.length)];
        }
    }
    
    
    
}
