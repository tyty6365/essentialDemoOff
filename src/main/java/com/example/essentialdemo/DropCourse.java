package com.example.essentialdemo;

import java.io.*;

public class DropCourse {
    public static void updateTextFile(String fileName, String newClass) {
        try {
            File oldFile = new File("C:\\Users\\mimic\\IdeaProjects\\essentialDemoOff\\src\\main\\java\\com\\example\\essentialdemo\\classtxtFolder\\" + fileName);
            BufferedReader reader = new BufferedReader(new FileReader(oldFile));
            String line = "", oldContent = "";
            while ((line = reader.readLine()) != null) {
                oldContent += line + System.lineSeparator();
            }
            reader.close();
            String newContent = oldContent.replaceAll("(ScheduledClasses:.*)(,\\s*" + newClass + "\\s*)(,|$)", "$1$3");
            File newFile = new File("C:\\Users\\mimic\\IdeaProjects\\essentialDemoOff\\src\\main\\java\\com\\example\\essentialdemo\\classtxtFolder\\" + fileName);
            FileWriter writer = new FileWriter(newFile);
            writer.write(newContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}