package dz.aminegasa;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
public class PlayerService {
    List<Player> jsonToArrayObject(String response) {
        List<Player> players = new ArrayList<>();
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();

        // Parse the data section of the JSON response
        JsonArray playersArray = jsonResponse
                .getAsJsonObject("data")
                .getAsJsonArray("findAll");

        // Iterate over the JSON array and create Player objects
        int bound = playersArray.size();
        for (int i = 0; i < bound; i++) {
            JsonObject playerJson = playersArray.get(i).getAsJsonObject();
            Integer id = playerJson.get("id").getAsInt();
            String name = playerJson.get("name").getAsString();
            String team = playerJson.get("team").getAsString();
            Player player = new Player(id, name, team);
            players.add(player);
        }
        return players;
    }

    Player jsonToObject(String response,String nameQuery) {
        Player player = null;
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();

        // Parse the data section of the JSON response
        JsonObject playerJson = jsonResponse
                .getAsJsonObject("data")
                .getAsJsonObject(nameQuery);
        if (playerJson != null) {
            Integer id = playerJson.get("id").getAsInt();
            String name = playerJson.get("name").getAsString();
            String team = playerJson.get("team").getAsString();
            player = new Player(id, name, team);
            }
        return player;
    }
    Integer jsonToNumber(String response) {
       JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
        Integer numberOfPlayers=0;

        // Parse the data section of the JSON response
         numberOfPlayers  = jsonResponse
                .getAsJsonObject("data")
                .get("numberPlayers").getAsInt();
            return numberOfPlayers;}

    void showPlayerList(List<Player> players) {
        for (Player player : players) {
            System.out.println(player.toString());
        }
    }
    void showPlayer(Player player) {
        System.out.println(player.toString());

    }
}
