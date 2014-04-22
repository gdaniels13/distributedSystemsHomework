/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import AgentCommon.Agent;
import Communication.Config;
import Communication.RegistryClient;
import brillianstudent.BrilliantStudent;
import excusegenerator.ExcuseGenerator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twinegenerator.TwineGenerator;
import webService.GameInfoAlt;
import webService.GameInfoGameStatus;

/**
 *
 * @author gregor
 */
public class SelectGame extends javax.swing.JPanel {

    private Agent agent;
    private List<GameInfoAlt> games;
    private MainGui window;

    /**
     * Creates new form gameStatus
     */
    public SelectGame() {
        initComponents();
    }

    public SelectGame(MainGui aThis) {
        initComponents();
        this.window = aThis;
        initializeGameList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        agentTypeSelector = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        availableGames = new java.awt.List();
        refreshButtion = new javax.swing.JButton();
        selectGame = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        selectedGameInfo = new javax.swing.JTextPane();
        bsRadio = new javax.swing.JRadioButton();
        wRadio = new javax.swing.JRadioButton();
        eRadio = new javax.swing.JRadioButton();

        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(266, 311));

        availableGames.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                availableGamesItemStateChanged(evt);
            }
        });
        availableGames.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                availableGamesCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        refreshButtion.setText("Refresh");
        refreshButtion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtionActionPerformed(evt);
            }
        });

        selectGame.setText("Join Game");
        selectGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectGameActionPerformed(evt);
            }
        });

        selectedGameInfo.setFocusable(false);
        jScrollPane1.setViewportView(selectedGameInfo);

        agentTypeSelector.add(bsRadio);
        bsRadio.setSelected(true);
        bsRadio.setText("Brilliant Student");

        agentTypeSelector.add(wRadio);
        wRadio.setText("Whiner");

        agentTypeSelector.add(eRadio);
        eRadio.setText("Excuser");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(refreshButtion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(selectGame, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bsRadio)
                    .addComponent(wRadio)
                    .addComponent(eRadio)
                    .addComponent(availableGames, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(bsRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(availableGames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectGame)
                    .addComponent(refreshButtion))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void availableGamesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_availableGamesItemStateChanged
        displayGameInfo();
    }//GEN-LAST:event_availableGamesItemStateChanged

    private void availableGamesCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_availableGamesCaretPositionChanged
        // TODO add your handling code here:
        System.out.println("Something changed");
    }//GEN-LAST:event_availableGamesCaretPositionChanged

    private void refreshButtionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtionActionPerformed
        initializeGameList();
    }//GEN-LAST:event_refreshButtionActionPerformed

    private void selectGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectGameActionPerformed
        int selected = availableGames.getSelectedIndex();
        if (selected == -1) {
            return;
        }
            //"BS N A00798340 Greg Daniels\n"

        try {
            if (bsRadio.isSelected()) {
                this.agent = new BrilliantStudent(new Config("BS N A00798340 Greg Daniels".split(" ")));
            }
            else if(wRadio.isSelected()){
                this.agent = new TwineGenerator(new Config("WG N A00798340 Greg Daniels".split(" ")));
            }
            else{
                this.agent = new ExcuseGenerator(new Config("EG N A00798340 Greg Daniels".split(" ")));
            }
        } catch (Exception ex) {
            Logger.getLogger(SelectGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        GameInfoAlt gi = games.get(selected);
        agent.getConfig().setGameInfo(gi);
        window.setAgent(agent);
        System.out.println(gi.getLabel().getValue());
        window.setTitle(agent.getConfig().getAgentType().toString());
        window.nextWindow();

    }//GEN-LAST:event_selectGameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup agentTypeSelector;
    private java.awt.List availableGames;
    private javax.swing.JRadioButton bsRadio;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JRadioButton eRadio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButtion;
    private javax.swing.JButton selectGame;
    private javax.swing.JTextPane selectedGameInfo;
    private javax.swing.JRadioButton wRadio;
    // End of variables declaration//GEN-END:variables

    private void initializeGameList() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                games = RegistryClient.getGames(GameInfoGameStatus.AVAILABLE).getGameInfoAlt();
                availableGames.removeAll();
                if (games.size() == 0) {
                    selectedGameInfo.setText("NO GAMES AVAILABLE");

                } else {
                    for (GameInfoAlt gi : games) {
                        availableGames.add(gi.getLabel().getValue());
                    }
                }
            }
        }).start();
    }

    private void displayGameInfo() {
        GameInfoAlt gi = games.get(availableGames.getSelectedIndex());
        selectedGameInfo.removeAll();
//        EndPoint ep = gi.getCommunicationEndPoint().getValue();
//        byte[] bytes = BigInteger.valueOf(ep.getAddress()).toByteArray();
//        reverseArray(bytes);
//        try {
//            selectedGameInfo.setText("Name:" + gi.getLabel().getValue() + "\n"
//                    + "ID:" + gi.getId() + "\n"
//                    + "Location: " + InetAddress.getByAddress(bytes).toString() + ":" + ep.getPort()
//            );
//        } catch (UnknownHostException ex) {
//            selectedGameInfo.setText("CRAPPY ENDPOINT DETECTED:\n" + Arrays.toString(ex.getStackTrace()));
//            Logger.getLogger(SelectGame.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public void reverseArray(byte[] bytes) {
        int begin = 0;
        int end = bytes.length - 1;
        while (begin < end) {
            byte temp = bytes[begin];
            bytes[begin] = bytes[end];
            bytes[end] = temp;
            end--;
            begin++;
        }
    }

}
