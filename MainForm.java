import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainForm extends JFrame {
    private JButton add, search;

    public  MainForm() {
        setTitle("Library System");
        setSize(300, 300);
        setLayout(new GridLayout(5, 1));

        add = new JButton("Add Book");
        search = new JButton("Search Book");

        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                openAddBookForm();
            }
            private void openAddBookForm() {
                AddBookForm AddBookForm = new AddBookForm();
                AddBookForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                AddBookForm.setVisible(true);
                dispose();
            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSeacrhForm();
            }
            private void openSeacrhForm() {
                SearchForm SearchForm = new SearchForm();
                SearchForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                SearchForm.setVisible(true);
                dispose();
            }
        });

        add(add);
        add(search);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
