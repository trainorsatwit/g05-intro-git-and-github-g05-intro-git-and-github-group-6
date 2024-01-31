public class Furniture {
    private boolean hasKey;
    private boolean hasBeenLookedAt = false;
    private final String name;
    public Furniture(boolean key, String objName)
    {
        hasKey = key;
        name = objName;
    }
    public String getName() {
        return name;
    }
    public boolean getLook(){
        return hasBeenLookedAt;
    }
    public void setLook(boolean update){
        hasBeenLookedAt = update;
    }
    public boolean hasKey(){
        return hasKey;
    }
    public void setHasKey(boolean update) {hasKey = update;}
}
