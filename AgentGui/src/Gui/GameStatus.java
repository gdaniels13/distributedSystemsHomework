/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import AgentCommon.Agent;
import Common.AgentInfo;
import Common.AgentList;
import Common.Bomb;
import Common.FieldLocation;
import Common.GameConfiguration;
import Messages.GetResource;
import brillianstudent.BrilliantStudent;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 *
 * @author gregor
 */
public class GameStatus extends javax.swing.JPanel implements Observer {

    /**
     * Creates new form asdf
     */
    private Agent agent;
    private static GameStatus instance;

    private DefaultMutableTreeNode treeRootNode, gcNode, bsNode, egNode, wgNode, zNode;
    HashMap<String, DefaultMutableTreeNode> bsMap;

    public GameStatus(Agent agent) {
        initComponents();
        this.agent = agent;
        this.agent.addObserver(this);
        this.instance = this;
        this.bsMap = new HashMap<>();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        logTA = new javax.swing.JTextArea();
        getConfigurationButton = new javax.swing.JButton();
        getZombieList = new javax.swing.JButton();
        getTwineGeneratorList = new javax.swing.JButton();
        getTwine = new javax.swing.JButton();
        getStudentList = new javax.swing.JButton();
        getExcuse = new javax.swing.JButton();
        increaseHealth = new javax.swing.JButton();
        treeScrollPane = new javax.swing.JScrollPane();
        infoTree = new javax.swing.JTree();
        getExcuseGeneratorList = new javax.swing.JButton();
        getPlayingField = new javax.swing.JButton();
        bombX = new javax.swing.JTextField();
        bombY = new javax.swing.JTextField();
        throwBombButton = new javax.swing.JButton();
        moveX = new javax.swing.JTextField();
        moveY = new javax.swing.JTextField();
        moveButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        moveUP = new javax.swing.JButton();
        moveDown = new javax.swing.JButton();
        moveRight = new javax.swing.JButton();
        moveLeft = new javax.swing.JButton();

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

        logTA.setColumns(20);
        logTA.setLineWrap(true);
        logTA.setRows(5);
        logTA.setToolTipText("");
        jScrollPane1.setViewportView(logTA);

        getConfigurationButton.setText("Get Game Configuration");
        getConfigurationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getConfigurationButtonActionPerformed(evt);
            }
        });

        getZombieList.setText("Get Zombie List");
        getZombieList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getZombieListActionPerformed(evt);
            }
        });

        getTwineGeneratorList.setText("Get Twine Generator List");
        getTwineGeneratorList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getTwineGeneratorListActionPerformed(evt);
            }
        });

        getTwine.setText("Get Twine");
        getTwine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getTwineActionPerformed(evt);
            }
        });

        getStudentList.setText("Get Brilliant Student List");
        getStudentList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getStudentListActionPerformed(evt);
            }
        });

        getExcuse.setText("Get Excuse");
        getExcuse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getExcuseActionPerformed(evt);
            }
        });

        increaseHealth.setText("Increase Health");
        increaseHealth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseHealthActionPerformed(evt);
            }
        });

        infoTree.setModel(getModel());
        treeScrollPane.setViewportView(infoTree);

        getExcuseGeneratorList.setText("Get Excuse Generator List");
        getExcuseGeneratorList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getExcuseGeneratorListActionPerformed(evt);
            }
        });

        getPlayingField.setText("Get Playing Field Layout");
        getPlayingField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getPlayingFieldActionPerformed(evt);
            }
        });

        bombX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bombXActionPerformed(evt);
            }
        });

        bombY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bombYActionPerformed(evt);
            }
        });

        throwBombButton.setText("Throw Bomb");
        throwBombButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                throwBombButtonActionPerformed(evt);
            }
        });

        moveX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveXActionPerformed(evt);
            }
        });

        moveY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveYActionPerformed(evt);
            }
        });

        moveButton.setText("Move");
        moveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveButtonActionPerformed(evt);
            }
        });

        moveUP.setText("UP");
        moveUP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveUPActionPerformed(evt);
            }
        });

        moveDown.setText("DOWN");
        moveDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveDownActionPerformed(evt);
            }
        });

        moveRight.setText("RIGHT");
        moveRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveRightActionPerformed(evt);
            }
        });

        moveLeft.setText("LEFT");
        moveLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveLeftActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(moveLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(moveDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(moveUP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moveRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(moveUP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moveDown)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(moveRight))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(moveLeft)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bombX, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bombY, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(throwBombButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(getTwineGeneratorList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(positionLabel)
                    .addComponent(ExcuseLabel)
                    .addComponent(AgentStatus)
                    .addComponent(twineLabel)
                    .addComponent(ticksLabel)
                    .addComponent(healthLabel)
                    .addComponent(getConfigurationButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(getStudentList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(getZombieList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(getExcuseGeneratorList, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(increaseHealth, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(getPlayingField, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(getExcuse, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(getTwine, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(moveX, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveY, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(treeScrollPane)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(treeScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(healthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ticksLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(twineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AgentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(ExcuseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(positionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(getConfigurationButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getStudentList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getZombieList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getTwineGeneratorList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getExcuseGeneratorList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getTwine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getExcuse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(increaseHealth)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getPlayingField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bombX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bombY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(throwBombButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(moveX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(moveY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(moveButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(97, Short.MAX_VALUE))
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

    private void getConfigurationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getConfigurationButtonActionPerformed
        agent.requestResourceFromServer(GetResource.PossibleResourceType.GameConfiguration);
    }//GEN-LAST:event_getConfigurationButtonActionPerformed

    private void getZombieListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getZombieListActionPerformed
        agent.requestResourceFromServer(GetResource.PossibleResourceType.ZombieProfessorList);

    }//GEN-LAST:event_getZombieListActionPerformed

    private void getTwineGeneratorListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getTwineGeneratorListActionPerformed
        agent.requestResourceFromServer(GetResource.PossibleResourceType.WhiningSpinnerList);
    }//GEN-LAST:event_getTwineGeneratorListActionPerformed

    private void getTwineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getTwineActionPerformed
        try {
            ((BrilliantStudent) agent).requestResource(null, GetResource.PossibleResourceType.WhiningTwine);
        } catch (ClassCastException e) {
            updateLog("Dont push that button");
        }
    }//GEN-LAST:event_getTwineActionPerformed

    private void getStudentListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getStudentListActionPerformed
        agent.requestResourceFromServer(GetResource.PossibleResourceType.BrillianStudentList);

    }//GEN-LAST:event_getStudentListActionPerformed

    private void getExcuseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getExcuseActionPerformed
        try {
            ((BrilliantStudent) agent).requestResource(null, GetResource.PossibleResourceType.Excuse);
        } catch (ClassCastException e) {
            updateLog("Dont push that button");
        }
    }//GEN-LAST:event_getExcuseActionPerformed

    private void increaseHealthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseHealthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseHealthActionPerformed

    private void getExcuseGeneratorListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getExcuseGeneratorListActionPerformed
        agent.requestResourceFromServer(GetResource.PossibleResourceType.ExcuseGeneratorList);
    }//GEN-LAST:event_getExcuseGeneratorListActionPerformed

    private void getPlayingFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getPlayingFieldActionPerformed
        agent.requestResourceFromServer(GetResource.PossibleResourceType.PlayingFieldLayout);

    }//GEN-LAST:event_getPlayingFieldActionPerformed

    private void bombXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bombXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bombXActionPerformed

    private void bombYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bombYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bombYActionPerformed

    private void throwBombButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_throwBombButtonActionPerformed
        short x = Short.parseShort(bombX.getText());
        short y = Short.parseShort(bombY.getText());
        FieldLocation fl= new FieldLocation(x, y, false);
        BrilliantStudent bs;
        try{
            bs = (BrilliantStudent)agent;
        }
        catch(ClassCastException e){
            updateLog("Don't Push that Button");
            return;
        }
        
        Bomb b = new Bomb(agent.getPid(), bs.getExcuses(Integer.MAX_VALUE), bs.getTwine(Integer.MAX_VALUE), bs.getTickQueue().poll());
        bs.throwBomb(b, fl);
    }//GEN-LAST:event_throwBombButtonActionPerformed

    private void moveXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moveXActionPerformed

    private void moveYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moveYActionPerformed

    private void moveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveButtonActionPerformed
        short x = Short.parseShort(moveX.getText());
        short y = Short.parseShort(moveY.getText());
        FieldLocation fl= new FieldLocation(x, y, false);
        try{
        ((BrilliantStudent)agent).move(fl);
        }
        catch(ClassCastException e){
            updateLog("Don't Push that Button");
        }
    }//GEN-LAST:event_moveButtonActionPerformed

    private void moveUPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUPActionPerformed
        ((BrilliantStudent)agent).move('u');
    }//GEN-LAST:event_moveUPActionPerformed

    private void moveDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDownActionPerformed
        ((BrilliantStudent)agent).move('d');
    }//GEN-LAST:event_moveDownActionPerformed

    private void moveRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveRightActionPerformed
        ((BrilliantStudent)agent).move('r');
    }//GEN-LAST:event_moveRightActionPerformed

    private void moveLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveLeftActionPerformed
        ((BrilliantStudent)agent).move('d');
    }//GEN-LAST:event_moveLeftActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AgentStatus;
    private javax.swing.JTextField ExcuseLabel;
    private javax.swing.JTextField bombX;
    private javax.swing.JTextField bombY;
    private javax.swing.JButton getConfigurationButton;
    private javax.swing.JButton getExcuse;
    private javax.swing.JButton getExcuseGeneratorList;
    private javax.swing.JButton getPlayingField;
    private javax.swing.JButton getStudentList;
    private javax.swing.JButton getTwine;
    private javax.swing.JButton getTwineGeneratorList;
    private javax.swing.JButton getZombieList;
    private javax.swing.JTextField healthLabel;
    private javax.swing.JButton increaseHealth;
    private javax.swing.JTree infoTree;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logTA;
    private javax.swing.JButton moveButton;
    private javax.swing.JButton moveDown;
    private javax.swing.JButton moveLeft;
    private javax.swing.JButton moveRight;
    private javax.swing.JButton moveUP;
    private javax.swing.JTextField moveX;
    private javax.swing.JTextField moveY;
    private javax.swing.JTextField positionLabel;
    private javax.swing.JButton throwBombButton;
    private javax.swing.JTextField ticksLabel;
    private javax.swing.JScrollPane treeScrollPane;
    private javax.swing.JTextField twineLabel;
    // End of variables declaration//GEN-END:variables

    public void updateAgentInfo() {
        AgentInfo a = agent.getAgentInfo();
        healthLabel.setText("Health: " + Double.toString(a.getStrength()));
        ticksLabel.setText("Ticks: " + Integer.toString(agent.getTickQueue().size()));
        twineLabel.setText("Twine: " + Integer.toString(agent.getTwineQueue().size()));
        AgentStatus.setText("Status: " + a.getAgentStatus().toString());
        ExcuseLabel.setText("Excuses: " + Integer.toString(agent.getExcuseQueue().size()));
        positionLabel.setText("Agent Position: " + "x: " + a.getLocation().getX() + " y: " + a.getLocation().getY());
//        scoreLabel.setText("Score: " + Double.toString(a.getPoints()));
//        twineCount5.setText("Speed: " + Double.toString(a.getSpeed()));
//        

    }

    public static void updateLog(String toAppend) {
        if (instance == null) {
            return;
        } else {
            boolean moveToEnd = false;
            if (instance.logTA.getCaretPosition() == instance.logTA.getDocument().getLength()) {
                moveToEnd = true;
            }
            instance.logTA.append(toAppend + "\n");
            if (moveToEnd) {
                instance.logTA.setCaretPosition(instance.logTA.getDocument().getLength());
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        updateAgentInfo();
        updateTree();

        repaint();
        validate();
    }

    
    public void updateTree() {
        
        
        updateGameInfo();
        
        updateAgentList(bsNode, agent.getBrilliantStudentList(),bsMap);
//        updateAgentList(egNode, agent.getExcuseGeneratorList());
//        updateAgentList(wgNode, agent.getWhiningSpinnerList());
//        updateAgentList(zNode, agent.getZombieList());
        infoTree.updateUI();
        repaint();
        validate();
    }

    private TreeModel getModel() {
        treeRootNode = new DefaultMutableTreeNode("Game Info");

        gcNode = new DefaultMutableTreeNode("Game Configuration");
        gcNode.add(new DefaultMutableTreeNode("No Information Available"));
        treeRootNode.add(gcNode);

        egNode = new DefaultMutableTreeNode("Excuse Generator List");
        egNode.add(new DefaultMutableTreeNode("No Information Available"));
        treeRootNode.add(egNode);

        bsNode = new DefaultMutableTreeNode("Brilliant Student List");
        bsNode.add(new DefaultMutableTreeNode("No Information Available"));
        treeRootNode.add(bsNode);

        wgNode = new DefaultMutableTreeNode("Twine Generator ");
        wgNode.add(new DefaultMutableTreeNode("No Information Available"));
        treeRootNode.add(wgNode);

        zNode = new DefaultMutableTreeNode("Zombies ");
        zNode.add(new DefaultMutableTreeNode("No Information Available"));
        treeRootNode.add(zNode);
        TreeModel tm = new DefaultTreeModel(treeRootNode);
        return tm;
    }

    private ArrayList<String> getFields(GameConfiguration obj) {
        ArrayList<String> toReturn = new ArrayList<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(GameConfiguration.class);
            for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
                String propertyName = propertyDesc.getName();
                Object value = propertyDesc.getReadMethod().invoke(obj);

                if (value instanceof Float) {
                    propertyName += " " + (float) value;
                } else if (value instanceof Byte) {
                    propertyName += " " + (byte) value;
                } else if (value instanceof Short) {
                    propertyName += " " + (short) value;
                }
                toReturn.add(propertyName);
            }
        } catch (IntrospectionException ex) {
            Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toReturn;
    }

    private void addList(DefaultMutableTreeNode tn, ArrayList<String> list) {
        for (String t : list) {
            tn.add(new DefaultMutableTreeNode(t));
        }
    }

    private void updateGameInfo() {
        GameConfiguration gc = agent.getGameConfiguration();
        if (gc == null) {
            gcNode.removeAllChildren();
            gcNode.add(new DefaultMutableTreeNode("Game Info was null"));
        } else {
            gcNode.removeAllChildren();
            
            addList(gcNode, getFields(gc));
        }
    }

    private void updateAgentList(DefaultMutableTreeNode node, AgentList agentList, HashMap<String,DefaultMutableTreeNode> map) {
        if (agentList == null) {
            node.removeAllChildren();
            node.add(new DefaultMutableTreeNode("no known agents"));
        } else {
            node.removeAllChildren();
//          Enumeration en = node.children();
//           while(en.hasMoreElements()){
//               DefaultMutableTreeNode temp = (DefaultMutableTreeNode)en.nextElement();
//               updateLog(temp.toString());
//           }
           
           for (AgentInfo agentInfo : agentList) {
                node.add(getNodeFromAgent(agentInfo));
            }
        }
    }

    private DefaultMutableTreeNode getNodeFromAgent(AgentInfo ai) {
        DefaultMutableTreeNode toReturn = new DefaultMutableTreeNode(ai.getFirstName() + "_" + ai.getLastName() +"_"+  ai.getId()
        + "_"+ ai.getLocation().toString() + "_" + ai.getStrength() + "_" + ai.getPoints());
        return toReturn;
    }

}
