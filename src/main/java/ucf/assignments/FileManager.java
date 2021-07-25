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
        //check for extension type and call appropriate function
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
            //create a writer for given file
            FileWriter writer = new FileWriter(saveFile);
            //write to the file with table data separated by tabs
            for (InventoryItem item : table) {
                writer.write(item.getValue() + "\t");
                writer.write(item.getSerial() + "\t");
                writer.write(item.getName() + "\n");
            }
            //close file writer
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
            //create a writer for given file
            FileWriter writer = new FileWriter(saveFile);
            //write beginning of html
            writer.write("""
            <!DOCTYPE html>
            <html>
            <table style="width:100%">
            <tr><th>Value</th><th>Serial Number</th><th>Name</th></tr>
            """);
            //write table contents of html with table data
            for(InventoryItem item : table)
            {
                String line = String.format("<tr><th>%s</th><th>%s</th><th>%s</th></tr>\n", item.getValue(), item.getSerial(), item.getName());
                writer.write(line);
            }
            //write end of html
            writer.write( "</table>\n</html>");
            //close file writer
            writer.close();
        }
        catch (Exception e)
        {
            //file invalid
        }
    }
    void saveJSON(ObservableList<InventoryItem> table, File saveFile) {
        try {
            //create a json writer for given file
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(new FileOutputStream(saveFile), StandardCharsets.UTF_8));
            writer.setIndent("    ");
            //create an array
            writer.beginArray();
            //create an object for each item in table data
            for(InventoryItem item : table)
            {
                writer.beginObject();
                writer.name("value").value(item.getValue());
                writer.name("serial").value(item.getSerial());
                writer.name("name").value(item.getName());
                writer.endObject();
            }
            //end array
            writer.endArray();
            //close writer
            writer.close();
        }
        catch (Exception e)
        {
            //file invalid
        }
    }

    ObservableList<InventoryItem> loadFile(File loadFile, String extension) {
        //check for extension type and call appropriate function
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
        //create a new table to load into
        ObservableList<InventoryItem> loadedTable = observableArrayList();
        try {
            //create a scanner
            Scanner sc = new Scanner(loadFile);
            String line;
            String[] lineSplit;
            //scan next line until there is no next line
            while(sc.hasNextLine())
            {
                line = sc.nextLine();
                //split each line into separate strings using tab character as regex
                lineSplit = line.split("\t");
                //create an item with split strings as data
                InventoryItem newItem = new InventoryItem(lineSplit[0], lineSplit[1], lineSplit[2]);
                //add item to table
                loadedTable.add(newItem);
            }
            //close scanner
            sc.close();
        }
        catch (Exception e)
        {
            //file invalid
        }
        //return the table with loaded data
        return loadedTable;
    }
    ObservableList<InventoryItem> loadHTML(File loadFile) {
        //create a new table to load into
        ObservableList<InventoryItem> loadedTable = observableArrayList();
        try {
            //create a scanner
            Scanner sc = new Scanner(loadFile);
            String line;
            String[] lineSplit;
            //skip beginning of html data
            sc.skip("""
            <!DOCTYPE html>
            <html>
            <table style="width:100%">
            <tr><th>Value</th><th>Serial Number</th><th>Name</th></tr>
            """);
            //scan next line until there is no next line
            while(sc.hasNextLine())
            {
                line = sc.nextLine();
                //split each line into separate strings using html formatting as regex
                lineSplit = line.split("</th><th>");
                //remove html formatting from split strings
                lineSplit[0] = lineSplit[0].replace("<tr><th>", "");
                lineSplit[2] = lineSplit[2].replace("</th></tr>", "");
                //create an item with split strings as data
                InventoryItem newItem = new InventoryItem(lineSplit[0], lineSplit[1], lineSplit[2]);
                //add item to table
                loadedTable.add(newItem);
            }
            //close scanner
            sc.close();
        }
        catch (Exception e)
        {
            //file invalid
        }
        //return the table with loaded data
        return loadedTable;
    }
    ObservableList<InventoryItem> loadJSON(File loadFile) {
        //create a new table to load into
        ObservableList<InventoryItem> loadedTable = observableArrayList();
        try {
            //create a json reader
            JsonElement json = JsonParser.parseReader(new FileReader(loadFile));
            //get array from reader
            JsonArray jsonArray = json.getAsJsonArray();
            for(JsonElement element : jsonArray)
            {
                //get object from array
                JsonObject object = element.getAsJsonObject();
                //get data from object
                String value = object.get("value").getAsString();
                String serial = object.get("serial").getAsString();
                String name = object.get("name").getAsString();
                //create an item with object data
                InventoryItem newItem = new InventoryItem(value, serial, name);
                //add item to table
                loadedTable.add(newItem);
            }
        }
        catch (Exception e)
        {
            //file invalid
        }
        //return the table with loaded data
        return loadedTable;
    }
}
