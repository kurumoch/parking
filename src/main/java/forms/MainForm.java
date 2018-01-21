package forms;

import JPanels.Surface;
import controllers.Controller;
import javafx.stage.FileChooser;
import models.State;
import models.TileType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by denis on 22.11.2017.
 */
public class MainForm extends JFrame {
    private JPanel panel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu modelMenu;
    private JMenu aboutMenu;
    private JLabel timeLabel;
    private JButton lawnButton;
    private JButton roadButton;
    private JButton parkingButton;
    private JButton doubleParkingButton;
    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JButton rewindButton;
    private JButton slowerButton;
    public Surface graphicsPanel;
    private Controller controller;
    JMenuItem paramsItem;
    JMenuItem slowerItem;
    JMenuItem rewindItem;
    JMenuItem pauseItem;
    JMenuItem stopItem;
    JMenuItem startItem;
    private TileType currentTileType;

    public MainForm(Controller controller) {
        super("Моделирование работы платной парковки");
        this.controller = controller;
        panel = new JPanel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Файл");
        JMenuItem createFileItem = new JMenuItem("Создать..");
        JMenuItem openFileItem = new JMenuItem("Открыть..");
        JMenuItem saveFileItem = new JMenuItem("Сохранить..");
        fileMenu.add(createFileItem);
        fileMenu.add(openFileItem);
        fileMenu.add(saveFileItem);
        modelMenu = new JMenu("Моделирование");

        startItem = new JMenuItem("Пуск");

        stopItem = new JMenuItem("Стоп");

        pauseItem = new JMenuItem("Пауза");

        rewindItem = new JMenuItem("Ускорить");

        slowerItem = new JMenuItem("Замедлить");

        paramsItem = new JMenuItem("Параметры..");
        modelMenu.add(startItem);
        modelMenu.add(stopItem);
        modelMenu.add(pauseItem);
        modelMenu.add(rewindItem);
        modelMenu.add(slowerItem);
        modelMenu.addSeparator();
        modelMenu.add(paramsItem);
        aboutMenu = new JMenu("Справка");
        JMenuItem aboutAuthorsItem = new JMenuItem("О Авторах..");
        JMenuItem aboutProgramm = new JMenuItem("О Программе..");
        aboutMenu.add(aboutAuthorsItem);
        aboutMenu.add(aboutProgramm);
        timeLabel = new JLabel("Время 00:00");
        lawnButton = new JButton("Газон");
        roadButton = new JButton("Дорога");
        parkingButton = new JButton("Парк. место");
        doubleParkingButton = new JButton("Парк. место х2");
        startButton = new JButton("Пуск");
        pauseButton = new JButton("Пауза");
        stopButton = new JButton("Стоп");
        rewindButton = new JButton("Ускорить");
        slowerButton = new JButton("Замедлить");
        GroupLayout layout = new GroupLayout(panel);
        menuBar.add(fileMenu);
        menuBar.add(modelMenu);
        menuBar.add(aboutMenu);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        graphicsPanel = new Surface(controller);
        controller.setSurface(graphicsPanel);
        graphicsPanel.setPreferredSize(new Dimension(400, 400));
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup().addComponent(menuBar).addComponent(timeLabel))
                .addComponent(graphicsPanel).addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lawnButton).addComponent(roadButton).addComponent(parkingButton).addComponent(doubleParkingButton))
                        .addComponent(startButton).addComponent(pauseButton).addComponent(stopButton).addComponent(rewindButton).addComponent(slowerButton)));
        setContentPane(panel);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup().addComponent(menuBar).addComponent(timeLabel))
                .addComponent(graphicsPanel).addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lawnButton).addComponent(roadButton).addComponent(parkingButton).addComponent(doubleParkingButton))
                        .addComponent(startButton).addComponent(pauseButton).addComponent(stopButton).addComponent(rewindButton).addComponent(slowerButton)));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);

        paramsItem.addActionListener(e -> new ParamsForm(controller));
//        controller.setDefaultTiles();
        setEnabledConstructButtons(false);
        setEnableModellingButtons(false);
        startButton.addActionListener(e -> {
            if(controller.getState() == State.CONSTRUCT) {
                setEnabledConstructButtons(false);
                setEnableModellingButtons(true);
                controller.setState(State.MODELLING);
                controller.startModelling();
            }
        });
        MainForm mf = this;
        createFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ParkingCreationForm(controller, mf);
                setEnabledConstructButtons(true);
            }
        });

        openFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                try {

                    ObjectInputStream inputStream = new ObjectInputStream((new FileInputStream(fileChooser.getSelectedFile())));
                    Controller controller1 =  (Controller) inputStream.readObject();
                    inputStream.close();
                    controller.setRectangles(controller1.getRectangles());
                    controller.initParking(controller1.getxSize(), controller1.getySize());
                    controller.setTiles(controller1.getTiles());
                    controller.setCostOfOneHour(controller1.getCostOfOneHour());
                    controller.setCostToThreeHours(controller1.getCostToThreeHours());
                    controller.setCostMoreThreeHours(controller1.getCostMoreThreeHours());
                    controller.setTypeOfThreadTimeOnParking(controller1.getTypeOfThreadTimeOnParking());
//                    controller.setDistributionTimeOnParking(controller1.getDistributionTimeOnParking());
                    controller.setLeftDetermInterval(controller1.getLeftDetermInterval());
                    controller.setRightDetermInterval(controller1.getRightDetermInterval());
                    controller.setTypeOfThreadOfCars(controller1.getTypeOfThreadOfCars());
//                    controller.setDistributionThreadOfCars(controller1.getDistributionThreadOfCars());
                    controller.setPartOfTrucks(controller1.getPartOfTrucks());
                    controller.setProbOfArrivalToParking(controller1.getProbOfArrivalToParking());
                    controller.setMxTime(controller1.getMxTime());
                    controller.setDxTime(controller1.getDxTime());
                    controller.setLambdaTime(controller1.getLambdaTime());
                    controller.setT1Time(controller1.getT1Time());
                    controller.setT2Time(controller1.getT2Time());
                    controller.setMxCars(controller1.getMxCars());
                    controller.setDxCars(controller1.getDxCars());
                    controller.setLambdaCars(controller1.getLambdaCars());
                    controller.setT1Cars(controller1.getT1Cars());
                    controller.setT2Cars(controller1.getT2Cars());
                    controller.setDefaultTiles();
                    graphicsPanel = controller1.getSurface();
                    controller.setSurface(controller1.getSurface());
                    graphicsPanel.setController(controller);
                    setEnabledConstructButtons(true);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

            }
        });

        saveFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showSaveDialog(null);
                try {
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileChooser.getSelectedFile()));
                    outputStream.writeObject(controller);
                    outputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setState(State.CONSTRUCT);
                setEnabledConstructButtons(true);
                setEnableModellingButtons(false);
            }
        });
        graphicsPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(currentTileType != TileType.DOUBLE_PARKING)
                    controller.setTile(e.getX(),e.getY(),currentTileType);
                else controller.setDoubleTile(e.getX(), e.getY(), false);
            }
        });
        lawnButton.addActionListener(e -> currentTileType = TileType.LAWN);
        roadButton.addActionListener(e -> currentTileType = TileType.PARK_ROAD);
        parkingButton.addActionListener(e -> currentTileType = TileType.PARKING);
        doubleParkingButton.addActionListener(e -> currentTileType = TileType.DOUBLE_PARKING);
//        stopButton.addActionListener(e -> System.out.println(controller.toString()));
    }

    public void setEnabledConstructButtons(boolean var){
        lawnButton.setEnabled(var);
        roadButton.setEnabled(var);
        doubleParkingButton.setEnabled(var);
        parkingButton.setEnabled(var);
        startButton.setEnabled(var);
        startItem.setEnabled(var);
    }

    public void setEnableModellingButtons(boolean var){
        pauseButton.setEnabled(var);
        stopButton.setEnabled(var);
        rewindButton.setEnabled(var);
        slowerButton.setEnabled(var);
        pauseItem.setEnabled(var);
        rewindItem.setEnabled(var);
        slowerItem.setEnabled(var);
        stopItem.setEnabled(var);
    }
}
