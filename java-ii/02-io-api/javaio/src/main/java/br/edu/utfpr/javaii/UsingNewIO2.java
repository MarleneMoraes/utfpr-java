package br.edu.utfpr.javaii;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UsingNewIO2 {
    private static final String MY_FILE = "f:" + File.separator + "file-using-new-io.txt";

    public UsingNewIO2() {
        try {
            writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeFile() throws IOException {
       final Path path = Paths.get(MY_FILE);
       Files.writeString(path, "Ola Mundo NIO2!");

       System.out.println("Arquivo gravado com sucesso!");
    }

    private void readFile() throws IOException {
        final Path path = Paths.get(MY_FILE);
        Files.readAllLines(path).forEach(System.out::println);
    }

    public static void main(String[] args) {
        new UsingNewIO2();
    }
}
