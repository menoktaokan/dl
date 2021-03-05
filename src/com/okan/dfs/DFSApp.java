package com.okan.dfs;

public class DFSApp {
    public static void main(String[] args) {
        Tree myTree = new Tree(
                53,
                new Tree(
                        23,
                        new Tree(
                                11,
                                null,
                                null
                        ),
                        new Tree(
                                4,
                                null,
                                null
                        )
                ),
                new Tree(
                        17,
                        null,
                        null
                ));

        System.out.println(myTree);
        System.out.println("Count myTree: " + count(myTree));
        System.out.println("Count left of myTree: " + count(myTree.left));
        System.out.println("Count left of left of myTree: " + count(myTree.left.left));
        System.out.println("Count right of left of myTree: " + count(myTree.left.right));
        System.out.println("Count right of myTree: " + count(myTree.right));

        System.out.println(DFS(myTree, 4));
        System.out.println(DFS(myTree, 53));
        System.out.println(DFS(myTree, 44));
        System.out.println(DFS(myTree, 23));
        System.out.println(DFS(myTree, 17));
        System.out.println(DFS(myTree, 11));
    }

    static Integer count(Tree atree) {
        if (atree == null)
            return 0;
        return (count(atree.left) + count(atree.right) + 1);
    }

    static Integer DFS(Tree atree, Integer anode) {
        if (atree == null)
            return 0;
        else if (DFS(atree.left, anode) > 0)
            return DFS(atree.left, anode);
        else if (DFS(atree.right, anode) > 0)
            return DFS(atree.right, anode);
        else if (anode.equals(atree.root))
            return count(atree);
        return 0;
    }

    public static class Tree {

        Integer root;
        Tree left;
        Tree right;

        public Tree(Integer root, Tree left, Tree right) {
            this.root = root;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Construct{ " +
                    "root = " + root +
                    ", left = " + left +
                    ", right = " + right +
                    '}';
        }
    }

}