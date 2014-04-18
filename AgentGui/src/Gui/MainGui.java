/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import AgentCommon.Agent;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;

public class MainGui extends JFrame {

    private SelectGame selectGame;
    private GameStatus gameStatus;
    private Agent agent;

    public MainGui(Agent agent, String title) throws HeadlessException {
        super(title);
        this.agent = agent;
        setSize(266, 311);

        nextWindow();
    }

    public MainGui() {
        super("Select your agent");
        
        selectGame = new SelectGame(this);
        setLayout(new BorderLayout(1, 1));
        setSize(selectGame.getMinimumSize());
        add(selectGame);
        validate();
        repaint();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void nextWindow() {
        gameStatus = new GameStatus(agent);
        if(selectGame !=null){
            remove(selectGame);
        }
        setSize(gameStatus.getPreferredSize());
        add(gameStatus);
        validate();
        repaint();
         
        agent.init();
        new Thread(agent).start();
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

}
