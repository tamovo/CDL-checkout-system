import java.util.*;

public class Checkout {
    public static void main(String args[]) {
        Set<Item> items = new HashSet<Item>();

        Item itemA = new Item();
        itemA.setName("A");
        itemA.setPrice(50.0);
        items.add(itemA);

        Offer offerA = new Offer();
        offerA.setItem(itemA);
        offerA.setQuantity(3);
        offerA.setSpecialPrice(130);

        Item itemB = new Item();
        itemB.setName("B");
        itemB.setPrice(30.0);
        items.add(itemB);

        Offer offerB = new Offer();
        offerB.setItem(itemB);
        offerB.setQuantity(2);
        offerB.setSpecialPrice(45);

        Item itemC = new Item();
        itemC.setName("C");
        itemC.setPrice(20.0);
        items.add(itemC);

        Item itemD = new Item();
        itemD.setName("D");
        itemD.setPrice(15.0);
        items.add(itemD);

        Basket basket = new Basket();
        basket.addOffer(offerA);
        basket.addOffer(offerB);

        String inputItem = "";

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("Enter item name");
            inputItem = sc.nextLine();
            // input 0 to exit
            if (inputItem.equals("0")) {
                break;
            }

            inputItem = inputItem.toUpperCase();
            for (Item item : items) {
                if (inputItem.equals(item.getName())) {
                    basket.addItem(item);
                    basket.setTotalPrice(basket.calculateTotalPrice(basket.basketItems));
                }
            }

            System.out.println(basket.toString());

        }

    }
}