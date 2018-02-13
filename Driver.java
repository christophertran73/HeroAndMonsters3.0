import java.io.*;
import java.util.*;
public class Driver{
    //used to indicate which farmer is being interacted with
    public static int numF = 0;
    public static void main(String args[]){
        System.out.println("** Hero & Monsters **\n");

        //create objects
        Hero hero = new Hero();

        //ArrayList<Potion> potions = new ArrayList<Potion>();

        //ArrayList<Monster> monsters = new ArrayList<Monster>();
        ArrayList<Boss> bosses = new ArrayList<Boss>();
        Boss sphinx = new Boss("Sphinx", 4, 4);
        Boss chimera = new Boss("Chimera", 5, 5);
        Boss dragon = new Boss("Dragon", 6, 6);
        Boss finalBoss = new Boss("Final Boss", 7, 7);
        bosses.add(sphinx);
        bosses.add(chimera);
        bosses.add(dragon);
        bosses.add(finalBoss);

        ArrayList<Farmer> farmers = new ArrayList<Farmer>();
        Farmer f1 = new Farmer();
        Farmer f2 = new Farmer();
        Farmer f3 = new Farmer();
        Farmer f4 = new Farmer();
        Farmer f5 = new Farmer();
        Farmer f6 = new Farmer();
        farmers.add(f1);
        farmers.add(f2);
        farmers.add(f3);
        farmers.add(f4);
        farmers.add(f5);
        farmers.add(f6);

        int worldSize = 15;

        //generate the maps (1 with objects, 1 with a String that we display)
        System.out.println("* The map has been generated *");
        Object map[][] = new Object[worldSize][worldSize];
        String printedMap[][] = new String[worldSize][worldSize];

        //fill map with objects
        map[hero.getY()][hero.getX()] = hero;
        printedMap[hero.getY()][hero.getX()] = "H  ";
        for(Farmer obj: farmers){
            while(map[obj.getX()][obj.getY()] != null){
                obj.setX((int)(Math.random() * worldSize));
                obj.setY((int)(Math.random() * worldSize)); 
            }
            map[obj.getY()][obj.getX()] = obj;
            printedMap[obj.getY()][obj.getX()] = "F  ";            
        }
        System.out.println("* The farmers have been generated *");
        
        for(Boss obj: bosses){
            while(map[obj.getX()][obj.getY()] != null){
                obj.setX((int)(Math.random() * worldSize));
                obj.setY((int)(Math.random() * worldSize)); 
            }
            map[obj.getY()][obj.getX()] = obj;
            if(obj.getName().equals("Chimera")){
                printedMap[obj.getY()][obj.getX()] = "C  ";
            } else if(obj.getName().equals("Sphinx")){
                printedMap[obj.getY()][obj.getX()] = "S  ";
            } else if(obj.getName().equals("Dragon")){
                printedMap[obj.getY()][obj.getX()] = "D  ";
            } else if (obj.getName().equals("Final Boss")){
                printedMap[obj.getY()][obj.getX()] = "B  ";
            }
        }
        System.out.println("* Enemies have been spawned *");

        //Storyline intro (add to this)
        System.out.println("Hero begins his journey");

        //prints map
        printMap(map, printedMap, worldSize, hero);

        //gameplay
        Scanner reads = new Scanner(System.in);
        String key;
        int oldX, oldY;
        //Potion potionHeroIsCarrying = null;
        while(true){
            if(hero.getHealth() <= 0){
                System.out.println("** Game Over **");
                break;
                //FIX
            }
            else if(hero.getHealth() > 0 && hero.getKilledB() == 4){
                System.out.println("* Hero has defeated all the bosses *");
                System.out.println("** You win! **");
                break;
            }
            System.out.print("Enter direction (WASD): ");

            key = reads.next();
            oldX = hero.getX();
            oldY = hero.getY();

            //check clicks of WASD to move
            //take input from the user
            if(key.equals("w")){
                if(hero.getY() - 1 < 0 || map[hero.getY() - 1][oldX] != null){
                    System.out.println("Hero cannot go there.");
                } else {
                    hero.setY(hero.getY() - 1);
                }
            } else if(key.equals("s")){
                if(hero.getY() + 1 > 14 || map[hero.getY() + 1][oldX] != null){
                    System.out.println("Hero cannot go there.");
                } else {
                    hero.setY(hero.getY() + 1);
                }
            } else if(key.equals("a")){
                if(hero.getX() - 1 < 0 || map[oldY][hero.getX() - 1] != null){
                    System.out.println("Hero cannot go there.");
                } else {
                    hero.setX(hero.getX() - 1);
                }
            } else if(key.equals("d")){
                if(hero.getX() + 1 > 14 || map[oldY][hero.getX() + 1] != null){
                    System.out.println("Hero cannot go there.");
                } else {
                    hero.setX(hero.getX() + 1);
                }
            }else {
                System.out.println("You've entered an incorrect key.");
            }

            map[oldY][oldX] = null;
            printedMap[oldY][oldX] = null;
            map[hero.getY()][hero.getX()] = hero;
            printedMap[hero.getY()][hero.getX()] = "H  ";
            printMap(map, printedMap, worldSize, hero);
            placeMonsters(map, printedMap,hero, worldSize);
            ArrayList<Integer>touched = touching(map, printedMap, hero);
            
            //check if touching anything
            if(touched.size() > 0){
                differentiate(touching(map, printedMap, hero), map, printedMap, hero, worldSize, bosses);
            } else {
                printMap(map, printedMap, worldSize, hero);
            }

        }
    }

    public static void printMap(Object[][] map, String[][] printedMap, int worldSize, Hero h){
        int k;
        String str = "";
        Monster m;
        Boss b;
        Farmer f;
        for(int i=0; i< worldSize; i++){
            for(k=0; k< worldSize; k++){
                if(map[i][k] == null){
                    str += "–  ";
                } else {
                    if(printedMap[i][k] == "H  "){
                        str += printedMap[i][k];
                    }
                    if(printedMap[i][k] == "M  "){
                        m = (Monster)map[i][k];
                        str += printedMap[i][k];
                        //System.out.println("Monster hasbeen printed");
                    } else if(printedMap[i][k] == "S  " || printedMap[i][k] == "C  " || printedMap[i][k] == "D  " || printedMap[i][k] == "B  " ){
                        b = (Boss)map[i][k];
                        if(b.isInteracted()){
                            str += printedMap[i][k];
                        } else {
                            str += "–  ";
                        }
                    } else if(printedMap[i][k] == "F  "){
                        f = (Farmer)map[i][k];
                        if(h.getShowPpl()){
                            str += printedMap[i][k];
                        } else if(f.getInteracted()){
                            str += printedMap[i][k];
                        } else {
                            str += "–  ";
                        }
                    }
                }
            }
            str += "\n";
        }

        System.out.println(str);
        String arm = "";
        for(Armor obj: h.getArmors()){
            arm += obj.getType() + ", ";
        }
        System.out.println("Hero's Items: \n\tArmor: " + arm + "  Weapon: " + h.getWeapon().getType()+ "  Potions: " + h.getPotions().size() + "  Bombs: " + h.getBombs().size() + "\n");

    }

    public static void placeMonsters(Object[][] map, String[][] printedMap, Hero h, int worldSize){
        //ArrayList<Monster> monsters = new ArrayList<Monster>();
        Monster m;
        int num = (int)(Math.random()*4);
        if(Math.random() < 0.2){
            //System.out.println("(" + h.getY() + ", " + h.getX() + ")");
            //monster on top of hero
            if(num == 0 && h.getY() - 1 >= 0 && map[h.getY() - 1][h.getX()] == null){
                m = new Monster(h.getX(), h.getY() - 1);
                map[m.getY()][m.getX()] = m;
                printedMap[m.getY()][m.getX()] = "M  ";
                //printMap(map, printedMap, worldSize, h);
                //m.interact(h);
                //System.out.println("aaaaaaaaa");
                //System.out.println(m.getX() + ", " + m.getY());
            }
            //monster below hero
            else if(num == 1 && h.getY() + 1 < 15 && map[h.getY() + 1][h.getX()] == null){
                m = new Monster(h.getX(), h.getY() + 1);
                map[m.getY()][m.getX()] = m;
                printedMap[m.getY()][m.getX()] = "M  ";
                //printMap(map, printedMap, worldSize, h);
                //m.interact(h);
                //System.out.println("bbbbbbbbb");
                //System.out.println(m.getX() + ", " + m.getY());
            }
            //monster to the left of hero
            else if(num == 2 && h.getX() - 1 >= 0 && map[h.getY()][h.getX() - 1] == null){
                m = new Monster(h.getX() - 1, h.getY());
                map[m.getY()][m.getX()] = m;
                printedMap[m.getY()][m.getX()] = "M  ";
                //printMap(map, printedMap, worldSize, h);
                //m.interact(h);
                //System.out.println("cccccccccc");
                //System.out.println(m.getX() + ", " + m.getY());
            }
            //monster to the left of hero
            else if(num == 3 && h.getX() + 1 < 15 && map[h.getY()][h.getX() + 1] == null){
                m = new Monster(h.getX() + 1, h.getY());
                map[m.getY()][m.getX()] = m;
                printedMap[m.getY()][m.getX()] = "M  ";
                //printMap(map, printedMap, worldSize, h);
                //m.interact(h);
                //System.out.println("ddddddddd");
                //System.out.println(m.getX() + ", " + m.getY());
            }
            //System.out.println(h.getX() + ", " + h.getY());
        }
    }

    public static ArrayList<Integer> touching(Object[][] map, String[][] printedMap, Hero h){
        ArrayList<Integer> interactedPos = new ArrayList<Integer>();
        if(h.getY()+1 <= 14 && map[h.getY()+1][h.getX()] != null){
            interactedPos.add(h.getY()+1);
            interactedPos.add(h.getX());
        }

        if(h.getY()-1 >= 0 && map[h.getY()-1][h.getX()] != null){
            interactedPos.add(h.getY()-1);
            interactedPos.add(h.getX());
        }

        if(h.getX()+1 <= 14 && map[h.getY()][h.getX()+1] != null){
            interactedPos.add(h.getY());
            interactedPos.add(h.getX()+1);
        }

        if(h.getX()-1 >= 0 && map[h.getY()][h.getX()-1] != null){
            interactedPos.add(h.getY());
            interactedPos.add(h.getX()-1);
        }

        return interactedPos;
    }

    public static void differentiate(ArrayList<Integer> touchedPos, Object[][] map, String[][] printedMap, Hero h, int worldSize, ArrayList<Boss> bosS){
        //Scanner reads = new Scanner(System.in);
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        ArrayList<Farmer> farmers = new ArrayList<Farmer>();
        ArrayList<Boss> bosses = new ArrayList<Boss>();
        Monster m;
        Boss b;
        Farmer f;

        for(int i=0; i < touchedPos.size(); i+=2){
            if(printedMap[(int)(touchedPos.get(i))][(int)(touchedPos.get(i+1))].equals("C  ") || printedMap[(int)(touchedPos.get(i))][(int)(touchedPos.get(i+1))].equals("S  ") ||printedMap[(int)(touchedPos.get(i))][(int)(touchedPos.get(i+1))].equals("D  ")||printedMap[(int)(touchedPos.get(i))][(int)(touchedPos.get(i+1))].equals("B  ")){
                b = (Boss)map[touchedPos.get(i)][touchedPos.get(i+1)];
                b.setInteracted(true);
                bosses.add(b);
            }else if(printedMap[(int)(touchedPos.get(i))][(int)(touchedPos.get(i+1))].equals("M  ")){
                m = (Monster)map[touchedPos.get(i)][touchedPos.get(i+1)];
                m.setInteracted(true);
                monsters.add(m);
            }else if(printedMap[(int)(touchedPos.get(i))][(int)(touchedPos.get(i+1))].equals("F  ")){
                f = (Farmer)map[touchedPos.get(i)][touchedPos.get(i+1)];
                f.setInteracted(true);
                farmers.add(f);
            }
        }
        //prints map
        printMap(map, printedMap, worldSize, h);
        interact(monsters, farmers, h, bosS);
    }

    public static void interact(ArrayList<Monster> mo, ArrayList<Farmer> fa, Hero h, ArrayList<Boss> bosses){
        for(Farmer f: fa){
            numF++;
            if(numF==1){
                f.setType(1);
            }
            f.interact(h, bosses);
        }

        for(Monster m: mo){
            m.interact(h);
        }
        
        for(Boss b: bosses){
            b.interact(h);
        }
    }

    public boolean isMonster(int x, int y){
        if(Math.random() < 0.9){
            return true;
        } else {
            return false;
        }
    }
}




