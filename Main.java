import java.util.*;

import javax.print.DocFlavor.STRING;

public class Main{
    
    //Arrays
    public static int[] twoSums(int[] nums, int target){ //O(N) both time and space
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int difference = target - nums[i];
            if(map.containsKey(difference)){
                result[0] = i;
                result[1] = map.get(difference);
                return result;
            }    
            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }

    public static int buyAndSellStocks(int[] prices){ //O(N) time and O(1) space
        int maxProfit = 0;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if(prices[i]< minValue){
                minValue = prices[i];
            }else{
                maxProfit = Math.max(maxProfit, prices[i] - minValue);
            }
        }

        return maxProfit;
    }

    public static boolean containsDuplicate(int[] nums){ //O(N) time and space
        HashSet<Integer> set = new HashSet<>();
        for(int i : nums){
            if(set.contains(i)){
                return true;
            }
            else{
                set.add(i);
            }
        }
        return false;
    }

    public static int[] productExceptSelf(int[] nums){ //O(N) time O(1) space
        int[] result = new int[nums.length];
        int rp = 1;

        for(int i = 0; i < nums.length; i++){
            result[i] = rp;
            rp = rp * result[i];
        }

        rp = 1;

        for(int i = nums.length - 1; i >= 0; i--){
            result[i] = rp * result[i];
            rp = rp * result[i];
        }

        return result;
    }

    public static int maxSubarrayQuadratic(int[] nums){ //O(N^2) and O(1)
        int currSum = 0;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++){
            for(int j = i; j< nums.length; j++){
                currSum += nums[j];
                max = Math.max(currSum, max);
            }
            currSum = 0;
        }
        return max;
    }

    //Kadane's algorithm
    public static int maxSubArray(int[] nums){ //O(N) and O(1)
        int prefix = 0;
        int max = nums[0];

        for(int i = 0; i < nums.length; i++){
            if(prefix < 0){
                prefix = 0;
            }
            prefix += nums[i];
            max = Math.max(max, prefix);
            
        }

        return max;
    }

    public static int maxProductSubArray(int[] nums){ //O(N) and (O)1
        int max = nums[0];
        int max_end = nums[0];
        int min_end = nums[0];

        for(int i=1; i < nums.length; i++){
            int tmp = max_end;
            max_end = Math.max(max_end * nums[i], Math.max(min_end*nums[i], nums[i]));
            min_end = Math.min(min_end * nums[i], Math.max(max_end*nums[i], nums[i]));
            max = Math.max(max, max_end);
        }

        return max;
    }

    //Index
    public static int minimumInRotatedArrayIndex(int[] nums){
        if(nums.length == 0 || nums == null){
            return -1;
        }

        if(nums.length == 1) return 0;

        int left = 0;
        int right = nums.length - 1;

        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] < nums[mid -1] && (mid-1) >= 0){
                return mid;
            }

            if(nums[left] <= nums[mid] && nums[right] < nums[mid]){
                left = mid + 1;
            }
            else{
                right = mid -1;
            }
        }

        return left;
    }

    public static int minimumInRotatedArray(int[] nums){
        if(nums.length == 0 || nums == null){
            return -1;
        }

        if(nums.length == 1) return nums[0];

        int left = 0;
        int right = nums.length - 1;

        while(left > right){
            int mid = left + (right - left)/2;
            if(nums[mid] < nums[mid -1] && (mid-1) >= 0){
                return nums[mid];
            }

            if(nums[left] <= nums[mid] && nums[right] < nums[mid]){
                left = mid + 1;
            }
            else{
                right = mid -1;
            }
        }

        return nums[left];
    }

    public static int BinarySearch(int[] nums, int l, int r, int target){
        while(l < r){
            int mid = l + (r - l)/2;
            if(nums[mid] == target){
                return mid;
            }

            if(target < nums[mid]){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }

        if(nums[l] == target) return l;

        return -1;
    } 

    public static int searchRotatedSortedArray(int[] nums, int target){ //O(logN) and O(1)
        if(nums == null || nums.length == 0) return -1;

        if(nums.length == 1) return nums[0] == target ? 0 : -1;

        int pivot = minimumInRotatedArrayIndex(nums);

        int sorted = BinarySearch(nums, 0, pivot - 1, target);
        int unsorted = BinarySearch(nums, pivot, nums.length - 1, target);

        if(sorted != -1) return sorted;
        if(unsorted != -1) return unsorted;

        return -1;

    } 

    public static int[] twoSums2(int[] nums, int l, int r, int target){ //O(N) both time and space
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = l; i <= r; i++){
            int difference = target - nums[i];
            if(map.containsKey(difference)){
                result[0] = i;
                result[1] = map.get(difference);
                return result;
            }    
            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }

    public static List<List<Integer>> threeSum(int[] nums){ //O(N^2) and O(1)
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();        
        if(nums == null || nums.length < 3) return result;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++){
            if(i-1 >=0 && nums[i] != nums[i-1]){
                int left = i + 1;
                int right = nums.length -1;
                while(left < right){
                    int sum = nums[i] + nums[left] + nums[right];
                    if(sum == 0){
                        List<Integer> lst = new ArrayList<Integer>();
                        lst.add(nums[i]);
                        lst.add(nums[left]);
                        lst.add(nums[right]);
                        result.add(lst);
                        while(nums[left] == nums[left + 1] && left < right) left++;
                        while(nums[right] == nums[right - 1] && left < right) right--;
                        left++;
                        right--;
                    }else if(sum > 0){
                        left ++;
                    }else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    //Greedy
    public static int containerWithMostWater(int[] nums){ //O(N) and O(1)
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = nums.length - 1;

        while(left < right){
            max = Math.max(max, Math.min(nums[left], nums[right])*(right - left));
            if(nums[left] < nums[right]){
                left++;
            }else{
                right--;
            }
        }

        return max;
    }

    //Binary
    public static int addTwoIntegers(int a, int b){
        while(b != 0){
            int tmp = (a & b) << 1;
            a = a ^ b;
            b = tmp;
        }

        return a;
    }

    public static int numberOfOneBits(int n){
        int count = 0;
        while(n!=0){
            n = n & (n-1);
            count++;
        }
        return count;
    }

    public static int[] countingBits(int n){
        int[] result = new int[n+1];
        for(int i = 0; i <= n; i++){
            int bits = numberOfOneBits(i);
            result[i] = bits;
        }
        return result;
    }

    public static int[] countingBitsOptimal(int n){
        int[] result  = new int[n + 1];
        result[0] = 0;

        for(int i = 1; i < n + 1; i++){
            result[i] = i%2 + result[i/2];
        }
        return result;
    }

    //Gauss's formula O(N) and O(1)
    public static int missingNumber(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }

        int n  = nums.length + 1;
        int actual = (n * (n-1))/2;
        
        return actual - sum; 

    }

    public static int reverseBits(int n) {
        int reverse = 0;
        for(int i = 0; i< 32; i++){
            reverse = reverse << 1;
            reverse |= (n & 1);
            n = n >> 1;
        }
        return reverse;
    }

    //Dynamic Programming 

    public static int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i < n + 1; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; 
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for(int i = 1; i < amount + 1; i++){
            for(int j = 0; j < coins.length; j++){
                if(coins[j] <= i){
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }else{
                    break;
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static int maxWaysCoin(int[] coins,  int amount){
        int m = coins.length;
        int n = amount;

        int[][] dp = new int[n+1][m+1];
 
        for(int i = 0; i < n + 1; i++){
            for(int j = 0; j < m + 1 ; j++){
                if(i == 0){
                    dp[i][j] = 1;
                }else if(j == 0){
                    dp[i][j] = 0;
                }
            }
        }

        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j < m + 1 ; j++){
                if(coins[j-1] <= i){
                    dp[i][j] = dp[i][j-1] + dp[i - coins[j-1]][j];
                }else{
                    dp[i][j] = dp[i][j-1];
                }
             }
        }

        for(int i = 0; i < n + 1; i++){
            for(int j = 0; j < m + 1 ; j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println("");
        }
        return dp[n][m];
    }

    //LIS O(N^2) and O(N)
    public static int LIS(int[] nums){
        if(nums.length == 0) return 0;
        
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 0);

        for(int i = 0; i < n; i++){
            int max = Integer.MIN_VALUE;
            for(int j = i; j >= 0; j--){
                if(nums[j] < nums[i]){
                    max = Math.max(max, 1 + dp[j]);
                }
            }
            dp[i] = Math.max(1, max);
        }

        return dp[n-1];
    }

    //LCS
    public static int LCS(String text1, String text2){
        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n+1][m+1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j],dp[i][j-1]);
                }
            }
        }

        return dp[n][m];
    }

    //O(N^2) and O(N)
    public static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; 
        
        for(int i = 0; i < n ; i++){
            int j = i;
            while(j >= 0){
                String thisWord = s.substring(j, i + 1);
                if(wordDict.contains(thisWord)){
                    dp[i+1] = dp[j]; 
                    System.out.println("returning: "+ thisWord);
                    break;
                }
                j--;
            }
        }
        return dp[n];
    }

    public static int combinationSum4(int[] nums, int target) {
        return 0;
    }
    
    public static void main(String[] args){
        System.out.println("Lets get this started!");
        int[] arr = new int[]{10,9,2,5,3,7,101,18};

        System.out.println(LCS("banana", "hbanana")); 
        String str = "abdullah";
        List<String> wordsDict = new ArrayList<>();
        wordsDict.add("cats");
        wordsDict.add("dog");
        wordsDict.add("and");
        wordsDict.add("sand");

        System.out.println(wordBreak("catsanddog", wordsDict));
    }

}


