//Talya Cohen- 213954217
package gameManagement;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import coliisions.Block;
import coliisions.Collidable;
import coliisions.Paddle;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ChangeBallColor;
import listeners.Counter;
import listeners.ScoreTrackingListener;
import sprites.Sprite;
import sprites.SpriteCollection;
import sprites.ScoreIndicator;
import java.awt.Color;

/**
 * The gameManagement.Game class represents the main game logic and loop.
 * It initializes game objects, runs the game loop.
 */
public class Game {
    // Fields
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Rectangle frame;
    private GUI gui;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private Counter score;

    /**
     * Constructs a new gameManagement.Game object.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        // Set default frame size to 800x600 and add a frame block to the game environment
        this.frame = new Rectangle(0, 0, 800, 600, Color.DARK_GRAY);
        this.environment.addCollidable(new Block(frame));
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
    }

    /**
     * Adds a collidable object to the game environment.
     * @param c The collidable object to add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the game.
     * @param s The sprite object to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initializes the game by creating and adding game objects like blocks and balls.
     */
    public void initialize() {
        this.gui = new GUI("Arkanoid", 800, 600);
        // Create and add a paddle to the game
        Paddle paddle = new Paddle(this.gui.getKeyboardSensor(),
                new Block(new Rectangle(400, 550, 100, 20, Color.YELLOW)));
        paddle.addToGame(this);
        // Create and add three balls to the game
        Ball[] balls = new Ball[3];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(new Point(700 + i * 10, 100), 6, Color.WHITE);
            balls[i].setVelocity(3 + 2 * i, 4 + 2 * i);
            balls[i].setMyEnvironment(this.environment);
            balls[i].addToGame(this);
            this.remainingBalls.increase(1);
        }

        int defaultSize = 25;
        //create bounds blocks
        Block upBlock = new Block(new Rectangle(0, 0, frame.getWidth(), defaultSize, Color.GRAY));
        upBlock.addToGame(this);
        Block downBlock = new Block(new Rectangle(defaultSize, frame.getHeight() - defaultSize,
                frame.getWidth() - 2 * defaultSize,  defaultSize, Color.darkGray));
        downBlock.setBoundsColor(Color.darkGray);
        downBlock.addToGame(this);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        downBlock.addHitListener(ballRemover);
        Block righrBlock = new Block(new Rectangle(0, defaultSize, defaultSize,
                frame.getHeight() - defaultSize, Color.GRAY));
        righrBlock.addToGame(this);
        Block leftBlock = new Block(new Rectangle(frame.getWidth() - defaultSize, defaultSize,
                defaultSize, frame.getHeight() - defaultSize, Color.GRAY));
        leftBlock.addToGame(this);


        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        ChangeBallColor changeBallColor = new ChangeBallColor();
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(new Counter());
        Color[] blocksColor = {Color.WHITE, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN };
        // Create and add blocks to the game
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12 - i; j++) {
                Block block = new Block(new Rectangle(725 - 50 * j, 150 + 20 * i, 50, 20, blocksColor[i]));
                block.setBoundsColor(Color.black);
                block.addToGame(this);
                block.addHitListener(blockRemover);
                block.addHitListener(changeBallColor);
                this.remainingBlocks.increase(1);
                block.addHitListener(scoreTrackingListener);
            }
        }
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreTrackingListener.getScore());
        scoreIndicator.addToGame(this);
    }

    /**
     * Runs the main game loop, updating sprites and handling collisions.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (remainingBlocks.getValue() > 0 && remainingBalls.getValue() > 0) {
            long startTime = System.currentTimeMillis(); //timing
            DrawSurface d = gui.getDrawSurface();
            frame.drawOn(d);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed(); //timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (remainingBlocks.getValue() == 0) {
            this.score.increase(100);
        }
        gui.close();
    }

    /**
     * Removes a collidable object from the game environment.
     * @param c The collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getGameBlock().remove(c);
    }

    /**
     * Removes a sprite object from the game.
     * @param s The sprite object to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }


    /**
     * The main method that creates and runs the game.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
