import AgentCommon.Agent;
import AgentCommon.Config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        if(args == null){
            System.out.println("Must specify file with configuration parameters");
            return;
        }
        ArrayList<Config> configurations = getConfigs(args);
        ArrayList<Agent> agents = getAgents(configurations);
        generatAndStartThreads(agents);
    }

    private static void generatAndStartThreads(ArrayList<Agent> agents) {
        ArrayList<Thread> threads = new ArrayList<>();
        for(Agent t : agents)
        {
            Thread thread = new Thread(t);
            thread.start();
            threads.add(thread);
        }
    }

    private static ArrayList<Agent> getAgents(ArrayList<Config> configurations) {
        ArrayList<Agent> agents = new ArrayList<>();
        for(Config t : configurations){
            agents.add(Agent.Create(t));
        }
        return agents;
    }

    public static ArrayList<Config> getConfigs(String[] args) throws FileNotFoundException {
        ArrayList<Config> configurations = new ArrayList<>();
        Scanner reader;
        String line;
        for(int i = 0; i<args.length; ++i){
            reader = new Scanner(new FileReader(args[i]));
            while(reader.hasNext()){
                line = reader.nextLine();
                if(validateArgString(line)){
                    configurations.add(new Config(line.split(" ")));
                }
            }
        }
        return configurations;
    }

    public static boolean validateArgString(String str){
        if(str == null) return false;
        if(str.compareTo("")==0) return false;
        if(str.charAt(0)=='#') return false;
        if(str.split(" ").length != 7) return false;
        //could use a regex here to validate but this is just quick and dirty
        return true;
    }
}
