
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

//GuiFrame for displaying desk information.

public class GuiFrame extends JFrame {
	private FlightList flightdata; 
	private Airport deskdata; 
	
	
	//private JButton updateButton = new JButton("Update");
	
	//Create Frame & panel
	public GuiFrame(Airport model, FlightList flist){
		
		//Set Close by default close button of window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//Gui Title
		setTitle("Airport Check-in Status" ); 
		
		//Set Queue Display Panel
		add(BorderLayout.NORTH,  QueueDisplay(model));
		//Set Desk Display Panel
		add(BorderLayout.CENTER,  DeskDisplay(model));
		//Set Flight Display Panel
		add(BorderLayout.SOUTH,  FlightDisplay(flist));
		// Set Update 
		//add(BorderLayout.EAST, createUpdatePanel());

		setVisible(true);

	}
	
	// sets up DeskStatus Deisplay Panel
		public JPanel DeskDisplay(Airport actual) { //Set CenterPanel for all Desks information
			this.deskdata = actual;
			JPanel setDeskPanel = new JPanel(new BorderLayout());
			
			JTextPane desk1Pane = new JTextPane();
			desk1Pane.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
			desk1Pane.setEditable(false);
			String text1 = deskdata.getDeskStatuses(1);
			desk1Pane.setText(text1);
			setDeskPanel.add(BorderLayout.WEST, desk1Pane);
			
			JTextPane desk2Pane = new JTextPane();
			desk2Pane.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
			desk2Pane.setEditable(false);
			String text2 = deskdata.getDeskStatuses(2);
			desk2Pane.setText(text2);
			setDeskPanel.add(BorderLayout.EAST, desk2Pane);
			
			return setDeskPanel;

	}

		// sets up FlightDisplay Panel
		public JPanel FlightDisplay(FlightList flight) {	//Set SouthPanel for all flights information
			this.flightdata = flight;
			JPanel setFlightPanel = new JPanel(new BorderLayout());
			
			//Randomly select a flight to display
			ArrayList<Integer> numbers = new ArrayList<Integer>(); 
	        int total = 5; // This has to be less than 11 
	        Random random = new Random(); 
	        do 
	        { 
	            int next = random.nextInt(5); 
	            if (!numbers.contains(next)) 
	            { 
	                numbers.add(next); 
	                System.out.println(next); 
	            } 
	        } while (numbers.size() < total);

			String[] list = {"BA8719", "BA472", "BA1472", "BA412", "BA828"};
			String text1 = flightdata.getFlightDetails(list[numbers.get(1)]);
			String text2 = flightdata.getFlightDetails(list[numbers.get(2)]);
			String text3 = flightdata.getFlightDetails(list[numbers.get(3)]);

			
			JTextPane flight1Pane = new JTextPane();
			flight1Pane.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
			flight1Pane.setEditable(false);
			flight1Pane.setText(text1);	
			setFlightPanel.add(BorderLayout.WEST,flight1Pane);
			
			JTextPane flight2Pane = new JTextPane();
			flight2Pane.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
			flight2Pane.setEditable(false);
			flight2Pane.setText(text2);
			setFlightPanel.add(BorderLayout.CENTER, flight2Pane);
			
			JTextPane flight3Pane = new JTextPane();
			flight3Pane.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
			flight3Pane.setEditable(false);
			flight3Pane.setText(text3);
			setFlightPanel.add(BorderLayout.EAST, flight3Pane); 
			
			return setFlightPanel;
			
		}

	private JTextArea queueList;
	// sets up QueueDisplay Panel
		public JPanel QueueDisplay(Airport queue) {
			this.deskdata = queue;
			
			JPanel setQueueDisplay = new JPanel(new BorderLayout());
			
			JTextArea queueList = new JTextArea();
			Font queueFont = new Font("SansSerif", Font.BOLD, 14);
			queueList.setFont(queueFont);
			queueList.setEditable(false);
			String text = deskdata.getQueueStatus();
			queueList.setText(text);
			
			setQueueDisplay.add(BorderLayout.NORTH, queueList);		
			return setQueueDisplay;
		}

}

		

	
/*	public JPanel createUpdatePanel() {
		JPanel updatePanel = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(updateButton);
		updatePanel.add(BorderLayout.NORTH,buttonPanel);	
		return updatePanel;
	}
	
	public void addSetListener(ActionListener al) {
		updateButton.addActionListener(al);
	}
*/

