package forms;

import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 1 on 22.01.2018.
 */
public class AboutAuthorsForm extends JFrame {
    private JLabel authorsLabel1;
    private JLabel authorsLabel2;
    private JLabel authorsLabel3;

    public AboutAuthorsForm() {
        super("Об авторах");
        final JFrame frame = new JFrame();
        setSize(350, 125);
        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        authorsLabel1 = new JLabel("Авторами этой системы являются студенты");
        authorsLabel2 = new JLabel("Самарского Университета им. С.П.Королева");
        authorsLabel3 = new JLabel("Мысьянов Сергей, Павлов Денис, Шлыков Влад");


        container.add(authorsLabel1);
        container.add(authorsLabel2);
        container.add(authorsLabel3);
        setVisible(true);

    }
}
