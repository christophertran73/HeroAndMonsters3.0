import java.io.*;
import java.util.*;
public class Monster extends Enemy{
    private String direction;
    private boolean interacted;
    private boolean canFlee;
    public Monster(){
        super();
        direction = getDirection();
    }

    public Monster(int x, int y){
        super(x, y);
        direction = getDirection();
        interacted = true;
    }

    public Monster(int x, int y, Hero h){
        super(x, y);
        //setStrength((int)(Math.random() * 30) + 1;);
        setStrength((int)(h.getXP()/2 + 5));
        //hero gains 5 XP for each lvl 1 monster 
        //hero gains 10 XP for each lvl 2 monster 
        //hero gains 15 XP for each lvl 3 monster 
        if(h.getXP()<20){
            setSpeed(1);
        } else if(h.getXP()<80){
            setSpeed(2);
        } else {
            setSpeed(3);
        }
        direction = getDirection();
    }

    public void interact(Hero h){
        if(isAlive()){
            Scanner reads = new Scanner(System.in);
            String option;
            canFlee = false;
            while(!canFlee){
                System.out.println("You have run into a monster!");
                System.out.println(toString());
                System.out.println("\nWhat do you want to do?(1 or 2)\n" 
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
                //kill monster with bomb
                if(option.equals("b")){
                    setHealth(0);
                    h.getBombs().remove(0);
                } else if(option.equals("p")){
                    //ask which type of potion & make sure has that potion
                    System.out.println("Which type of potion would you like to use: (H) half potion of (F) full potion or (N) none?");
                    String pot = reads.next();
                    int numNonP = 0;
                    while(!(pot.equals("f") || pot.equals("h") || pot.equals("n"))){
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
                    } else if(pot.equals("f")){
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
                    //if monster is above hero
                    if (h.getX() == getX() && h.getY() == getY() + 1){
                        //100% flee
                        if ( direction.equals("north") ){
                            flee(h);
                            //0% flee
                        }else if ( direction.equals("south") ){
                            if(h.getLS()==true){
                                if(Math.random() > 0.50){
                                    flee(h);
                                }
                            }else{
                                System.out.println("Hero cannot flee. He must fight the monster.");
                                fight(h);
                            }
                            /*depends on getSpeed() of monster and is 2x more
                             * likely to flee than old H&M*/
                        }else if ( direction.equals("east") || direction.equals("west")){
                            if(h.getLS()==true){
                                if(getSpeed() == 1 && Math.random() > 0.05){
                                    flee(h);
                                } else if(getSpeed() == 2 && Math.random() > 0.15){
                                    flee(h);
                                } else if(getSpeed() == 3 && Math.random() > 0.25){
                                    flee(h);
                                } else{
                                    System.out.println("Hero cannot flee. He must fight the monster.");
                                    fight(h);
                                }
                            }else{
                                if(getSpeed() == 1 && Math.random() > 0.10){
                                    flee(h);
                                } else if(getSpeed() == 2 && Math.random() > 0.30){
                                    flee(h);
                                } else if(getSpeed() == 3 && Math.random() > 0.50){
                                    flee(h);
                                } else{
                                    System.out.println("Hero cannot flee. He must fight the monster.");
                                    fight(h);
                                }
                            }

                        }
                        //if monster is east of hero
                    }else if (h.getX() == getX() -1 && h.getY() == getY()){
                        //100% flee
                        if ( direction.equals("east") ){
                            flee(h);
                            //0% flee
                        }else if ( direction.equals("west") ){
                            if(h.getLS()==true){
                                if(Math.random() > 0.50){
                                    flee(h);
                                }
                            }else{
                                System.out.println("Hero cannot flee. He must fight the monster.");
                                fight(h);
                            }
                            /*depends on getSpeed() of monster and is 2x more
                             * likely to flee than old H&M*/
                        }else if ( direction.equals("north") || direction.equals("south")){
                            if(h.getLS()==true){
                                if(getSpeed() == 1 && Math.random() > 0.05){
                                    flee(h);
                                } else if(getSpeed() == 2 && Math.random() > 0.15){
                                    flee(h);
                                } else if(getSpeed() == 3 && Math.random() > 0.25){
                                    flee(h);
                                } else{
                                    System.out.println("Hero cannot flee. He must fight the monster.");
                                    fight(h);
                                }
                            }else{
                                if(getSpeed() == 1 && Math.random() > 0.10){
                                    flee(h);
                                } else if(getSpeed() == 2 && Math.random() > 0.30){
                                    flee(h);
                                } else if(getSpeed() == 3 && Math.random() > 0.50){
                                    flee(h);
                                } else{
                                    System.out.println("Hero cannot flee. He must fight the monster.");
                                    fight(h);
                                }
                            }
                        }
                        //if monster is above hero
                    }else if (h.getX() == getX() && h.getY() == getY() - 1){
                        //100% flee
                        if ( direction.equals("south") ){
                            flee(h);
                            //0% flee
                        }else if ( direction.equals("north") ){
                            if(h.getLS()==true){
                                if(Math.random() > 0.50){
                                    flee(h);
                                }
                            }else{
                                System.out.println("Hero cannot flee. He must fight the monster.");
                                fight(h);
                            }
                            /*depends on getSpeed() of monster and is 2x more
                             * likely to flee than old H&M*/
                        }else if ( direction.equals("east") || direction.equals("west")){
                            if(h.getLS()==true){
                                if(getSpeed() == 1 && Math.random() > 0.05){
                                    flee(h);
                                } else if(getSpeed() == 2 && Math.random() > 0.15){
                                    flee(h);
                                } else if(getSpeed() == 3 && Math.random() > 0.25){
                                    flee(h);
                                } else{
                                    System.out.println("Hero cannot flee. He must fight the monster.");
                                    fight(h);
                                }
                            }else{
                                if(getSpeed() == 1 && Math.random() > 0.10){
                                    flee(h);
                                } else if(getSpeed() == 2 && Math.random() > 0.30){
                                    flee(h);
                                } else if(getSpeed() == 3 && Math.random() > 0.50){
                                    flee(h);
                                } else{
                                    System.out.println("Hero cannot flee. He must fight the monster.");
                                    fight(h);
                                }
                            }
                        }
                        //if monster is west of hero
                    }else if (h.getX() == getX() + 1 && h.getY() == getY()){
                        //100% flee
                        if ( direction.equals("west") ){
                            flee(h);
                            //0% flee
                        }else if ( direction.equals("east") ){
                            if(h.getLS()==true){
                                if(Math.random() > 0.50){
                                    flee(h);
                                }
                            }else{
                                System.out.println("Hero cannot flee. He must fight the monster.");
                                fight(h);
                            }
                            /*depends on getSpeed() of monster and is 2x more
                             * likely to flee than old H&M*/
                        }else if ( direction.equals("north") || direction.equals("south")){
                            if(h.getLS()==true){
                                if(getSpeed() == 1 && Math.random() > 0.05){
                                    flee(h);
                                } else if(getSpeed() == 2 && Math.random() > 0.15){
                                    flee(h);
                                } else if(getSpeed() == 3 && Math.random() > 0.25){
                                    flee(h);
                                } else{
                                    System.out.println("Hero cannot flee. He must fight the monster.");
                                    fight(h);
                                }
                            }else{
                                if(getSpeed() == 1 && Math.random() > 0.10){
                                    flee(h);
                                } else if(getSpeed() == 2 && Math.random() > 0.30){
                                    flee(h);
                                } else if(getSpeed() == 3 && Math.random() > 0.50){
                                    flee(h);
                                } else{
                                    System.out.println("Hero cannot flee. He must fight the monster.");
                                    fight(h);
                                }
                            }
                        }
                    }   
                }

                if(h.getHealth() <= 0){
                    System.out.println("\nThe monster has killed you.");
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
                    System.out.println("\nYou have succeeded in defeating the monster!");
                    canFlee = true;
                    setAlive(false);
                    h.setKilledB(h.getKilledB()+1);
                    h.setXP(h.getXP() + getSpeed() * 5 );
                    //Hero gains hides(currency) of defeated monsters
                    //The # of gained hides is the getSpeed() x 2 so the Hero gains more hides for more difficulty
                    h.setHides(h.getHides() + getSpeed() * 2);
                    System.out.println("Hero has gained " + (getSpeed() * 2) + " hides, for a total of " + h.getHides());
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
        if(h.getLS() == true){    
            setHealth((int)(getHealth() - h.getWeapon().getAtt()));
            System.out.println("You move again.\nYou quickly use your " + h.getWeapon().getType() + ". The monster's health has been lowered.");
        }
        if(numNonBR == h.getArmors().size()){
            h.setHealth(h.getHealth() - getStrength());
        }
        System.out.println("The monster attacked. Your health is now " + h.getHealth() + " xp.");
        if(getSpeed() == 1 ){
            setHealth((int)(getHealth() - h.getWeapon().getAtt()));
            System.out.println("You quickly use your " + h.getWeapon().getType() + ". The monster's health has been lowered.");
        } else if(getSpeed() == 2){
            if(Math.random() > 0.25){
                setHealth((int)(getHealth() - h.getWeapon().getAtt()));
                System.out.println("You quickly use your " + h.getWeapon().getType() + ". The monster's health has been lowered.");
            } else {
                System.out.println("The monster is too fast. You have missed");
            }
        } else if(getSpeed() == 3){
            if(Math.random() > 0.50){
                setHealth((int)(getHealth() - h.getWeapon().getAtt()));
                System.out.println("You quickly use your " + h.getWeapon().getType() + ". The monster's health has been lowered.");
            } else {
                System.out.println("The monster is too fast. You have missed");
            }
        }
    }

    public void flee(Hero h){
        System.out.println("Hero has managed to avoid engaging in battle.");
        canFlee = true;
    }

    public void setDirection(String d){
        direction = d;
    }

    public void setInteracted(boolean i){
        interacted = i;
    }

    public boolean getCanFlee(){
        return canFlee;
    }

    public String getDirection(){
        String ret = "";
        int rand = (int)(Math.random() * 4);
        if (rand == 0 ){
            ret = "north";
        }else if ( rand == 1 ){
            ret = "east";
        }else if ( rand == 2 ){
            ret = "south";
        }else if ( rand == 3 ){
            ret = "west";
        }
        return ret;
    }

    public boolean getInteracted(){
        return interacted;
    }

    public void setCanFlee(boolean cF){
        canFlee = cF;
    }

    public String toString(){
        return ("Monster Health: " + getHealth() + ",\nMonster Strength: " + getStrength() + ",\nMonster Speed: " + getSpeed() + "\nMonster Direction: " + direction);
    }
}




