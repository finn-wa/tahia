package tahia;

public class TahiaApp {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        new TahiaCodeFormatter().start(args);
    }
}
