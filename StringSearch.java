import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;

class StringSearch {
    public static void main(String[] args) {
        System.out.println(Arrays.asList(args));
        // Args order: <file> <query> <transform>
        String[] lines = getLines(args[0]);
        HashMap<String, String> queries = queryToMap(args[1]);

        System.out.println(Arrays.asList(lines));
        System.out.println(queries);
        // for (String line: lines) {
        //     Query q = new Query(line);
            
        // }
    }

    static String[] getLines(String path) {
        try {
            return Files.readAllLines(Paths.get(path)).toArray(String[]::new);
        } catch (IOException e) {
            System.err.println("Error reading file " + path + ": " + e);
            return new String[]{"Error reading file " + path + ": " + e};
        }
    }

    static HashMap<String, String> queryToMap(String arg) {
        HashMap<String, String> queries = new HashMap<String, String>();
        String[] argAsArray = arg.split("&");
        for (String request: argAsArray) {
            String[] query = request.split("=");
            queries.put(query[0], query[1]);
        }
        return queries;
    }
}

class Query {
    String line;

    Query(String line) {
        this.line = line;
    }

    // Length
    boolean matchesLength(int length) {
        return line.length() == length;
    }

    // Less than or greater than specified length
    boolean matchesEquality(int length, boolean isLessThan) {
        return isLessThan ? line.length() < length : line.length() > length;
    }

    // Contains another string
    boolean matchesString(String string) {
        return line.contains(string);
    }

    // Starts or ends with string
    boolean matchesPart(String phrase, boolean isStart) {
        return isStart ? line.startsWith(phrase) : line.endsWith(phrase);
    }
}

