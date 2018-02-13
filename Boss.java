import java.io.*;
import java.util.*;
public class Boss extends Enemy{
    private String name;
    private boolean heroHasItems;
    private boolean canFlee;
    public Boss(String n, int st, int sp){
        super();
        name = n;
        setStrength(st);
        setSpeed(sp);
        setHealth(getHealth() + sp * 15);
        heroHasItems = false;
    }

    public void interact(Hero h){
        //checks if hero has proper items
        if(name.equals("sphinx")){
            for(Armor obj: h.getArmors()){
                if(obj.equals("satchel")){
                    heroHasItems = true;
                }else{
                    System.out.println("Come back when you have a satchel.");
                }
            }
        }else if(name.equals("chimera")){
            if ( h.getWeapon().equals("short sword") ){
                heroHasItems = true;
            }else{
                System.out.println("Come back when you have a short sword.");
            }
        }else if(name.equals("dragon")){
            if ( h.getWeapon().equals("long sword") ){
                heroHasItems = true;
            }else{
                System.out.println("Come back when you have a long sword.");
            }
        }else if(name.equals("finalBoss")){
            for(Armor obj: h.getArmors()){
                if(obj.equals("breastplate")&&h.getLS()==true&&h.getWeapon().equals("axe")){
                    int counter = 0;
                    for(Potion obj2: h.getPotions()){
                        if( obj2.equals("phoenix") ){
                            counter++;
                        }
                    }
                    if (counter>= 3){
                        heroHasItems = true;
                    }else{
                        System.out.println("Come back when you have a satchel.");
                    }
                }else{
                    System.out.println("Come back when you have a satchel.");
                }
            }
        }
        if(isAlive() && heroHasItems){
            Scanner reads = new Scanner(System.in);
            String option;
            canFlee = false;
            while(!canFlee){
                System.out.println("You have run into the " + name + "!");
                System.out.println("\nWhat do you want to do?\n" 
                    + "1.  Fight\n2.  Flee");
                if(h.getBombs().size() > 0){
                    System.out.println("Press B to use bomb & kill monster.");
                }
                if(h.getPotions().size() > 0){
                    System.out.println("Press P to use revive your health.");
                }
                option = reads.next();
                while(!(option.equals("1") || option.equals("2") || option.equals("b") || option.equals("p"))){
                    System.out.print("\nOnly enter the keys 1 or 2");
                    if(h.getBombs().size() > 0){
                        System.out.print(" or B");
                    }
                    if(h.getPotions().size() > 0){
                        System.out.print(" or P");
                    }
                    System.out.println("\nWhat do you want to do?\n" 
                        + "1.  Fight\n2.  Flee");
                    option = reads.next();
                }
                //kills monster with bomb
                if(option.equals("b")){
                    setHealth(0);
                    h.getBombs().remove(0);
                } else if(option.equals("p")){
                    //ask which type of potion & make sure has that potion
                    System.out.println("Which type of potion would you like to use: (H) half potion of (F) full potion or (N) none?");
                    String pot = reads.next();
                    int numNonP = 0;
                    while(!(option.equals("f") || pot.equals("h") || pot.equals("n"))){
                        System.out.print("\nOnly enter the keys h,f, or n");
                        System.out.println("\nWhat do you want to use?\n");
                        pot = reads.next();
                    }
                    if(pot.equals("n")){
                        break;
                    } else if(pot.equals("h")) {
                        for(Potion obj: h.getPotions()){
                            if(obj.getType().equals("half") && pot.equals("h")){
                                h.getPotions().remove(obj);
                                h.setHealth(h.getHealth() + 50);
                                System.out.println("Hero's health is now " + h.getHealth());
                                break;
                            } else {
                                numNonP++;
                            }
                        }
                        if(numNonP == h.getPotions().size()){
                            System.out.println("You don't have any half potions.");
                        }
                    } else if(pot.equals("h")){
                        for(Potion obj: h.getPotions()){
                            if(obj.getType().equals("full") && pot.equals("f")){
                                h.getPotions().remove(obj);
                                h.setHealth(100);
                                System.out.println("Hero's health is now maxed");
                                break;
                            } else {
                                numNonP++;
                            }
                        }
                        if(numNonP == h.getPotions().size()){
                            System.out.println("You don't have any full potions.");
                        }
                    }
                }else if(option.equals("1")){
                    fight(h);
                } else{
                    flee(h);
                }

                if(h.getHealth() <= 0){
                    System.out.println("\nThe " + name + " has killed you.");
                    int numNonPh = 0;
                    for(Potion p: h.getPotions()){
                        if(p.getType().equals("phoenix")){
                            System.out.println("You have a phoeonix potion. Hero is resuscitated.");
                            h.getPotions().remove(p);
                            h.setHealth(100);
                            break;
                        } else {
                            numNonPh++;
                        }
                    }
                    if(numNonPh == h.getPotions().size()){
                        canFlee = true;
                        break;
                    }
                } else if(getHealth() <= 0){
                    System.out.println("\nYou have succeeded in defeating the " + name + "!");
                    canFlee = true;
                    setAlive(false);
                    h.setKilledB(h.getKilledB()+1);
                    h.setXP(h.getXP() + getSpeed() * 5 );
                    //Hero gains gold(currency) of defeated bosses
                    //The # of gained gold is the getSpeed() x 2 so the Hero gains more gold for more difficulty
                    h.setGold(h.getGold() + getSpeed() * 2);
                    System.out.println("Hero has gained " + (getSpeed() * 2) + " hides, for a total of " + h.getHides());
                    if(name.equals("chimera")){
                        h.getPotions().add(new Potion("phoenix"));
                        System.out.println("You have gained a phoenix potion.");
                    }else if(name.equals("dragon")){
                        h.getPotions().add(new Potion("phoenix"));
                        h.getPotions().add(new Potion("phoenix"));
                        System.out.println("You have gained two phoenix potions.");
                    }
                    break;
                }
            }
        }
    }

    public void fight(Hero h){
        int numNonBR = 0;
        for(Armor a: h.getArmors()){
            if(a.getType().equals("breastplate")){
                h.setHealth(h.getHealth() - getStrength() * (1-a.getDef()));
                break;
            } else {
                numNonBR++;
            }
        }
        if(numNonBR == h.getArmors().size()){
            h.setHealth(h.getHealth() - getStrength());
        }
        if(h.getLS() == true){    
            setHealth((int)(getHealth() - h.getWeapon().getAtt()));
            System.out.println("You move again.\nYou quickly use your " + h.getWeapon().getType() + ". The monster's health has been lowered.");

        }
        System.out.println("The " + name + " attacked. Your health is now " + h.getHealth() + " xp.");
        if(getSpeed() == 4 ){
            if(Math.random() > 0.20){
                setHealth((int)(getHealth() - h.getWeapon().getAtt()));
                System.out.println("You quickly use your " + h.getWeapon().getType() + ". The " + name +"'s health has been lowered.");
            } else {
                System.out.println("The " + name + " is too fast. You have missed");
            }
        } else if(getSpeed() == 5){
            if(Math.random() > 0.30){
                setHealth((int)(getHealth() - h.getWeapon().getAtt()));
                System.out.println("You quickly use your " + h.getWeapon().getType() + ". The " + name +"'s health has been lowered.");
            } else {
                System.out.println("The " + name + " is too fast. You have missed");
            }
        } else if(getSpeed() == 6){
            if(Math.random() > 0.40){
                setHealth((int)(getHealth() - h.getWeapon().getAtt()));
                System.out.println("You quickly use your " + h.getWeapon().getType() + ". The " + name +"'s health has been lowered.");
            } else {
                System.out.println("The " + name + " is too fast. You have missed");
            }
        }  else if(getSpeed() == 7){
            if(Math.random() > 0.50){
                setHealth((int)(getHealth() - h.getWeapon().getAtt()));
                System.out.println("You quickly use your " + h.getWeapon().getType() + ". The " + name +"'s health has been lowered.");
            } else {
                System.out.println("The " + name + " is too fast. You have missed");
            }
        }
    }

    public void flee(Hero h){
        System.out.println("Hero has managed to avoid engaging in battle.");
        canFlee = true;
    }

    public void setName(String n){
        name = n;
    }

    public void setHeroHasItems(boolean h){
        heroHasItems = h;
    }

    public void setCanFlee(boolean cF){
        canFlee = cF;
    }

    public String getName(){
        return name;
    }

    public boolean getHeroHasItems(){
        return heroHasItems;
    }

    public boolean getCanFlee(){
        return canFlee;
    }

    public String toString(){
        return (name + " Health: " + getHealth() + ",\n" + name + " Strength: " + getStrength() + ",\n" + name + " Speed: " + getSpeed());
    }
}



