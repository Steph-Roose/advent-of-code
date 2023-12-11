package com.rose.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Readers {

    public static List<String> inputReader(String path) {
        List<String> input = new ArrayList<>();

        try {
            File filePath = new File(path);
            Scanner scanner = new Scanner(filePath);

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                input.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return input;
    }
}
