import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CheckoutTest {
     private Item itemA;
     private Item itemB;
     private Item itemC;
     private Item itemD;
     private Offer offerA;
     private Offer offerB;
     private Basket basket;

     @Before
     public void setUp(){
          itemA = new Item();
          itemA.setName("A");
          itemA.setPrice(50.0);
  
          offerA = new Offer();
          offerA.setItem(itemA);
          offerA.setQuantity(3);
          offerA.setSpecialPrice(130);
  
          itemB = new Item();
          itemB.setName("B");
          itemB.setPrice(30.0);
  
          offerB = new Offer();
          offerB.setItem(itemB);
          offerB.setQuantity(2);
          offerB.setSpecialPrice(45);
  
          itemC = new Item();
          itemC.setName("C");
          itemC.setPrice(20.0);
  
          itemD = new Item();
          itemD.setName("D");
          itemD.setPrice(15.0);
  
          basket = new Basket();
     }
     @Test
     public void testCalculateTotalPrice(){
          //3 itemA before apply offer
          basket.basketItems.put(itemA, 3);
          assertEquals(150,basket.calculateTotalPrice(basket.basketItems),0);

          basket.addOffer(offerA);
          basket.addOffer(offerB);
          //3 itemA after apply offer
          assertEquals(130,basket.calculateTotalPrice(basket.basketItems),0);

          //adding itemB after apply offer
          basket.basketItems.put(itemB, 2);
          assertEquals(175,basket.calculateTotalPrice(basket.basketItems),0);

          //adding remaining items
          basket.basketItems.put(itemC, 2);
          basket.basketItems.put(itemD, 1);
          assertEquals(230,basket.calculateTotalPrice(basket.basketItems),0);

     }
     @Test
     public void testCalculateTotalPriceRandom(){
          //itemA with offerA, itemB without offerB
          basket.basketItems.put(itemA, 29);
          basket.addOffer(offerA);
          assertEquals(1270,basket.calculateTotalPrice(basket.basketItems),0); 
          basket.basketItems.put(itemB, 2);
          assertEquals(1330,basket.calculateTotalPrice(basket.basketItems),0); 
     }
}
