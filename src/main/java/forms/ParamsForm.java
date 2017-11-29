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
    private JComboBox<String> stochasticComboBoxTime;
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
    private JComboBox<String> flowDistribComboBox;
    private JPanel determinePanelFlow;
    private JLabel intervalLabelFlow;
    private JTextField intervalFieldFlow;
    private JComboBox<String> stochasticComboBoxFlow;
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
    private JSpinner truckPartSpinner;
    private JLabel probLabel;
    private JSpinner probSpinner;
    private JButton submitButton;
    private GroupLayout layout;
    private GroupLayout stochasticLayoutTime;
    private GroupLayout stochasticLayoutFlow;
    private GroupLayout determineLayoutTime;
    private GroupLayout determineLayoutFlow;
    private GroupLayout normalLayoutTime;
    private GroupLayout normalLayoutFlow;
    private GroupLayout uniformLayoutTime;
    private GroupLayout uniformLayoutFlow;
    private GroupLayout exponentialLayoutTime;
    private GroupLayout exponentialLayoutFlow;


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
        ratesLabel = new JLabel("Тариф");
        oneHourLabel = new JLabel("до 1ч");
        oneToThreeHourLabel = new JLabel("от 1ч до 3ч");
        moreThreeHoursLabel = new JLabel("более 3ч");
        oneHourSpinner = new JSpinner();
        oneHourSpinner.setValue(200);
        oneToThreeHourSpinner = new JSpinner();
        oneToThreeHourSpinner.setValue(150);
        moreThreeHoursSpinner = new JSpinner();
        moreThreeHoursSpinner.setValue(100);
        timeDistribLabel = new JLabel("Распределение времени нахождения на парковке");
        timeDistribComboBox = new JComboBox<String>(new String[]{"Детерминированный", "Стохастический"});
        determinePanelTime = new JPanel();
        intervalFieldTime = new JTextField();
        intervalLabelTime = new JLabel("Интервал");
        determineLayoutTime = new GroupLayout(determinePanelTime);
        determineLayoutTime.setAutoCreateGaps(true);
        determineLayoutTime.setAutoCreateContainerGaps(true);
        determinePanelTime.setLayout(determineLayoutTime);
        determineLayoutTime.setHorizontalGroup(determineLayoutTime.createSequentialGroup().addComponent(intervalLabelTime).addComponent(intervalFieldTime));
        determineLayoutTime.setVerticalGroup(determineLayoutTime.createParallelGroup().addComponent(intervalLabelTime).addComponent(intervalFieldTime));
        stochasticPanelTime = new JPanel();
        stochasticComboBoxTime = new JComboBox<String>(new String[]{"Нормальный", "Показательный", "Экспоненциальный"});
        normalPanelTime = new JPanel();
        mxFieldTime = new JTextField();
        mxLabelTime = new JLabel("MX");
        dxFieldTime = new JTextField();
        dxLabelTime = new JLabel("DX");
        normalLayoutTime = new GroupLayout(normalPanelTime);
        normalLayoutTime.setAutoCreateGaps(true);
        normalLayoutTime.setAutoCreateContainerGaps(true);
        normalPanelTime.setLayout(normalLayoutTime);
        normalLayoutTime.setHorizontalGroup(normalLayoutTime.createSequentialGroup().addComponent(mxLabelTime).addComponent(mxFieldTime).addComponent(dxLabelTime).addComponent(dxFieldTime));
        normalLayoutTime.setVerticalGroup(normalLayoutTime.createParallelGroup().addComponent(mxLabelTime).addComponent(mxFieldTime).addComponent(dxLabelTime).addComponent(dxFieldTime));
        uniformPanelTime = new JPanel();
        t1FieldTime = new JTextField();
        t1LabelTime = new JLabel("T1");
        t2FieldTime = new JTextField();
        t2LabelTime = new JLabel("T2");
        uniformLayoutTime = new GroupLayout(uniformPanelTime);
        uniformLayoutTime.setAutoCreateGaps(true);
        uniformLayoutTime.setAutoCreateContainerGaps(true);
        uniformPanelTime.setLayout(uniformLayoutTime);
        uniformLayoutTime.setHorizontalGroup(uniformLayoutTime.createSequentialGroup().addComponent(t1LabelTime).addComponent(t1FieldTime).addComponent(t2LabelTime).addComponent(t2FieldTime));
        uniformLayoutTime.setVerticalGroup(uniformLayoutTime.createParallelGroup().addComponent(t1LabelTime).addComponent(t1FieldTime).addComponent(t2LabelTime).addComponent(t2FieldTime));
        exponentialPanelTime = new JPanel();
        lambdaFieldTime = new JTextField();
        lambdaLabelTime = new JLabel("lambda");
        exponentialLayoutTime = new GroupLayout(exponentialPanelTime);
        exponentialLayoutTime.setAutoCreateGaps(true);
        exponentialLayoutTime.setAutoCreateContainerGaps(true);
        exponentialPanelTime.setLayout(exponentialLayoutTime);
        exponentialLayoutTime.setHorizontalGroup(exponentialLayoutTime.createSequentialGroup().addComponent(intervalLabelTime).addComponent(intervalFieldTime));
        exponentialLayoutTime.setVerticalGroup(exponentialLayoutTime.createParallelGroup().addComponent(intervalLabelTime).addComponent(intervalFieldTime));
        stochasticLayoutTime = new GroupLayout(stochasticPanelTime);
        stochasticLayoutTime.setAutoCreateGaps(true);
        stochasticLayoutTime.setAutoCreateContainerGaps(true);
        stochasticPanelTime.setLayout(stochasticLayoutTime);
        stochasticLayoutTime.setVerticalGroup(stochasticLayoutTime.createSequentialGroup().addComponent(normalPanelTime).addComponent(uniformPanelTime).addComponent(exponentialPanelTime));
        stochasticLayoutTime.setHorizontalGroup(stochasticLayoutTime.createParallelGroup().addComponent(normalPanelTime).addComponent(uniformPanelTime).addComponent(exponentialPanelTime));
        flowDistribLabel = new JLabel("Поток автомобилей");
        flowDistribComboBox = new JComboBox<String>(new String[]{"Детерминированный", "Стохастический"});
        determinePanelFlow = new JPanel();
        intervalFieldFlow = new JTextField();
        intervalLabelFlow = new JLabel("Интервал");
        determineLayoutFlow = new GroupLayout(determinePanelFlow);
        determineLayoutFlow.setAutoCreateGaps(true);
        determineLayoutFlow.setAutoCreateContainerGaps(true);
        determinePanelFlow.setLayout(determineLayoutFlow);
        determineLayoutFlow.setHorizontalGroup(determineLayoutFlow.createSequentialGroup().addComponent(intervalLabelFlow).addComponent(intervalFieldFlow));
        determineLayoutFlow.setVerticalGroup(determineLayoutFlow.createParallelGroup().addComponent(intervalLabelFlow).addComponent(intervalFieldFlow));
        stochasticPanelFlow = new JPanel();
        stochasticComboBoxFlow = new JComboBox<String>(new String[]{"Нормальный", "Показательный", "Экспоненциальный"});
        normalPanelFlow = new JPanel();
        mxFieldFlow = new JTextField();
        mxLabelFlow = new JLabel("MX");
        dxFieldFlow = new JTextField();
        dxLabelFlow = new JLabel("DX");
        normalLayoutFlow = new GroupLayout(normalPanelFlow);
        normalLayoutFlow.setAutoCreateGaps(true);
        normalLayoutFlow.setAutoCreateContainerGaps(true);
        normalPanelFlow.setLayout(normalLayoutFlow);
        normalLayoutFlow.setHorizontalGroup(normalLayoutFlow.createSequentialGroup().addComponent(mxLabelFlow).addComponent(mxFieldFlow).addComponent(dxLabelFlow).addComponent(dxFieldFlow));
        normalLayoutFlow.setVerticalGroup(normalLayoutFlow.createParallelGroup().addComponent(mxLabelFlow).addComponent(mxFieldFlow).addComponent(dxLabelFlow).addComponent(dxFieldFlow));
        uniformPanelFlow = new JPanel();
        t1FieldFlow = new JTextField();
        t1LabelFlow = new JLabel("T1");
        t2FieldFlow = new JTextField();
        t2LabelFlow = new JLabel("T2");
        uniformLayoutFlow = new GroupLayout(uniformPanelFlow);
        uniformLayoutFlow.setAutoCreateGaps(true);
        uniformLayoutFlow.setAutoCreateContainerGaps(true);
        uniformPanelFlow.setLayout(uniformLayoutFlow);
        uniformLayoutFlow.setHorizontalGroup(uniformLayoutFlow.createSequentialGroup().addComponent(t1LabelFlow).addComponent(t1FieldFlow).addComponent(t2LabelFlow).addComponent(t2FieldFlow));
        uniformLayoutFlow.setVerticalGroup(uniformLayoutFlow.createParallelGroup().addComponent(t1LabelFlow).addComponent(t1FieldFlow).addComponent(t2LabelFlow).addComponent(t2FieldFlow));
        exponentialPanelFlow = new JPanel();
        lambdaFieldFlow = new JTextField();
        lambdaLabelFlow = new JLabel("lambda");
        exponentialLayoutFlow = new GroupLayout(exponentialPanelFlow);
        exponentialLayoutFlow.setAutoCreateGaps(true);
        exponentialLayoutFlow.setAutoCreateContainerGaps(true);
        exponentialPanelFlow.setLayout(exponentialLayoutFlow);
        exponentialLayoutFlow.setHorizontalGroup(exponentialLayoutFlow.createSequentialGroup().addComponent(intervalLabelFlow).addComponent(intervalFieldFlow));
        exponentialLayoutFlow.setVerticalGroup(exponentialLayoutFlow.createParallelGroup().addComponent(intervalLabelFlow).addComponent(intervalFieldFlow));
        stochasticLayoutFlow = new GroupLayout(stochasticPanelFlow);
        stochasticLayoutFlow.setAutoCreateGaps(true);
        stochasticLayoutFlow.setAutoCreateContainerGaps(true);
        stochasticPanelFlow.setLayout(stochasticLayoutFlow);
        stochasticLayoutFlow.setVerticalGroup(stochasticLayoutFlow.createSequentialGroup().addComponent(normalPanelFlow).addComponent(uniformPanelFlow).addComponent(exponentialPanelFlow));
        stochasticLayoutFlow.setHorizontalGroup(stochasticLayoutFlow.createParallelGroup().addComponent(normalPanelFlow).addComponent(uniformPanelFlow).addComponent(exponentialPanelFlow));
        truckPartLabel = new JLabel("Доля грузового транспорта");
        truckPartSpinner = new JSpinner();
        truckPartSpinner.setValue(0.3);
        probLabel = new JLabel("Вероятность заезда на парковку");
        probSpinner = new JSpinner();
        probSpinner.setValue(0.5);
        submitButton = new JButton("Готово");
        layout = new GroupLayout(panel);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(label)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(xLabel).addComponent(xSpinner).addComponent(yLabel).addComponent(ySpinner))
                .addComponent(ratesLabel)
                .addGroup(layout.createParallelGroup().addComponent(oneHourLabel).addComponent(oneHourSpinner))
                .addGroup(layout.createParallelGroup().addComponent(oneToThreeHourLabel).addComponent(oneToThreeHourSpinner))
                .addGroup(layout.createParallelGroup().addComponent(moreThreeHoursLabel).addComponent(moreThreeHoursSpinner))
                .addComponent(timeDistribLabel)
                .addComponent(timeDistribComboBox)
                .addComponent(determinePanelTime)
                .addComponent(stochasticPanelTime)
                .addComponent(flowDistribLabel)
                .addComponent(flowDistribComboBox)
                .addComponent(determinePanelFlow)
                .addComponent(stochasticComboBoxFlow)
                .addComponent(truckPartLabel)
                .addComponent(truckPartSpinner)
                .addComponent(probLabel)
                .addComponent(probSpinner)
                .addComponent(submitButton));
        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(label)
                .addGroup(layout.createSequentialGroup().addComponent(xLabel).addComponent(xSpinner).addComponent(yLabel).addComponent(ySpinner))
                .addComponent(ratesLabel)
                .addGroup(layout.createSequentialGroup().addComponent(oneHourLabel).addComponent(oneHourSpinner))
                .addGroup(layout.createSequentialGroup().addComponent(oneToThreeHourLabel).addComponent(oneToThreeHourSpinner))
                .addGroup(layout.createSequentialGroup().addComponent(moreThreeHoursLabel).addComponent(moreThreeHoursSpinner))
                .addComponent(timeDistribLabel)
                .addComponent(timeDistribComboBox)
                .addComponent(determinePanelTime)
                .addComponent(stochasticPanelTime)
                .addComponent(flowDistribLabel)
                .addComponent(flowDistribComboBox)
                .addComponent(determinePanelFlow)
                .addComponent(stochasticComboBoxFlow)
                .addComponent(truckPartLabel)
                .addComponent(truckPartSpinner)
                .addComponent(probLabel)
                .addComponent(probSpinner)
                .addComponent(submitButton));
        panel.setLayout(layout);
//        panel.setPreferredSize(new Dimension(300,100));
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
