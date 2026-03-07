package org.services;

import org.domain.BetLevel;
import org.domain.RiskLevel;

import java.net.Socket;

public interface ManagerWorkerConnection {

    //Users

    static void requestUser(int identifier,String username, Socket[] workerConnections) {
        String userRequest =  identifier + ":USER:"+username;
        for (Socket workerConnection : workerConnections) {
            Connection.sendStringData(workerConnection, userRequest);
        }
    }


    //Bet

    static void requestUserBet(int identifier,String username,String game,double bet, Socket[] workerConnections) {
        String betRequest = identifier + ":BET:"+username+":"+game+":"+bet;
        for (Socket workerConnection : workerConnections) {
            Connection.sendStringData(workerConnection, betRequest);
        }

    }

    static void requestGameInformation(int identifier,String game, Socket[] workerConnections) {
        String gameRequestInformation = identifier+":GAME:"+game;
        for (Socket workerConnection : workerConnections) {
            Connection.sendStringData(workerConnection, gameRequestInformation);
        }

    }


    static void requestAvailableGames(int identifier, Socket[] workerConnections) {
        String availableGamesRequest = identifier+":AVAILABLE_GAMES";
        for (Socket workerConnection : workerConnections) {
            Connection.sendStringData(workerConnection, availableGamesRequest);
        }
    }

    // Manager Settings

    static void requestGames(int identifier, Socket[] workerConnections) {
        String gamesRequest = identifier+":GAMES";
        for (Socket workerConnection : workerConnections) {
            Connection.sendStringData(workerConnection, gamesRequest);
        }
    }

    static void requestGameAvailabilitySwitch(int identifier, String game , Socket[] workerConnections) {
        String gameSwitchRequest = identifier+":GAME_AVAILABILITY_SWITCH:" + game;
        for (Socket workerConnection : workerConnections) {
            Connection.sendStringData(workerConnection, gameSwitchRequest);
        }
    }

    static void requestGameRiskChange(int identifier, String game, RiskLevel riskLevel, Socket[] workerConnections) {
        String gameRiskChangeRequest = identifier+":GAME_RISK_SWITCH:" + game +":"+riskLevel;
        for (Socket workerConnection : workerConnections) {
            Connection.sendStringData(workerConnection, gameRiskChangeRequest);
        }
    }
    static void requestGameBetLevelChange(int identifier, String game, BetLevel betLevel, Socket[] workerConnections) {
        String gameChangeBetLevelRequest = identifier+":GAME_BET_LEVEL:" + game +":"+betLevel;
        for (Socket workerConnection : workerConnections) {
            Connection.sendStringData(workerConnection, gameChangeBetLevelRequest);
        }
    }
    static void requestGamesNetProfits(int identifier, String game, Socket[] workerConnections) {
        String gamesNetProfitsRequest = identifier+":GAME_NET_PROFITS:" + game;
        for (Socket workerConnection : workerConnections) {
            Connection.sendStringData(workerConnection, gamesNetProfitsRequest);
        }
    }
    static void requestUsersNetProfits(int identifier, String game, Socket[] workerConnections) {
        String usersNetProfitsRequest = identifier+":USER_NET_PROFITS:" + game;
        for (Socket workerConnection : workerConnections) {
            Connection.sendStringData(workerConnection, usersNetProfitsRequest);
        }
    }


    //Providers
    static void requestProviders(int identifier, Socket[] workerConnections) {
        String providersRequest =  identifier + ":PROVIDERS";
        for (Socket workerConnection : workerConnections) {
            Connection.sendStringData(workerConnection, providersRequest);
        }
    }


    static void requestProvider(int identifier,String providerName, Socket[] workerConnections) {
        String providerRequest =  identifier + ":PROVIDER:"+ providerName;
        for (Socket workerConnection : workerConnections) {
            Connection.sendStringData(workerConnection, providerRequest);
        }
    }



}
