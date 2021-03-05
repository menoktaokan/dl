package com.okan.bsf;

public class BSFApp {
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

        System.out.println("Limitte ulaşılan düğüm sayısı");
        System.out.println("1: " + countLimited(myTree, 1));
        System.out.println("2: " + countLimited(myTree, 2));
        System.out.println("3: " + countLimited(myTree, 3));
        System.out.println("4: " + countLimited(myTree, 4));

        System.out.println("\nDüğümün seyivesi: ");
        System.out.println("53: " + level(myTree, 53));
        System.out.println("17: " + level(myTree, 17));
        System.out.println("23: " + level(myTree, 23));
        System.out.println("11: " + level(myTree, 11));
        System.out.println("4: " + level(myTree, 4));
        System.out.println("44: " + level(myTree, 44));

        System.out.println("\nBSF:");
        System.out.println("53: " + BSF(myTree, 53));
        System.out.println("17: " + BSF(myTree, 17));
        System.out.println("23: " + BSF(myTree, 23));
        System.out.println("11: " + BSF(myTree, 11));
        System.out.println("4: " + BSF(myTree, 4));
        System.out.println("44: " + BSF(myTree, 44));

    }

    static Integer countLimited(Tree aTree, Integer limit) {
        if (aTree == null)
            return 0;
        if (limit.equals(0))
            return 0;
        else return (1 + countLimited(aTree.left, limit - 1) + countLimited(aTree.right, limit - 1));

    }

    static Integer level(Tree aTree, Integer aNode) {
        if (aTree == null)
            return 0;
        if (aTree.root.equals(aNode))
            return 1;
        else return (
                level(aTree.left, aNode)
                        + level(aTree.right, aNode)
                        + ((level(aTree.left, aNode) + level(aTree.right, aNode)) > 0 ? 1 : 0)
        );
    }

    static Integer BSF(Tree aTree, Integer aNOde){
        return countLimited(aTree, (level(aTree, aNOde)));
    }


    static class Tree {

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