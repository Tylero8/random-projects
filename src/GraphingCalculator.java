import javax.swing.*;
import java.awt.*;

public class GraphingCalculator extends JFrame {

    private final JTextField aField;
    private final JTextField bField;
    private final JTextField cField;
    private final GraphPanel graphPanel;

    public GraphingCalculator() {
        super("Graphing Calculator");

        // Fields for coefficients
        aField = new JTextField("1", 5);
        bField = new JTextField("0", 5);
        cField = new JTextField("0", 5);

        // Button to redraw graph
        JButton drawButton = new JButton("Draw Graph");
        drawButton.addActionListener(e -> drawGraph());

        // Setting up the layout
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("y = ax^2 + bx + c"));
        controlPanel.add(new JLabel("a:"));
        controlPanel.add(aField);
        controlPanel.add(new JLabel("b:"));
        controlPanel.add(bField);
        controlPanel.add(new JLabel("c:"));
        controlPanel.add(cField);
        controlPanel.add(drawButton);

        graphPanel = new GraphPanel();
        add(controlPanel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void drawGraph() {
        try {
            double a = Double.parseDouble(aField.getText());
            double b = Double.parseDouble(bField.getText());
            double c = Double.parseDouble(cField.getText());
            graphPanel.setFunction(a, b, c);
            graphPanel.repaint();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for a, b, and c.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraphingCalculator().setVisible(true));
    }

    private static class GraphPanel extends JPanel {
        private double a = 1, b = 0, c = 0;

        public void setFunction(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawAxes(g);
            plotFunction(g);
        }

        private void drawAxes(Graphics g) {
            // Draw X and Y axes
            g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
            g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        }

        private void plotFunction(Graphics g) {
            g.setColor(Color.RED);

            // Scale factors
            double xScale = getWidth() / 20.0;
            double yScale = getHeight() / 20.0;

            for (int xPixel = 0; xPixel < getWidth(); xPixel++) {
                double x = (xPixel - getWidth() / 2.0) / xScale;
                double y = a * x * x + b * x + c; // quadratic function

                int yPixel = getHeight() / 2 - (int) (y * yScale);

                // Ensure the point is within the bounds of the panel
                if (yPixel >= 0 && yPixel < getHeight()) {
                    g.fillOval(xPixel - 2, yPixel - 2, 4, 4);
                }
            }
        }
    }
}
