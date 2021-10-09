import java.util.*;
public class Basket {

    Map<Item, Integer> basketItems = new LinkedHashMap<Item, Integer>();
    List<Offer> appliedOffers = new ArrayList<Offer>();

    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    
    public void addItem(Item item){
        if(basketItems.containsKey(item)){
           basketItems.put(item, basketItems.get(item)+1);
        }else{
            basketItems.put(item, 1);
        }
    }

    public void addOffer(Offer offer){
        appliedOffers.add(offer);
    }

    public double calculateTotalPrice(Map<Item, Integer>basketItems){
        double totalPrice=0.0;
        if(!basketItems.isEmpty()){
            //normal calculation
            for (Item item: basketItems.keySet()) {
                int basketItemQuantity = basketItems.get(item);
                totalPrice += item.getPrice()*basketItemQuantity;
            }
            if(!appliedOffers.isEmpty()){
                for(Offer offer: appliedOffers){
                    if(basketItems.containsKey(offer.getItem())){ //check if the item has offer
                        //applied offer calculation
                        int basketItemQuantity =  basketItems.get(offer.getItem());
                        int numberOfOfferApply= basketItemQuantity/offer.getQuantity(); //e.g itemA offer: 12/3=apply 4 times offer
                        if(numberOfOfferApply>0){
                            double originalPrice = offer.getItem().getPrice()*offer.getQuantity(); // itemA price 50*3 item = 150
                            double discountedPriceReduced = originalPrice - offer.getSpecialPrice();//price difference: 150-130 = 20
                            totalPrice-=discountedPriceReduced*numberOfOfferApply; //total amount = total amount - (20*4 times offer)
                        }
                    }
                }
            }
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        String basketInfo = "Basket : \n";
        for (Item item: basketItems.keySet()) {
            String key = item.toString();
            String value = basketItems.get(item).toString();
            basketInfo += key + ", Quantity:" + value + "\n";
        }
        basketInfo += "Total price : "+ totalPrice + "(pence)";

        return basketInfo;
    }
}
