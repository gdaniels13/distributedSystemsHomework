/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Communication;

import webService.ArrayOfGameInfo;
import webService.ArrayOfGameInfoAlt;

/**
 *
 * @author gregor
 */
public class RegistryClient {

    public static Short getProcessId() {
        webService.Registrar service = new webService.Registrar();
        webService.IRegistrar port = service.getBasicHttpBindingIRegistrar();
        return port.getProcessId();
    }

    public static ArrayOfGameInfo getGames1(webService.GameInfoGameStatus status) {
        webService.Registrar service = new webService.Registrar();
        webService.IRegistrar port = service.getBasicHttpBindingIRegistrar();
        return port.getGames(status);
    }

    public static void amAlive(java.lang.Integer gameId) {
        webService.Registrar service = new webService.Registrar();
        webService.IRegistrar port = service.getBasicHttpBindingIRegistrar();
        port.amAlive(gameId);
    }

    public static ArrayOfGameInfoAlt getGames(webService.GameInfoGameStatus status) {
        webService.Registrar service = new webService.Registrar();
        webService.IRegistrar port = service.getBasicHttpBindingIRegistrar();
        return port.getGamesAlt(status);
    }
    
}
