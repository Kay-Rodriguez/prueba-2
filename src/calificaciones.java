import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class calificaciones {
    public JPanel mainPanel2;
    private JTextField cedula1;
    private JTextField nota1;
    private JTextField nota2;
    private JTextField nota3;
    private JTextField nota4;
    private JTextField nota5;
    private JButton button1;

    public calificaciones() {
        button1.addActionListener(e -> {
            String cedula = cedula1.getText();

            try {
                double n1 = Double.parseDouble(nota1.getText());
                double n2 = Double.parseDouble(nota2.getText());
                double n3 = Double.parseDouble(nota3.getText());
                double n4 = Double.parseDouble(nota4.getText());
                double n5 = Double.parseDouble(nota5.getText());

                if (isValidGrade(n1) && isValidGrade(n2) && isValidGrade(n3) && isValidGrade(n4) && isValidGrade(n5)) {
                    double promedio = (n1 + n2 + n3 + n4 + n5) / 5;
                    String estado = promedio >= 60 ? "Aprobado" : "Reprobado";
                    JOptionPane.showMessageDialog(mainPanel2, "Promedio: " + promedio + "\nEstado: " + estado);

                    String url = "jdbc:mysql://localhost:3306/calificaciones";
                    String user = "root";
                    String password = "123456";

                    try (Connection conn = DriverManager.getConnection(url, user, password)) {

                        String insert = "INSERT INTO estudiantes (cedula, nombre, estudiante1, estudiante2, estudiante3, estudiante4, estudiante5) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement ps = conn.prepareStatement(insert);
                        ps.setString(1, cedula);
                        ps.setDouble(3, n1);
                        ps.setDouble(4, n2);
                        ps.setDouble(5, n3);
                        ps.setDouble(6, n4);
                        ps.setDouble(7, n5);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(mainPanel2, "Datos guardados correctamente");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(mainPanel2, "Error al guardar los datos");
                    }
                } else {
                    JOptionPane.showMessageDialog(mainPanel2, "Las calificaciones deben estar entre 0 y 20.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel2, "Por favor ingrese valores numéricos válidos.");
            }
        });
    }

    private boolean isValidGrade(double grade) {
        return grade >= 0 && grade <= 20;
    }
}
