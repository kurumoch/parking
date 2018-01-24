package forms;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 1 on 22.01.2018.
 */
public class AboutProgramForm extends JFrame {
    private JLabel authorsLabel1;
    private JLabel authorsLabel2;
    private JLabel authorsLabel3;
    private JLabel authorsLabel4;
    private JLabel authorsLabel5;
    private JLabel authorsLabel6;
    private JLabel img;

    public AboutProgramForm(){
        super("О программе");
        final JFrame frame = new JFrame();
        setSize(500, 230);
        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        authorsLabel1 = new JLabel("Добрый день!");
        authorsLabel2 = new JLabel("Данная система создана для моделирования работы платных парковок.");
        authorsLabel3 = new JLabel("Вы можете сконструировать твою собственную парковку, ");
        authorsLabel4 = new JLabel("расставив шаблоны на поле, можете изменять параметры функционирования ");
        authorsLabel5 = new JLabel("этой парковки: от тарифов до настроек потока автомобилей.");
        authorsLabel6 = new JLabel("Очень надеемся, что Вам понравится работа в данной системе! Всего доброго!");
        ImageIcon icon1 = new ImageIcon("book.png");
        img = new JLabel(icon1);

        container.add(img).setBounds(10,10,64 ,64);
        container.add(authorsLabel1);
        container.add(authorsLabel2);
        container.add(authorsLabel3);
        container.add(authorsLabel4);
        container.add(authorsLabel5);
        container.add(authorsLabel6);
        setVisible(true);
    }
}
