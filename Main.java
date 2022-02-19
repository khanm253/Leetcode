import java.util.*;

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

    public static int maxSubarrayQuadratic(int[] nums){ //O(N) and O(1)
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
    public static int maxSubArray(int[] nums){
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


    public static void main(String[] args){
        System.out.println("Lets get this started!");
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println(maxSubArray(arr)); 
    }

}