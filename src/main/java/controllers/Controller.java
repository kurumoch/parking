package controllers;

import drawing.DrawTiles;
import drawing.DrawRect;
import models.TileType;
import org.apache.commons.math3.distribution.RealDistribution;

import javax.swing.*;
import java.awt.*;

import static models.TileType.*;

public class Controller {

    private TileType[][] tiles;
    private Rectangle[][] rectangles;
    private final int TILES_X = 13;
    private final int TILES_Y = 14;
    private int xSize;
    private int ySize;
    private JPanel surface;

    public int getxSize() {
        return TILES_X;
    }

    public int getySize() {
        return TILES_Y;
    }

    private int costOfOneHour;
    private int costToThreeHours;
    private int costMoreThreeHours;
    private String typeOfThreadTimeOnParking;
    private RealDistribution distributionTimeOnParking;
    private int leftDetermInterval;
    private int rightDetermInterval;
    private String typeOfThreadOfCars;
    private RealDistribution distributionThreadOfCars;
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

    public Rectangle[][] getRectangles() {
        return rectangles;
    }

    public void setRectangles(Rectangle[][] rectangles) {
        this.rectangles = rectangles;
    }

    public Controller(int x, int y) {
        xSize = ++x;
        ySize = ++y;
        float mid_x = ((float) TILES_X) / 2;
        int a = Math.round(mid_x - ((float) xSize) / 2) - 1;
        int b = Math.round(mid_x + ((float) xSize) / 2) - 1;
        tiles = new TileType[TILES_X][TILES_Y];

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
        for (int i = 0; i < TILES_X-1; i++) {
            for (int j = 0; j < TILES_Y-1; j++) {
                if (x > rectangles[i][j].y && x < rectangles[i + 1][j].y && y > rectangles[i][j].x && y < rectangles[i][j+1].x) {
                    tiles[j][i] = tileType;
                    DrawTiles drawTiles = new DrawTiles(surface.getGraphics(), this);
                    drawTiles.draw(tiles);
                    return;
                }


            }
        }
    }

    public void setDefaultTiles() {
        DrawTiles drawTiles = new DrawTiles(surface.getGraphics(), this);
        drawTiles.draw(tiles);

    }

    public void setSurface(JPanel surface) {
        this.surface = surface;
    }
}
