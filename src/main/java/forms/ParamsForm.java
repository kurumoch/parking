package forms;

import controllers.Controller;
import org.apache.commons.math3.distribution.RealDistribution;

import javax.swing.*;
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

    public ParamsForm(Controller controller) {
        super("Изменение параметров");
        this.controller = controller;
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
                dispose();
            }
        });
    }
}
