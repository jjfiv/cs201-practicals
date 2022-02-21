// Since this code is in the week1 folder, we must announce that.
package week1;

/**
 * This is class Wallet; it keeps track of money.
 */
public class Wallet {
    /**
     * Rather than deal with dollars/cents separately, or as doubles, use integer
     * number of pennies.
     */
    private int pennies;

    /**
     * Create a new wallet, with some number of dollars inside.
     * 
     * @param dollars the amount of money to start with.
     */
    public Wallet(int dollars) {
        // there are 100 pennies in each dollar.
        this.pennies = dollars * 100;
    }

    /**
     * Remove the given amount from this wallet.
     * 
     * @param dollars >= 0 amount of dollars to remove.
     * @param cents   >= 0 amount of cents to remove.
     */
    public void spend(int dollars, int cents) {
        assert dollars >= 0;
        assert cents >= 0;
        assert cents <= 99;
        int cost = dollars * 100 + cents;
        this.pennies -= cost;
    }

    /**
     * Determine if we can remove the given amount from this wallet.
     * 
     * @param dollars >= 0 amount of dollars to remove.
     * @param cents   >= 0 amount of cents to remove.
     * @return true if we can afford it, false otherwise.
     */
    public boolean canAfford(int dollars, int cents) {
        assert dollars >= 0;
        assert cents >= 0;
        assert cents <= 99;

        // TODO: implement this correctly:
        return false;
    }

    public int getDollars() {
        return this.pennies / 100;
    }

    public int getCents() {
        return this.pennies % 100;
    }

    public void print() {
        System.out.println(this.getDollars() + " dollars and " + this.getCents() + " cents.");
    }

    public static void main(String[] args) {
        // Create a wallet with 10 dollars.
        Wallet money = new Wallet(10);
        // Spend some of it.
        money.spend(7, 99);
        // Look at it.
        money.print();

        // always true:
        assert money.canAfford(0, 0);
        // currently false:
        assert !money.canAfford(3, 0);
        // currrently just barely true:
        assert money.canAfford(2, 1);

        // Create a wallet with $1M.
        Wallet rich = new Wallet(1_000_000);
        // Spend $1k.
        rich.spend(1_000, 0);
        // Make sure they can still afford $1k.
        assert rich.canAfford(1_000, 99);
        // Make sure we can't.
        assert !money.canAfford(1_000, 99);
    }
}