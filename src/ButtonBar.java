import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonBar extends JPanel {
	JButton AcceptButton;
	public ButtonBar() {
		AcceptButton = new JButton("Deal New Cards");
		this.add(AcceptButton);
		this.setBounds(0, 0, 40, 40);

	}
	public void changeToSecondMode(){
		
	}
}
