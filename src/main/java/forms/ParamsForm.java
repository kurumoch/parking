package forms;

import javax.swing.*;
import java.awt.*;

/**
 * Created by denis on 22.11.2017.
 */
public class ParamsForm extends JFrame{
    private JSpinner xSpinner;
    private JSpinner ySpinner;
    private JLabel label;
    private JLabel xLabel;
    private JLabel yLabel;
    private JLabel ratesLabel;
    private JLabel oneHourLabel;
    private JLabel oneToThreeHourLabel;
    private JLabel moreThreeHoursLabel;
    private JSpinner oneHourSpinner;
    private JSpinner oneToThreeHourSpinner;
    private JSpinner moreThreeHoursSpinner;
    private JLabel timeDistribLabel;
    private JComboBox timeDistribComboBox;
    private JPanel determinePanelTime;
    private JLabel intervalLabelTime;
    private JTextField intervalFieldTime;
    private JPanel stochasticPanelTime;
    private JPanel exponentialPanelTime;
    private JLabel lambdaLabelTime;
    private JTextField lambdaFieldTime;
    private JPanel normalPanelTime;
    private JLabel mxLabelTime;
    private JTextField mxFieldTime;
    private JLabel dxLabelTime;
    private JTextField dxFieldTime;
    private JPanel uniformPanelTime;
    private JLabel t1LabelTime;
    private JTextField t1FieldTime;
    private JLabel t2LabelTime;
    private JTextField t2FieldTime;
    private JLabel flowDistribLabel;
    private JComboBox flowDistribComboBox;
    private JPanel determinePanelFlow;
    private JLabel intervalLabelFlow;
    private JTextField intervalFieldFlow;
    private JPanel stochasticPanelFlow;
    private JPanel exponentialPanelFlow;
    private JLabel lambdaLabelFlow;
    private JTextField lambdaFieldFlow;
    private JPanel normalPanelFlow;
    private JLabel mxLabelFlow;
    private JTextField mxFieldFlow;
    private JLabel dxLabelFlow;
    private JTextField dxFieldFlow;
    private JPanel uniformPanelFlow;
    private JLabel t1LabelFlow;
    private JTextField t1FieldFlow;
    private JLabel t2LabelFlow;
    private JTextField t2FieldFlow;
    private JLabel truckPartLabel;
    private JTextField truckPartField;
    private JLabel probLabel;
    private JTextField probField;
    private JButton submitButton;
    public ParamsForm(){
        super("Изменение параметров");
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
//        panel.setPreferredSize(new Dimension(300,100));
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
