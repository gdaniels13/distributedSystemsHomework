package messageDefinition;

import Resources.FieldLayout;

public class LayoutRes extends Response {
	public FieldLayout layout;

	public LayoutRes(String messageString)
	{
		super(messageString);
		this.layout = new FieldLayout(messageString.split(DELIMITER + DELIMITER)[2]);
	}

	@Override
    public String serialize() {
        return mesString() + resString() + layout.toString();
    }
}