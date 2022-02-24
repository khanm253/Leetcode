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

    public static void main(String[] args){
        System.out.println("Lets get this started!");
        int[] arr = new int[]{1,8,6,2,5,4,8,3,7};

        System.out.println(addTwoIntegers(20, 30)); 
    }

}


