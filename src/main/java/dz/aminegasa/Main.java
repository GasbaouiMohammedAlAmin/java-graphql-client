package dz.aminegasa;
import java.util.List;
public class Main {
    private static final String GRAPHQL_ENDPOINT = "http://localhost:8080/graphql";
    public static void main(String[] args) {
        PlayerService playerService = new PlayerService();
        HttpRequest httpRequest = new HttpRequest(GRAPHQL_ENDPOINT);
        UserInterface userInterface=new UserInterface();
        while (true){
            Integer inputUserChoice=userInterface.dashboard();
            if(inputUserChoice>6 || inputUserChoice<1)
                break;
            List<String> arrayInputUser=userInterface.getInputUser(inputUserChoice);
            userInterface.processData(inputUserChoice,arrayInputUser
                    ,playerService,httpRequest);
        }
    }

}