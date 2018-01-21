package models;

import org.apache.commons.math3.util.Pair;

import java.util.*;

/**
 * Created by denis on 21.01.2018.
 */
public class Path {//массив точек и направление
    public LinkedList<Pair<Pair<Integer, Integer>, Direction>> path;

    public Path(Pair<Integer, Integer>[] points, Direction[] directions){
        path = new LinkedList<>();
        for (int i = 0; i < points.length; i++){
            path.add(new Pair<>(points[i], directions[i]));
        }
    }

    public Path(LinkedList<Pair<Pair<Integer, Integer>, Direction>> path){
        this.path = path;
    }

    public Pair<Pair<Integer, Integer>, Direction> next(){
        return path.pop();
    }

}
