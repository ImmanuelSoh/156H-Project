/**
 * Authors: Jaden Miller				Immanuel Soh
 * Emails:  jmiller144@huskers.unl.edu  isoh2@huskers.unl.edu
 * 
 * Description: This is a class used to handle writing data in JSON and XML format into files.
 */
package com.yrl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.util.List;

public class FilePrinter {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T> void writeToJson(List<T> objects, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            String jsonOutput = gson.toJson(objects);
            writer.write(jsonOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static <T> void writeToXml(String alias, List<T> objects, String filename) {
        XStream xstream = new XStream(new DomDriver());
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.alias(alias, List.class);

        try (Writer writer = new FileWriter(filename)) {
            PrettyPrintWriter prettyPrintWriter = new PrettyPrintWriter(writer);
            xstream.marshal(objects, prettyPrintWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
