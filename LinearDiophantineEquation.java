import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Abc {
    static int x, y, g;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int a, b, c, x1, x2, y1, y2;
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        x1 = sc.nextInt();
        x2 = sc.nextInt();
        y1 = sc.nextInt();
        y2 = sc.nextInt();


        if (a == 0 && b == 0) {
            if (c == 0) {
                long total = (long) (x2 - x1 + 1) * (long) (y2 - y1 + 1);
                System.out.println(total);//all combinations of x,y within the limits...
            } else {
                System.out.println(0);
            }
        } else if (a == 0) {
            if (c % b == 0 && -c / b >= y1 && -c / b <= y2) { // this also includes case when c=0 i.e eg: 0%2==0
                System.out.println((x2 - x1) + 1);
            } else {
                System.out.println(0);
            }
        } else if (b == 0) {
            if (c % a == 0 && -c / a >= x1 && -c / a <= x2) { // this also includes case when c=0 i.e eg: 0%2==0
                System.out.println((y2 - y1) + 1);
            } else {
                System.out.println(0);
            }
        } else {
            System.out.println(findTotalDiophantineSol(a, b, -c, x1, x2, y1, y2));
        }

    }

    static int gcd(int a, int b) {
        if (a == 0) {
            x = 0;
            y = 1;
            return b;
        }
        g = gcd(b % a, a);
        int x1 = y - (b / a) * x;
        int y1 = x;
        x = x1;
        y = y1;
        return g;
    }

    static boolean findAnyDiophantineSol(int a, int b, int c) {
        g = gcd(a, b);
//        System.out.println(g+" "+x+" "+y);
        if (c % g != 0) {
            return false;
        }
        x = (c / g) * x;
        y = (c / g) * y;
        if (a < 0) x *= -1;
        if (b < 0) y *= -1;
        return true;
    }

    static int[] shift_solution(int x, int y, int a, int b, int cnt) {
        x += cnt * b;
        y -= cnt * a;
        return new int[]{x, y};
    }

    static int findTotalDiophantineSol(int a, int b, int c, int minx, int maxx, int miny, int maxy) {
        if (!findAnyDiophantineSol(a, b, c))
            return 0;
        a /= g;
        b /= g;

        int sign_a = a > 0 ? +1 : -1;
        int sign_b = b > 0 ? +1 : -1;

        int[] arr = shift_solution(x, y, a, b, (minx - x) / b);
        x = arr[0];
        y = arr[1];
        if (x < minx) {
            arr = shift_solution(x, y, a, b, sign_b);
            x = arr[0];
            y = arr[1];
        }
        if (x > maxx)
            return 0;
        int lx1 = x;

        arr = shift_solution(x, y, a, b, (maxx - x) / b);
        x = arr[0];
        y = arr[1];
        if (x > maxx) {
            arr = shift_solution(x, y, a, b, -sign_b);
            x = arr[0];
            y = arr[1];
        }
        int rx1 = x;

        arr = shift_solution(x, y, a, b, -(miny - y) / a);
        x = arr[0];
        y = arr[1];
        if (y < miny) {
            arr = shift_solution(x, y, a, b, -sign_a);
            x = arr[0];
            y = arr[1];
        }
        if (y > maxy)
            return 0;
        int lx2 = x;

        arr = shift_solution(x, y, a, b, -(maxy - y) / a);
        x = arr[0];
        y = arr[1];
        if (y > maxy) {
            arr = shift_solution(x, y, a, b, sign_a);
            x = arr[0];
            y = arr[1];
        }
        int rx2 = x;

        if (lx2 > rx2) {//swap
            int temp = lx2;
            lx2 = rx2;
            rx2 = temp;
        }

        int lx = max(lx1, lx2);
        int rx = min(rx1, rx2);

        if (lx > rx)
            return 0;
        return (rx - lx) / abs(b) + 1;
    }
}
