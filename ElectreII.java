import java.util.ArrayList;

class ElectreII {
    static double[][] concordance(double[][] tableDePerfermance, double[] poids) {
        double sommeDesPoids = calcSommeDesPoids(poids);
        double sommeCondistionalDesPoids = 0;
        ArrayList<Double> list = new ArrayList<>();
        for (int count = 0; count < tableDePerfermance.length; count++) {
            for (int i = 0; i < tableDePerfermance.length; i++) {
                for (int j = 0; j < tableDePerfermance[i].length; j++) {
                    if (tableDePerfermance[count][j] >= tableDePerfermance[i][j]) {
                        sommeCondistionalDesPoids += poids[j];
                    }
                }
                list.add(sommeCondistionalDesPoids / sommeDesPoids);
                sommeCondistionalDesPoids = 0;
            }
        }
        System.out.println(list);
        int dim = tableDePerfermance.length;
        return listToMatrix(list, dim);
    }

    static double calcSommeDesPoids(double[] poids) {
        double somme = 0;
        for (int i = 0; i < poids.length; i++)
            somme += poids[i];
        return somme;
    }

    static double[][] listToMatrix(ArrayList<Double> list, int dim) {
        double[][] result = new double[dim][dim];
        int i = 0, j = 0;
        for (int counter = 0; counter < list.size(); counter++) {
            result[i][j] = list.get(counter);
            j++;
            if (j == dim) {
                i++;
                j = 0;
            }
        }
        return result;
    }

    static void afficher(double[][] matrice) {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }

    public static void main(String[] args) {
        double[][] tableDePerfermance = {
                { 10, 20, 5, 10, 16 },
                { 0, 5, 5, 16, 10 },
                { 0, 10, 0, 16, 7 },
                { 20, 5, 10, 10, 13 },
                { 20, 10, 15, 13, 13 },
                { 20, 10, 20, 13, 13 }
        };
        double[] poids = { 3, 2, 3, 1, 1 };
        afficher(tableDePerfermance);
        afficher(concordance(tableDePerfermance, poids));
    }
}