import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class libraryApplication extends JFrame {
    private JTextField userName;
    private JPasswordField password;
    private JButton login;

    public libraryApplication() {
        setTitle("Login");
        setSize(300, 300);
        setLayout(new GridLayout(5, 1));

        userName = new JTextField("Enter your user name");
        password = new JPasswordField();
        login = new JButton("Login");

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
            void login() {
                String Username = userName.getText();
                String Password = new String(password.getPassword());
                boolean loginSuccessful = validatelogin(Username, Password);

                if (loginSuccessful) {
                    openMainForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);

                }
            }
            private boolean validatelogin(String username, String password) {
                return username.equals("ceyda") && password.equals("ceyda123");
            }

            private void openMainForm() {
                MainForm mainForm = new MainForm();
                mainForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                mainForm.setVisible(true);
                dispose();
            }
        });

        add(new JLabel("Username:"));
        add(userName);
        add(new JLabel("Password:"));
        add(password);
        add(login);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}