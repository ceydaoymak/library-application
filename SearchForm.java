import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
public class SearchForm extends JFrame {
    private JTextField SearchBook;
    private JButton Search, Back;
    private JLabel time;

    SearchForm() {
        setTitle("Search Book");
        setSize(300, 300);
        setLayout(new GridLayout(5, 1));

        SearchBook = new JTextField();

        Search = new JButton("Search");

        Back = new JButton("Back");
        time = new JLabel();

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });
        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });

        add(new JLabel("search for the book"));

        add(SearchBook);
        add(Search);
        add(Back);
        add(time);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void goBack() {
        MainForm mainForm = new MainForm();
        mainForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainForm.setVisible(true);

        dispose();
    }

    public void search() {
        String S = Search.getText();
        Instant start = Instant.now();
        try (BufferedReader reader = new BufferedReader(new FileReader("book_info.txt"))) {
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.contains("Book Name: " + S)) {
                    result.append(line).append("\n");
                }
            }
            if (result.length() > 0) {
                JOptionPane.showMessageDialog(null, "Search Results:\n" + result.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No results found for \"" + S + "\"");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while searching", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        long seconds = duration.getSeconds();

        time.setText(seconds + "seconds");
    }
}



