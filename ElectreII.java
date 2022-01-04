import java.text.DecimalFormat;
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
        int dim = tableDePerfermance.length;
        return listToMatrix(list, dim);
    }

    static double[][] discordance(double[][] tableDePerfermance) {
        double delta = calcDelta(tableDePerfermance);
        ArrayList<Double> list = new ArrayList<>();
        ArrayList<Double> list0 = new ArrayList<>();
        for (int count = 0; count < tableDePerfermance.length; count++) {
            for (int i = 0; i < tableDePerfermance.length; i++) {
                for (int j = 0; j < tableDePerfermance[i].length; j++) {
                    if (tableDePerfermance[count][j] <= tableDePerfermance[i][j]) {
                        list0.add(tableDePerfermance[i][j] - tableDePerfermance[count][j]);
                    } else
                        list0.add(0.0);
                }
                list.add(Double.parseDouble(new DecimalFormat("##.####").format((1 / delta) * max(list0))));
                list0 = new ArrayList<>();
            }
        }
        int dim = tableDePerfermance.length;
        return listToMatrix(list, dim);
    }

    static double calcSommeDesPoids(double[] poids) {
        double somme = 0;
        for (int i = 0; i < poids.length; i++)
            somme += poids[i];
        return somme;
    }

    static double calcDelta(double[][] tableDePerfermance) {
        double min = tableDePerfermance[0][0];
        double max = tableDePerfermance[0][0];
        for (int i = 0; i < tableDePerfermance.length; i++) {
            for (int j = 0; j < tableDePerfermance[i].length; j++) {
                if (tableDePerfermance[i][j] > max) {
                    max = tableDePerfermance[i][j];
                }
                if (tableDePerfermance[i][j] < min) {
                    min = tableDePerfermance[i][j];
                }
            }
        }
        return max - min;
    }

    static ArrayList<String> coupleConc(double matriceConc[][]) {
        ArrayList<String> couples = new ArrayList<>();
        for (int i = 0; i < matriceConc.length; i++) {
            for (int j = 0; j < matriceConc[i].length; j++) {
                if (i != j) {
                    if (matriceConc[i][j] == 1) {
                        couples.add("P" + (i + 1) + "; P" + (j + 1));
                    }
                }
            }
        }
        return couples;
    }

    static ArrayList<String> coupleDisc(double matriceConc[][]) {
        ArrayList<String> couples = new ArrayList<>();
        for (int i = 0; i < matriceConc.length; i++) {
            for (int j = 0; j < matriceConc[i].length; j++) {
                if (i != j) {
                    if (matriceConc[i][j] == 0) {
                        couples.add("P" + (i + 1) + "; P" + (j + 1));
                    }
                }
            }
        }
        return couples;
    }

    static double max(ArrayList<Double> values) {
        double max = values.get(0);
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) > max) {
                max = values.get(i);
            }
        }
        return max;
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

    static void afficher(double[][] matrice, String title) {
        System.out.println(title);
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
        afficher(tableDePerfermance, "Table de perfermance :");
        double[][] matriceDeConc = concordance(tableDePerfermance, poids);
        afficher(matriceDeConc, "Matrice de concordance :");
        double[][] matriceDeDisc = discordance(tableDePerfermance);
        afficher(matriceDeDisc, "Matrice de discordance : ");
        System.out.println("Couple de Conc" + coupleConc(matriceDeConc));
        System.out.println("Couple de Disc" + coupleDisc(matriceDeDisc));
    }
}