import java.util.*;

public class Main{
    
    public static int[] sortColors(int[] colors){
        int start = 0;
        int end = colors.length -1;
        int index = 0;

        while(index <= end && start < end){
            if(colors[index] == 0){
                colors[index] = colors[start];
                colors[start] = 0;
                index++;
                start++;
            }else if(colors[index] == 2){
                colors[index] = colors[end];
                colors[end] = 2;
                end--;
            }else{
                index ++;
            }
        }

        return colors;
    }

    public static void main(String[] args){
        System.out.println("Lets get this started!");
    }

}