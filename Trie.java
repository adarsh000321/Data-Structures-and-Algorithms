static class TrieNode{
        TrieNode[] next;
        boolean end;
        TrieNode(){
            next=new TrieNode[26];
            end=false;
        }
    }
    static TrieNode root;
    static boolean search(String s){
        TrieNode tmp=root;
        for (int i=0;i<s.length();i++){
            if (tmp.next[s.charAt(i)-'a']==null)return false;
            tmp=tmp.next[s.charAt(i)-'a'];
        }
        return tmp.end;
    }
    static void insert(String s){
        TrieNode tmp=root;
        for (int i=0;i<s.length();i++){
            if (tmp.next[s.charAt(i)-'a']==null){
                tmp.next[s.charAt(i)-'a']=new TrieNode();
            }
            tmp=tmp.next[s.charAt(i)-'a'];
        }
        tmp.end=true;
    }
