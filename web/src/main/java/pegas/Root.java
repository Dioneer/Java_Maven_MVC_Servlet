package pegas;

import java.util.ArrayList;
import java.util.List;

public class Root {
    private final List<People> peoples = new ArrayList<>();
    public List<People> getPeoples() {
        return peoples;
    }
    @Override
    public String toString() {
        return "Root{" +
                "peoples=" + peoples +
                '}';
    }
}
