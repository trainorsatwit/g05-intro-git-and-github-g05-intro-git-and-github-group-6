public class Object {
    private boolean hasKey;
    private boolean hasBeenLookedAt = false;
    private String name;
    public Object(boolean key, String objName)
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
}
