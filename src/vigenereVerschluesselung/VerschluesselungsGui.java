package vigenereVerschluesselung;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


/*Die Klasse verschluesslungGui dient zur Erstellung das Frames 
 * Die klasse organasiert die Ereignisse,wann auf einzele Knopf gedrueckt wird
 */
public class VerschluesselungsGui extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JButton textUpload;
	private JButton verschluesseln;
	private JLabel keyWord;
	private JTextArea zuVerschuesendeText;
	private JTextArea verschluessendeText;
	private JPanel panel1;
	private JPanel panel2;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JFileChooser chooser;
	private JTextField textField;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private ButtonGroup group;
	
	/**
	 * In der Konstruktor wird für jede Attribut ein Instanz erstellt
	 *textUpload ist der knopf für das Hochladen eines Textes
	 *verschluesseln ist der Knopf für das Verschlüsseln/entschlüsseln des Textes
	 *keyWord ist ein Label und wird neben dem des Schlüsselwortes fuer den User gzeigt
	 *zuVerschluessendeText ist eine Textarea,in der der zu verschlüssende Text hinzugefügt wird
	 *scrollPane1 ist ein Panel im Frame und enthält der zuverschluessende Text
	 *scrollPane2 ist ein Panel im Frame und enthält der verschlüsselte Text 
	 *chooser dient dazu,damit der User ein txt aus dem computer hochladen kann 
	 * radioButton1 repräsentiert ein Wahl des Verschlsselungsverfahrens 
	 * radioButton2 repräsentiert ein Wahl des Enschlüsselungsverfahrens
	 * group dient dazu,nur eine von den Radiobuttons wählen zu können
	 */
	
	public VerschluesselungsGui() {
		textUpload = new JButton("Text upload");
		verschluesseln = new JButton("Text verchluesseln");
		keyWord = new JLabel("Schluesselwort: ");
		zuVerschuesendeText = new JTextArea();
		verschluessendeText = new JTextArea();
		panel1 = new JPanel(null);
		panel2 = new JPanel(null);
		scrollPane1 = new JScrollPane(zuVerschuesendeText);
		scrollPane2 = new JScrollPane(verschluessendeText);
		chooser= new JFileChooser();
		textField = new JTextField();
		radioButton1 = new JRadioButton("verschluesseln" ,true );
		radioButton2 = new JRadioButton("entschluesseln");
	    group = new ButtonGroup();
	    buttonTextUpload();
	    buttonradioButton1();
	    buttonradioButton2();
	    prozess();
	    bound();
	    add();
	    
	}
	/**
	 * Die Methode bound dient zum Festsetzen der Grenze und die Eigenschaft jedes einzelen Komponente des Frames
	 */
	public void bound() {
		panel1.setBounds(340, 70,510 , 210);
		panel2.setBounds(340, 290, 510, 210);
	    scrollPane1.setBounds(10, 20, 490, 180);
	    scrollPane2.setBounds(10, 20, 490, 180);
	    keyWord.setBounds(350, 30, 100, 50);
	    textUpload.setBounds(100, 160, 200, 50);
	    verschluesseln.setBounds(100, 220 ,200, 50);
	    radioButton1.setBounds(100, 80, 130, 40);
	    radioButton2.setBounds(100, 110,130, 50);
	    textField.setBounds(440, 40, 400, 30);
	    panel1.setBorder(BorderFactory.createTitledBorder("Zu verschluessende Text"));
	    panel2.setBorder(BorderFactory.createTitledBorder("verschluesselte Text"));
	    //setLineWrap  damit man zu neur zeile geht wenn der zeile zu lang ist 
	    zuVerschuesendeText.setLineWrap(true);
	    zuVerschuesendeText.setWrapStyleWord(true);
	    verschluessendeText.setLineWrap(true);
	    verschluessendeText.setWrapStyleWord(true);
	}
	/**
	 * in der Methode add werden die Komponente im Frame hinzugefügt 
	 */
	public void add() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    this.setSize(900, 600);	
	    this.setLayout(null);
	    this.setTitle("Vigenere Verschluesselung");
	    this.add(panel1);
	    this.add(panel2);
	    this.add(keyWord);
	    this.add(textUpload);
	    this.add(verschluesseln);
	    this.add(textField);
	    //von klasse JRadioButton (verschlüsseln und entschlüssen)
	    this.add(radioButton1);
	    this.add(radioButton2);
	    
	    panel1.add(scrollPane1);
	    panel2.add(scrollPane2);
	    //damit nur einer von den RadioButton ausgewählt werden kann;
	    group.add(radioButton1);
	    group.add(radioButton2);
	}
	/**
	 * Die Methode prozess organsiert das,wenn der User auf verschlüssen/enschlüssen gedrückt hat 
	 * text bekommt den Text,der der User eingegebn hat,zugewiesen 
	 * schluesselwort bekommt den schlüsselwort für den Text von der Methode schluesselwortgenerator in der Klasse VerschluesselungsDaten zugewesen 
	 * verschluesselteText bekommt den verschlüsselter Text von  der Methode verschluesslung in der Klasse VerschluesselungsDaten zugewesen 
	 */
	private void prozess() {	
		verschluesseln.addActionListener(new ActionListener() {	
		
			public void actionPerformed(ActionEvent e) { 
			if(radioButton1.isSelected()) {	
		 String text =  zuVerschuesendeText.getText();
		 String schlueselwort =   VerschluesselungsDaten.schluesselwortgenerator(text);
		 
		 try {
				String verschluesselteText =  VerschluesselungsDaten.verschluesslung(text, schlueselwort);
				verschluessendeText.setText(verschluesselteText );
				 textField.setText(schlueselwort);
				 
			 } catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			 }
		
		}else {
			//wenn der Nutzer den Wahl entschlüssen gedrückt hat werden die Titel der Komponente geändert durch die Methode geÃ¤ndert
			//Das Schlüsselwort wird geprüft,ob es nur Buchstaben enthält 
			//entschluesseneText bekommt der entschlüssene Text von der Funktion entschluesslung in der  Klasse VerschluesselungsDaten
			if(schluesselpruefen()) {
				String tmp1 = textField.getText();
				String tmp2 = zuVerschuesendeText.getText();
				String entschluesseneText;
				 try {
					 entschluesseneText = VerschluesselungsDaten.entschluesslung(tmp2, tmp1);
					 verschluessendeText.setText(entschluesseneText);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(null ,"Bitte nur keline  Buchstaben beim Schluesselwort eingeben !");
			}
		}
   }});
}
	/**
	 * Die Methode schluesselpruefen() dient zum Pruefen des Scluesselwortes,das der Nutzer eingegeben hat(ob das Schluesselwort nur aus Buchstaben besteht)
	 * tmp bekommt das SchlÃ¼sselwort,das der Nutzer eingegeben hat zugewesen
	 * tmp2 zur Umwandlung String zu Char(damit der text Zeichen nachdem anderer geprueft wird)
	 * @return true falls das Schluesselwort nur Buchstaben enthaelt und der Text.lange groesser eins ist
	 */
		private boolean schluesselpruefen() {
			String tmp = textField.getText();
			if(tmp.length()<1 || tmp == null)
			return false;
			char tmp2;
			int ascii;
			for(int i = 0 ; i<tmp.length() ; i++) {
				tmp2 = tmp.charAt(i);
				ascii = (int)tmp2;
				//nur kleine Buchstaben sind dür das Schlüsselwort erlaubt 
				if(ascii<97 || ascii>122) {
					return false;
				}
				
			}
			return true;
		}
		/*Die Methode buttonTextUpload() dient zum Organasieren das Ereigniss,dass auf dem Button Text Upload gedrueckt wird
		 * in der Methode wird die Pfad des Ordners , der der Nutzer gewÃ¤hlt hat gespeichert und in der entsprechnden Textarea hinzugefuegt 
		 * einzele kommenter fuer die Schritte sind in der Methode 
		 */
		   public void  buttonTextUpload() {
			   textUpload.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					FileNameExtensionFilter filter = new FileNameExtensionFilter("text or java files", "txt", "java" );//der User beschraenken nur Txt oder java Ordner zu waehlen
					chooser.setFileFilter(filter);
					int returnedValue = chooser.showOpenDialog(textUpload);//Mit diesem Befehl wird das Fenester geoeffnet,damit der Nutzer den Ordner waehlt
					if(returnedValue == JFileChooser.APPROVE_OPTION) {//der User hat ein Ordner ausgewÃ¤hlt	
					File gewaehlteOrdner = chooser.getSelectedFile();
					String ordnerPfad = gewaehlteOrdner.getPath();//hier wird die Pfad des txt.Ordners gespeichert
		            try {
				    BufferedReader br = new BufferedReader(new FileReader(ordnerPfad));//der Text Zeile nach der anderen lesen	
					String line = "";
					String text= "";
						while( (line=br.readLine()) != null)
							text += line ;
						zuVerschuesendeText.setText(text);//der Text in dem Panel1 hinzufuegen und der Txt.ordner schliessen
						br.close();
					}catch(IOException ioe)//wenn ein Fehler passiert ist ,wird dies in Option Pane gezeigt 
		             {	
						JOptionPane.showMessageDialog(null, ioe.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
					 }
			      }
		     }});
		  }
		   /**
		    * Die Methode buttonradioButton1 dient dazu,das Ereigniss zu organasieren,wenn auf dem radioButton1(hier verschluesseln) gedrueckt wird  
		    */
		   	   public void buttonradioButton1() {
		   			  radioButton1.addActionListener(new ActionListener() {

		   					public void actionPerformed(ActionEvent e) {
		   		    panel1.setBorder(BorderFactory.createTitledBorder("Zu verschluessende Text"));
		   		    panel2.setBorder(BorderFactory.createTitledBorder("verschluesselte Text")); 
		   		    verschluesseln.setText("verschluessen");
		   					}});
		   	   }
		   /**
		    * Die Mrthode buttonradioButton2 dient dazu,das Ereigniss zu organasieren,wenn auf dem radioButton2(hier entschluesseln) gedrueckt wird 
		    * panel1 wird einen neuen Titel bekommen "zu entschluessende Text"(panel1 hier hat der zu entschluesseneder Text)	
		    * panel2 wird einen neuen Titel bekommen "entschluesselte Text"(panel2 hat hier der entschluessete Text )  
		    */
		   	  public void buttonradioButton2() {
		   		  radioButton2.addActionListener(new ActionListener() {
		   		
		   			public void actionPerformed(ActionEvent e) {
		   			    panel1.setBorder(BorderFactory.createTitledBorder("Zu entschluessender Text"));
		   			    panel2.setBorder(BorderFactory.createTitledBorder("entschluesselter Text")); 
		   			    verschluesseln.setText("entschluessen");
		   				
		   			}});
		   	  }
}
