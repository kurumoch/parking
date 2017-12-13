package controllers;

import models.TileType;
import static models.TileType.*;

public class Controller {

   public TileType[][] tiles;
    private final int TILES_X = 15;
    private final int TILES_Y = 15;

    public Controller(int x, int y){
        x+=1; //не баг, а фича
        y+=1;
        float mid_x = ((float)TILES_X)/2;
        int a = Math.round(mid_x - ((float)x)/2) ;
        int b = Math.round(mid_x + ((float)x)/2) ;
        tiles = new TileType[TILES_X][TILES_Y];
        for(int i = 0; i < TILES_X; i++){
            for (int j = 0; j <TILES_Y; j++){
                tiles[i][j] = LAWN;
                if(i > TILES_Y - y - 1 && i < TILES_Y - 1){
                    if(j < b + 1 && j > a + 1)
                        tiles[i][j] = PARKING;
                    if(j == b + 1 || j == a + 1)
                        tiles[i][j] = PARK_ROAD;
                }
                if(i ==  TILES_Y - y - 1 && j <= b + 1 && j >= a + 1){
                    tiles[i][j] = PARK_ROAD;
                }
                if(i == TILES_Y - 1)
                    tiles[i][j] = ROAD;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for(int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                switch (tiles[i][j]){
                    case LAWN: out.append("L "); break;
                    case PARKING: out.append("P "); break;
                    case PARK_ROAD: out.append("R "); break;
                    case ROAD: out.append("W "); break;
                }
            }
            out.append("\n");
        }
        return out.toString();

    }
}
