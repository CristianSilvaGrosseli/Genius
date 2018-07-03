package cristian.genius;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private String filePath;

    FileHandler() {
        filePath = "./configGenius.json";

         try {
             createDefaultFile();
         } catch (IOException e)
         {
             new RuntimeException(e);
         }
    }

    private void createDefaultFile() throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                JSONObject fileData = new JSONObject();

                JSONObject player1 = new JSONObject();
                player1.put("name", "cristian");
                player1.put("hits", 10);

                JSONObject player2 = new JSONObject();
                player2.put("name", "Lucy");
                player2.put("hits", 20);

                JSONArray players = new JSONArray();
                players.put(player1);
                players.put(player2);

                JSONObject playersObject = new JSONObject();
                playersObject.put("players", players);

                fileData.put("score", fileData);

                FileWriter writer = new FileWriter(filePath);
                writer.write(fileData.toString());

                writer.flush();
                writer.close();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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

    public List<Player> getPlayers() throws IOException
    {
        List<Player> ret = new ArrayList<>();

        File file = new File(filePath);

        try {
            FileReader reader = new FileReader(file);
            JSONObject fileObject = new JSONObject(reader.toString());

            JSONArray players = fileObject.getJSONArray("players");

            int numOfPlayers = players.length();

            for (int i = 0; i < numOfPlayers; i++) {
                JSONObject player = players.getJSONObject(i);
                String playerName = player.getString("name");
                int playerScore = player.getInt("hits");

                ret.add(new Player(playerName, playerScore));
            }

            reader.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public void savePlayer(String name, int score, int indexToReplace) throws IOException{

        File file = new File(filePath);

        if(!file.exists()) {
            this.createDefaultFile();
            file = new File(filePath);
        }

        try {
            JSONObject playerObject = new JSONObject();
            playerObject.put("name", name);
            playerObject.put("hits", score);

            FileReader reader = new FileReader(file);
            JSONObject fileObject = new JSONObject(reader.toString());

            JSONObject scoreObject = fileObject.getJSONObject("score");
            JSONArray players = scoreObject.getJSONArray("players");

            if (indexToReplace >= 0) {
                players.put(indexToReplace, playerObject);
            } else {
                players.put(playerObject);
            }

            scoreObject.put("players", players);
            fileObject.put("score", scoreObject);

            FileWriter writer = new FileWriter(filePath);
            writer.write(fileObject.toString());

            reader.close();
            writer.flush();
            writer.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
