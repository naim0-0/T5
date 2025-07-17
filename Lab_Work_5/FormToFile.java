package com.iitdu.util;

import java.io.FileWriter;
import java.io.IOException;

public class FormToFile {
    private static final String FILE_PATH = "iitdu_football_participants.txt";

    public static boolean saveData(String data) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
