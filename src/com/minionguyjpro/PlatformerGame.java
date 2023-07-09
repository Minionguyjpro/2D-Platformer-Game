package com.minionguyjpro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlatformerGame extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Player player;
    private boolean isJumping;

    public PlatformerGame() {
        setTitle("2D Platformer Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        player = new Player(50, HEIGHT - 100);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && !isJumping) {
                    player.jump();
                    isJumping = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Clear the screen
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw the platform
        g2d.setColor(Color.GREEN);
        g2d.fillRect(100, HEIGHT - 50, 200, 20);

        // Draw the player
        g2d.setColor(Color.RED);
        g2d.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }

    public void update() {
        player.update();

        // Check for collisions or other game logic here

        repaint();
    }

    public static void main(String[] args) {
        PlatformerGame game = new PlatformerGame();

        // Game loop
        while (true) {
            game.update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Player {
        private static final int WIDTH = 50;
        private static final int HEIGHT = 50;
        private static final int GRAVITY = 1;
        private static final int JUMP_HEIGHT = 20;

        private int x;
        private int y;
        private int velocityY;

        public Player(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return WIDTH;
        }

        public int getHeight() {
            return HEIGHT;
        }

        public void jump() {
            velocityY = -JUMP_HEIGHT;
        }

        public void update() {
            y += velocityY;
            velocityY += GRAVITY;

            if (y >= HEIGHT - HEIGHT / 2 - HEIGHT) {
                y = HEIGHT - HEIGHT / 2 - HEIGHT;
                isJumping = false;
            }
        }
    }
}
