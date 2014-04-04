/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reg;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import registry.ArrayOfGameInfo;
import registry.GameInfo;
import registry.GameInfoGameStatus;

/**
 *
 * @author gregor
 * must be on campus and must have at least one game server running
 */
public class RegistryClientTest {
    
    @Test
    public void testGetGames() {
        GameInfoGameStatus status = GameInfoGameStatus.AVAILABLE;

        ArrayOfGameInfo result = RegistryClient.getGames(status);
        assertNotNull(result);
        List<GameInfo> gi =  result.getGameInfo();
        assertNotNull(gi);
        assert(gi.size()>0);
    
    }
    
}
