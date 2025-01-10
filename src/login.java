import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class login {
    public JPanel mainPanel;
    private JTextField usuario;
    private JTextField contraseña;
    private JButton ingresarButton;

    public login() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuario.getText();
                char[] passwordArray = contraseña.getPassword();
                String password = new String(passwordArray);

                String url = "jdbc:mysql://localhost:3306/calificaciones";
                String user = "root";
                String passwordDb = "123456";

                try {
                    Connection conn = DriverManager.getConnection(url, user, passwordDb);
                    System.out.println("Se ha conectado con la base de datos");

                    String query = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1, username);
                    pst.setString(2, password);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {

                        JFrame frame = new JFrame("Gestión de Calificaciones");
                        frame.setContentPane(new calificaciones().mainPanel2);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(800, 600);
                        frame.pack();
                        frame.setVisible(true);
                        frame.setLocationRelativeTo(null);
                    } else {

                        JOptionPane.showMessageDialog(mainPanel, "Credenciales incorrectas");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, "Error al conectar con la base de datos");
                }
            }
        });
    }
}

