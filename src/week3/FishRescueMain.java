package week3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import me.jjfoley.gfx.IntPoint;
import week3.grid.Actor;
import week3.grid.Buttons;
import week3.grid.Decoration;
import week3.grid.GridEnv;
import week3.grid.GridView;

/**
 * FishRescueMain deals with bringing fish to safety.
 */
public class FishRescueMain extends GridView {
    /**
     * Fish initially begin in this list; totally randomly placed, "missing".
     */
    private List<LostFish> missingFish;
    /**
     * Once you bump into them, they decide to follow you around.
     */
    private List<LostFish> followFish;
    /**
     * When you get to the same place as the 'home', they are saved.
     */
    private Actor fishHome;
    /**
     * The hero is (once again) not a special class, since we move it here.
     */
    private Decoration hero;

    // TODO: track the number of fish saved.

    /**
     * Construct a new FishRescue simulation.
     */
    public FishRescueMain() {
        super(new GridEnv(10, 10));
        // create a list of missing fish; add to grid.
        this.missingFish = LostFish.createLostFish();
        for (LostFish f : this.missingFish) {
            this.grid.insertRandomly(f);
        }
        // the list of 'following' fish starts empty.
        this.followFish = new ArrayList<>();

        // create a home for the fish:
        this.fishHome = new Decoration("houses"); // maybe "coral" is more thematic...
        // put it anywhere:
        this.grid.insertRandomly(this.fishHome);

        // create a hero, put it on top of the home, add to grid:
        this.hero = new Decoration("dolphin");
        this.hero.setPoint(this.fishHome.getPoint());
        this.grid.insert(hero);
    }

    // TODO: override getHeaderText to express the number of fish saved.

    @Override
    public void buttons(Buttons pressed) {
        IntPoint maybeStep = pressed.nextPosition(this.hero.getPoint());
        // TODO: don't let the hero go outside the grid. (cmp w/HungryTurtleMain)
        this.hero.setPoint(maybeStep);

        // update fish locations based on hero movement:
        this.fishFollow();
        // grab any fish in the same spot as the hero to follow
        this.recruitFish();
        // if we're at home, 'rescue' all the fish.
        this.rescueFish();
    }

    /**
     * For all the missing fish, if they're in the same spot as the hero:
     * 1. move them from missingFish to followFish.
     * 2. change their isMissing variable to false.
     */
    public void recruitFish() {
        for (LostFish f : missingFish) {
            if (f.getPoint().equals(this.hero.getPoint())) {
                // TODO: recruit fish to follow you.
            }
        }
    }

    public void rescueFish() {
        // TODO: determine if hero & fishHome in same spot
        // TODO: then: remove fish from game & follow list, award points.
    }

    /**
     * Keep track of the places we have been recently:
     * Why a LinkedList... hmm?
     */
    private LinkedList<IntPoint> steps = new LinkedList<>();

    /**
     * Make fish in the follow list trail after you.
     * 
     * This works good enough (no todos).
     */
    public void fishFollow() {
        // Following fish trail after the hero:
        for (int i = 0; i < Math.min(this.steps.size(), this.followFish.size()); i++) {
            this.followFish.get(i).setPoint(this.steps.get(i));
        }

        // keep track of where the hero has been:
        if (this.steps.size() > 32) {
            this.steps.removeLast();
        }
        this.steps.addFirst(this.hero.getPoint());
    }

    /**
     * Start up the game!
     * 
     * @param args - ignored, as usual.
     */
    public static void main(String[] args) {
        GridView app = new FishRescueMain();
        app.start();
    }

}
