/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Agents.AgentCommon;

import java.util.List;
import registrarClient.ArrayOfGameInfo;
import registrarClient.GameInfo;

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */
public class WebServerClient {
		
	public static ArrayOfGameInfo getGames(registrarClient.GameInfoGameStatus status) {
		registrarClient.Registrar service = new registrarClient.Registrar();
		registrarClient.IRegistrar port = service.getBasicHttpBindingIRegistrar();
		return port.getGames(status);
	}

	public static GameInfo registerGame(java.lang.String label, registrarClient.EndPoint publicEP) {
		registrarClient.Registrar service = new registrarClient.Registrar();
		registrarClient.IRegistrar port = service.getBasicHttpBindingIRegistrar();
		return port.registerGame(label, publicEP);
	}

	public static void changeStatus(java.lang.Integer gameId, registrarClient.GameInfoGameStatus status) {
		registrarClient.Registrar service = new registrarClient.Registrar();
		registrarClient.IRegistrar port = service.getBasicHttpBindingIRegistrar();
		port.changeStatus(gameId, status);
	}

	public  static void amAlive(java.lang.Integer gameId) {
		registrarClient.Registrar service = new registrarClient.Registrar();
		registrarClient.IRegistrar port = service.getBasicHttpBindingIRegistrar();
		port.amAlive(gameId);
	}
	
}
