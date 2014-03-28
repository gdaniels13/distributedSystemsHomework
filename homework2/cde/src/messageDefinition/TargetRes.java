package messageDefinition;

import Resources.Agent;

public class TargetRes extends Response {
	public Agent target;

    public TargetRes(String messageString) {
        super(messageString);
        this.target = new Agent(messageString.split(DELIMITER + DELIMITER)[2]);
    }

    @Override
    public String serialize() {
        return mesString() + resString() + target.toString();
    }
}