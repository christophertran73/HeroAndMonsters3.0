public class Potion extends Item{
    private int heal;
    private boolean used;
    
    public Potion(){
        super();
        heal = 0;
        used = false;
    }
    
    public Potion(String t){
        super(t, 0);
        setHeal(t);
        setP(t);
        used = false;
    }
    
    public boolean getUsed(){
        return used;
    }
    
    public void setUsed(boolean u){
        used = u;
    }
    
    public int getHeal(){
        return heal;
    }
    
    public void setHeal(String t){
        if ( t.equals("full") || t.equals("phoenix") ){
            heal = 200;
        }else{
            heal = 100;
        }
    }
    
    public void setP(String t){
        if ( t.equals("full") ){
            setPrice(2);
        }else if ( t.equals("phoenix") ){
            setPrice(3);
        }else{
            setPrice(1);
        }
    }
    
    public void healHero(Hero h){
        //h.setHealth(heal);
        System.out.println("Hero's health has been completely refilled.");
        used = true;
    }
}





