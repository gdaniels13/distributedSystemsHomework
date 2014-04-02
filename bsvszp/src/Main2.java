
import Agents.AgentCommon.Agent;
import Agents.AgentCommon.Config;
import Gui.SelectGame;
import Gui.MainGui;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Greg Daniels
 *gdaniels13@gmail.com
 *A00798340
 */
public class Main2 {
	public static void main(String[] args) throws Exception {
	if (args.length == 0) {
		args = "BS N A00798340 Greg Daniels\n".split(" ");
	}

	Agent currentAgent = Agent.Create(new Config(args));
        MainGui t = new MainGui(currentAgent);
//	mainGui gui = new mainGui(currentAgent);
//	
//		gui.setVisible(true);
//		new Thread(currentAgent).run();
            t.setVisible(true);
	}

}
