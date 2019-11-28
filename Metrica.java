
public interface Metrica {
    
    public double calcular(double x1[], double x2[]);
    
    public class Euclidiana implements Metrica {
        public double calcular(double x1[], double x2[]) {
            double soma = 0.0;
            for(int i=0; i < x1.length; ++i) {
                soma += Math.pow(x1[i]-x2[i],2);
            }
            return Math.sqrt(soma);
        }
    }
    
    public class Erro implements Metrica {
        
        public double calcular (double y[], double f[]) {
            double erro = 0;
            int n = f.length;
            for(int i =0; i < n; ++i) {
                if(y[i] != f[i]) {
                    erro++;
                }
            }
            return erro/(double)n;
        }
        
    }
}
