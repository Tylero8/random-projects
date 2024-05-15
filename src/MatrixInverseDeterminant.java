import java.util.Scanner;

public class MatrixInverseDeterminant {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] matrix = readMatrix(sc);
        double a = matrix[0][0], b = matrix[0][1], c = matrix[1][0], d = matrix[1][1];

        System.out.println("Inverse (i) or Determinant (d)?");
        String choice = sc.next();

        if (choice.equalsIgnoreCase("i")) {
            inverse(a, b, c, d);
        } else if (choice.equalsIgnoreCase("d")) {
            System.out.println("The determinant is: " + toFraction(determinant(a, b, c, d)));
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static double[][] readMatrix(Scanner sc) {
        System.out.println("|a b|\n|c d|");
        double[][] matrix = new double[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println("Enter value for row " + (i + 1) + ", column " + (j + 1) + ": ");
                matrix[i][j] = sc.nextDouble();
            }
        }
        return matrix;
    }

    public static void inverse(double a, double b, double c, double d) {
        double det = determinant(a, b, c, d);
        if (det == 0) {
            System.out.println("This matrix is not invertible.");
            return;
        }
        System.out.println("The inverse is: ");
        System.out.println("|" + toFraction(d / det) + " " + toFraction(-b / det) + "|");
        System.out.println("|" + toFraction(-c / det) + " " + toFraction(a / det) + "|");
    }

    public static double determinant(double a, double b, double c, double d) {
        return (a * d) - (b * c);
    }

    public static String toFraction(double number) {
        if (number == (long) number) {
            return String.valueOf((long) number);
        }

        final double tolerance = 1.0E-5;
        double numerator;
        double bestNumerator = 1;
        double bestDenominator = 1;
        double bestError = Double.MAX_VALUE;

        for (double denominator = 1; denominator <= 100000; denominator++) {
            numerator = Math.round(number * denominator);
            double error = Math.abs(number - (numerator / denominator));
            if (error < bestError && error < tolerance) {
                bestError = error;
                bestNumerator = numerator;
                bestDenominator = denominator;
            }
        }

        return (long) bestNumerator + "/" + (long) bestDenominator;
    }
}

