package dz.aminegasa;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class UserInterface {
    Scanner scanner;
    public UserInterface() {
        this.scanner = new Scanner(System.in).useDelimiter("\\n");
    }
    Integer dashboard() {
        System.out.println("_____________________________");
        System.out.println("1: find all players");
        System.out.println("2: find a player by id");
        System.out.println("3: get number of players");
        System.out.println("4: create a player");
        System.out.println("5: update a player");
        System.out.println("6: delete a player");
        System.out.println("_____________________________");
        System.out.println("choose a number");
        return scanner.nextInt();
    }
    List<String> getInputUser(Integer inputUserChoice) {
        List<String> arrayInputUser = new ArrayList<>();
        switch (inputUserChoice) {
            case 2: {
                System.out.println("put the id player :");
                arrayInputUser.add(scanner.nextInt() + "");
                break;
            }
            case 4: {
                System.out.println("put the name player :");
                arrayInputUser.add(scanner.next());
                System.out.println("put the team player :  MCO, MCA, JSS, MCS, JSK");
                arrayInputUser.add(scanner.next());

                break;
            }
            case 5: {
                System.out.println("put the id player :");
                arrayInputUser.add(scanner.nextInt() + "");
                System.out.println("put the name player :");
                arrayInputUser.add(scanner.next());
                System.out.println("put the team player : MCO, MCA, JSS, MCS, JSK");
                arrayInputUser.add(scanner.next());
                break;
            }
            case 6: {
                System.out.println("put the id player you want to delete :");
                arrayInputUser.add(scanner.nextInt() + "");
                break;
            }

        }
        return arrayInputUser;
    }
    public void processData(Integer inputUserChoice, List<String> arrayInputUser
            , PlayerService playerService, HttpRequest httpRequest) {

        switch (inputUserChoice) {
            case 1: {
                String findAllQuery = "{ findAll { id name team } }";
                String jsonResponse = httpRequest.sendRequest(findAllQuery);
                List<Player> players = playerService.jsonToArrayObject(jsonResponse);
                playerService.showPlayerList(players);
                break;
            }
            case 2: {
                String findOneQuery = "{ findOne(id:" +
                        arrayInputUser.get(0) + ") { id name team } }";
                String jsonResponse = httpRequest.sendRequest(findOneQuery);
                Player player = playerService.jsonToObject(jsonResponse, "findOne");
                playerService.showPlayer(player);
                break;
            }
            case 3: {
                String numberPlayersQuery = "{ numberPlayers }";
                String jsonResponse = httpRequest.sendRequest(numberPlayersQuery);
                Integer numberPlayers = playerService.jsonToNumber(jsonResponse);
                System.out.println("number of players: " + numberPlayers);
                break;
            }
            case 4: {
                String createQuery
                        = "mutation {create(name:\"" + arrayInputUser.get(0)
                        + "\",team:" + arrayInputUser.get(1) + ") {    id    name    team  }}";
                String jsonResponse = httpRequest.sendRequest(createQuery);
                Player player = playerService.jsonToObject(jsonResponse, "create");
                playerService.showPlayer(player);
                break;
            }
            case 5: {
                String updateQuery
                        = "mutation {update(id: " + arrayInputUser.get(0)
                        + ", name: \"" + arrayInputUser.get(1) + "\", team:"
                        + arrayInputUser.get(2) + ") {    id    name    team  }}";
                String jsonResponse = httpRequest.sendRequest(updateQuery);
                Player player = playerService.jsonToObject(jsonResponse, "update");
                playerService.showPlayer(player);
                break;
            }
            case 6: {
                String deleteQuery
                        = "mutation {  delete(id: "
                        + arrayInputUser.get(0) + ") {    id    name    team  }}";
                String jsonResponse = httpRequest.sendRequest(deleteQuery);
                Player player = playerService.jsonToObject(jsonResponse, "delete");
                playerService.showPlayer(player);
                break;
            }
        }

    }
}
