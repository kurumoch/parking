package forms;

import javax.swing.*;
import java.awt.*;

/**
 * Created by denis on 22.11.2017.
 */
public class ParkingCreationForm extends JFrame {

    private JSpinner xSpinner;
    private JSpinner ySpinner;
    private JButton submitButton;
    private JLabel label;
    private JLabel xLabel;
    private JLabel yLabel;
    public ParkingCreationForm(){
        super("Создание парковки");
        JPanel panel = new JPanel();
        label = new JLabel("Введите размер парковки");
        xLabel = new JLabel("x: ");
        yLabel = new JLabel("y: ");
        xSpinner = new JSpinner();
        xSpinner.setValue(5);
        ySpinner = new JSpinner();
        ySpinner.setValue(5);
        submitButton = new JButton("Готово");
        GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(label)
                .addGroup(layout.createSequentialGroup().addComponent(xLabel).addComponent(xSpinner).addComponent(yLabel).addComponent(ySpinner))
                .addComponent(submitButton));
        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(label)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(xLabel).addComponent(xSpinner).addComponent(yLabel).addComponent(ySpinner))
                .addComponent(submitButton));
        panel.setLayout(layout);
        panel.setPreferredSize(new Dimension(300,100));
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
