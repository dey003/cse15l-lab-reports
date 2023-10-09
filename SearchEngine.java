import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String searches[];
    int counter = 0;

    public static String[] addString(int n, String arr[], String str) {
        String newarr[] = new String[n + 1];

        for (int i = 0; i < n; i++) {
            newarr[i] = arr[i];
        }

        newarr[n] = str;

        return newarr;
    }

    public String handleRequest(URI url) {
        if (url.getPath().equals("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                searches = addString(counter, searches, parameters[1]);
                counter++;
            }
        } 
