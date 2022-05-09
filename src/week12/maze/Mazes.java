package week12.maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Mazes {
    static List<Maze> definitions = new ArrayList<>(11);
    static {
        for (int i = 0; i <= 10; i++) {
            String path = String.format("/mazes/maze%d.txt", i);
            BufferedReader rdr = new BufferedReader(
                    new InputStreamReader(Maze.class.getResourceAsStream(path), Charset.forName("UTF-8")));
            definitions.add(readLines(rdr));
        }
    }

    static Maze readLines(BufferedReader rdr) {
        List<String> out = new ArrayList<>(10);
        while (true) {
            String line;
            try {
                line = rdr.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line == null) {
                break;
            }
            assert line.length() == 10 : "mazes should be 10x10";
            out.add(line);
        }
        return new Maze(out);
    }
}
