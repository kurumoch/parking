package models;

import controllers.Controller;
import org.apache.commons.math3.util.Pair;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by denis on 21.01.2018.
 */
public class PathGenerator {
    private Controller controller;

    public PathGenerator(Controller controller) {
        this.controller = controller;
    }

    public Path generate() {
        List<Pair<TileType, Pair<Integer, Integer>>> list1 = controller.initGraph();
        Iterator<Pair<TileType, Pair<Integer, Integer>>> iterator = list1.iterator();

        LinkedList<Pair<Pair<Integer, Integer>, Direction>> list = new LinkedList<>();


        for(int i = 0; i < list1.size()-1;i++) {
            int x1, x2 = 0;
            int y1, y2 = 0;
            x1 = list1.get(i).getSecond().getFirst();
            y1 = list1.get(i).getSecond().getSecond();
            x2 = list1.get(i+1).getSecond().getFirst();
            y2 = list1.get(i+1).getSecond().getSecond();
            if(x1 > x2) {
                list.add(new Pair<>(iterator.next().getSecond(), Direction.RIGHT));
            }
            else if(x1 < x2) {
                list.add(new Pair<>(iterator.next().getSecond(), Direction.LEFT));
            }
            if (y1 > y2) {
                list.add(new Pair<>(iterator.next().getSecond(), Direction.DOWN));
            }
            else if (y1 < y2) {
                list.add(new Pair<>(iterator.next().getSecond(), Direction.UP));
            }

        }
//
//        LinkedList<Pair<Integer, Integer>> temp = new LinkedList<>();
//        while (iterator.hasNext()) {
//            int x1,x2 = 0;
//            int y1,y2 = 0;
//            x1 = iterator.next().getSecond().getFirst();
//            y1 = iterator.next()
//            if(x1 > x2) {
//
//            }
//            list.add(new Pair<>(iterator.next().getSecond(), Direction.RIGHT));
//        }

     //   list.add(new Pair<>(new Pair<>(11, 7), Direction.LEFT));
     //   list.add(new Pair<>(new Pair<>(5, 7), Direction.UP));
     //   list.add(new Pair<>(new Pair<>(5, 4), Direction.LEFT));
     //   list.add(new Pair<>(new Pair<>(5, 4), Direction.PARK));
      //  list.add(new Pair<>(new Pair<>(5, 2), Direction.LEFT));
     //   list.add(new Pair<>(new Pair<>(2, 2), Direction.DOWN));
        return new Path(list);
    }
}
