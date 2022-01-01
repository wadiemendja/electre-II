class ElectreII {
    static double[][] concordance(double[][] tableDePerfermance, double[] poids) { 
        double matriceConcordance[][] = tableDePerfermance;
        afficher(matriceConcordance);
        double sommeCondistionalDesPoids = 0;
        int jj = 0;
        for (int i = 0; i < matriceConcordance.length - 1; i++) {
            for (int j = 0; j < matriceConcordance[i].length; j++) {
                if (tableDePerfermance[i][j] > tableDePerfermance[i + 1][j]) {
                    sommeCondistionalDesPoids += poids[j];
                }
            }
            matriceConcordance[i][jj] = sommeCondistionalDesPoids / calcSommeDesPoids(poids);
            jj++;
        }
        return matriceConcordance;
    }

    static double calcSommeDesPoids(double[] poids) {
        double somme = 0;
        for (int i = 0; i < poids.length; i++)
            somme += poids[i];
        return somme;
    }

    static void afficher (double [][] matrice) {
        for (int i= 0 ; i< matrice.length; i++ ){
            for (int j = 0 ; j< matrice[i].length; j++) {
                System.out.print(matrice[i][j] + "    ");
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
        afficher(concordance(tableDePerfermance, poids));
    }
}