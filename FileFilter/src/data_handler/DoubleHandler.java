package data_handler;

import java.util.ArrayList;
import java.util.List;

public class DoubleHandler implements DataHandler<Double> {
    List<Double> doubles = new ArrayList<>();

    @Override
    public void processData(String data) {
        if (data.matches("-?\\d*\\.\\d+([eE][+-]?\\d+)?")) {
            doubles.add(Double.parseDouble(data));
        }
    }

    @Override
    public List<Double> getData() {
        return doubles;
    }
}