package LeetCode.Facebook.TreesAndGraph;

import java.util.*;

public class AccountsMerger {

    // handle the case of duplicates for an account in the input.
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> output = new ArrayList<>();
        if(accounts.size() == 0) return output;

        if(accounts.size() == 1){
            List<String> list = accounts.get(0);
            Collections.sort(list.subList(1, list.size()));
            return accounts;
        }

        boolean[] visited = new boolean[accounts.size()];

        for(int i=0 ; i<accounts.size(); i++){
            if(visited[i]) continue;

            List<String> list= accounts.get(i);

            for(int j=i+1; j< accounts.size() ; j++){
                if(visited[j]) continue;

                List<String> current = accounts.get(j);
                boolean merge = false;

                for(int a=1; a<list.size(); a++){
                    for(int b=1; b<current.size(); b++){
                        if(list.get(a).equals(current.get(b))){
                            merge = true;
                            visited[j] = true;
                            break;
                        }
                    }
                    if(merge) break;
                }

                if(merge){
                    for(int b=1; b<current.size(); b++){
                        if( !list.contains(current.get(b)) ){
                            list.add(current.get(b));
                        }
                    }
                }
            }
            Collections.sort(list.subList(1, list.size()));
            output.add(list);
        }

        return output;
    }

    // Approach 2: DFS in a Graph:
    /*
Note:
The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].
     */
    public List<List<String>> accountsMerge2(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap();
        Map<String, ArrayList<String>> graph = new HashMap();

        for (List<String> account: accounts) {
            String name = "";
            for (String email: account) {
                if (name == "") {
                    name = email;
                    continue;
                }
                graph.computeIfAbsent(email, x-> new ArrayList<String>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), x-> new ArrayList<String>()).add(email);
                emailToName.put(email, name);
            }
        }
        // Interesting way in which graph is created.

        Set<String> seen = new HashSet(); // to mark nodes as visited.
        List<List<String>> ans = new ArrayList();

        for (String email: graph.keySet()) {
            if (!seen.contains(email)) {
                seen.add(email);
                Stack<String> stack = new Stack();
                stack.push(email);
                List<String> component = new ArrayList();

                while (!stack.empty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String neighbor: graph.get(node)) {
                        if (!seen.contains(neighbor)) {
                            seen.add(neighbor);
                            stack.push(neighbor);
                        }
                    }
                }

                Collections.sort(component);
                component.add(0, emailToName.get(email));
                ans.add(component);
            }
        }
        return ans;
    }

}
