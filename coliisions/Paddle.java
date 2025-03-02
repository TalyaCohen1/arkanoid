//Talya Cohen- 213954217
package coliisions;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameManagement.Game;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;
import sprites.Sprite;

/**
 * The coliisions.Paddle class represents the paddle in the game.
 */
public class Paddle implements Sprite, Collidable {
    //fields
    private final biuoop.KeyboardSensor keyboard;
    private final Block paddleBlock;
    private final double movePaddle;

    /**
     * Constructs a new coliisions.Paddle object with the specified keyboard sensor and block.
     * @param keyboard The keyboard sensor.
     * @param block The block that represents the paddle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Block block) {
        this.keyboard = keyboard;
        this.paddleBlock = block;
        this.movePaddle = this.paddleBlock.getCollisionRectangle().getWidth() / 10;
    }

    //sprites.Sprite
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.paddleBlock.getCollisionRectangle().getColor());
        d.fillRectangle((int) this.paddleBlock.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.paddleBlock.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.paddleBlock.getCollisionRectangle().getWidth(),
                (int) this.paddleBlock.getCollisionRectangle().getHeight());
        d.setColor(this.paddleBlock.getCollisionRectangle().getColor());
        d.drawRectangle((int) this.paddleBlock.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.paddleBlock.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.paddleBlock.getCollisionRectangle().getWidth(),
                (int) this.paddleBlock.getCollisionRectangle().getHeight());
    }


    //coliisions.Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleBlock.getCollisionRectangle();
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int regionIndex = findRegion(this.paddleBlock.getCollisionRectangle().getUpperLeft().getX(),
                this.paddleBlock.getCollisionRectangle().getWidth(), collisionPoint.getX());
        double currentSpeed = Velocity.getSpeedFromVelocity(currentVelocity);
        switch (regionIndex) {
             case 1:
                currentVelocity = Velocity.fromAngleAndSpeed(300, currentSpeed);
                break;
            case 2:
                currentVelocity = Velocity.fromAngleAndSpeed(330, currentSpeed);
                break;
            case 3:
                currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                break;
            case 4:
                currentVelocity = Velocity.fromAngleAndSpeed(30, currentSpeed);
                break;
            case 5:
                currentVelocity = Velocity.fromAngleAndSpeed(60, currentSpeed);
                break;
            default: // Do nothing
                break;
        }
        return currentVelocity;
    }

    /**
     * Finds the region of the paddle that the ball hit.
     * @param xstart The x-coordinate of the start of the paddle.
     * @param width The width of the paddle.
     * @param xintersection The x-coordinate of the intersection point.
     * @return The region of the paddle that the ball hit.
     */
    public int findRegion(double xstart, double width, double xintersection) {
        for (int i = 0; i < 5; i++) {
            double startRange = xstart + (i * width) / 5;
            double endRange = xstart + ((i + 1) * (width)) / 5;
            if (xintersection >= startRange && xintersection <= endRange) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
    double newX = this.paddleBlock.getCollisionRectangle().getUpperLeft().getX() - this.movePaddle;
    // Check if the paddle is reaching the left edge of the screen
    if (newX < 0) {
        newX = 800 - this.paddleBlock.getCollisionRectangle().getWidth();
        // Set the x-coordinate to the right edge of the screen
    }
    this.paddleBlock.getCollisionRectangle().setUpperLeft(new Point(newX,
            this.paddleBlock.getCollisionRectangle().getUpperLeft().getY()));
}

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        double newX = this.paddleBlock.getCollisionRectangle().getUpperLeft().getX() + this.movePaddle;
        double paddleWidth = this.paddleBlock.getCollisionRectangle().getWidth();
        double screenWidth = 800 /* Calculate the screen width */;
        // Check if the paddle is reaching the right edge of the screen
        if (newX + paddleWidth > screenWidth) {
            newX = 0; // Set the x-coordinate to the left edge of the screen
        }
        this.paddleBlock.getCollisionRectangle().setUpperLeft(new Point(newX,
                this.paddleBlock.getCollisionRectangle().getUpperLeft().getY()));
    }

    /**
     * Adds the paddle to the game.
     * @param g The game to add the paddle to.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
