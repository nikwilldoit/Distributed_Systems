package Entity.master;

public class Master {

    public String handleRequest(String requestJson) {

        if(requestJson.contains("ADD_GAME")) {

            System.out.println("Add game request received");

            return "{\"status\":\"OK\",\"message\":\"Game added\"}";
        }

        if(requestJson.contains("REMOVE_GAME")) {

            return "{\"status\":\"OK\",\"message\":\"Game removed\"}";
        }

        if(requestJson.contains("UPDATE_RISK")) {

            return "{\"status\":\"OK\",\"message\":\"Risk updated\"}";
        }

        if(requestJson.contains("QUERY_PROFIT_PER_GAME")) {

            return "{\"status\":\"OK\",\"data\":\"profit list\"}";
        }

        if(requestJson.contains("QUERY_PROFIT_PER_PLAYER")) {

            return "{\"status\":\"OK\",\"data\":\"player profit\"}";
        }

        return "{\"status\":\"ERROR\",\"message\":\"Unknown request\"}";
    }
}