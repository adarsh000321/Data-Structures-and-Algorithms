
import java.util.Arrays;
import java.util.Scanner;

public class Abc {
    static int inf = Integer.MAX_VALUE;
// longest increasing subsequence in O(nlgn) 
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int dp[] = new int[n + 1];
        Arrays.fill(dp, inf);
        dp[0] = -inf;
        for (int i = 0; i < n; i++) {
            int j = pos(dp, arr[i]);
            dp[j] = arr[i];

        }
        int i = n;
        while (dp[i] == inf) {
            i--;
        }
        System.out.println(i);
    }

    static int pos(int arr[], int ele) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] <= ele) {
                l = mid + 1;
            } else r = mid - 1;
        }
        return l;
    }
}
