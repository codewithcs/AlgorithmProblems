package practice.AmazonOA2;

import java.util.ArrayList;

class ComponentNode{
    public int value;
    public ArrayList<ComponentNode> components;
    public ComponentNode(){
        components = new ArrayList<ComponentNode>();
    }
    public ComponentNode(int numOfLinesChanged){
        this.value = numOfLinesChanged;
        this.components = new ArrayList<ComponentNode>();
    }

    int countNodes(ComponentNode x){
        if(x.components.size() == 0){
            return 1;
        }
        int sum =0;
        for(ComponentNode y : x.components){
            sum += countNodes(y);
        }
        return 1 + sum;
    }

    int countValues(ComponentNode x){
        if(x.components.size() == 0){
            return x.value;
        }
        int value = x.value;
        for(ComponentNode y : x.components){
            value += countValues(y);
        }
        return value;
    }

    public ComponentNode getFastestComponent(ComponentNode rootComponent){
        return result;
    }

    double average = -1;
    ComponentNode result = null;
    public void traverse(ComponentNode root){
        if(root == null || root.components.size() == 0 ){
            return;
        }

        int count = countNodes(root);
        int value = countValues(root);

        if( value/count > average){
            result = root;
        }

        for(ComponentNode x: root.components){
            traverse(x);
        }
    }
}

public class q3 {
}
