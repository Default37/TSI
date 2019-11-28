
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dados {
    
    // Faz a leitura de dados no formato CSV.
    // Importante que todos os dados do arquivo CSV sejam numéricos, inclusive a classe, se houver.
    public static double[][] leituraCsv(String arqCsv) {
        try {
            
            System.out.println("Iniciando leitura dos dados...");
            long inicio = System.currentTimeMillis();
            
            double mat[][];// Matriz final com os dados.
            
            Stream<String> streamStr = Files.lines(Paths.get(Dados.class.getResource(arqCsv).getPath()),
                    StandardCharsets.UTF_8);
            
            mat = streamStr.map(linha -> Arrays.asList(linha.split(","))).
                            map(linha -> linha.stream().
                                mapToDouble(v -> Double.parseDouble(v)).
                                toArray()).
                            toArray(double[][]::new);
            
            System.out.printf("Leitura finalizada %ds\n",((System.currentTimeMillis()-inicio)));
            
            return mat;            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static ArrayList<double[][]> particionaDados(double dados[][], int porcentagem) {
        
        if(dados != null) {
            int indice = porcentagem*dados.length/100;

            ArrayList<double[][]> particoes = new ArrayList();
            AtomicInteger count = new AtomicInteger();

            // Divide os elementos...
            Arrays.stream(dados).
                    map(obsvs -> Arrays.copyOf(obsvs,obsvs.length)).
                    collect(Collectors.partitioningBy(obsvs -> count.getAndIncrement() < indice)).
                    values().stream().
                    forEach(particao -> particoes.add(particao.stream().toArray(double[][]::new)));
            
            return particoes;
        } else {
            System.out.println("Matriz de dados está vazia!");
            return null;
        }
    }
    
    public static void printDados(double[][] mat) {
        if(mat != null) {
            System.out.printf("(observacoes,atributos):(%d,%d)\n",mat.length,mat[0].length);
            Arrays.asList(mat).stream().forEach(obsvs -> System.out.println(Arrays.toString(obsvs)));
        } else {
            System.out.println("Matriz de dados está vazia!");
        }
    }
    
}
