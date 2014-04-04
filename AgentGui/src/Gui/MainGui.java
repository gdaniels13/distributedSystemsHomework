/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gui;

import AgentCommon.Agent;
import Communication.Config;
import brillianstudent.BrilliantStudent;
import excusegenerator.ExcuseGenerator;
import java.awt.BorderLayout;
import javax.swing.JFrame;
//import twinegenerator.TwineGenerator;

public class MainGui extends JFrame{
   
    private SelectGame selectGame;
    private GameStatus gameStatus;
    private Agent agent;
    
    public MainGui(Agent agent){
        super("Brilliant Student");
        this.agent = agent;
        selectGame = new SelectGame(this);
        selectGame.setAgent(agent);
        setLayout(new BorderLayout(1,1));
        setSize(selectGame.getMinimumSize());
        add(selectGame);
        validate(); repaint();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void nextWindow() {
        gameStatus = new GameStatus(agent);
        
        remove(selectGame);
        add(gameStatus);
        validate();
        repaint();
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
                agent.init();
                new Thread(agent).start();
//            }
//        });

    }
    

    
}
