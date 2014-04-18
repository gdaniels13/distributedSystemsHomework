
import AgentCommon.Agent;
import Common.MessageNumber;
import Communication.Config;
import Communication.RegistryClient;
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
public class Main {
    //"BS N A00798340 Greg Daniels\n"
    public static void main(String[] args) throws Exception {
        short pid = RegistryClient.getProcessId();
        MessageNumber.LocalProcessId = pid;
        if (args.length == 5) {
            Agent currentAgent = Create(new Config(args));
            
            MainGui t = new MainGui(currentAgent,currentAgent.getConfig().getAgentType().toString());
            t.setVisible(true);
            
        } else {
            MainGui t = new MainGui();
            
            t.setVisible(true);
            
        }

    }

    public static Agent Create(Config config) {
        switch (config.getAgentType()) {
            case WhiningSpinner:
                return new TwineGenerator(config);
            case BrilliantStudent:
                return new BrilliantStudent(config);
            case ExcuseGenerator:
                return new ExcuseGenerator(config);
            default:
                throw new IllegalArgumentException(config.getAgentType() + " is not a valid agent Type");
        }
    }


}
