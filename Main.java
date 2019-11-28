
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Main {
    /*
        
        
    */
    
    public static void main(String args[]) {
        /*
        KNN knn = new KNN("teste.csv", new Metrica.Euclidiana());
        double obs[] = new double[]{5.5,4.2,1.4,0.2};
        System.out.println("Classe = "+knn.classificar(obs));
        */
        
        double iris[][] = Dados.leituraCsv("iris.csv");
        KMeans kmeans = new KMeans(3, iris, new Metrica.Euclidiana());
        int particao[] = kmeans.gerarParticao(0.1);
        for(int i = 0; i < particao.length; ++i) {
            System.out.println(particao[i]);
        }
        
    }
    
}
