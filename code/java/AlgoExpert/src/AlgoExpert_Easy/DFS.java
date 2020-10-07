package AlgoExpert_Easy;

import java.util.ArrayList;
import java.util.List;

public class DFS {

    static class Node {
        String name;
        List<Node> children = new ArrayList<Node>();

        public Node(String name) {
            this.name = name;
        }

        public List<String> depthFirstSearch(List<String> array) {
            List<Node> stack = new ArrayList<>();
            stack.add(this);
            while(stack.size() > 0){
                Node node = stack.remove(stack.size()-1);
                array.add(node.name);
                for(int i= node.children.size()-1 ; i>=0 ; i--){
                    stack.add(node.children.get(i));
                }
            }

            return array;
        }

        public List<String> depthFirstSearch2(List<String> array) {
            array.add(this.name);
            for(Node child: children){
                child.depthFirstSearch2(array);
            }
            return array;
        }

        public Node addChild(String name) {
            Node child = new Node(name);
            children.add(child);
            return this;
        }
    }
}
