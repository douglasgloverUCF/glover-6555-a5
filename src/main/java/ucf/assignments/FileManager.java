/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Douglas Glover
 */
package ucf.assignments;

import com.google.gson.stream.JsonWriter;
import javafx.collections.ObservableList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static javafx.collections.FXCollections.observableArrayList;

public class FileManager {
    void saveFile(ObservableList<InventoryItem> table, File saveFile, String extension)
    {
        if(extension.equals("TSV (.txt)")) {
            saveTSV(table, saveFile);
        }
        if(extension.equals("HTML (.html)")) {
            saveHTML(table, saveFile);
        }
        if(extension.equals("JSON (.json)")) {
            saveJSON(table, saveFile);
        }
    }
    void saveTSV(ObservableList<InventoryItem> table, File saveFile)
    {
        try {
            FileWriter writer = new FileWriter(saveFile);
            for (InventoryItem item : table) {
                writer.write(item.getValue() + "\t");
                writer.write(item.getSerial() + "\t");
                writer.write(item.getName() + "\n");
            }
            writer.close();
        }
        catch(Exception e)
        {
            //file invalid
        }
    }
    void saveHTML(ObservableList<InventoryItem> table, File saveFile)
    {
        try {
            FileWriter writer = new FileWriter(saveFile);
            writer.write("""
            <!DOCTYPE html>
            <html>
            <table style="width:100%">
            <tr><th>Value</th><th>Serial Number</th><th>Name</th></tr>
            """);
            for(InventoryItem item : table)
            {
                String line = String.format("<tr><th>%s</th><th>%s</th><th>%s</th></tr>\n", item.getValue(), item.getSerial(), item.getName());
                writer.write(line);
            }
            writer.write( "</table>\n</html>");
            writer.close();
        }
        catch (Exception e)
        {
            //file invalid
        }
    }
    void saveJSON(ObservableList<InventoryItem> table, File saveFile) {
        ObservableList<InventoryItem> loadedTable = observableArrayList();
        try {
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(new FileOutputStream(saveFile), StandardCharsets.UTF_8));
            writer.setIndent("    ");
            writer.beginArray();
            for(InventoryItem item : table)
            {
                writer.beginObject();
                writer.name("value").value(item.getValue());
                writer.name("serial").value(item.getSerial());
                writer.name("name").value(item.getName());
                writer.endObject();
            }
            writer.endArray();
            writer.close();
        }
        catch (Exception e)
        {
            //file invalid
        }
    }

    ObservableList<InventoryItem> loadFile(File loadFile, String extension) {
        if(extension.equals("TSV (.txt)")) {
            return loadTSV(loadFile);
        }
        if(extension.equals("HTML (.html)")) {
            return loadHTML(loadFile);
        }
        if(extension.equals("JSON (.json)")) {
            return loadJSON(loadFile);
        }
        return observableArrayList();
    }
    ObservableList<InventoryItem> loadTSV(File loadFile) {
        ObservableList<InventoryItem> loadedTable = observableArrayList();
        try {
            Scanner sc = new Scanner(loadFile);
            String line;
            String[] lineSplit;
            while(sc.hasNextLine())
            {
                line = sc.nextLine();
                lineSplit = line.split("\t");
                InventoryItem newItem = new InventoryItem(lineSplit[0], lineSplit[1], lineSplit[2]);
                loadedTable.add(newItem);
            }
            sc.close();
        }
        catch (Exception e)
        {
            //file invalid
        }
        return loadedTable;
    }
    ObservableList<InventoryItem> loadHTML(File loadFile) {
        ObservableList<InventoryItem> loadedTable = observableArrayList();
        try {
            Scanner sc = new Scanner(loadFile);
            String line;
            String[] lineSplit;
            sc.skip("""
            <!DOCTYPE html>
            <html>
            <table style="width:100%">
            <tr><th>Value</th><th>Serial Number</th><th>Name</th></tr>
            """);
            while(sc.hasNextLine())
            {
                line = sc.nextLine();
                lineSplit = line.split("</th><th>");
                lineSplit[0] = lineSplit[0].replace("<tr><th>", "");
                lineSplit[2] = lineSplit[2].replace("</tr></th>", "");
                InventoryItem newItem = new InventoryItem(lineSplit[0], lineSplit[1], lineSplit[2]);
                loadedTable.add(newItem);
            }
            sc.close();
        }
        catch (Exception e)
        {
            //file invalid
        }
        return loadedTable;
    }
    ObservableList<InventoryItem> loadJSON(File loadFile) {
        ObservableList<InventoryItem> loadedTable = observableArrayList();
        try {
            JsonElement json = JsonParser.parseReader(new FileReader(loadFile));
            JsonObject data = json.getAsJsonObject();
            JsonArray jsonArray = data.get("products").getAsJsonArray();
            for(JsonElement element : jsonArray)
            {
                JsonObject object = element.getAsJsonObject();
                String value = object.get("value").getAsString();
                String serial = object.get("serial").getAsString();
                String name = object.get("name").getAsString();
                InventoryItem newItem = new InventoryItem(value, serial, name);
                loadedTable.add(newItem);
            }
        }
        catch (Exception e)
        {
            //file invalid
        }
        return loadedTable;
    }
}
