import java.io.*;
import java.util.*;

public class Farmer{
    private int xpos;
    private int ypos;
    private int type;
    private boolean interacted;
    
    public Farmer(){
        xpos = (int)(Math.random() * 15);
        ypos = (int)(Math.random() *15);
        type = 2;
        interacted = false;
    }
    
    public Farmer(int x, int y){
        xpos = x;
        ypos = y;
        type = 2;
        interacted = false;
    }
    
    public int getX(){
        return xpos;
    }
    public int getY(){
        return ypos;
    }
    public int getType(){
        return type;
    }
    public boolean getInteracted(){
        return interacted;
    }
    
    public void setX(int x){
        xpos = x;
    }
    public void setY(int y){
        ypos = y;
    }
    public void setType(int t){
        type = t;
    }
    public void setInteracted(boolean i){
        interacted = i;
    }
    
    public void interact(Hero h, ArrayList<Boss> bosses){
        System.out.println("You have run into a farmer!");
        if(type==1){
            System.out.println("Farmer:  Oh—hello. Here is a map with the location of all the townspeople.");
            System.out.println("*Map Generated*");
            h.setShowPpl(true);
        }else if(type >= 2 && type <=5){
            if(bosses.get(1).isAlive() == true){
                int xdiff = h.getX()-bosses.get(1).getX();
                if(xdiff>0){
                    System.out.println("Farmer: Your next boss is " + xdiff + " units to the left.");
                }else if(xdiff<0){
                    System.out.println("Farmer: Your next boss is " + Math.abs(xdiff) + " units to the right.");
                }
                int ydiff = h.getY()-bosses.get(1).getY();
                if(ydiff>0){
                    System.out.println("and " + ydiff + " units up.");
                }else if(ydiff<0){
                    System.out.println("and " + Math.abs(ydiff) + " units down.");
                }
            }else if(bosses.get(1).isAlive() == false && bosses.get(2).isAlive() == true){
                int xdiff = h.getX()-bosses.get(2).getX();
                if(xdiff>0){
                    System.out.println("Farmer: Your next boss is " + xdiff + " units to the left.");
                }else if(xdiff<0){
                    System.out.println("Farmer: Your next boss is " + Math.abs(xdiff) + " units to the right.");
                }
                int ydiff = h.getY()-bosses.get(2).getY();
                if(ydiff>0){
                    System.out.println("and " + ydiff + " units up.");
                }else if(ydiff<0){
                    System.out.println("and " + Math.abs(ydiff) + " units down.");
                }
            }else if(bosses.get(1).isAlive() == false && bosses.get(2).isAlive() == false && bosses.get(3).isAlive() == true){
                int xdiff = h.getX()-bosses.get(3).getX();
                if(xdiff>0){
                    System.out.println("Farmer: Your next boss is " + xdiff + " units to the left.");
                }else if(xdiff<0){
                    System.out.println("Farmer: Your next boss is " + Math.abs(xdiff) + " units to the right.");
                }
                int ydiff = h.getY()-bosses.get(3).getY();
                if(ydiff>0){
                    System.out.println("and " + ydiff + " units up.");
                }else if(ydiff<0){
                    System.out.println("and " + Math.abs(ydiff) + " units down.");
                }
            }else if(bosses.get(1).isAlive() == false && bosses.get(2).isAlive() == false && bosses.get(3).isAlive() == false && bosses.get(4).isAlive() == true){
                int xdiff = h.getX()-bosses.get(4).getX();
                if(xdiff>0){
                    System.out.println("Farmer: Your next boss is " + xdiff + " units to the left.");
                }else if(xdiff<0){
                    System.out.println("Farmer: Your next boss is " + Math.abs(xdiff) + " units to the right.");
                }
                int ydiff = h.getY()-bosses.get(4).getY();
                if(ydiff>0){
                    System.out.println("and " + ydiff + " units up.");
                }else if(ydiff<0){
                    System.out.println("and " + Math.abs(ydiff) + " units down.");
                }
            }
        }else if(type==6){
            Scanner kbReader = new Scanner(System.in);
            int numItems = 0;
            int maxItems = 2;
            System.out.println("Farmer: Would you like trade your hides for items?");
            System.out.println("I have potions, bombs, breastplate, a satchel, and leather sandals.(Y/N)");
            String choice = kbReader.next();
            if(numItems <= maxItems){
                if ( choice.equals("Y") ){
                    System.out.println("What would you like?: (P:Potions, BO:Bombs, BR:Breastplate, S:Satchel, L:Leather Sandals)");
                    String item = kbReader.next();
                    if(item.equals("P")){
                        System.out.println("Which potion would you like?: (H: 1/2 Potion, F: Full Potion, P: Revival)");
                        String wPot = kbReader.next();
                        if(wPot.equals("H")){
                            if(h.getHides()>=1){
                                System.out.println("*Purchase Successful*");
                                h.setHides(h.getHides()-1);
                                //h.setNOPot("half", 1);
                                h.getPotions().add(new Potion("half"));
                                numItems++;
                            }else{
                                System.out.println("*Purchase Unsuccessful*");
                                System.out.println("Farmer: Come back when you have one hide.");
                            }
                        }else if(wPot.equals("F")){
                            if(h.getHides()>=2){
                                System.out.println("*Purchase Successful*");
                                h.setHides(h.getHides()-2);
                                //h.setNOPot("full", 1);
                                h.getPotions().add(new Potion("full"));
                                numItems++;
                            }else{
                                System.out.println("*Purchase Unsuccessful*");
                                System.out.println("Farmer: Come back when you have two hides.");
                            }
                        }else if(wPot.equals("P")){
                            if(h.getHides()>=3){
                                System.out.println("*Purchase Successful*");
                                h.setHides(h.getHides()-3);
                                //h.setNOPot("phoenix", 1);
                                h.getPotions().add(new Potion("phoenix"));
                                numItems++;
                            }else{
                                System.out.println("*Purchase Unsuccessful*");
                                System.out.println("Farmer: Come back when you have three hides.");
                            }
                        }
                    }else if(item.equals("BO")){
                        if(h.getHides()>=3){
                            System.out.println("*Purchase Successful*");
                            h.setHides(h.getHides()-3);
                            //h.setBO(1);
                            h.getBombs().add(new Bombs());
                            numItems++;
                        }else{
                            System.out.println("*Purchase Unsuccessful*");
                            System.out.println("Farmer: Come back when you have three hides.");
                        }
                    }else if(item.equals("BR")){
                        if(h.getHides()>=2){
                            System.out.println("*Purchase Successful*");
                            h.setHides(h.getHides()-3);
                            //h.setBP(true);
                            h.getArmors().add(new Armor("breast plate"));
                            numItems++;
                        }else{
                            System.out.println("*Purchase Unsuccessful*");
                            System.out.println("Farmer: Come back when you have three hides.");
                        }
                    }else if(item.equals("S")){
                        if(h.getHides()>=3){
                            System.out.println("*Purchase Successful*");
                            h.setHides(h.getHides()-3);
                            //h.setNOA("satchel");
                            h.getArmors().add(new Armor("satchel"));
                            maxItems = 9;
                        }else{
                            System.out.println("*Purchase Unsuccessful*");
                            System.out.println("Farmer: Come back when you have three hides.");
                        }
                    }else if(item.equals("L")){
                        if(h.getHides()>=3){
                            System.out.println("*Purchase Successful*");
                            h.setHides(h.getHides()-3);
                            h.setLS(true);
                            h.getArmors().add(new Armor("leather sandals"));
                            numItems++;
                        }else{
                            System.out.println("*Purchase Unsuccessful*");
                            System.out.println("Farmer: Come back when you have three hides.");
                        }
                    }
                }else{
                    System.out.println("Farmer: Okay then! Come back when you need items!");
                }
            }
        }
    }
}




