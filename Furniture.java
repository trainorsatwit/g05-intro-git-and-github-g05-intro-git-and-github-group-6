public class Furniture 
{
    private boolean hasKey1;
    private boolean hasKey2;
    private boolean hasKey3;
    private boolean hasBeenLookedAt = false;
    private final String name;
    public Furniture(boolean keyslot1, boolean keyslot2, boolean keyslot3, String objName)
    {
        hasKey1 = keyslot1;
        hasKey2 = keyslot2;
        hasKey3 = keyslot3;
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
    public boolean hasKey(int slot){
    	if(slot==1)
        	return hasKey1;
        else if(slot==2)
        	return hasKey2;
        else if(slot==3)
        	return hasKey3;
        else
        	return false;
    }
    public void setHasKey(int slot) {
    if(slot==1)
    	hasKey1 = true;
    else if(slot==2)
    	hasKey2 = true;
    else
    	hasKey3 = true;
    }
}

