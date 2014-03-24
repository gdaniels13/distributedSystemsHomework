
package wsdlplay2;

import org.datacontract.schemas._2004._07.common.ArrayOfGameInfo;
import org.datacontract.schemas._2004._07.common.GameInfoGameStatus;

public class Wsdlplay2 {


    public static void main(String[] args) {        
        ArrayOfGameInfo x;
        x = getGames( GameInfoGameStatus.AVAILABLE);
        x = getGames( GameInfoGameStatus.COMPLETED);
        x = getGames( GameInfoGameStatus.DEAD);
        x = getGames( GameInfoGameStatus.NOT_INITI_AL_IZED);
        x = getGames( GameInfoGameStatus.RUNNING);
        
    }

    private static ArrayOfGameInfo getGames(org.datacontract.schemas._2004._07.common.GameInfoGameStatus status) {
        org.tempuri.Registrar service = new org.tempuri.Registrar();
        org.tempuri.IRegistrar port = service.getBasicHttpBindingIRegistrar();
        return port.getGames(status);
    }

    private static void amAlive(java.lang.Integer gameId) {
        org.tempuri.Registrar service = new org.tempuri.Registrar();
        org.tempuri.IRegistrar port = service.getBasicHttpBindingIRegistrar();
        port.amAlive(gameId);
    }
    
}
