/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wsdlplay;

import java.util.List;
import org.datacontract.schemas._2004._07.common.ArrayOfGameInfo;
import org.datacontract.schemas._2004._07.common.EndPoint;
import org.datacontract.schemas._2004._07.common.GameInfo;
import org.datacontract.schemas._2004._07.common.GameInfoGameStatus;

/**
 *
 * @author gregor
 */
public class Wsdlplay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        GameInfo y;
//        y = registerGame("this is a test lets see if it works", new EndPoint());
        List<GameInfo> x;
        x = getGames( GameInfoGameStatus.AVAILABLE).getGameInfo();
        x = getGames( GameInfoGameStatus.COMPLETED).getGameInfo();
        x = getGames( GameInfoGameStatus.DEAD).getGameInfo();
        x = getGames( GameInfoGameStatus.NOT_INITI_AL_IZED).getGameInfo();
        x = getGames( GameInfoGameStatus.RUNNING).getGameInfo();
    }



    private static ArrayOfGameInfo getGames(org.datacontract.schemas._2004._07.common.GameInfoGameStatus status) {
        org.tempuri.Registrar service = new org.tempuri.Registrar();
        org.tempuri.IRegistrar port = service.getBasicHttpBindingIRegistrar();
        return port.getGames(status);
    }

    private static GameInfo registerGame(java.lang.String label, org.datacontract.schemas._2004._07.common.EndPoint publicEP) {
        org.tempuri.Registrar service = new org.tempuri.Registrar();
        org.tempuri.IRegistrar port = service.getBasicHttpBindingIRegistrar();
        return port.registerGame(label, publicEP);
    }

    private static void amAlive(java.lang.Integer gameId) {
        org.tempuri.Registrar service = new org.tempuri.Registrar();
        org.tempuri.IRegistrar port = service.getBasicHttpBindingIRegistrar();
        port.amAlive(gameId);
    }
    
}
