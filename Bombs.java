public class Bombs extends Item{
    private boolean used;

    public Bombs(){
        super("bomb", 3);
        used = false;
    }

    public boolean getUsed(){
        return used;
    }

    public void setUsed(boolean u){
        used = u;
    }
}

