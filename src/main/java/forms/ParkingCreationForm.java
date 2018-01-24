package forms;

import controllers.Controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public ParkingCreationForm(Controller controller, MainForm mainForm){
        super("Создание парковки");
        JPanel panel = new JPanel();
        label = new JLabel("Введите размер парковки");
        xLabel = new JLabel("x: ");
        yLabel = new JLabel("y: ");
        xSpinner = new JSpinner();
        xSpinner.setValue(10);
        ySpinner = new JSpinner();
        ySpinner.setValue(10);
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
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.initParking((int) xSpinner.getValue(), (int) ySpinner.getValue());
                controller.reinitParking((int)xSpinner.getValue(), (int)ySpinner.getValue());
                controller.drawTiles();
                dispose();
            }
        });
        xSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ImageIcon icon = new ImageIcon("error.png");
                if ((int)xSpinner.getValue()>10){
                    xLabel.setForeground(Color.red);
                    JOptionPane.showMessageDialog(new JApplet(),"Максимальное значение - '10'!","Перебор",0,icon);
                    xSpinner.setValue(10);
                    xLabel.setForeground(Color.black);
                }
                if ((int)xSpinner.getValue()<1){
                    xLabel.setForeground(Color.red);
                    JOptionPane.showMessageDialog(new JApplet(),"Минимальное значение - '1'!","Недобор",0,icon);
                    xSpinner.setValue(1);
                    xLabel.setForeground(Color.black);
                }
            }
        });
        ySpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ImageIcon icon = new ImageIcon("error.png");
                if ((int)ySpinner.getValue()>10){
                    yLabel.setForeground(Color.red);
                    JOptionPane.showMessageDialog(new JApplet(),"Максимальное значение - '10'!","Перебор",0,icon);
                    ySpinner.setValue(10);
                    yLabel.setForeground(Color.black);
                }
                if ((int)ySpinner.getValue()<1){
                    yLabel.setForeground(Color.red);
                    JOptionPane.showMessageDialog(new JApplet(),"Минимальное значение - '1'!","Недобор",0,icon);
                    ySpinner.setValue(1);
                    yLabel.setForeground(Color.black);
                }
            }
        });
    }

}
