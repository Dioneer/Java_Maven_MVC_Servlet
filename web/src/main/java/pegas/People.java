package pegas;

public class People {
    private String value;
    private String unrestricted_value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnrestricted_value() {
        return unrestricted_value;
    }

    public void setUnrestricted_value(String unrestricted_value) {
        this.unrestricted_value = unrestricted_value;
    }

    public People(String value, String unrestricted_value) {
        this.value = value;
        this.unrestricted_value = unrestricted_value;
    }

    @Override
    public String toString() {
        return "People{" +
                "value='" + value + '\'' +
                ", unrestricted_value='" + unrestricted_value + '\'' +
                '}';
    }
}
