import java.util.Scanner;
public class Dictionary
{
	public static void main(String[] args) {
		BST tree = new BST();
		tree.insert("Job","It is a kind of word done professionally.");
		tree.insert("Dog","It is an animal which has four legs and it is friendly to the humans.");
		tree.insert("Parrot","It is a kind of bird which is in green colour and it has red beak.");
		tree.inOrder();
		Scanner sc = new Scanner(System.in);
		while (true){
		    System.out.println();
		    System.out.print("Enter any word to find their meaning:");
		    String s1 = sc.nextLine();
		    tree.search(s1);
		}
	}
}
class Node{
    String word;
    String meaning;
    Node left;
    Node right;
    Node(String w, String m){
        word = w;
        meaning = m;
        left = null;
        right = null;
    }
}
class BST{
    Node root;
    BST(){
        root = null;
    }
    void insert(String word, String meaning){
        root = insertRec(root, word, meaning);
    }
    Node insertRec(Node root, String w, String m){
        if(root == null){
            return new Node(w,m);
        }
        if(w.compareTo(root.word) < 0){
            root.left = insertRec(root.left, w, m);
        } else if(w.compareTo(root.word) > 0){
            root.right = insertRec(root.right, w, m);
        }
        return root;
    }
    void delete(String word){
        root = deleteRec(root, word);
    }
    Node deleteRec(Node root, String w){
        if(root == null){
            return root;
        }
        if(w.compareTo(root.word) < 0){
            deleteRec(root.left, w);
        } else if(w.compareTo(root.word) > 0){
            deleteRec(root.right, w);
        } else{
            if(root.left == null){
                return root.right;
            } else if(root.right == null){
                return root.left;
            } else{
                String[] a = minValue(root.right);
                root.word = a[0];
                root.meaning = a[1];
                root.right = deleteRec(root.right, root.word);
            }
        }
        return root;
    }
    String[] minValue(Node root){
        String[] a = new String[2];
        String minW = root.word;
        String minM = root.meaning;
        while(root.left != null){
            minW = root.left.word;
            minM = root.left.meaning;
            root = root.left;
        }
        a[0] = minW;
        a[1] = minM;
        return a;
    }
    void search(String word){
        searchRec(root, word);
    }
    void searchRec(Node root, String w){
        if(root == null){
            System.out.println("Not Found");
            return;
        }
        if(w.compareTo(root.word) == 0){
            System.out.println(root.meaning);
            return;
        } else if(w.compareTo(root.word) > 0){
            searchRec(root.right, w);
        } else {
            searchRec(root.left, w);
        }
    }
    void inOrder(){
        inOrderRec(root);
    }
    void inOrderRec(Node root){
        if(root != null){
            inOrderRec(root.left);
            System.out.println(root.word+": "+root.meaning);
            inOrderRec(root.right);
        }
    }
}
