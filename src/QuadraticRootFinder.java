import java.util.Scanner;

public class QuadraticRootFinder{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("ax^2 + bx + c = 0");

        System.out.println("Enter a:");
        double a = sc.nextDouble();
        if (a == 0) {
            System.out.println("a cannot be zero in a quadratic equation.");
            return;
        }

        System.out.println("Enter b:");
        double b = sc.nextDouble();
        System.out.println("Enter c:");
        double c = sc.nextDouble();

        double discriminant = discriminant(a, b, c);

        if (discriminant < 0) {
            System.out.println("No real roots");
        } else if (discriminant == 0) {
            System.out.println("The root is: " + quadraticFormula(a, b, discriminant, true));
        } else {
            System.out.println("The roots are: " + quadraticFormula(a, b, discriminant, true) +
                    " and " + quadraticFormula(a, b, discriminant, false));
        }
    }

    public static double discriminant(double a, double b, double c){
        return (b * b) - (4 * a * c);
    }

    public static double quadraticFormula(double a, double b, double discriminant, boolean positive){
        if (positive) {
            return (-b + Math.sqrt(discriminant)) / (2 * a);
        } else {
            return (-b - Math.sqrt(discriminant)) / (2 * a);
        }
    }
}