package data_handler;

import java.util.List;

public interface DataHandler<T> {
    void processData(String data);

    List<T> getData();
}