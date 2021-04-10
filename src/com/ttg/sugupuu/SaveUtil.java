package com.ttg.sugupuu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveUtil {

    public static void dataToFile(Serializable data)  {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Save Family tree");
        FileNameExtensionFilter f = new FileNameExtensionFilter("familytree", "familytree");
        jfc.addChoosableFileFilter(f);
        jfc.setAcceptAllFileFilterUsed(true);
        int rv = jfc.showSaveDialog(null);
        if (rv == 0) {
            ObjectOutputStream oos;
            try {
                oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(jfc.getSelectedFile().getPath())));
                oos.writeObject(data);
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Object loadFromFile() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select Family tree");
        FileNameExtensionFilter f = new FileNameExtensionFilter("Family tree file", "familytree");
        jfc.addChoosableFileFilter(f);
        jfc.setAcceptAllFileFilterUsed(true);
        int rv = jfc.showOpenDialog(null);
        if (rv == 0) {
            ObjectInputStream ois;
            try {
                ois = new ObjectInputStream(Files.newInputStream(Paths.get(jfc.getSelectedFile().getPath())));
                Object toReturn = ois.readObject();
                ois.close();
                return toReturn;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

}
