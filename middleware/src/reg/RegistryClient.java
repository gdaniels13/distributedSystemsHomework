package reg;

import registry.ArrayOfGameInfo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gregor
 */
public class RegistryClient {

    public static ArrayOfGameInfo getGames(registry.GameInfoGameStatus status) {
        registry.Registrar service = new registry.Registrar();
        registry.IRegistrar port = service.getBasicHttpBindingIRegistrar();
        return port.getGames(status);
    }
    

}
