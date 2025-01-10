
import javax.swing.*;
import java.awt.*;
import java.sql.*;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Variables de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/calificaciones";
        String user = "root";
        String password = "123456";

        Connection conn = null;

        try {
            // Establecer conexión con la base de datos
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado a la base de datos");

            // Crear la ventana principal para el login
            JFrame frame = new JFrame("Login");
            frame.setContentPane(new login().mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setPreferredSize(new Dimension(800, 600));
            frame.pack();
            frame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        } finally {
            // Asegurarse de cerrar la conexión
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
