package forms;

import controllers.Controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Created by denis on 22.11.2017.
 */
public class ParamsForm extends JFrame {
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
    private Controller controller;
    private Boolean flag = false;//флаг для обычной проверки
    private Boolean tflag = false;//флаг для сравнения T1 и T2

    public ParamsForm(Controller controller) {
        super("Изменение параметров");
        this.controller = controller;
        JPanel panel = new JPanel();
        label = new JLabel("Введите размер парковки");
        xLabel = new JLabel("x: ");
        yLabel = new JLabel("y: ");
        xSpinner = new JSpinner();
        xSpinner.setModel(new SpinnerNumberModel(5,-999999,999999,1));
        ySpinner = new JSpinner();
        ySpinner.setModel(new SpinnerNumberModel(5,-999999,999999,1));
        ratesLabel = new JLabel("Тариф");
        oneHourLabel = new JLabel("до 1ч");
        oneToThreeHourLabel = new JLabel("от 1ч до 3ч");
        moreThreeHoursLabel = new JLabel("более 3ч");
        oneHourSpinner = new JSpinner();
        oneHourSpinner.setModel(new SpinnerNumberModel(200,-999999,999999,10));
        oneToThreeHourSpinner = new JSpinner();
        oneToThreeHourSpinner.setModel(new SpinnerNumberModel(150,-999999,999999,10));
        moreThreeHoursSpinner = new JSpinner();
        moreThreeHoursSpinner.setModel(new SpinnerNumberModel(100,-999999,999999,10));
        timeDistribLabel = new JLabel("Распределение времени нахождения на парковке");
        timeDistribComboBox = new JComboBox<String>(new String[]{"Детерминированный", "Стохастический"});
        determinePanelTime = new JPanel();
        intervalFieldTime = new JTextField();
        intervalLabelTime = new JLabel("Интервал");
        intervalFieldTime.setText("5000");
        determineLayoutTime = new GroupLayout(determinePanelTime);
        determineLayoutTime.setAutoCreateGaps(true);
        determineLayoutTime.setAutoCreateContainerGaps(true);
        determinePanelTime.setLayout(determineLayoutTime);
        determineLayoutTime.setHorizontalGroup(determineLayoutTime.createSequentialGroup().addComponent(intervalLabelTime).addComponent(intervalFieldTime));
        determineLayoutTime.setVerticalGroup(determineLayoutTime.createParallelGroup().addComponent(intervalLabelTime).addComponent(intervalFieldTime));
        stochasticPanelTime = new JPanel();
        stochasticComboBoxTime = new JComboBox<String>(new String[]{"Нормальный", "Показательный", "Равномерный"});
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
        exponentialLayoutTime.setHorizontalGroup(exponentialLayoutTime.createSequentialGroup().addComponent(lambdaLabelTime).addComponent(lambdaFieldTime));
        exponentialLayoutTime.setVerticalGroup(exponentialLayoutTime.createParallelGroup().addComponent(lambdaLabelTime).addComponent(lambdaFieldTime));
        stochasticLayoutTime = new GroupLayout(stochasticPanelTime);
        stochasticLayoutTime.setAutoCreateGaps(true);
        stochasticLayoutTime.setAutoCreateContainerGaps(true);
        stochasticPanelTime.setLayout(stochasticLayoutTime);
        stochasticLayoutTime.setVerticalGroup(stochasticLayoutTime.createSequentialGroup().addComponent(stochasticComboBoxTime).addComponent(normalPanelTime).addComponent(uniformPanelTime).addComponent(exponentialPanelTime));
        stochasticLayoutTime.setHorizontalGroup(stochasticLayoutTime.createParallelGroup().addComponent(stochasticComboBoxTime).addComponent(normalPanelTime).addComponent(uniformPanelTime).addComponent(exponentialPanelTime));
        flowDistribLabel = new JLabel("Поток автомобилей");
        flowDistribComboBox = new JComboBox<String>(new String[]{"Детерминированный", "Стохастический"});
        determinePanelFlow = new JPanel();
        intervalFieldFlow = new JTextField();
        intervalLabelFlow = new JLabel("Интервал");
        intervalFieldFlow.setText("5000");
        determineLayoutFlow = new GroupLayout(determinePanelFlow);
        determineLayoutFlow.setAutoCreateGaps(true);
        determineLayoutFlow.setAutoCreateContainerGaps(true);
        determinePanelFlow.setLayout(determineLayoutFlow);
        determineLayoutFlow.setHorizontalGroup(determineLayoutFlow.createSequentialGroup().addComponent(intervalLabelFlow).addComponent(intervalFieldFlow));
        determineLayoutFlow.setVerticalGroup(determineLayoutFlow.createParallelGroup().addComponent(intervalLabelFlow).addComponent(intervalFieldFlow));
        stochasticPanelFlow = new JPanel();
        stochasticComboBoxFlow = new JComboBox<String>(new String[]{"Нормальный", "Показательный", "Равномерный"});
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
        exponentialLayoutFlow.setHorizontalGroup(exponentialLayoutFlow.createSequentialGroup().addComponent(lambdaLabelFlow).addComponent(lambdaFieldFlow));
        exponentialLayoutFlow.setVerticalGroup(exponentialLayoutFlow.createParallelGroup().addComponent(lambdaLabelFlow).addComponent(lambdaFieldFlow));
        stochasticLayoutFlow = new GroupLayout(stochasticPanelFlow);
        stochasticLayoutFlow.setAutoCreateGaps(true);
        stochasticLayoutFlow.setAutoCreateContainerGaps(true);
        stochasticPanelFlow.setLayout(stochasticLayoutFlow);
        stochasticLayoutFlow.setVerticalGroup(stochasticLayoutFlow.createSequentialGroup().addComponent(stochasticComboBoxFlow).addComponent(normalPanelFlow).addComponent(uniformPanelFlow).addComponent(exponentialPanelFlow));
        stochasticLayoutFlow.setHorizontalGroup(stochasticLayoutFlow.createParallelGroup().addComponent(stochasticComboBoxFlow).addComponent(normalPanelFlow).addComponent(uniformPanelFlow).addComponent(exponentialPanelFlow));
        truckPartLabel = new JLabel("Доля грузового транспорта");
        truckPartSpinner = new JSpinner();
        truckPartSpinner.setModel(new SpinnerNumberModel(0.5,-999999.0,999999.0,0.1));
        probLabel = new JLabel("Вероятность заезда на парковку");
        probSpinner = new JSpinner();
        probSpinner.setModel(new SpinnerNumberModel(0.5,-999999.0,999999.0,0.1));
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
                .addComponent(stochasticPanelFlow)
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
                .addComponent(stochasticPanelFlow)
                .addComponent(truckPartLabel)
                .addComponent(truckPartSpinner)
                .addComponent(probLabel)
                .addComponent(probSpinner)
                .addComponent(submitButton));
        panel.setLayout(layout);
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        stochasticPanelTime.setVisible(false);
        stochasticPanelFlow.setVisible(false);

        timeDistribComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timeDistribComboBox.getSelectedItem() == "Стохастический") {
                    stochasticPanelTime.setVisible(true);
                    determinePanelTime.setVisible(false);
                } else {
                    stochasticPanelTime.setVisible(false);
                    determinePanelTime.setVisible(true);
                }
            }
        });

        flowDistribComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flowDistribComboBox.getSelectedItem() == "Стохастический") {
                    stochasticPanelFlow.setVisible(true);
                    determinePanelFlow.setVisible(false);
                } else {
                    stochasticPanelFlow.setVisible(false);
                    determinePanelFlow.setVisible(true);
                }
            }
        });

        exponentialPanelTime.setVisible(false);
        uniformPanelTime.setVisible(false);
        exponentialPanelFlow.setVisible(false);
        uniformPanelFlow.setVisible(false);

        stochasticComboBoxTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (stochasticComboBoxTime.getSelectedItem() == "Нормальный") { // свитч мозг ебет, в пизду
                    normalPanelTime.setVisible(true);
                    exponentialPanelTime.setVisible(false);
                    uniformPanelTime.setVisible(false);
                } else {
                    if (stochasticComboBoxTime.getSelectedItem() == "Равномерный") { //привет из индии
                        normalPanelTime.setVisible(false);
                        exponentialPanelTime.setVisible(false);
                        uniformPanelTime.setVisible(true);
                    } else {
                        normalPanelTime.setVisible(false);
                        exponentialPanelTime.setVisible(true); // почему-то в этом лейауте нет дочерних контейнеров
                        uniformPanelTime.setVisible(false);
                    }
                }
            }
        });

        stochasticComboBoxFlow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (stochasticComboBoxFlow.getSelectedItem() == "Нормальный") { // свитч мозг ебет, в пизду
                    normalPanelFlow.setVisible(true);
                    exponentialPanelFlow.setVisible(false);
                    uniformPanelFlow.setVisible(false);
                } else {
                    if (stochasticComboBoxFlow.getSelectedItem() == "Равномерный") { //привет из индии
                        normalPanelFlow.setVisible(false);
                        exponentialPanelFlow.setVisible(false);
                        uniformPanelFlow.setVisible(true);
                    } else {
                        normalPanelFlow.setVisible(false);
                        exponentialPanelFlow.setVisible(true);
                        uniformPanelFlow.setVisible(false);
                    }
                }
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 controller.setCostOfOneHour((int)oneHourSpinner.getValue());
                controller.setCostToThreeHours((int)oneToThreeHourSpinner.getValue());
                controller.setCostMoreThreeHours((int)moreThreeHoursSpinner.getValue());
                String type;
                if(timeDistribComboBox.getSelectedItem() == "Стохастический"){
                    type = (String) stochasticComboBoxTime.getSelectedItem();
                }
                else type = (String) timeDistribComboBox.getSelectedItem();
                controller.setTypeOfThreadTimeOnParking(type);
                if(!Objects.equals(t1FieldFlow.getText(), "") && !Objects.equals(t2FieldFlow.getText(), "")) {
                    controller.setLeftDetermInterval(Integer.valueOf(t1FieldFlow.getText()));
                    controller.setRightDetermInterval(Integer.valueOf(t2FieldFlow.getText()));
                }
                if(flowDistribComboBox.getSelectedItem() == "Стохастический"){
                    type = (String) stochasticComboBoxFlow.getSelectedItem();
                }
                else type = (String) flowDistribComboBox.getSelectedItem();
                controller.setTypeOfThreadOfCars(type);
                controller.setPartOfTrucks((double)truckPartSpinner.getValue());
                controller.setProbOfArrivalToParking((double)probSpinner.getValue());
                if(!Objects.equals(mxFieldTime.getText(), "") && !Objects.equals(dxFieldTime.getText(), "")){
                controller.setMxTime(Integer.valueOf(mxFieldTime.getText()));
                controller.setDxTime(Integer.valueOf(dxFieldTime.getText()));
                }
                if(!Objects.equals(lambdaFieldTime.getText(), ""))
                controller.setLambdaTime(Integer.valueOf(lambdaFieldTime.getText()));
                if(!Objects.equals(t1FieldTime.getText(), "") && !Objects.equals(t2FieldTime.getText(), "")) {
                    controller.setT1Time(Integer.valueOf(t1FieldTime.getText()));
                    controller.setT2Time(Integer.valueOf(t2FieldTime.getText()));
                }
                if(!Objects.equals(mxFieldFlow.getText(), "") && !Objects.equals(dxFieldFlow.getText(), "")) {
                    controller.setMxCars(Integer.valueOf(mxFieldFlow.getText()));
                    controller.setDxCars(Integer.valueOf(dxFieldFlow.getText()));
                }
                if(!Objects.equals(lambdaFieldFlow.getText(), ""))
                controller.setLambdaCars(Integer.valueOf(lambdaFieldFlow.getText()));
                if(!Objects.equals(t1FieldFlow.getText(), "") && !Objects.equals(t2FieldFlow.getText(), "")) {
                    controller.setT1Cars(Integer.valueOf(t1FieldFlow.getText()));
                    controller.setT2Cars(Integer.valueOf(t2FieldFlow.getText()));
                }
                if(!Objects.equals(intervalFieldFlow.getText(), ""))
                controller.setIntervalCars(Integer.valueOf(intervalFieldFlow.getText()));
                if(!Objects.equals(intervalFieldTime.getText(), ""))
                controller.setIntervalTime(Integer.valueOf(intervalFieldTime.getText()));
                controller.initParking((int)xSpinner.getValue(), (int)ySpinner.getValue());
                controller.reinitParking((int)xSpinner.getValue(),(int)ySpinner.getValue());
                controller.drawTiles();
                dispose();
            }

        });
        //проверки спинеров
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
        oneHourSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ImageIcon icon = new ImageIcon("error.png");
                if ((int)oneHourSpinner.getValue()>400){
                    oneHourLabel.setForeground(Color.red);
                    JOptionPane.showMessageDialog(new JApplet(),"Максимальное значение - '400'!","Перебор",0,icon);
                    oneHourSpinner.setValue(400);
                    oneHourLabel.setForeground(Color.black);
                }
                if ((int)oneHourSpinner.getValue()<0){
                    oneHourLabel.setForeground(Color.red);
                    JOptionPane.showMessageDialog(new JApplet(),"Минимальное значение - '0'!","Недобор",0,icon);
                    oneHourSpinner.setValue(0);
                    oneHourLabel.setForeground(Color.black);
                }
            }
        });
        oneToThreeHourSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ImageIcon icon = new ImageIcon("error.png");
                if ((int)oneToThreeHourSpinner.getValue()>350){
                    oneToThreeHourLabel.setForeground(Color.red);
                    JOptionPane.showMessageDialog(new JApplet(),"Максимальное значение - '350'!","Перебор",0,icon);
                    oneToThreeHourSpinner.setValue(350);
                    oneToThreeHourLabel.setForeground(Color.black);
                }
                if ((int)oneToThreeHourSpinner.getValue()<50){
                    oneToThreeHourLabel.setForeground(Color.red);
                    JOptionPane.showMessageDialog(new JApplet(),"Минимальное значение - '50'!","Недобор",0,icon);
                    oneToThreeHourSpinner.setValue(50);
                    oneToThreeHourLabel.setForeground(Color.black);
                }
            }
        });
        moreThreeHoursSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ImageIcon icon = new ImageIcon("error.png");
                if ((int)moreThreeHoursSpinner.getValue()>300){
                    moreThreeHoursLabel.setForeground(Color.red);
                    JOptionPane.showMessageDialog(new JApplet(),"Максимальное значение - '300'!","Перебор",0,icon);
                    moreThreeHoursSpinner.setValue(300);
                    moreThreeHoursLabel.setForeground(Color.black);
                }
                if ((int)moreThreeHoursSpinner.getValue()<70){
                    moreThreeHoursLabel.setForeground(Color.red);
                    JOptionPane.showMessageDialog(new JApplet(),"Минимальное значение - '70'!","Недобор",0,icon);
                    moreThreeHoursSpinner.setValue(70);
                    moreThreeHoursLabel.setForeground(Color.black);
                }
            }
        });
        truckPartSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ImageIcon icon = new ImageIcon("error.png");
                if ((double)truckPartSpinner.getValue()>1.0){
                    truckPartLabel.setForeground(Color.red);
                    JOptionPane.showMessageDialog(new JApplet(),"Максимальное значение - '1'!","Перебор",0,icon);
                    truckPartSpinner.setValue(1.0);
                    truckPartLabel.setForeground(Color.black);
                }
                if ((double)truckPartSpinner.getValue()<0.0){
                    truckPartLabel.setForeground(Color.red);
                    JOptionPane.showMessageDialog(new JApplet(),"Минимальное значение - '0'!","Недобор",0,icon);
                    truckPartSpinner.setValue(0.0);
                    truckPartLabel.setForeground(Color.black);
                }
            }
        });
        probSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ImageIcon icon = new ImageIcon("error.png");
                if ((double)probSpinner.getValue()>1.0){
                    probLabel.setForeground(Color.red);
                    JOptionPane.showMessageDialog(new JApplet(),"Максимальное значение - '1'!","Перебор",0,icon);
                    probSpinner.setValue(1.0);
                    probLabel.setForeground(Color.black);
                }
                if ((double)probSpinner.getValue()<0.0){
                    probLabel.setForeground(Color.red);
                    JOptionPane.showMessageDialog(new JApplet(),"Минимальное значение - '0'!","Недобор",0,icon);
                    probSpinner.setValue(0.0);
                    probLabel.setForeground(Color.black);
                }
            }
        });
        //проверки спинеров
        //добавление слушателей на текстовые поля
        intervalFieldTime.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                flag = check10000(intervalFieldTime,flag);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                flag = check10000(intervalFieldTime,flag);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                flag = check10000(intervalFieldTime,flag);
            }
        });
        intervalFieldFlow.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                flag = check10000(intervalFieldFlow,flag);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                flag = check10000(intervalFieldFlow,flag);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                flag = check10000(intervalFieldFlow,flag);
            }
        });
        mxFieldTime.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                flag = check10000(mxFieldTime,flag);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                flag = check10000(mxFieldTime,flag);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                flag = check10000(mxFieldTime,flag);
            }
        });
        dxFieldTime.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                flag = check10000(dxFieldTime,flag);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                flag = check10000(dxFieldTime,flag);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                flag = check10000(dxFieldTime,flag);
            }
        });
        mxFieldFlow.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                flag = check10000(mxFieldFlow,flag);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                flag = check10000(mxFieldFlow,flag);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                flag = check10000(mxFieldFlow,flag);
            }
        });
        dxFieldFlow.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                flag = check10000(dxFieldFlow,flag);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                flag = check10000(dxFieldFlow,flag);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                flag = check10000(dxFieldFlow,flag);
            }
        });
        lambdaFieldTime.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                flag = check100(lambdaFieldTime,flag);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                flag = check100(lambdaFieldTime,flag);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                flag = check100(lambdaFieldTime,flag);
            }
        });
        lambdaFieldFlow.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                flag = check100(lambdaFieldFlow,flag);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                flag = check100(lambdaFieldFlow,flag);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                flag = check100(lambdaFieldFlow,flag);
            }
        });
        t1FieldTime.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                flag = check10000(t1FieldTime,flag);
                tflag = checkt(t1FieldTime,t2FieldTime,tflag);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                flag = check10000(t1FieldTime,flag);
                tflag = checkt(t1FieldTime,t2FieldTime,tflag);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                flag = check10000(t1FieldTime,flag);
                tflag = checkt(t1FieldTime,t2FieldTime,tflag);
            }
        });
        t1FieldFlow.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                flag = check10000(t1FieldFlow,flag);
                tflag = checkt(t1FieldFlow,t2FieldFlow,tflag);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                flag = check10000(t1FieldFlow,flag);
                tflag = checkt(t1FieldFlow,t2FieldFlow,tflag);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                flag = check10000(t1FieldFlow,flag);
                tflag = checkt(t1FieldFlow,t2FieldFlow,tflag);
            }
        });
        t2FieldTime.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                flag = check10000(t2FieldTime,flag);
                tflag = checkt(t1FieldTime,t2FieldTime,tflag);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                flag = check10000(t2FieldTime,flag);
                tflag = checkt(t1FieldTime,t2FieldTime,tflag);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                flag = check10000(t2FieldTime,flag);
                tflag = checkt(t1FieldTime,t2FieldTime,tflag);
            }
        });
        t2FieldFlow.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                flag = check10000(t2FieldFlow,flag);
                tflag = checkt(t1FieldFlow,t2FieldFlow,tflag);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                flag = check10000(t2FieldFlow,flag);
                tflag = checkt(t1FieldFlow,t2FieldFlow,tflag);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                flag = check10000(t2FieldFlow,flag);
                tflag = checkt(t1FieldFlow,t2FieldFlow,tflag);
            }
        });
        //добавление слушателей на текстовые поля
    }
    //методы проверки для текстовых полей
    private boolean check10000(JTextField tf, Boolean flag){//проверка полей с макс значением 10000
        ImageIcon eicon = new ImageIcon("error.png");
        ImageIcon cicon = new ImageIcon("correct.png");

        try {
            if (Integer.parseInt(tf.getText()) < 0) {
                tf.setForeground(Color.red);
                JOptionPane.showMessageDialog(new JApplet(), "Минимальное значение - '0'!", "Недобор", 0,eicon);
                return true;
            }
            if (Integer.parseInt(tf.getText()) > 10000) {
                tf.setForeground(Color.red);
                JOptionPane.showMessageDialog(new JApplet(), "Максимальное значение - '10000'!", "Перебор", 0,eicon);
                return true;
            }
            if (Integer.parseInt(tf.getText()) >= 0 && Integer.parseInt(tf.getText())<10001 && flag)  {
                tf.setForeground(Color.black);
                JOptionPane.showMessageDialog(new JApplet(), "Значение приведено в допустимый диапозон", "Исправлено", 0,cicon);
                return false;
            }
        }
        catch (NumberFormatException e){
            tf.setForeground(Color.red);
            JOptionPane.showMessageDialog(new JApplet(), "Введите число больше нуля!", "Ошибка", 0,eicon);
            return true;
        }
        return false;
    }
    private boolean check100(JTextField tf, Boolean flag){//проверка полей с макс значением 100
        ImageIcon eicon = new ImageIcon("error.png");
        ImageIcon cicon = new ImageIcon("correct.png");

        try {
            if (Integer.parseInt(tf.getText()) < 0) {
                tf.setForeground(Color.red);
                JOptionPane.showMessageDialog(new JApplet(), "Минимальное значение - '0'!", "Недобор", 0,eicon);
                return true;
            }
            if (Integer.parseInt(tf.getText()) > 100) {
                tf.setForeground(Color.red);
                JOptionPane.showMessageDialog(new JApplet(), "Максимальное значение - '100'!", "Перебор", 0,eicon);
                return true;
            }
            if (Integer.parseInt(tf.getText()) >= 0 && Integer.parseInt(tf.getText())<101 && flag)  {
                tf.setForeground(Color.black);
                JOptionPane.showMessageDialog(new JApplet(), "Значение приведено в допустимый диапозон", "Исправлено", 0,cicon);
                return false;
            }
        }
        catch (NumberFormatException e){
            tf.setForeground(Color.red);
            JOptionPane.showMessageDialog(new JApplet(), "Введите число больше нуля!", "Ошибка", 0,eicon);
            return true;
        }
        return false;
    }
    private boolean checkt(JTextField tf1, JTextField tf2, Boolean flag1){//проверка T1<T2
        ImageIcon eicon = new ImageIcon("error.png");
        ImageIcon cicon = new ImageIcon("correct.png");
        if (tf1.getText()!="" && tf2.getText()!=""){//хуй знает почему проходит проверку с пустым полем
            if (Integer.parseInt(tf1.getText()) >= Integer.parseInt(tf2.getText())){
                tf1.setForeground(Color.red);
                tf2.setForeground(Color.red);
                JOptionPane.showMessageDialog(new JApplet(), "T1 должен быть меньше T2", "Ошибка", 0,eicon);
                return true;
            }
            if (Integer.parseInt(tf1.getText()) < Integer.parseInt(tf2.getText()) && flag1){
                tf1.setForeground(Color.black);
                tf2.setForeground(Color.black);
                JOptionPane.showMessageDialog(new JApplet(), "Значения исправлены", "Исправлено", 0,cicon);
                return false;
            }
        }
        return false;
    }
    //методы проверки для текстовых полей
}

