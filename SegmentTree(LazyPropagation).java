class SegmentTree{
    private int[] lo,hi,min,delta;

    public SegmentTree(int n){
        lo=new int[4*n+1];
        hi=new int[4*n+1];
        delta=new int[4*n+1];
        min=new int[4*n+1];
        init(1,0,n-1);
    }
    public void increment(int a,int b,int val){
        increment(1,a,b,val);
    }

    public int minimum(int a,int b){
        return minimum(1,a,b);
    }
    private void init(int i,int a,int b){
        lo[i]=a;
        hi[i]=b;
        if (a==b)return;
        int m=(a+b)/2;
        init(2*i,a,m);
        init(2*i+1,m+1,b);
    }
    private void propagate(int i){
        delta[2*i]+=delta[i];
        delta[2*i+1]+=delta[i];
        delta[i]=0;
    }
    private void update(int i){                                      //                   i
        min[i]=Math.min(min[2*i]+delta[2*i],min[2*i+1]+delta[2*i+1]);//                  / \
    }                                                                //                 /   \
                                                                     //               2*i   2*i+1
    private void increment(int i,int a,int b,int val){
        if (b<lo[i] || hi[i]<a)return;// not covered at all
        if (a<=lo[i] && hi[i]<=b){// fully covered interval
            delta[i]+=val;
            return;
        }
        // else partially covered
        propagate(i);
        increment(2*i,a,b,val);
        increment(2*i+1,a,b,val);
        update(i);
    }
    private int minimum(int i,int a,int b){
        if (b<lo[i] || hi[i]<a)return Integer.MAX_VALUE;
        if (a<=lo[i] && hi[i]<=b){
            return min[i]+delta[i];
        }
        propagate(i);
        int minLeft=minimum(2*i,a,b);
        int minRight=minimum(2*i+1,a,b);
        update(i);
        return Math.min(minLeft,minRight);
    }
}
