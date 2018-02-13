public abstract class Enemy{
    private int xpos;
    private int ypos;
    private int health;
    //relative to hero
    private int strength;
    //relative to hero
    private int speed;
    private boolean alive;
    private boolean interacted;
    
    public Enemy(){
        xpos = (int)(Math.random()) * 15;
        ypos = (int)(Math.random()) * 15;
        health = (int)(Math.random() * 100) + 1;
        strength = (int)(Math.random() * 30) + 1;
        speed = (int)Math.round(Math.random() * 3);
        alive = true;
        interacted = false;
    }
    
    public Enemy(int x, int y){
        xpos = x;
        ypos = y;
        health = (int)(Math.random() * 100) + 1;
        strength = (int)(Math.random() * 30) + 1;
        speed = (int)Math.round(Math.random() * 3);
        alive = true;
        interacted = false;
    }
    
    /*public Enemy(int x, int y, Hero h){
        xpos = x;
        ypos = y;
        health = (int)(Math.random() * 100) + 1;
        strength = h.getXP();
        speed = h.getXP();
        alive = true;
        interacted = false;
    }*/
    
    public void setX(int x){
        xpos = x;
    }
    public void setY(int y){
        ypos = y;
    }
    public void setStrength(int s){
        strength = s;
    }
    public void setHealth(int h){
        health = h;
    }
    public void setSpeed(int s){
        speed = s;
    }
    public void setAlive(boolean a){
        alive = a;
    }
    public void setInteracted(boolean i){
        interacted = i;
    }
    
    public int getX(){
        return xpos;
    }
    public int getY(){
        return ypos;
    }
    public int getStrength(){
        return strength;
    }
    public int getHealth(){
        return health;
    }
    public int getSpeed(){
        return speed;
    }
    public boolean isAlive(){
        return alive;
    }
    public boolean isInteracted(){
        return interacted;
    }
}

