package data_handler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class IntegerHandler implements DataHandler<BigInteger> {
    List<BigInteger> bigIntegers = new ArrayList<>();

    @Override
    public void processData(String data) {
        if (data.matches("-?\\d+")) {
            bigIntegers.add(new BigInteger(data));
        }
    }

    @Override
    public List<BigInteger> getData() {
        return bigIntegers;
    }
}