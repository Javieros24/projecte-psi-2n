package InterficieGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AccioBoto implements ActionListener {
	
	private Login finestra;
	
	
	public AccioBoto(Login finestra){
		this.finestra=finestra;
	}

	public void actionPerformed(ActionEvent evt) {
		JButton boto = (JButton) evt.getSource();
		
		finestra.getTfNom();
		
	}

}
