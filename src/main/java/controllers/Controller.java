package controllers;

import JPanels.Surface;
import drawing.DrawLines;
import drawing.DrawTiles;
import drawing.DrawRect;
import models.Pair;
import models.State;
import models.TileType;
import models.Vehicle;
import org.jgrapht.graph.*;
import threads.CarsCreator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import static models.TileType.*;

public class Controller implements Serializable {

    private TileType[][] tiles;
    private Rectangle[][] rectangles;
    private static final int TILES_X = 13;
    private static final int TILES_Y = 14;
    private int xSize;
    private int ySize;
    private Surface surface;
    private State state;
    public CopyOnWriteArrayList<Vehicle> vehicles;
    private boolean[][] isEmpty;
    private int costOfOneHour;
    private int costToThreeHours;
    private int costMoreThreeHours;
    private String typeOfThreadTimeOnParking;
    private int leftDetermInterval;
    private int rightDetermInterval;
    private String typeOfThreadOfCars;
    private double partOfTrucks;
    private double probOfArrivalToParking;
    private int mxTime;
    private int dxTime;
    private int lambdaTime;
    private int t1Time;
    private int t2Time;
    private int mxCars;
    private int dxCars;
    private int lambdaCars;
    private int t1Cars;
    private int t2Cars;
    private int intervalCars;
    private int intervalTime;
    private int entrance, exit;
    private SimpleGraph<Pair<TileType, Pair<Integer, Integer>>, DefaultEdge> graph;
    private ArrayList<Pair<TileType, Pair<Integer, Integer>>> allVertexes;
    private  ArrayList<Pair<TileType, Pair<Integer, Integer>>> usedParkingPlaces;
    private LinkedList<Pair<Integer,Integer>> freeParkingSpace = new LinkedList<>();
    int a;
    int b;
    public int widthh;
    Timer t;
    CarsCreator carsCreator;
    int defaultDelay;
    long startMills;
    long elapsedMills;


    public Controller() {
        typeOfThreadOfCars = "Детерминированный";
        typeOfThreadTimeOnParking = "Детерминированный";
        intervalCars = 200;
        intervalTime = 50000;
        probOfArrivalToParking = 0.5;
        vehicles = new CopyOnWriteArrayList<>();
        startMills = System.currentTimeMillis();
        elapsedMills = startMills;
        defaultDelay = 20;
    }

    private void initEmptyParking(){
        isEmpty = new boolean[TILES_X][TILES_Y];
        for (int i = 0; i < TILES_X; i++) {
            for (int j = 0; j < TILES_X; j++) {
                isEmpty[i][j] = true;
            }
        }
    }

    private void calcEntranceExit(int x, int y){
        widthh = x;
        state = State.CONSTRUCT;
        xSize = ++x;
        ySize = ++y;
        float mid_x = ((float) TILES_X) / 2;
        a = Math.round(mid_x - ((float) xSize) / 2) - 1;
        b = Math.round(mid_x + ((float) xSize) / 2) - 1;
       tiles = new TileType[TILES_X][TILES_Y];
    }

    private void recalcEntranceExit(int x, int y){
        state = State.CONSTRUCT;
        xSize = ++x;
        ySize = ++y;
        float mid_x = ((float) TILES_X) / 2;
        a = Math.round(mid_x - ((float) xSize) / 2) - 1;
        b = Math.round(mid_x + ((float) xSize) / 2) - 1;
//        tiles = new TileType[TILES_X][TILES_Y];
    }

    private void fillDefaultTiles(){
        for (int j = 0; j < TILES_Y; j++) {
            for (int i = 0; i < TILES_X; i++) {
                tiles[i][j] = LAWN;
                if (i > TILES_X - ySize - 1 && i < TILES_X - 1) {
                    if (j < b + 1 && j > a + 1)
                        tiles[i][j] = PARKING;
                    if (j == b + 1 || j == a + 1)
                        tiles[i][j] = PARK_ROAD;
                }
                if (i == TILES_X - ySize - 1 && j <= b + 1 && j >= a + 1) {
                    tiles[i][j] = PARK_ROAD;
                }
                if (i == TILES_X - 1)
                    tiles[i][j] = ROAD;
            }
        }
    }

    public void initParking(int x, int y) {
        calcEntranceExit(x,y);
        initEmptyParking();
        fillDefaultTiles();
        entrance = b+1;
        exit = a+1;
        vehicles = new CopyOnWriteArrayList<>();
//        initGraph();
    }

    public void reinitParking(int x, int y) {
        recalcEntranceExit(x,y);
        initEmptyParking();
        entrance = b+1;
        exit = a+1;
        vehicles = new CopyOnWriteArrayList<>();
        initGraph();
    }


    public TileType[][] getTiles() {
        return tiles;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < TILES_X; i++) {
            for (int j = 0; j < TILES_Y; j++) {
                switch (tiles[i][j]) {
                    case LAWN:
                        out.append("L ");
                        break;
                    case PARKING:
                        out.append("P ");
                        break;
                    case PARK_ROAD:
                        out.append("R ");
                        break;
                    case ROAD:
                        out.append("W ");
                        break;
                }
            }
            out.append("\n");
        }
        return out.toString();
    }

    public void setTile(int x, int y, TileType tileType) {
        if(state == State.CONSTRUCT) {
            DrawRect drawRect = new DrawRect(surface.getGraphics());
            for (int i = 2; i < TILES_X - 1; i++) {
                for (int j = 2; j < TILES_Y - 2; j++) {
                    if (y > rectangles[i][j].y && y < rectangles[i + 1][j].y && x > rectangles[i][j].x && x < rectangles[i][j + 1].x) {
                        tiles[i][j] = tileType;
                        DrawTiles drawTiles = new DrawTiles(surface, this);
                        drawTiles.draw(tiles);
                        return;
                    }
                }
            }
        }
    }

    public void setDoubleTile(int x, int y, boolean vert) {
        if(state == State.CONSTRUCT){
        DrawRect drawRect = new DrawRect(surface.getGraphics());
        for (int i = 2; i < TILES_X - 1; i++) {
            for (int j = 2; j < TILES_Y - 2; j++) {
                if (y > rectangles[i][j].y && y < rectangles[i + 1][j].y && x > rectangles[i][j].x && x < rectangles[i][j + 1].x) {
                    if ((j - 1 > 1 && !vert) || (i - 1 > 1 && vert)) {
                        tiles[i][j] = TileType.DOUBLE_PARKING;
                        if (vert)
                            tiles[--i][j] = TileType.DOUBLE_PARKING;
                        else tiles[i][--j] = TileType.DOUBLE_PARKING;
                    }
                    DrawTiles drawTiles = new DrawTiles(surface, this);
                    drawTiles.draw(tiles);
                    return;
                }
            }
        }
        }
    }

    public int[] getTilesNumber(int x, int y) {
        int xx = x - 20;
        int yy = y - 20;
        int[] arr = new int[2];
        for (int i = 2; i < TILES_X - 1; i++) {
            for (int j = 2; j < TILES_Y - 1; j++) {
                if (yy >= rectangles[i][j].y && yy < rectangles[i + 1][j].y && xx >= rectangles[i][j].x && xx < rectangles[i][j + 1].x) {
                    arr[0] = i;
                    arr[1] = j;
                    return arr;
                }
            }
        }
        return null;
    }

    public void drawTiles() {
        DrawLines dr = new DrawLines(surface, this);
        dr.draw();
        DrawTiles drawTiles = new DrawTiles(surface, this);
        drawTiles.draw(tiles);
    }


    public Timer getT() {
        return t;
    }

    public long getStartMills() {
        return startMills;
    }

    public void setStartMills(long startMills) {
        this.startMills = startMills;
    }

    public long getElapsedMills() {
        return elapsedMills;
    }

    public void setElapsedMills(long elapsedMills) {
        this.elapsedMills = elapsedMills;
    }

    public void startModelling() {
//        initGraph();
        if(carsCreator == null)
            carsCreator = new CarsCreator(this);
        if(t == null) {
            state = State.MODELLING;

            carsCreator.start();

            t = new Timer(defaultDelay, surface);
            t.setInitialDelay(0);
            t.start();
        }
        else {
            carsCreator.stop();
            carsCreator = new CarsCreator(this);
            carsCreator.start();
            t.start();
//        carsCreator.start();
        }
    }

    public void stopModelling() {

    }

    public void pauseModelling() {

    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }

    public void initGraph() {
        allVertexes = new ArrayList<>();
        usedParkingPlaces = new ArrayList<>();
        graph = new SimpleGraph<>(DefaultEdge.class);
        initVertex();
        initEdges();
    }

    private void initVertex(){
        for (int i = 0; i < TILES_X; i++) {
            for (int j = 0; j < TILES_Y; j++) {
                Pair<TileType, Pair<Integer, Integer>> pair = new Pair<>(tiles[i][j], new Pair<>(j, i));
                graph.addVertex(pair);
                allVertexes.add(pair);
            }
        }
    }

    public Pair<Integer,Integer> getFreePlace(){
//        if (freeParkingSpace.size() != 1)
        return freeParkingSpace.pop();
//        else return new Pair<Integer, Integer>(2,12);
    }
    public LinkedList<Pair<Integer,Integer>> getFreeParkingSpace(){
        return freeParkingSpace;
    }

    private boolean addFreeSpace(Pair<Integer, Integer> freeSpace) {
        if (freeSpace != null) {
            boolean isAlreadyConsist = false;
            for (int a = 0; a < freeParkingSpace.size(); a++) {
                if ((freeParkingSpace.get(a).getFirst() == freeSpace.getFirst()) && (freeParkingSpace.get(a).getSecond() == freeSpace.getSecond())) {
                    isAlreadyConsist = true;
                    break;
                }
            }
            return isAlreadyConsist;
        }
        return false;
    }
    private void initEdges() {
        if (freeParkingSpace.size() != 0) {
            freeParkingSpace.clear();
        }
        for (int i = 0; i < TILES_X; i++) {
            Pair<Integer, Integer> freeSpace = null;
            for (int j = 0; j < TILES_Y; j++) {
                freeSpace = null;
                if (tiles[i][j].ordinal() == TileType.PARK_ROAD.ordinal()) {
                    freeSpace = null;
                    if (tiles[i][j + 1].ordinal() == TileType.PARKING.ordinal() || tiles[i][j + 1].ordinal() == TileType.PARK_ROAD.ordinal()) {
                        if(!usedParkingPlaces.contains(allVertexes.get(i * TILES_Y + j+1))) {
                            graph.addEdge(allVertexes.get(i * TILES_Y + j), allVertexes.get(i * TILES_Y + j + 1));
                            if (tiles[i][j + 1].ordinal() == TileType.PARKING.ordinal()) {
                                freeSpace = new Pair<>(j + 1, i);
                                if (!addFreeSpace(freeSpace)) {
                                    usedParkingPlaces.add(allVertexes.get(i * TILES_Y + j + 1));
                                        freeParkingSpace.add(freeSpace);
                                }
//                            freeParkingSpace.add(new Pair<>(j+1,i));
                            }
                        }
                    }
                    if (tiles[i + 1][j].ordinal() == TileType.PARKING.ordinal() || tiles[i + 1][j].ordinal() == TileType.PARK_ROAD.ordinal()) {
                        if(!usedParkingPlaces.contains(allVertexes.get((i + 1) * TILES_Y + j))) {
                            graph.addEdge(allVertexes.get(i * TILES_Y + j), allVertexes.get((i + 1) * TILES_Y + j));
                            if (tiles[i + 1][j].ordinal() == TileType.PARKING.ordinal()) {
                                freeSpace = new Pair<>(j, i + 1);
                                if (!addFreeSpace(freeSpace)) {
                                    usedParkingPlaces.add(allVertexes.get((i + 1) * TILES_Y + j));
                                    freeParkingSpace.add(freeSpace);
                                }
//                            freeParkingSpace.add(new Pair<>(j, i+1));
                            }
                        }
                    }
                    if (tiles[i - 1][j].ordinal() == TileType.PARKING.ordinal() || tiles[i - 1][j].ordinal() == TileType.PARK_ROAD.ordinal()) {
                        if(!usedParkingPlaces.contains(allVertexes.get((i - 1) * TILES_Y + j))) {
                            graph.addEdge(allVertexes.get(i * TILES_Y + j), allVertexes.get((i - 1) * TILES_Y + j));
                            if (tiles[i - 1][j].ordinal() == TileType.PARKING.ordinal()) {
                                freeSpace = new Pair<>(j, i - 1);
                                if (!addFreeSpace(freeSpace)) {
                                    usedParkingPlaces.add(allVertexes.get((i - 1) * TILES_Y + j));
                                    freeParkingSpace.add(freeSpace);
                                }
//                            freeParkingSpace.add(new Pair<>(j, i-1));
                            }
                        }
                    }
                    if (tiles[i][j - 1].ordinal() == TileType.PARKING.ordinal() || tiles[i][j - 1].ordinal() == TileType.PARK_ROAD.ordinal()) {
                        if(!usedParkingPlaces.contains(allVertexes.get(i * TILES_Y + j-1))) {
                            graph.addEdge(allVertexes.get(i * TILES_Y + j), allVertexes.get(i * TILES_Y + j - 1));
                            if (tiles[i][j - 1].ordinal() == TileType.PARKING.ordinal()) {
                                freeSpace = new Pair<>(j - 1, i);
                                if (!addFreeSpace(freeSpace)) {
                                    usedParkingPlaces.add(allVertexes.get(i * TILES_Y + j - 1));
                                    freeParkingSpace.add(freeSpace);
                                }
//                            freeParkingSpace.add(new Pair<>(j-1, i));
                            }
                        }
                    }
                }
            }
        }
        System.out.println("qwe");

    }

    public int getDefaultDelay() {
        return defaultDelay;
    }

    public int getDelay(){
        return t.getDelay();
    }

    public void stopTimer(){
        t.stop();
    }

    public void startTimer(){
        t.start();
    }

    public void setDelay(int delay) {
        t.setDelay(delay);
    }

    public int getIntervalCars() {
        return intervalCars;
    }

    public int getIntervalTime() {
        return intervalTime;

    }

    public void setIntervalCars(int intervalCars) {
        this.intervalCars = intervalCars;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public Rectangle[][] getRectangles() {
        return rectangles;
    }

    public void setRectangles(Rectangle[][] rectangles) {
        this.rectangles = rectangles;
    }


    public static int vForGen(int i, int j){
        return (j-1) * TILES_Y + (i-1);
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public void setTiles(TileType[][] tiles) {
        this.tiles = tiles;
    }

    public int getTILES_X() {
        return TILES_X;
    }

    public int getTILES_Y() {
        return TILES_Y;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }

    public CopyOnWriteArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(CopyOnWriteArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public int getCostOfOneHour() {
        return costOfOneHour;
    }

    public void setCostOfOneHour(int costOfOneHour) {
        this.costOfOneHour = costOfOneHour;
    }

    public int getCostToThreeHours() {
        return costToThreeHours;
    }

    public Pair<Integer, Integer> getEntrance() {
       // return new Pair<Integer, Integer>(TILES_Y-1,entrance);
        for(int i = TILES_Y-1;i>=0;i--) {
            if(tiles[11][i].ordinal() == TileType.PARK_ROAD.ordinal()) {
                return new Pair<Integer, Integer>(12,i+1);
            }
        }
        return null;
    }

    public void setEntrance(int entrance) {
        this.entrance = entrance;
    }

    public Pair<Integer, Integer> getExit() {
      //  return new Pair<Integer, Integer>(TILES_Y-1,exit);
        for(int i = 0; i < TILES_Y; i++) {
            if(tiles[11][i].ordinal() == TileType.PARK_ROAD.ordinal()) {
                return new Pair<Integer, Integer>(12,i+1);
            }
        }
        return null;
    }

    public void setExit(int exit) {
        this.exit = exit;
    }

    public void setCostToThreeHours(int costToThreeHours) {
        this.costToThreeHours = costToThreeHours;
    }

    public int getCostMoreThreeHours() {
        return costMoreThreeHours;
    }

    public void setCostMoreThreeHours(int costMoreThreeHours) {
        this.costMoreThreeHours = costMoreThreeHours;
    }

    public String getTypeOfThreadTimeOnParking() {
        return typeOfThreadTimeOnParking;
    }

    public void setTypeOfThreadTimeOnParking(String typeOfThreadTimeOnParking) {
        this.typeOfThreadTimeOnParking = typeOfThreadTimeOnParking;
    }


    public int getLeftDetermInterval() {
        return leftDetermInterval;
    }

    public void setLeftDetermInterval(int leftDetermInterval) {
        this.leftDetermInterval = leftDetermInterval;
    }

    public int getRightDetermInterval() {
        return rightDetermInterval;
    }

    public void setRightDetermInterval(int rightDetermInterval) {
        this.rightDetermInterval = rightDetermInterval;
    }

    public String getTypeOfThreadOfCars() {
        return typeOfThreadOfCars;
    }

    public void setTypeOfThreadOfCars(String typeOfThreadOfCars) {
        this.typeOfThreadOfCars = typeOfThreadOfCars;
    }

    public double getPartOfTrucks() {
        return partOfTrucks;
    }

    public void setPartOfTrucks(double partOfTrucks) {
        this.partOfTrucks = partOfTrucks;
    }

    public double getProbOfArrivalToParking() {
        return probOfArrivalToParking;
    }

    public void setProbOfArrivalToParking(double probOfArrivalToParking) {
        this.probOfArrivalToParking = probOfArrivalToParking;
    }


    public boolean[][] getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean[][] isEmpty) {
        this.isEmpty = isEmpty;
    }

    public ArrayList<Pair<TileType, Pair<Integer, Integer>>> getAllVertexes() {
        return allVertexes;
    }

    public void setAllVertexes(ArrayList<Pair<TileType, Pair<Integer, Integer>>> allVertexes) {
        this.allVertexes = allVertexes;
    }

    public int getMxTime() {
        return mxTime;
    }

    public void setMxTime(int mxTime) {
        this.mxTime = mxTime;
    }

    public int getDxTime() {
        return dxTime;
    }

    public void setDxTime(int dxTime) {
        this.dxTime = dxTime;
    }

    public int getLambdaTime() {
        return lambdaTime;
    }

    public void setLambdaTime(int lambdaTime) {
        this.lambdaTime = lambdaTime;
    }

    public int getT1Time() {
        return t1Time;
    }

    public void setT1Time(int t1Time) {
        this.t1Time = t1Time;
    }

    public int getT2Time() {
        return t2Time;
    }

    public void setT2Time(int t2Time) {
        this.t2Time = t2Time;
    }

    public int getMxCars() {
        return mxCars;
    }

    public void setMxCars(int mxCars) {
        this.mxCars = mxCars;
    }

    public SimpleGraph<Pair<TileType, Pair<Integer, Integer>>, DefaultEdge> getGraph() {
        return graph;
    }

    public void setGraph(SimpleGraph<Pair<TileType, Pair<Integer, Integer>>, DefaultEdge> graph) {
        this.graph = graph;
    }

    public int getDxCars() {
        return dxCars;
    }

    public void setDxCars(int dxCars) {
        this.dxCars = dxCars;
    }

    public int getLambdaCars() {
        return lambdaCars;
    }

    public void setLambdaCars(int lambdaCars) {
        this.lambdaCars = lambdaCars;
    }

    public int getT1Cars() {
        return t1Cars;
    }

    public void setT1Cars(int t1Cars) {
        this.t1Cars = t1Cars;
    }

    public int getT2Cars() {
        return t2Cars;
    }

    public void setT2Cars(int t2Cars) {
        this.t2Cars = t2Cars;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
