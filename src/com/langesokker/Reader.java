package com.langesokker;

import com.langesokker.media.Film;
import java.io.*;


public class Reader {
    public static void main(String[] args) {
        new Reader();
    }

    public Reader() {
        //Handles no file exception
        ClassLoader loader = getClass().getClassLoader();
        if (loader == null) {
            // TODO: make it throw exception...
            return;
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("src/com/langesokker/data/film.txt"));
            String CurrentLine;
            while ((CurrentLine = reader.readLine()) != null) {
                    System.out.println(CurrentLine);

                    String[] currentFilm = CurrentLine.split(";");
                    new Film(currentFilm[0], Integer.parseInt(currentFilm[1]), currentFilm[2].replaceAll("\\s+", "").split(","), Double.parseDouble(currentFilm[3]), "");

                }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
