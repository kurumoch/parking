package forms;

import JPanels.Surface;
import controllers.Controller;
import drawing.DrawLines;
import models.Car2D;
import models.TileType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

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
    private Surface graphicsPanel;
    private Controller controller;
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
        JMenuItem startItem = new JMenuItem("Пуск");
        JMenuItem stopItem = new JMenuItem("Стоп");
        JMenuItem pauseItem = new JMenuItem("Пауза");
        JMenuItem rewindItem = new JMenuItem("Ускорить");
        JMenuItem slowerItem = new JMenuItem("Замедлить");
        JMenuItem paramsItem = new JMenuItem("Параметры..");
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
        graphicsPanel = new Surface(controller);
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
        controller.setSurface(graphicsPanel);
        paramsItem.addActionListener(e -> new ParamsForm(controller));
        startButton.addActionListener(e -> {
            controller.setDefaultTiles();

        });


        graphicsPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
               // if(currentTileType != TileType.DOUBLE_PARKING)
                 //   controller.setTile(e.getX(),e.getY(),currentTileType);
//                else controller.setDoubleTile(e.getX(), e.getY(), /* is hotkey pressed*/);
            }
        });
        lawnButton.addActionListener(e -> currentTileType = TileType.LAWN);
        roadButton.addActionListener(e -> currentTileType = TileType.PARK_ROAD);
        parkingButton.addActionListener(e -> currentTileType = TileType.PARKING);
        doubleParkingButton.addActionListener(e -> currentTileType = TileType.DOUBLE_PARKING);
//        stopButton.addActionListener(e -> System.out.println(controller.toString()));
    }
}
