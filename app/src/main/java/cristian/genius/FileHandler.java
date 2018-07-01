package cristian.genius;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    final String filePath = "";

    private void createDefaultFile() throws IOException
    {
        final String songFilePath = "";
        File file = new File(songFilePath);

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("musicas");
        writer.newLine();
        writer.write("exemplo1.song");
        writer.newLine();
        writer.write("exemplo2.song");
        writer.newLine();
        writer.write("musicas_fim");
        writer.write("score");

        writer.flush();
        writer.close();
    }

    public List<String> getSongs() throws IOException {

        List<String> songs = new ArrayList<>();

        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);

        BufferedReader reader = new BufferedReader(fileReader);

        String data = null;

        while(((data = reader.readLine()) != "musicas") && data != null);

        if(data != null)
        {
            while(((data = reader.readLine()) != "musicas_fim") && data != null)
            {
                songs.add(data);
            }
        }

        fileReader.close();
        reader.close();

        return songs;
    }

    private void readFile() throws IOException {

        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);

        BufferedReader reader = new BufferedReader(fileReader);

        String data = null;

        while ((data = reader.readLine()) != null) {

        }
        fileReader.close();
        reader.close();
    }

    private void writeFile() throws IOException
    {
        final String songFilePath = "";
        File file = new File(songFilePath);

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write("");
        writer.newLine();

        writer.flush();
        writer.close();
    }
}
