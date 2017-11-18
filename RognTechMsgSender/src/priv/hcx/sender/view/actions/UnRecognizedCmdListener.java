package priv.hcx.sender.view.actions;

public interface UnRecognizedCmdListener {
	boolean isRecognized(String cmd);

	Object doAction(String cmd);
}
