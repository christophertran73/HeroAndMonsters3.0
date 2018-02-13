public abstract class Item{
    private String type;
    private int price;
    
    public Item(){
        type = null;
        price = 0;
    }
    
    public Item(String t, int p){
        type = t;
        price = p;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String t){
        type = t;
    }
    
    public int getPrice(){
        return price;
    }
    
    public void setPrice(int p){
        price = p;
    }
}

