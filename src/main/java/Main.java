import controllers.Controller;
import forms.MainForm;
import forms.ParamsForm;
import forms.ParkingCreationForm;
import models.TileType;

import java.awt.*;

/**
 * Created by denis on 19.11.2017.
 */
public class Main {

    public static void main(String...args){
        Controller c = new Controller(3, 10);
        System.out.print(c.toString());
        new MainForm(c);
    }

}
