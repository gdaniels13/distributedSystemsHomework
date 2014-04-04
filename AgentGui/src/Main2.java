
import AgentCommon.Agent;
import Communication.Config;
import Gui.MainGui;
import brillianstudent.BrilliantStudent;
import excusegenerator.ExcuseGenerator;
import twinegenerator.TwineGenerator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Greg Daniels gdaniels13@gmail.com A00798340
 */
public class Main2 {

    public static void main(String[] args) throws Exception {
        if (args.length < 5) {
            args = "BS N A00798340 Greg Daniels\n".split(" ");
        }

        Agent currentAgent = Create(new Config(args));
        MainGui t = new MainGui(currentAgent);
//	mainGui gui = new mainGui(currentAgent);
//	
//		gui.setVisible(true);
//		new Thread(currentAgent).run();
        t.setVisible(true);
    }

    public static Agent Create(Config config) {
        switch (config.getAgentType()) {
            case "WG":
                return new TwineGenerator(config);
            case "BS":
                return new BrilliantStudent(config);
            case "EG":
                return new ExcuseGenerator(config);
            default:
                throw new IllegalArgumentException(config.getAgentType() + "is not a valid agent Type");
        }
    }
}
