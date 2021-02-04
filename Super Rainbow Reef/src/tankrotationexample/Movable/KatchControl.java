/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankrotationexample.Movable;


import tankrotationexample.game.GameObject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 *
 * @author anthony-pc
 */
public class KatchControl implements KeyListener {

    private Katch t1;
    private final int left;
    private final int right;

    private final int shoot;
    
    public KatchControl(Katch t1, int left, int right, int shoot) {
        this.t1 = t1;
        this.left = left;
        this.right = right;

        this.shoot = shoot;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == left) {
            this.t1.toggleRightPressed();
        }
        if (keyPressed == right) {
            this.t1.toggleLeftPressed();
        }

        if (keyPressed == shoot) {
            this.t1.toggleShootPressed();
        }
        if (keyPressed == KeyEvent.VK_CAPS_LOCK){
            GameObject.debugger = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == left) {
            this.t1.unToggleLeftPressed();
        }
        if (keyReleased == right) {
            this.t1.unToggleRightPressed();
        }

        if (keyReleased == shoot) {
            this.t1.unToggleShootPressed();
        }
        if (keyReleased == KeyEvent.VK_CAPS_LOCK){
            GameObject.debugger = false;
        }
    }
}
