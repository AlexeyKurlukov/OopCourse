package data_handler;

import java.util.ArrayList;
import java.util.List;

public class StringHandler implements DataHandler<String> {
    List<String> strings = new ArrayList<>();

    @Override
    public void processData(String data) {
        if (!data.matches("-?\\d+(\\.\\d+)?([eE][+-]?\\d+)?")) {
            strings.add(data);
        }
    }

    @Override
    public List<String> getData() {
        return strings;
    }
}