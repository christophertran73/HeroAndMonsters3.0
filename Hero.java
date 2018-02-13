import java.io.*;
import java.util.*;
public class Hero{
    private int xpos;
    private int ypos;
    private double health;
    private Weapon sword;
    private int killedB;
    //private Armor armor;
    private int hides;
    private int xp;
    private int gold;
    private boolean showPpl;
    /*private int NOH;
    private int NOF;
    private int NOP;
    private int numBo;
    private int numAR;
    private int numB;
    private int numLS;
    private boolean hasBP;*/
    private ArrayList<Bombs> bombs;
    private ArrayList<Potion> potions;
    private ArrayList<Armor> armors;
    private boolean leatherSandals;
    
    public Hero(){
        xpos = 0;
        ypos = 14;
        health = 200;
        sword = new Weapon("dagger");
        killedB = 0;
        hides = 0;
        xp = 0;
        gold = 0;
        bombs = new ArrayList<Bombs>();
        potions = new ArrayList<Potion>();
        armors = new ArrayList<Armor>();
        armors.add(new Armor("trousers"));
    }
    
    public Hero(int x, int y){
        xpos = x;
        ypos = y;
        health = 200;
        sword = new Weapon("dagger");
        killedB = 0;
        hides = 0;
        xp = 0;
        gold = 0;
        bombs = new ArrayList<Bombs>();
        potions = new ArrayList<Potion>();
        armors = new ArrayList<Armor>();
        armors.add(new Armor("trousers"));        
    }
    
    public int getX(){
        return xpos;
    }
    public int getY(){
        return ypos;
    }
    public double getHealth(){
        return health;
    }
    public Weapon getWeapon(){
        return sword;
    }
    public int getKilledB(){
        return killedB;
    }
    /*public Armor getArmor(){
        return armor;
    }*/
    public int getHides(){
        return hides;
    }
    public int getXP(){
        return xp;
    }
    public int getGold(){
        return gold;
    }
    public boolean getShowPpl(){
        return showPpl;
    }
    public ArrayList<Bombs> getBombs(){
        return bombs;
    }
    public ArrayList<Potion> getPotions(){
        return potions;
    }
    public ArrayList<Armor> getArmors(){
        return armors;
    }
    public boolean getLS(){
        return leatherSandals;
    }
    
    public void setX(int x){
        xpos = x;
    }
    public void setY(int y){
        ypos = y;
    }
    public void setWeapon(Weapon s){
        sword = s;
    }
    public void setHealth(double h){
        health = h;
    }
    public void setKilledB(int kB){
        killedB = kB;
    }
    /*public void setArmor(Armor a){
        armor = a;
    }*/
    public void setHides(int h){
        hides = h;
    }
    public void setXP(int experience){
        xp = experience;
    }
    public void setGold(int g){
        gold = g;
    }
    public void setShowPpl(boolean p){
        showPpl = p;
    }
    public void setBombs(ArrayList<Bombs> b){
        bombs = b;
    }
    public void setPotions(ArrayList<Potion> p){
        potions = p;
    }
    public void setArmors(ArrayList<Armor> a){
        armors = a;
    }
    public void setLS(boolean ls){
        leatherSandals = ls;
    }

    
    /*public void setNOPot(String t, int amt){
        if ( t.equals("half") ){
            NOH += amt;
        }else if ( t.equals("full") ){
            NOF += amt;
        }else{
            NOP += amt;
        }
    }
    
    public int getNOPot(String t){
        if ( t.equals("half") ){
            return NOH;
        }else if ( t.equals("full") ){
            return NOF;
        }else{
            return NOP;
        }
    }
    
    public void setBO(int amt){
        numBo += amt;
    }
    
    public int getBO(){
        return numBo;
    }
    
    public void setNOA(String t, int amt){
        if ( t.equals("trousers")||t.equals("satchel")){
            numB += amt;
        }else{
            numLS += amt;
        }
    }
    
    public int getNOA(String t, int amt){
        if ( t.equals("trousers")||t.equals("satchel")){
            return numB;
        }else{
            return numLS;
        }
    }
    
    public void setBP(boolean b){
        hasBP = b;
    }
    
    public boolean getNOA(){
        return hasBP;
    }*/

    
    //when we add a display screen, we should add the # of hides we have on here
}







