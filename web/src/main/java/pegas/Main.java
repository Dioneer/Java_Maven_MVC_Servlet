package pegas;

public class Main {
    public static void main(String[] args) {
        ParseJSON parseJSON = new ParseJSON();
        Root root = parseJSON.parse();
        System.out.println(root.getPeoples());
    }
}
