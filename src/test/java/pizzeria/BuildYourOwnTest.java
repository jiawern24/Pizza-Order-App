package pizzeria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildYourOwnTest {

    /**
     * Test case #1 for price(): Test less than 3 toppings, small size, tomato sauce
     */
    @Test
    public void testLessThan3Toppings() {
        Pizza pizza = PizzaMaker.createPizza("byo");
        pizza.setSize(Size.SMALL);
        pizza.setSauce(Sauce.TOMATO);

        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.PEPPERONI);

        pizza.setExtraSauce(false);
        pizza.setExtraCheese(false);

        double actual = pizza.price();

        assertEquals(8.99, actual);
    }

    /**
     * Test case #2 for price(): Test 3 toppings, medium size, tomato sauce
     */
    @Test
    public void test3Toppings() {
        Pizza pizza = PizzaMaker.createPizza("byo");
        pizza.setSize(Size.MEDIUM);
        pizza.setSauce(Sauce.TOMATO);

        pizza.addTopping(Topping.BEEF);
        pizza.addTopping(Topping.HAM);
        pizza.addTopping(Topping.SHRIMP);

        pizza.setExtraSauce(false);
        pizza.setExtraCheese(false);

        double actual = pizza.price();

        assertEquals(10.99 , actual);

    }

    /**
     * Test case #3 for price(): Test 5 toppings (between 3 and 7 toppings),
     * large size, tomato sauce, extra sauce, extra cheese
     */
    @Test
    public void test5Toppings() {
        Pizza pizza = PizzaMaker.createPizza("byo");
        pizza.setSize(Size.LARGE);
        pizza.setSauce(Sauce.TOMATO);

        pizza.addTopping(Topping.SQUID);
        pizza.addTopping(Topping.CRABMEATS);
        pizza.addTopping(Topping.GREENPEPPER);
        pizza.addTopping(Topping.ONION);
        pizza.addTopping(Topping.BLACKOLIVE);

        pizza.setExtraSauce(true);
        pizza.setExtraCheese(true);

        double actual = pizza.price();

        assertEquals(17.97, actual);
    }

    /**
     * Test case #4 for price(): Test 6 different toppings from before (between 3 and 7 toppings),
     * small size, alfredo sauce, extra sauce
     */
    @Test
    public void test6Toppings() {
        Pizza pizza = PizzaMaker.createPizza("byo");
        pizza.setSize(Size.SMALL);
        pizza.setSauce(Sauce.ALFREDO);

        pizza.addTopping(Topping.MUSHROOM);
        pizza.addTopping(Topping.BACON);
        pizza.addTopping(Topping.CHICKEN);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.BEEF);

        pizza.setExtraSauce(true);
        pizza.setExtraCheese(false);

        double actual = pizza.price();

        assertEquals(14.46, actual);
    }

    /**
     * Test case #5 for price(): Test 7 toppings, medium size, alfredo sauce, extra cheese
     */
    @Test
    public void test7Toppings() {
        Pizza pizza = PizzaMaker.createPizza("byo");
        pizza.setSize(Size.MEDIUM);
        pizza.setSauce(Sauce.ALFREDO);

        pizza.addTopping(Topping.GREENPEPPER);
        pizza.addTopping(Topping.SHRIMP);
        pizza.addTopping(Topping.CRABMEATS);
        pizza.addTopping(Topping.ONION);
        pizza.addTopping(Topping.SQUID);
        pizza.addTopping(Topping.BLACKOLIVE);
        pizza.addTopping(Topping.HAM);

        pizza.setExtraSauce(false);
        pizza.setExtraCheese(true);

        double actual = pizza.price();

        assertEquals(17.95, actual);
    }

    /**
     * Test case #6 for price(): Test more than 7 toppings, large size, alfredo sauce
     */
    @Test
    public void testMoreThan7Toppings() {
        Pizza pizza = PizzaMaker.createPizza("byo");
        pizza.setSize(Size.LARGE);
        pizza.setSauce(Sauce.ALFREDO);

        pizza.addTopping(Topping.ONION);
        pizza.addTopping(Topping.BACON);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.MUSHROOM);
        pizza.addTopping(Topping.SQUID);
        pizza.addTopping(Topping.BEEF);
        pizza.addTopping(Topping.CHICKEN);
        pizza.addTopping(Topping.GREENPEPPER);

        pizza.setExtraSauce(false);
        pizza.setExtraCheese(false);

        double actual = pizza.price();

        assertEquals(20.44, actual);
    }
}