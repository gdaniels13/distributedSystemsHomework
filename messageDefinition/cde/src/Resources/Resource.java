package Resources;

/**
 * Created by gregor on 2/4/14.
 */

import messageDefinition.Message;
public abstract class Resource {
    private int id;

    public Resource(int id) {
        this.id = id;
    }

    public Resource(String id){
        this.id = Integer.parseInt(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }
}
