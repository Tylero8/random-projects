import java.util.Scanner;

public class MatrixDeterminantSolve {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find the determinant (d) or solve a system of equations (s)?");
        String choice = sc.next();
        if (choice.equalsIgnoreCase("d")) {
            double[][] matrix = readMatrix(sc, 3, "|a b c|\n|d e f|\n|g h i|");
            System.out.println("The determinant is: " + MatrixInverseDeterminant.toFraction(threeDeterminant(matrix)));
        } else if (choice.equalsIgnoreCase("s")) {
            double[][] matrix = readMatrix(sc, 4, "|a b c| d\n|e f g| h\n|i j k| l");
            solveSystem(matrix);
        } else System.out.println("Invalid choice.");
    }

    private static double[][] readMatrix(Scanner sc, int cols, String prompt) {
        System.out.println(prompt);
        double[][] matrix = new double[3][cols];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println("Enter value for row " + (i + 1) + ", column " + (j + 1) + ": ");
                matrix[i][j] = sc.nextDouble();
            }
        }
        return matrix;
    }

    private static double threeDeterminant(double[][] matrix) {
        double a = matrix[0][0], b = matrix[0][1], c = matrix[0][2];
        double d = matrix[1][0], e = matrix[1][1], f = matrix[1][2];
        double g = matrix[2][0], h = matrix[2][1], i = matrix[2][2];
        return (a * e * i) + (b * f * g) + (c * d * h) - (c * e * g) - (b * d * i) - (a * f * h);
    }

    private static void solveSystem(double[][] matrix) {
        double determinant = threeDeterminant(matrix);
        if (determinant == 0) {
            System.out.println("This system is not solvable.");
            return;
        }
        double x = threeDeterminant(new double[][]{{matrix[0][3], matrix[0][1], matrix[0][2]},
                {matrix[1][3], matrix[1][1], matrix[1][2]},
                {matrix[2][3], matrix[2][1], matrix[2][2]}}) / determinant;
        double y = threeDeterminant(new double[][]{{matrix[0][0], matrix[0][3], matrix[0][2]},
                {matrix[1][0], matrix[1][3], matrix[1][2]},
                {matrix[2][0], matrix[2][3], matrix[2][2]}}) / determinant;
        double z = threeDeterminant(new double[][]{{matrix[0][0], matrix[0][1], matrix[0][3]},
                {matrix[1][0], matrix[1][1], matrix[1][3]},
                {matrix[2][0], matrix[2][1], matrix[2][3]}}) / determinant;

        System.out.println("The solution is: ");
        System.out.println("x = " + MatrixInverseDeterminant.toFraction(x));
        System.out.println("y = " + MatrixInverseDeterminant.toFraction(y));
        System.out.println("z = " + MatrixInverseDeterminant.toFraction(z));
    }

}
