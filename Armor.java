public class Armor extends Item{
    private int numItems;
    private double defense;
    
    public Armor(){
        super();
        numItems = 0;
        defense = 0;
    }
    
    public Armor(String t){
        super(t, 0);
        setSet(t);
    }
    
    public void setSet(String t){
        if ( t.equals("trousers") ){
            numItems = 2;
            setPrice(0);
        }else if ( t.equals("satchel") ){
            numItems = 6;
            setPrice(3);
        }else if ( t.equals("breastplate") ){
            defense = 0.20;
            setPrice(2);
        }else{
            setPrice(3);
        }
    }
    
    public double getDef(){
        return defense;
    }
}



