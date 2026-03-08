package gr.aueb.casino.manager;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Manager console application.
 * ΟΛΑ τα actions του manager (add/remove/update/queries) είναι εδώ.
 * Στέλνει JSON requests στον Master μέσω TCP.
 */
public class ManagerConsole {

    private static final String MASTER_HOST = "localhost";
    private static final int MASTER_PORT = 9000;

    public static void main(String[] args) {
        new ManagerConsole().run();
    }

    private void run() {
        try (Socket socket = new Socket(MASTER_HOST, MASTER_PORT);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("=== Manager Console ===");

            while (true) {
                printMenu();
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1" -> handleAddGame(scanner, out, in);
                    case "2" -> handleRemoveGame(scanner, out, in);
                    case "3" -> handleUpdateRisk(scanner, out, in);
                    case "4" -> handleQueryProfitPerGame(out, in);
                    case "5" -> handleQueryProfitPerPlayer(scanner, out, in);
                    case "0" -> {
                        System.out.println("Exiting Manager...");
                        return;
                    }
                    default -> System.out.println("Invalid choice");
                }
            }

        } catch (IOException e) {
            System.err.println("Connection to Master failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /* ================== MENU ================== */

    private void printMenu() {
        System.out.println();
        System.out.println("1) Add game");
        System.out.println("2) Remove game");
        System.out.println("3) Update risk level");
        System.out.println("4) Show profit per game");
        System.out.println("5) Show profit per player");
        System.out.println("0) Exit");
        System.out.print("Choice: ");
    }

    /* ================== ACTIONS ================== */

    // 1) Προσθήκη παιχνιδιού
    private void handleAddGame(Scanner scanner,
                               PrintWriter out,
                               BufferedReader in) throws IOException {

        System.out.println("--- Add Game ---");

        System.out.print("Game name: ");
        String gameName = scanner.nextLine().trim();

        System.out.print("Provider name: ");
        String providerName = scanner.nextLine().trim();

        System.out.print("Stars (1-5, αρχική τιμή): ");
        String stars = scanner.nextLine().trim();

        System.out.print("Number of votes (π.χ. 0): ");
        String noOfVotes = scanner.nextLine().trim();

        System.out.print("Game logo path: ");
        String logoPath = scanner.nextLine().trim();

        System.out.print("Min bet (π.χ. 0.1): ");
        String minBet = scanner.nextLine().trim();

        System.out.print("Max bet (π.χ. 10): ");
        String maxBet = scanner.nextLine().trim();

        System.out.print("Risk level (LOW/MEDIUM/HIGH): ");
        String riskLevel = scanner.nextLine().trim().toUpperCase();

        System.out.print("Secret key (HashKey): ");
        String secretKey = scanner.nextLine().trim();

        // Απλό JSON string (χωρίς library, για αρχή)
        String requestJson = """
                {
                  "type": "ADD_GAME",
                  "gameName": "%s",
                  "providerName": "%s",
                  "stars": %s,
                  "noOfVotes": %s,
                  "gameLogo": "%s",
                  "minBet": %s,         
                  "maxBet": %s,
                  "riskLevel": "%s",
                  "hashKey": "%s"
                }
                """.formatted(
                escape(gameName),
                escape(providerName),
                stars,
                noOfVotes,          //πολλεσ γραμμες απο master διαβασμα
                escape(logoPath),
                minBet,
                maxBet,
                escape(riskLevel),
                escape(secretKey)
        );

//        String requestJson = String.format(
//                "{\"type\":\"ADD_GAME\",\"gameName\":\"%s\",\"providerName\":\"%s\",\"stars\":%s,\"noOfVotes\":%s,\"gameLogo\":\"%s\",\"minBet\":%s,\"maxBet\":%s,\"riskLevel\":\"%s\",\"hashKey\":\"%s\"}",
//                escape(gameName),
//                escape(providerName),
//                stars,
//                noOfVotes,
//                escape(logoPath),     //μια γραμμη απο μαστερ διαβασμα
//                minBet,
//                maxBet,
//                escape(riskLevel),
//                escape(secretKey)
//        );

        sendRequest(out, in, requestJson);
    }

    // 2) Αφαίρεση παιχνιδιού
    private void handleRemoveGame(Scanner scanner,
                                  PrintWriter out,
                                  BufferedReader in) throws IOException {

        System.out.println("--- Remove Game ---");
        System.out.print("Game name to remove: ");
        String gameName = scanner.nextLine().trim();

        String requestJson = """
                {
                  "type": "REMOVE_GAME",
                  "gameName": "%s"
                }
                """.formatted(escape(gameName));

        sendRequest(out, in, requestJson);
    }

    // 3) Αλλαγή risk level υπάρχοντος παιχνιδιού
    private void handleUpdateRisk(Scanner scanner,
                                  PrintWriter out,
                                  BufferedReader in) throws IOException {

        System.out.println("--- Update Risk Level ---");
        System.out.print("Game name: ");
        String gameName = scanner.nextLine().trim();

        System.out.print("New risk level (LOW/MEDIUM/HIGH): ");
        String riskLevel = scanner.nextLine().trim().toUpperCase();

        String requestJson = """
                {
                  "type": "UPDATE_RISK",
                  "gameName": "%s",
                  "riskLevel": "%s"
                }
                """.formatted(escape(gameName), escape(riskLevel));

        sendRequest(out, in, requestJson);
    }

    // 4) Συνολικά κέρδη/ζημιές ανά παιχνίδι
    private void handleQueryProfitPerGame(PrintWriter out,
                                          BufferedReader in) throws IOException {

        System.out.println("--- Profit per Game ---");

        String requestJson = """
                {
                  "type": "QUERY_PROFIT_PER_GAME"
                }
                """;

        sendRequest(out, in, requestJson);
    }

    // 5) Συνολικά κέρδη/ζημιές ανά παίκτη
    private void handleQueryProfitPerPlayer(Scanner scanner,
                                            PrintWriter out,
                                            BufferedReader in) throws IOException {

        System.out.println("--- Profit per Player ---");
        System.out.print("Player ID: ");
        String playerId = scanner.nextLine().trim();

        String requestJson = """
                {
                  "type": "QUERY_PROFIT_PER_PLAYER",
                  "playerId": "%s"
                }
                """.formatted(escape(playerId));

        sendRequest(out, in, requestJson);
    }

    /* ================== ΒΟΗΘΗΤΙΚΑ ================== */

    // Στέλνει 1 request και διαβάζει 1 απάντηση (μία γραμμή JSON ή string)
    private void sendRequest(PrintWriter out,
                             BufferedReader in,
                             String requestJson) throws IOException {

        // για debug
        // System.out.println("Sending: " + requestJson.replace("\n", " "));

        out.println(requestJson);          // στέλνουμε μια γραμμή
        String response = in.readLine();   // περιμένουμε μία γραμμή απάντηση

        if (response == null) {
            System.out.println("Master closed the connection.");
            System.exit(0);
        } else {
            System.out.println("Response from Master: " + response);
        }
    }

    // Πολύ απλό escaping για διπλά εισαγωγικά μέσα στο string
    private String escape(String s) {
        return s.replace("\"", "\\\"");
    }
}