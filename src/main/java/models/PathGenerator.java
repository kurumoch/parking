package models;

import controllers.Controller;
import org.apache.commons.math3.util.Pair;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by denis on 21.01.2018.
 */
public class PathGenerator {
    private Controller controller;

    public PathGenerator(Controller controller) {
        this.controller = controller;
    }

    public Path generate() {
        int coordsOfParkingPlace[] = new int[2];
        TileType[][] tiles = controller.getTiles();
        int TILES_X = controller.getTILES_X();
        int TILES_Y = controller.getTILES_Y();
        boolean[][] isEmpty = controller.getIsEmpty();
        for (int i = 0; i < TILES_X; i++) {
            for (int j = 0; j < TILES_Y; j++) {
                if (tiles[i][j].equals(TileType.PARKING) && isEmpty[i][j]) {
                    coordsOfParkingPlace[0] = i;
                    coordsOfParkingPlace[1] = j;
                    isEmpty[i][j] = false;
                    break;
                }
            }
        }
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(controller.getGraph());
        ArrayList<Pair<TileType, Pair<Integer, Integer>>> list = controller.getAllVertexes();
        java.util.List<Pair<TileType, Pair<Integer, Integer>>> shortestPath = dijkstraShortestPath.getPath(list.get(179), list.get(58)).getVertexList();
        Iterator<Pair<TileType, Pair<Integer, Integer>>> iterator = shortestPath.iterator();

        LinkedList<Pair<Pair<Integer, Integer>, Direction>> result = new LinkedList<>();
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            int x1, x2 = 0;
            int y1, y2 = 0;
            x1 = shortestPath.get(i).getSecond().getFirst();
            y1 = shortestPath.get(i).getSecond().getSecond();
            x2 = shortestPath.get(i + 1).getSecond().getFirst();
            y2 = shortestPath.get(i + 1).getSecond().getSecond();
            if (x1 > x2) {
                result.add(new Pair<>(iterator.next().getSecond(), Direction.LEFT));
            } else if (x1 < x2) {
                result.add(new Pair<>(iterator.next().getSecond(), Direction.RIGHT));
            }
            if (y1 > y2) {
                result.add(new Pair<>(iterator.next().getSecond(), Direction.UP));
            } else if (y1 < y2) {
                result.add(new Pair<>(iterator.next().getSecond(), Direction.DOWN));
            }
        }
       // result.add(new Pair<>(shortestPath.get(shortestPath.size()-1).getSecond(), Direction.PARK));
        return new Path(result);
    }


}
