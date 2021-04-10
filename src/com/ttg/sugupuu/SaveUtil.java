package com.ttg.sugupuu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveUtil {

    public static void dataToFile(Serializable data)  {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("familytree.person")));
            oos.writeObject(data);
            oos.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Object loadFromFile() {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(Files.newInputStream(Paths.get("familytree.person")));
            Object toReturn = ois.readObject();
            ois.close();
            return toReturn;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
