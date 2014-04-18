/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gui;

import AgentCommon.Agent;
import Common.AgentInfo;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author gregor
 */
public class GameStatus extends javax.swing.JPanel implements Observer{

    /**
     * Creates new form asdf
     */
    private Agent agent;
    private static GameStatus instance;
    public GameStatus(Agent agent) {
        initComponents();
        this.agent = agent;
        this.agent.addObserver(this);
        this.instance = this;
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ticksLabel = new javax.swing.JTextField();
        healthLabel = new javax.swing.JTextField();
        twineLabel = new javax.swing.JTextField();
        AgentStatus = new javax.swing.JTextField();
        ExcuseLabel = new javax.swing.JTextField();
        positionLabel = new javax.swing.JTextField();
        scoreLabel = new javax.swing.JTextField();
        twineCount5 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTA = new javax.swing.JTextArea();

        setFocusable(false);

        ticksLabel.setText("TimeTicks:");
        ticksLabel.setAutoscrolls(false);
        ticksLabel.setFocusable(false);

        healthLabel.setText("Health:");
        healthLabel.setAutoscrolls(false);
        healthLabel.setFocusable(false);

        twineLabel.setText("Whining Twine Count:");
        twineLabel.setAutoscrolls(false);
        twineLabel.setFocusable(false);
        twineLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twineLabelActionPerformed(evt);
            }
        });

        AgentStatus.setText("Excuse Count:");
        AgentStatus.setAutoscrolls(false);
        AgentStatus.setFocusable(false);
        AgentStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgentStatusActionPerformed(evt);
            }
        });

        ExcuseLabel.setText("Bomb Count:");
        ExcuseLabel.setAutoscrolls(false);
        ExcuseLabel.setFocusable(false);
        ExcuseLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcuseLabelActionPerformed(evt);
            }
        });

        positionLabel.setText("Kill Count:");
        positionLabel.setAutoscrolls(false);
        positionLabel.setFocusable(false);
        positionLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                positionLabelActionPerformed(evt);
            }
        });

        scoreLabel.setText("Twine Generator Count:");
        scoreLabel.setAutoscrolls(false);
        scoreLabel.setFocusable(false);
        scoreLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoreLabelActionPerformed(evt);
            }
        });

        twineCount5.setText("Excuese Generator Count:");
        twineCount5.setAutoscrolls(false);
        twineCount5.setFocusable(false);
        twineCount5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twineCount5ActionPerformed(evt);
            }
        });

        logTA.setColumns(20);
        logTA.setLineWrap(true);
        logTA.setRows(5);
        logTA.setToolTipText("");
        jScrollPane1.setViewportView(logTA);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(twineCount5, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(scoreLabel)
                    .addComponent(positionLabel)
                    .addComponent(ExcuseLabel)
                    .addComponent(AgentStatus)
                    .addComponent(twineLabel)
                    .addComponent(ticksLabel)
                    .addComponent(healthLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(healthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ticksLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(twineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AgentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ExcuseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(positionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(twineCount5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void twineLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twineLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_twineLabelActionPerformed

    private void AgentStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgentStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AgentStatusActionPerformed

    private void ExcuseLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcuseLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ExcuseLabelActionPerformed

    private void positionLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_positionLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_positionLabelActionPerformed

    private void scoreLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoreLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scoreLabelActionPerformed

    private void twineCount5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twineCount5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_twineCount5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AgentStatus;
    private javax.swing.JTextField ExcuseLabel;
    private javax.swing.JTextField healthLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logTA;
    private javax.swing.JTextField positionLabel;
    private javax.swing.JTextField scoreLabel;
    private javax.swing.JTextField ticksLabel;
    private javax.swing.JTextField twineCount5;
    private javax.swing.JTextField twineLabel;
    // End of variables declaration//GEN-END:variables

    public void updateAgentInfo(){
        AgentInfo a = agent.getAgentInfo();
        healthLabel.setText("Health: " + Double.toString(a.getStrength()));
        ticksLabel.setText("Ticks: " + Integer.toString(agent.getTickQueue().size()));
        twineLabel.setText("Twine: " + Integer.toString(agent.getTwineQueue().size()));
        AgentStatus.setText("Status: " + a.getAgentStatus().toString());
        ExcuseLabel.setText("Excuses: " + Integer.toString(agent.getExcuseQueue().size()));
        positionLabel.setText("Agent Position: " + "x: " +a.getLocation().getX() + " y: " + a.getLocation().getY());
        scoreLabel.setText("Score: " + Double.toString(a.getPoints()));
        twineCount5.setText("Speed: " + Double.toString(a.getSpeed()));
        
        
    }
    
  
    public static void updateLog(String toAppend){
        if(instance == null){
            return ;
        }
        else{
            instance.logTA.append(toAppend + "\n");
            instance.logTA.setCaretPosition(instance.logTA.getDocument().getLength());
        }
    }
    @Override
    public void update(Observable o, Object arg) {
        updateAgentInfo();
        
        
        
        repaint();
        validate();
    }
}
