package forms;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 1 on 22.01.2018.
 */
public class AboutAuthorsForm extends JFrame {
    private JLabel authorsLabel;
    private JButton closeButton;
    public AboutAuthorsForm(){
        super("Об авторах");
        setSize(250,200);
        Container container = getContentPane();
        authorsLabel = new JLabel("Авторами этой системы являются студенты \nСамарского Университета им С.П.Королева\nМысьянов Сергей\n Павлов Денис\n Шлыков Влад");
        closeButton = new JButton("Спасибо");
        closeButton.setSize(50,20);


        container.add(authorsLabel,"North");
        container.add(closeButton,"South");
        setVisible(true);
    }
}
