package AlgoExpert_Easy;

public class ClosestValueInBST {
    public static int findClosestValueInBST(BST tree, int target){
        return traverseBST(tree, target, tree.value);
    }

    public static int traverseBST(BST tree, int target, int closest){
        BST currentNode = tree ;

        while(currentNode != null){
            if(Math.abs(target-closest) > Math.abs(target-currentNode.value)){
                closest = currentNode.value;
            }

            if(target > currentNode.value){
                currentNode = currentNode.right;
            } else if(target < currentNode.value){
                currentNode = currentNode.left;
            } else{
                break;
            }

        }
        return closest;
    }

    public static int traverseBST2(BST tree, int target, int closest){

        if(Math.abs(tree.value-target) < Math.abs(target-closest)){
            closest = tree.value;
        }

        if(target > tree.value && tree.right != null){
            return traverseBST2(tree.right, target, closest);
        }else if(target< tree.value && tree.left != null){
            return traverseBST2(tree.left, target, closest);
        } else {
            return closest;
        }

    }


        static class BST{
        public int value;
        public BST left;
        public BST right;
        public BST(int value){
            this.value = value;
        }
    }
}
