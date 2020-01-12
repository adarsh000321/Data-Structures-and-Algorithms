
import java.awt.*;
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        sa_init();
        String s=sc.next();
        for (int i=0;i<s.length();i++)sa_add(s.charAt(i));
        
    }
    /*
    Suffix Automaton:Forms a DAG
    Let we want to insert Sting s in automaton
    o states mean vertices
    o transitions mean edges
    o we store characters in transitions
    o t0 is initial state
    o Every state is reachable from t0
    o terminal states : can be one or more
    o transition from t0 to any terminal state will give any suffix of s
    o transitions form t0 to any state with any path gives some substring of s
    o endpos(String t): gives set of indices where t ends in s. eg: if s="abcbc" then endpos("bc")={2,4}
    o endpos(t1)=endpos(t2) iff they have same set of elements , hence we say endpos(t1) is equivalent to endpos(t2)
    o states in suffix automaton are minimum possible
    o total states in suffix automaton = endpos_equivalence set Or total unique endpos sets + 1 [plus 1 is for initial state
    o if length(u) <= length(w) and endpos(w)=endpos(u) then u occurs in s only in form of suffix of w
    o if length(u)<=length(w) then endpos(w) is a subset of endpos(u)
    o in any endpos_equivalence class if we sort all substrings in it in non-increasing order then shorter strings in that class will be
      suffixes of longer ones in that class. Let x and y be lengths of shorter and longer strings respectively in that class, then that class
      will take up all stings of lengths in interval [x,y].No string will be of same length in [x,y] interval
      eg: consider s="abcabcc" and endpos class set be {2,5} then strings corresponding to this class are(in non-increasing order):-
      {"abc","bc"}.Note "c" is not in this set because endpos("c")={2,5,6} which is not equivalent to {2,5}
    o Suffix link: consider some state v!=t0. state v corresponds to class of stings with SAME endpos value.And if we denote w,
      the longest of these strings, then all the other strings are suffixes of w. Now we want to choose longest string among strings present
      in this state which is suffix of w and also endpos set of this string should not be equivalent to that of w
      i.e., it should be present in any other endpos other than endposes of w. Let THIS string be t. In other words,
      a suffix link, link(v) leads to the STATE that corresponds to the longest suffix of w that is in another endpos-equivalence class.
      eg: consider s="abcabcc" and we choose state v which have endpos value = 2, then strings corresponding to this state are(in non-increasing order):-
      {"abc","bc","c"}. Here {"abc","bc"} belongs to same equivalent endpos set = {2,5} and endpos("c")={2,5,6}. Therefore equivalent endpos set
      for {"abc","bc"} is not equivalent set to equivalent endpos set for {"c"}. Here w="abc" and t="c" as "c" is suffix of "abc" and belongs to different
      equivalent endpos set than "abc".
      Hence link(v) will link state v with state that has longest string as t.
     o endpos(String t) will have values in interval [-1,s.length()-1]
     o suffix link forms a tree with root t0 as recursively calling link(v) we would reach to state t0.
     o endpos(v) is subset of endpos(link(v))
     o Algorithm: we will add the characters of the string one by one, and modify the automaton accordingly in each step.
       -> we will only store the values len, link and a list of transitions in each state. We will not label terminal states
          (but we will later show how to arrange these labels after constructing the suffix automaton).
       1. Initially the automaton consists of a single state t0, which will be the index 0 (the remaining states will receive the indices 1,2,…).
          We assign it len=0 and link=−1 for convenience (−1 will be a fictional, non-existing state).
       2. Let last be the state corresponding to the entire string before adding the character c.
         (Initially we set last=0, and we will change last in the last step of the algorithm accordingly.)
       3. Create a new state cur, and assign it with len(cur)=len(last)+1. The value link(cur) is not known at the time.
       4. Now we to the following procedure: We start at the state last. While there isn't a transition through the letter c,
          we will add a transition to the state cur, and follow the suffix link. If at some point there already exists a transition
          through the letter c, then we will stop and denote this state with p.
       5. If it haven't found such a state p, then we reached the fictitious state −1, then we can just assign link(cur)=0 and leave.
       6. Suppose now that we have found a state p, from which there exists a transition through the letter c.
          We will denote the state, to which the transition leads, with q.
       7. Now we have two cases. Either len(p)+1=len(q), or not(is not when link(v) directly jumps to another state as
          in img(https://drive.google.com/file/d/1IM4bRHVU29MA1ZkAWt_soDBzFQ96tVPO/view)) state 2 directly jumps to initial state t0.
          If len(p)+1=len(q), then we can simply assign link(cur)=q and leave.Otherwise it is a bit more complicated.
          It is necessary to clone the state q: we create a new state clone, copy all the data from q (suffix link and transition)
          except the value len. We will assign len(clone)=len(p)+1.
          After cloning we direct the suffix link from cur to clone, and also from q to clone.
          Finally we need to walk from the state p back using suffix links as long as there is a transition through c to
          the state q, and redirect all those to the state clone.
          In any of the three cases, after completing the procedure, we update the value last with the state cur.
       Note: If we also want to know which states are terminal and which are not, the we can find all terminal states after constructing the
             complete suffix automaton for the entire string s. To do this, we take the state corresponding to the entire
             string (stored in the variable last), and follow its suffix links until we reach the initial state.
             We will mark all visited states as terminal. It is easy to understand that by doing so we will mark exactly the states
             corresponding to all the suffixes of the string s, which are exactly the terminal states.
       Time & Memory: O(nk) memory if we use array, where k is the size of the alphabet and O(n) build time. n is length of String s.
                      O(n)  memory and O(nlogk) time for processing the entire string if we use map(slow but less memory)
                      no. of states<=2n-1 because (1 for initial state + 2 becoz 1st two iter will have one state each + (n-2)*2 becoz
                      n-2 chars might make at_most 2 states i.e., clone and curr state)
                      no. of transitions<=3n-4 for n>=3
                      src: cp-algorithm, https://saisumit.wordpress.com/2016/01/26/suffix-automaton/
    */
    static int LEN;// length of string
    static State[] st;
    static int size,last;// size: total states, last: last state (both might differ)
    static void sa_init(){//initial state(t0)
        st=new State[2*LEN];// at_most 2n-1 states;
        for (int i=0;i<st.length;i++)st[i]=new State();
        size=0;last=0;
        st[0].len=0;
        st[0].link=-1;
        size++;
    }
    static void sa_add(char c){
        int curr=size++;
        st[curr].len=st[last].len+1;
        int p=last;
        while (p!=-1 && !st[p].next.containsKey(c)){
            st[p].next.put(c,curr);// adding transitions to every suffix link states
            p=st[p].link;          // for lable c because they dont have any transition of label c thats why loop isnt ending
        }
        //consider input "baca" and trace to understand
        if (p==-1){// reaches to initial state
            st[curr].link=0;//link to t0
        }else {
            int q=st[p].next.get(c);// state q having direct transition from state p with label c (q is the most 
            // recent state from which suffix links form last(variable) reacheas to p).
            if (st[p].len+1==st[q].len){
                st[curr].link=q;
            }else {
                int clone=size++;
                st[clone].len=st[p].len+1;
                st[clone].next.putAll(st[q].next);//copy map st[q].next to st[clone].next
                st[clone].link=st[q].link;
                while (p!=-1 && st[p].next.getOrDefault(c,-1)==q){//changing all transitions associated to q, to clone
                    st[p].next.put(c,clone);
                    p=st[p].link;
                }
                st[q].link=st[curr].link=clone;
            }
        }
        last=curr;
    }
    static class State{
        int len,link;
        Map<Character,Integer> next;
        State(){
            next=new HashMap<>();
        }
    }
}
