import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String searches[];
    int numSearches = 0;

    public static String[] addString(int n, String arr[], String str) {
        String newarr[] = new String[n + 1];

        for (int i = 0; i < n; i++) {
            newarr[i] = arr[i];
        }

        newarr[n] = str;

        return newarr;
    }

    public static String findStrings(String arr[], String str) {
        String matches = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].contains(str)) {
                if (matches.equals(""))
                    matches = arr[i];
                else
                    matches = matches + ", " + arr[i];
            }
        }
        return matches;
    }

    public String handleRequest(URI url) {
        if (url.getPath().equals("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                searches = addString(numSearches, searches, parameters[1]);
                numSearches++;
                return String.format("%s added to searchable strings! The number of strings is now %d!", parameters[1], numSearches);
            }
        } else if (url.getPath().equals("/search")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                if (numSearches > 0)
                    return findStrings(searches, parameters[1]);
                else
                    return "Search not found!";
            }
        } else if (url.getPath().equals("/")) {
            String strings = "";
            for (int i = 0; i < numSearches; i++) {
                if (strings.equals(""))
                    strings = searches[i];
                else
                    strings = strings + ", " + searches[i];
            }
            return String.format("Current list of searchable strings: %s", strings);
        }
        return "404 Not Found!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
