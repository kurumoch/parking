package controllers;

import JPanels.Surface;
import drawing.DrawLines;
import drawing.DrawTiles;
import drawing.DrawRect;
import models.State;
import models.TileType;
import models.Vehicle;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.util.Pair;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.*;
import threads.CarsCreator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.beans.Transient;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.concurrent.CopyOnWriteArrayList;

import static models.TileType.*;

public class Controller implements Serializable {

    private TileType[][] tiles;
    private Rectangle[][] rectangles;
    private final int TILES_X = 13;
    private final int TILES_Y = 14;
    private int xSize;
    private int ySize;
    private Surface surface;
    private State state;
    public CopyOnWriteArrayList<Vehicle> vehicles;
    // private int[][] graph;
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
    int a;
    int b;


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

    public Controller() {
        typeOfThreadOfCars = "Детерминированный";
        typeOfThreadTimeOnParking = "Детерминированный";
        intervalCars = 5000;
        intervalTime = 5000;
        vehicles = new CopyOnWriteArrayList<>();
    }

    public void initParking(int x, int y) {
        state = State.CONSTRUCT;
        xSize = ++x;
        ySize = ++y;
        float mid_x = ((float) TILES_X) / 2;
        a = Math.round(mid_x - ((float) xSize) / 2) - 1;
        b = Math.round(mid_x + ((float) xSize) / 2) - 1;
        tiles = new TileType[TILES_X][TILES_Y];
        isEmpty = new boolean[TILES_X][TILES_Y];
        for (int i = 0; i < TILES_X; i++) {
            for (int j = 0; j < TILES_X; j++) {
                isEmpty[i][j] = true;
            }
        }
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

    public void setDoubleTile(int x, int y, boolean vert) {
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

    public void setDefaultTiles() {
        DrawLines dr = new DrawLines(surface, this);
        dr.draw();
        DrawTiles drawTiles = new DrawTiles(surface, this);
        drawTiles.draw(tiles);
    }

    public void startModelling() {
        state = State.MODELLING;
        CarsCreator carsCreator = new CarsCreator(this);
        carsCreator.start();
        Timer t = new Timer(100, surface);
        t.setInitialDelay(0);
        t.start();
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

    public java.util.List<Pair<TileType, Pair<Integer, Integer>>> initGraph() {
        ArrayList<Pair<TileType, Pair<Integer, Integer>>> list = new ArrayList<>();
        SimpleGraph<Pair<TileType, Pair<Integer, Integer>>, DefaultEdge> graph = new SimpleGraph<Pair<TileType, Pair<Integer, Integer>>, DefaultEdge>(DefaultEdge.class);

        for (int i = 0; i < TILES_X; i++) {
            for (int j = 0; j < TILES_Y; j++) {
                Pair<TileType, Pair<Integer, Integer>> pair = new Pair<>(tiles[i][j], new Pair<>(j, i));
                graph.addVertex(pair);
                list.add(pair);
            }
        }
        int res[] = new int[2];
        for (int i = 0; i < TILES_X; i++) {
            for (int j = 0; j < TILES_Y; j++) {
                if (tiles[i][j].equals(TileType.PARKING) && isEmpty[i][j]) {
                    res[0] = i;
                    res[1] = j;
                    isEmpty[i][j] = false;
                    break;
                }
            }
        }
        for (int i = 0; i < TILES_X - 1; i++) {
            for (int j = 0; j < TILES_Y - 1; j++) {
             //   if ((tiles[i][j].equals(TileType.PARK_ROAD) && (tiles[i+1][j].equals(TileType.PARK_ROAD) || tiles[i][j+1].equals(TileType.PARK_ROAD)))
                //        ||( tiles[i][j].equals(TileType.PARKING) && (tiles[i+1][j].equals(TileType.PARKING) || tiles[i][j+1].equals(TileType.PARKING)))) {
                    graph.addEdge(list.get(i * TILES_Y + j), list.get(i * TILES_Y + j + 1));
                    graph.addEdge(list.get(i * TILES_Y + j), list.get((i + 1) * TILES_Y + j));
               // }
            }
        }

        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        java.util.List<Pair<TileType, Pair<Integer, Integer>>> shortestPath = dijkstraShortestPath.getPath(list.get(57), list.get(180)).getVertexList();
        return shortestPath;
    }

    public int getxSize() {
        return TILES_X;
    }

    public int getySize() {
        return TILES_Y;
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
