import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class AddBookForm extends JFrame {
    private JTextField BookName, AuthorName, BookID, BookCover;
    private JButton ChooseImage, Add, Back;
    private JLabel time;

    public AddBookForm() {


        setTitle("Add Book");
        setSize(300, 300);
        setLayout(new GridLayout(5, 1));

        BookName = new JTextField();
        AuthorName = new JTextField();
        BookID = new JTextField();
        BookCover = new JTextField();
        ChooseImage = new JButton("Choose image");
        Add = new JButton("Add Book");
        Back = new JButton("Back");
        time = new JLabel();

        ////////////////////////--------------
        ChooseImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getPath();
                    JOptionPane.showMessageDialog(null, "Selected File: " + filePath);
                }
            }
        });

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBook();
            }
        });

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });
        ////////////////////////--------------

        add(new JLabel("Book Name:"));
        add(BookName);
        add(new JLabel());
        add(new JLabel("Author Name:"));
        add(AuthorName);
        add(new JLabel());
        add(new JLabel("Book ID:"));
        add(BookID);
        add(new JLabel());
        add(new JLabel("Select Book Cover:"));
        add(BookCover);
        add(new JLabel());
        add(Back);
        add(time);
        add(Add);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        ////////////////////////--------------

    }

    private void AddBook() {
        String Name = BookName.getText();
        String Author = AuthorName.getText();
        String ID = BookID.getText();

        if (Name.isEmpty() || Author.isEmpty() || ID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Instant start = Instant.now();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("book_info.txt", true))) {
            writer.write("Book Name: " + Name + "\n");
            writer.write("Author Name: " + Author + "\n");
            writer.write("Book ID: " + ID + "\n");


            JOptionPane.showMessageDialog(null, "Book information saved to file");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while saving book information", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        long seconds = duration.getSeconds();

        time.setText(seconds + "seconds");

        BookName.setText("");
        AuthorName.setText("");
        BookID.setText("");
    }
    private void goBack() {
        MainForm mainForm = new MainForm();
        mainForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainForm.setVisible(true);

        dispose();
    }
}