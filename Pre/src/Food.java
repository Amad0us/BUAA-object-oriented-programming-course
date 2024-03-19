public class Food implements Commodity {
    private int foodId;
    private String foodName;
    private int foodEnergy;
    private long price;
    
    @Override
    public long getPrice() {
        return price;
    }
    
    public int getFood_Id() {
        return foodId;
    }
    
    public String getFood_name() {
        return foodName;
    }
    
    public int getFood_Energy() {
        return foodEnergy;
    }
    
    public Food(int foodId, String foodName, int foodCapacity, long price) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodEnergy = foodCapacity;
        this.price = price;
    }
}
