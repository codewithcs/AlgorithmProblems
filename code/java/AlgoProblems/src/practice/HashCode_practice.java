package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Teams of 2, 3 or 4
Every team orders pizza.

One pizza per team member.

Each ingredient in a pizza is different.
 */

public class HashCode_practice {

    public static void main(String[] args) {
        int m  = 221;
        int T2 = 10;
        int T3 = 14;
        int T4 = 100;

        int maxPizzaRequired = 2*T2 + 3*T3 + 4*T4;

        // Distribute pizzas such that per team,
        // number of ingredients used in all pizzas is maximized.

    }

    public static void pizza(){
        List<List<String>> pizzas =  new ArrayList<>();

        List<String> pizza = Arrays.asList("onion", "pepper", "olive");
        pizzas.add(pizza);
        pizza = Arrays.asList("mushroom", "tomato", "basil");
        pizzas.add(pizza);
        pizza = Arrays.asList("chicken", "mushroom", "pepper");
        pizzas.add(pizza);
        pizza = Arrays.asList("tomato", "mushroom", "basil");
        pizzas.add(pizza);
        pizza = Arrays.asList("chicken", "basil");
        pizzas.add(pizza);

    }
}
