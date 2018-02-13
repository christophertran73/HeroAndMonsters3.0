public class Weapon extends Item{
    private int attack;
    
    public Weapon(){
        super();
        attack = 0;
    }
    
    public Weapon(String t){
        super(t, 0);
        setAANDP(t);
    }
    
    public void setAANDP(String t){
        if ( t.equals("short sword") ){
            attack = (int)(Math.random()*11)+5;
            setPrice(1);
        }else if ( t.equals("long sword") ){
            attack = (int)(Math.random()*11)+10;
            setPrice(2);
        }else if ( t.equals("axe") ){
            attack = (int)(Math.random()*11)+45;
            setPrice(3);
        }else{
            attack = (int)((Math.random()*11)+3);
            setPrice(0);
        }
    }
    
    public double getAtt(){
        return attack;
    }
}






