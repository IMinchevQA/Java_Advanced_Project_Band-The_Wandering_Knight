package entity;

import game.Game;
import game.Handler;
import gfx.Animations;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

//PLAYER CLASS, PLAYER LOGIC GOES HERE
public class Player extends Creature {

        //ANIMATIONS

        private Animations animationDown, animationUp, animationLeft, animationRight;


        public static float playerSpeed = Creature.DEFAULT_SPEED;
        public static float playerAttackDamage = 20;

        private long lastAttack, attackCooldown = 800, attackTimer;

        //WHERE IS THE HERO GOING
        private boolean up, left, right, down, attackUp, attackLeft, attackRight, attackDown;

        public Player(Handler handler, float x, float y) {
            super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
            //Player MUST TAKE THE game OBJECT.
            //WHY? - TO GET ACCESS TO THE InputManager's INPUT METHODS(up, down, left, right)!
            //HOW? = BY CALLING Game CLASS METHOD - get.KeyManager().up/down/left/right

            //MINI RECTANGLE IN THE PLAYER FOR COLLISION DETECTION
            bounds.x = 44;
            bounds.y = 52;
            bounds.width = 18;
            bounds.height = 45;

            //Animations
            //REFRESH RATE IN MS , ANIMATION ASSET
            animationDown = new Animations((int) playerSpeed * 70, Assets.playerDownMove);
            animationUp = new Animations((int) playerSpeed * 70, Assets.playerUpMove);
            animationRight = new Animations((int) playerSpeed * 70, Assets.playerRightMove);
            animationLeft = new Animations((int) playerSpeed * 70, Assets.playerLeftMove);


        }

        @Override
        public void tick() {

            //MOVEMENT
            getInput();
            move();
            handler.getGameCamera().centerOnEntity(this);

            //ATTACKS
            checkAttack();
        }

        private void checkAttack() {
            attackLeft = false; attackRight = false; attackUp = false; attackDown = false;
            attackTimer += System.currentTimeMillis() - lastAttack;
            lastAttack = System.currentTimeMillis();
            if(attackTimer < attackCooldown)
                return;

            //MY COLLISION RECTANGLE
            Rectangle cb = getCollisionBounds(0, 0);
            //ATTACK RANGE RECTANGLE
            Rectangle ar = new Rectangle();
            int arSize = 20;
            ar.width = arSize;
            ar.height = arSize;
            if (handler.getKeyManager().attack) {
                if (right) {
                    attack("right");
                    ar.x = cb.x + cb.width;
                    ar.y = cb.y + cb.height / 2 - arSize / 2;
                } else if (left) {
                    attack("left");
                    ar.x = cb.x - arSize;
                    ar.y = cb.y + cb.height / 2 - arSize / 2;

                } else if (up) {
                    attack("up");
                    ar.x = cb.x + cb.width / 2 - arSize / 2;
                    ar.y = cb.y - arSize;

                } else if (down) {
                    attack("down");
                    ar.x = cb.x + cb.width / 2 - arSize / 2;
                    ar.y = cb.y + cb.height;
                }
                for(Entity e : handler.getWorld().getEntityManager().getEntities()){
                    if(e.equals(this))
                        continue;
                    if(e.getCollisionBounds(0, 0).intersects(ar)){
                        e.recieveDamage((int)playerAttackDamage);
                        attackTimer = 0;
                        return;
                    }
                }
                return;
            }

        }

        public void die() {
            System.out.println("You lost");
        }

        private void getInput() {
            xMove = 0;
            yMove = 0;
            if (handler.getKeyManager().up) {
                animationUp.update();
                left = false;
                right = false;
                down = false;
                up = true;
                yMove = -playerSpeed;
            }

            if (handler.getKeyManager().down) {
                animationDown.update();
                left = false;
                right = false;
                down = true;
                up = false;
                yMove = playerSpeed;
            }
            if (handler.getKeyManager().left) {
                animationLeft.update();
                left = true;
                right = false;
                down = false;
                up = false;
                xMove = -playerSpeed;
            }
            if (handler.getKeyManager().right) {
                animationRight.update();
                left = false;
                right = true;
                down = false;
                up = false;
                xMove = playerSpeed;
            }
        }

        @Override
        public void render(Graphics g) {
            g.drawImage(getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()), null);

            //HELP CODE FOR COLLISION DETECTION RECTANGLE;
            //uncomment to see the collision detection rect
//        g.setColor(Color.red);
//        g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
        }

        private BufferedImage getCurrentFrame() {
            if (xMove < 0) {
                return animationLeft.getCurrentFrame();
            } else if (xMove > 0) {
                return animationRight.getCurrentFrame();
            } else if (yMove > 0) {
                return animationDown.getCurrentFrame();
            } else if (yMove < 0) {
                return animationUp.getCurrentFrame();
            } else if (attackUp) {
                if(animationUp.getIndex() == 0)
                    return Assets.playerUpAttack[0];
                else
                    return Assets.playerUpAttack[1];
            } else if (attackDown) {
                if(animationUp.getIndex() == 0)
                    return Assets.playerDownAttack[0];
                else
                    return Assets.playerDownAttack[1];
            } else if (attackRight) {
                if(animationRight.getIndex() == 0)
                    return Assets.playerRightAttack[1];
                else
                    return Assets.playerRightAttack[1];
            } else if (attackLeft) {
                if(animationLeft.getIndex() == 0)
                    return Assets.playerLeftAttack[1];
                else
                    return Assets.playerLeftAttack[1];
            } else {
                if (down)
                    return animationDown.getCurrentFrame();
                if (up)
                    return animationUp.getCurrentFrame();
                if (left)
                    return animationLeft.getCurrentFrame();
                if (right)
                    return animationRight.getCurrentFrame();
            }
            return animationDown.getCurrentFrame();
        }

        private void attack(String attackDirection) {
            if(String.valueOf(attackDirection).equals("up")) {
                attackUp = true;
                attackDown = false;
                attackLeft = false;
                attackRight = false;
            }
            if(String.valueOf(attackDirection).equals("down")) {
                attackUp = false;
                attackDown = true;
                attackLeft = false;
                attackRight = false;
            }
            if(String.valueOf(attackDirection).equals("left")) {
                attackUp = false;
                attackDown = false;
                attackLeft = true;
                attackRight = false;
            }
            if(String.valueOf(attackDirection).equals("right")) {
                attackUp = false;
                attackDown = false;
                attackLeft = false;
                attackRight = true;
            }

        }

    }
