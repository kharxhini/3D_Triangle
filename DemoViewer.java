import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class DemoViewer extends JPanel {

    private final List<Triangle> triangles = new ArrayList<>();

    private double heading = 0;
    private double pitch = 0;

    public DemoViewer() {

        // Create 4 faces of a simple pyramid/tetrahedron
        triangles.add(new Triangle(
                new Vertex(100, 100, 100),
                new Vertex(-100, -100, 100),
                new Vertex(-100, 100, -100),
                Color.WHITE));

        triangles.add(new Triangle(
                new Vertex(100, 100, 100),
                new Vertex(-100, -100, 100),
                new Vertex(100, -100, -100),
                Color.RED));

        triangles.add(new Triangle(
                new Vertex(-100, 100, -100),
                new Vertex(100, -100, -100),
                new Vertex(100, 100, 100),
                Color.GREEN));

        triangles.add(new Triangle(
                new Vertex(-100, 100, -100),
                new Vertex(100, -100, -100),
                new Vertex(-100, -100, 100),
                Color.BLUE));
    }

    public void setHeading(double value) {
        heading = Math.toRadians(value);
        repaint();
    }

    public void setPitch(double value) {
        pitch = Math.toRadians(value);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Black background
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Move origin to center
        g2.translate(getWidth() / 2, getHeight() / 2);

        for (Triangle triangle : triangles) {

            Vertex v1 = rotate(triangle.v1);
            Vertex v2 = rotate(triangle.v2);
            Vertex v3 = rotate(triangle.v3);

            Path2D path = new Path2D.Double();

            path.moveTo(v1.x, v1.y);
            path.lineTo(v2.x, v2.y);
            path.lineTo(v3.x, v3.y);
            path.closePath();

            // Fill triangle
            g2.setColor(triangle.color);
            g2.fill(path);

            // Draw triangle border
            g2.setColor(Color.WHITE);
            g2.draw(path);
        }
    }

    private Vertex rotate(Vertex v) {

        // Rotation around Y-axis
        double x1 = v.x * Math.cos(heading)
                  - v.z * Math.sin(heading);

        double z1 = v.x * Math.sin(heading)
                  + v.z * Math.cos(heading);

        // Rotation around X-axis
        double y2 = v.y * Math.cos(pitch)
                  - z1 * Math.sin(pitch);

        return new Vertex(x1, y2, z1);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("3D Triangle");

        DemoViewer viewer = new DemoViewer();

        JSlider headingSlider =
                new JSlider(0, 360, 0);

        JSlider pitchSlider =
                new JSlider(-90, 90, 0);

        headingSlider.addChangeListener(e ->
                viewer.setHeading(headingSlider.getValue()));

        pitchSlider.addChangeListener(e ->
                viewer.setPitch(pitchSlider.getValue()));

        frame.setLayout(new BorderLayout());

        frame.add(viewer, BorderLayout.CENTER);
        frame.add(headingSlider, BorderLayout.SOUTH);
        frame.add(pitchSlider, BorderLayout.EAST);

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


class Vertex {

    double x;
    double y;
    double z;

    Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}


class Triangle {

    Vertex v1;
    Vertex v2;
    Vertex v3;

    Color color;

    Triangle(Vertex v1, Vertex v2, Vertex v3, Color color) {

        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = color;
    }
}