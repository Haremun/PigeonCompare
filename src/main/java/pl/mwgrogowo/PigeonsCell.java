package pl.mwgrogowo;

import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class PigeonsCell extends ListCell<String> {

    private List<String> pigeonsFound;

    public PigeonsCell(List<String> pigeonsFound) {
        this.pigeonsFound = pigeonsFound;
        if (pigeonsFound == null)
            this.pigeonsFound = new ArrayList<>();
    }


    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(item);
        for (String pigeon :
                pigeonsFound) {
            if (pigeon.equals(item)) {
                setTextFill(Color.GREEN);
                setText(item);
                System.out.println(item);
                break;
            } else
                setTextFill(Color.BLACK);

        }


    }
}
