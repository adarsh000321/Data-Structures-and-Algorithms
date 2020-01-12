
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int arr[]={1,2,4,5,32,7,6};
        quickSort(arr,0,arr.length-1);
        for (int i:arr){
            System.out.print(i+" ");
        }
    }
    static void quickSort(int arr[],int start,int end){
        if (start>=end)return;
//        int pIndex=partitionIndex(arr,start,end);
        int pIndex=randomizedPartitionIndex(arr,start,end);
        quickSort(arr,start,pIndex-1);
        quickSort(arr,pIndex+1,end);
    }
    static int partitionIndex(int arr[],int start,int end){
        int pIndex=start;
        for (int i=start;i<end;i++){
            if (arr[i]<arr[end]){
                int tmp=arr[i];
                arr[i]=arr[pIndex];
                arr[pIndex]=tmp;
                pIndex++;
            }
        }
        int tmp=arr[pIndex];
        arr[pIndex]=arr[end];
        arr[end]=tmp;
        return pIndex;
    }
    static int randomizedPartitionIndex(int arr[],int start,int end){// o(nlogn) with high prob.
        int pivotIndex=random(start,end);
        int tmp=arr[pivotIndex];
        arr[pivotIndex]=arr[end];
        arr[end]=tmp;
        return partitionIndex(arr,start,end);
    }
    static int random(int from,int to){// inclusive both
        return (int) (Math.random()*(to-from+1))+from;
    }
}
