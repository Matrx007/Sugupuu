package com.ttg.sugupuu.storage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveUtil {

    public static void dataToFile(Serializable data, String path) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(path)));
            oos.writeObject(data);
        } finally {
            oos.close();
        }
    }

    public static Object loadFromFile(String path) throws Exception {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(Files.newInputStream(Paths.get(path)));
            return ois.readObject();
        } finally {
            ois.close();
        }
    }
}
