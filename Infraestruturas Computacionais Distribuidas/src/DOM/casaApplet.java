package DOM;
import javax.swing.*;
import java.awt.*;



public class casaApplet extends JApplet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String cenas;
	public void init() {
		// TODO Auto-generated constructor stub
		
		setBackground(Color.BLACK);

		this.cenas = "omagadesidngkjdsngjkdsgjkdsgkjndskjgn";
	}
	
	public void start(){
		setBackground(Color.BLACK);

		this.cenas = "omagadesidngkjdsngjkdsgjkdsgkjndskjgn";
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.WHITE);
		g.drawString("LSDNGJSGKJSJKGJK VKJ ", 25, 25);
	}

}
