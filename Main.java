import java.util.*;

public class Main{
    
    //Arrays
    public static int[] twoSums(int[] nums, int target){
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


    public static void main(String[] args){
        System.out.println("Lets get this started!");
        int[] arr = new int[]{1,3,4,5,2,7};

        System.out.println(twoSums(arr, 9)[1]); 
    }

}