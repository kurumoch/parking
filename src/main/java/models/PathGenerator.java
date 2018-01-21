package models;

import org.apache.commons.math3.util.Pair;

import java.util.LinkedList;

/**
 * Created by denis on 21.01.2018.
 */
public class PathGenerator {
    public static Path generate(){
        LinkedList<Pair<Pair<Integer, Integer>, Direction>> list = new LinkedList<>();
        list.add(new Pair<>(new Pair<>(11, 7), Direction.LEFT));
        list.add(new Pair<>(new Pair<>(5, 7), Direction.UP));
        list.add(new Pair<>(new Pair<>(5, 4), Direction.LEFT));
        list.add(new Pair<>(new Pair<>(5, 4), Direction.PARK));
        list.add(new Pair<>(new Pair<>(5, 2), Direction.LEFT));
        list.add(new Pair<>(new Pair<>(2, 2), Direction.DOWN));
        return new Path(list);
    }
}
