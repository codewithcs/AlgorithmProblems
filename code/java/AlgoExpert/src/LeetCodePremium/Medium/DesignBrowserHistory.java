package LeetCodePremium.Medium;

import java.util.ArrayList;
import java.util.List;

/*
You have a browser of one tab where you start on the "homepage" and you can visit another url,
get back in the history number of steps or move forward in the history number of steps.

Implement the BrowserHistory class:

1. BrowserHistory(string homepage)
Initializes the object with the homepage of the browser.

2. void visit(string url)
Visits url from the current page. It clears up all the forward history.

3. string back(int steps)
Move steps back in history.
If you can only return x steps in the history and steps > x, you will return only x steps.
Return the current url after moving back in history at most steps.

4. string forward(int steps)
Move steps forward in history.
If you can only forward x steps in the history and steps > x, you will forward only x steps.
Return the current url after forwarding in history at most steps.

Constraints:
1 <= homepage.length <= 20
1 <= url.length <= 20
1 <= steps <= 100
homepage and url consist of  '.' or lower case English letters.
At most 5000 calls will be made to visit, back, and forward.
 */
public class DesignBrowserHistory {
}

class BrowserHistory {

    List<String> list = new ArrayList<>();
    String current = "";
    public BrowserHistory(String homepage) {
        list = new ArrayList<>();
        list.add(homepage);
    }

    public void visit(String url) {
        list.add(url);

    }

    public String back(int steps) {
        return "";
    }

    public String forward(int steps) {
        return "";
    }
}
