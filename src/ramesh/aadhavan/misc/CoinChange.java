package ramesh.aadhavan.misc;

import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if(amount < 1)
            return 0;

        return _coinChangeTopDown(coins, amount, new int[amount]);
    }


    /**
     * F(S) - minimum number of coins needed to make change for amount S using coin denominations [c0,c1..cn-1]
     *
     * F(S)=F(S−C)+1
     * F(S)=min i=0...n-1 F(S - Ci) + 1
     * F(S) = 0 when S = 0
     * F(S) = -1 when n = 0
     */
    private int _coinChangeTopDown(int[] coins, int remainder, int[] count) {
        if(remainder < 0)
            return -1;

        if(remainder == 0)
            return 0;

        if(count[remainder - 1] != 0)
            return count[remainder - 1];

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int result = _coinChangeTopDown(coins, remainder - coin, count);
            if (result >= 0 && result < min)
                min = 1 + result;
        }

        count[remainder - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[remainder - 1];
    }

    private int _coinChangeBottomUp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i=1; i<=amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
