package forms;

import JPanels.Surface;
import controllers.Controller;
import models.State;
import models.TileType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by denis on 22.11.2017.
 */
public class MainForm extends JFrame {
    private JPanel panel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    JMenuItem createFileItem;
    JMenuItem openFileItem;
    JMenuItem saveFileItem;
    private JMenu modelMenu;
    private JMenu aboutMenu;
    private JLabel timeLabel;
    private JLabel freePlacesNumberLabel;
    private JLabel freePlacesNumberLabelImg;
    private JLabel moneyLabel;
    private JLabel moneyLabelImg;
    private JButton lawnButton;
    private JButton roadButton;
    private JButton parkingButton;
    private JButton doubleParkingButton;
    private ImageIcon lawnIcon;
    private ImageIcon roadIcon;
    private ImageIcon parkingIcon;
    private ImageIcon doubleParkingIcon;
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
    Timer tt;
    private TileType currentTileType;

    public MainForm(Controller controller) {
        super("Моделирование работы платной парковки");
        this.controller = controller;
        panel = new JPanel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Файл");
        createFileItem = new JMenuItem("Создать..");
        openFileItem = new JMenuItem("Открыть..");
        saveFileItem = new JMenuItem("Сохранить..");
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
        JMenuItem aboutAuthorsItem = new JMenuItem("Об Авторах..");
        JMenuItem aboutProgramm = new JMenuItem("О Программе..");
        aboutMenu.add(aboutAuthorsItem);
        aboutMenu.add(aboutProgramm);
        timeLabel = new JLabel("Время 00:00");
        freePlacesNumberLabel = new JLabel(" ");
        moneyLabel = new JLabel(" ");
        lawnButton = new JButton();
        lawnIcon = new ImageIcon("lawn.png");
        lawnButton.setIcon(lawnIcon);
        roadButton = new JButton();
        roadIcon = new ImageIcon("road.png");
        roadButton.setIcon(roadIcon);
        parkingButton = new JButton();
        parkingIcon = new ImageIcon("park.png");
        parkingButton.setIcon(parkingIcon);
        doubleParkingButton = new JButton();
        doubleParkingIcon= new ImageIcon("doublepark.png");
        doubleParkingButton.setIcon(doubleParkingIcon);
        startButton = new JButton("Пуск");
        pauseButton = new JButton("Пауза");
        stopButton = new JButton("Стоп");
        rewindButton = new JButton("Ускорить");
        slowerButton = new JButton("Замедлить");
        ImageIcon freeicon = new ImageIcon("yplaces.png");
        ImageIcon nofreeicon = new ImageIcon("nplaces.png");
        ImageIcon moneyicon = new ImageIcon("money.png");
        freePlacesNumberLabelImg = new JLabel(freeicon);
        moneyLabelImg = new JLabel(moneyicon);
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
                .addGroup(layout.createSequentialGroup().addComponent(menuBar).addComponent(timeLabel).addGroup(layout.createParallelGroup()
                        .addComponent(freePlacesNumberLabelImg).addComponent(freePlacesNumberLabel))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(moneyLabelImg).addComponent(moneyLabel)))
                .addComponent(graphicsPanel).addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lawnButton).addComponent(roadButton).addComponent(parkingButton).addComponent(doubleParkingButton))
                        .addComponent(startButton).addComponent(pauseButton).addComponent(stopButton).addComponent(rewindButton).addComponent(slowerButton)));
        setContentPane(panel);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup().addComponent(menuBar).addComponent(timeLabel).addGroup(layout.createSequentialGroup()
                        .addComponent(freePlacesNumberLabelImg).addComponent(freePlacesNumberLabel))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(moneyLabelImg).addComponent(moneyLabel)))
                .addComponent(graphicsPanel).addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lawnButton).addComponent(roadButton).addComponent(parkingButton).addComponent(doubleParkingButton))
                        .addComponent(startButton).addGroup(layout.createSequentialGroup()
                                .addComponent(pauseButton)/*.addComponent(freePlacesNumberLabelImg).addComponent(freePlacesNumberLabel)*/)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(stopButton)/*.addComponent(moneyLabelImg).addComponent(moneyLabel)*/)
                .addComponent(rewindButton).addComponent(slowerButton)));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);

        paramsItem.addActionListener(e -> new ParamsForm(controller));
//        controller.drawTiles();
        setEnabledConstructButtons(false);
        setEnableModellingButtons(false);
        startButton.addActionListener(e -> {
            if(controller.getState() == State.CONSTRUCT) {
                setEnabledConstructButtons(false);
                setEnableModellingButtons(true);
                controller.setState(State.MODELLING);
                  controller.reinitParking(controller.getTILES_X(), controller.getTILES_Y());
                controller.startModelling();
                controller.startTimer();
                controller.setStartMills(controller.getElapsedMills());
                tt.start();
            }
            if(controller.getState() == State.MODELLING){
                setEnableModellingButtons(true);
                setEnabledConstructButtons(false);
                controller.startTimer();
                tt.start();
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

                    controller.initParking(controller1.getxSize()-1, controller1.getySize()-1);
                    controller.reinitParking(controller1.getxSize()-1, controller1.getySize()-1);
                    controller.setTiles(controller1.getTiles());
                    controller.drawTiles();
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
//                    Object[] array = new Object[24];
//                    array[0] = controller.getTILES_X();
//                    array[1] = controller.getTILES_Y();
//                    array[2] = controller.getRectangles();
//                    array[3] = controller.getTiles();
//                    array[4] = controller.getCostOfOneHour();
//                    array[5] = controller.getCostToThreeHours() ;
//                    array[6] = controller.getCostMoreThreeHours();
//                    array[7] = controller.getTypeOfThreadTimeOnParking();
//                    array[8] = controller.getLeftDetermInterval();
//                    array[9] = controller.getRightDetermInterval();
//                    array[10] = controller.getTypeOfThreadOfCars();
//                    array[11] = controller.getPartOfTrucks();
//                    array[12] = controller.getProbOfArrivalToParking();
//                    array[13] = controller.getMxTime();
//                    array[14] = controller.getDxTime();
//                    array[15] = controller.getLambdaTime();
//                    array[16] = controller.getT1Time();
//                    array[17] = controller.getT2Time() ;
//                    array[18] = controller.getMxCars();
//                    array[19] = controller.getDxCars();
//                    array[20] = controller.getLambdaCars();
//                    array[21] = controller.getT1Cars();
//                    array[22] = controller.getT2Cars();
//                    array[23] = controller.getSurface();
                    outputStream.writeObject(controller);
                    outputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        rewindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.faster();
            }
        });

        slowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.slower();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setState(State.CONSTRUCT);
                controller.stopTimer();
                setEnabledConstructButtons(true);
                setEnableModellingButtons(false);
                controller.setVehicles(new CopyOnWriteArrayList<>());
                controller.drawTiles();
                tt.stop();
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.stopTimer();
                setEnableModellingButtons(false);
                startButton.setEnabled(true);
                tt.stop();
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

        aboutAuthorsItem.addActionListener(e -> new AboutAuthorsForm());
        aboutProgramm.addActionListener(e -> new AboutProgramForm());


        tt = new Timer(controller.getDefaultDelay(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.getT()!=null) {
                    long milliseconds = controller.getElapsedMills() - controller.getStartMills();
                    String time = String.format("Время %02d:%02d", milliseconds / 360, (milliseconds % 360) / 6);
                    timeLabel.setText(time);
                    freePlacesNumberLabel.setText("" + controller.getFreeParkingSpace().size());
                    if (controller.getFreeParkingSpace().size()!=0)
                        freePlacesNumberLabelImg.setIcon(freeicon);
                    else freePlacesNumberLabelImg.setIcon(nofreeicon);
                    moneyLabel.setText(" " + controller.getMoney());
                    controller.setElapsedMills(controller.getElapsedMills() + controller.getDelay());
                }
            }
        });
        tt.start();
    }

    public void setEnabledConstructButtons(boolean var){
        lawnButton.setEnabled(var);
        saveFileItem.setEnabled(var);
        roadButton.setEnabled(var);
        doubleParkingButton.setEnabled(var);
        parkingButton.setEnabled(var);
        startButton.setEnabled(var);
        startItem.setEnabled(var);
    }

    public void setTimeLabel(String text){
        timeLabel.setText(text);
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
