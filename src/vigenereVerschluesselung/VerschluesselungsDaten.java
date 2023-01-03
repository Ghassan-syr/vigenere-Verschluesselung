package vigenereVerschluesselung;

import java.util.Random;

public class VerschluesselungsDaten {
	/**
	 * Die Klasse  VerschluesselungsDaten enthaelt die Methoden verschluesslung,schluesselwortgenerator und entschluesslung
	 * Die Klasse dient zur Verschluesselung/Entschluessekung des Textes und eine geignete schluesselwort zu erstellen
	 */
	public VerschluesselungsDaten() {}
	/**
	 * Die Methode verschluesslung dient zur Verschluesselung des Textes,der der Nutzer eingegeben hat.Am Anfagng wird durch die Umwandlung den Text buchstablich gelesen.
	 * Dann wird das schluesselwort entsprechend dem Textlaenge wiederholt(da der Variable i bis ende des textes läuft) 
	 * Danach wird der Text sowie das Schluesselwort in Asciicode umgewandelt,damit nur Buchstaben erlaubt werden und entschprechend vigener Verschluesselung den Text verschluesselt wird
	 * @param text ist der zu verschluessener Text.Die Methode bekommt den Parameter text aus der Klasse VerschluesselungsGui
	 * @param schluesselwort ist die Buchstabenfolge,die in der Methode schluesselwortgenerator erstellt wird,Die Methode bekommt den Parameter schluesselwort aus der Klasse VerschluesselungGui
	 * @return verschuesselteText ist der Text,der durch die Methode Verschluesslung erstellt wird
	 * @throws Exception wenn es etwas anderes als Buchstaben,space,Enter Knopf eingegeben wird
	 */

	public static  String verschluesslung(String text , String schluesselwort)throws Exception {
		String verschuesselteText="";
		for(int i=0 ; i<text.length() ; i++) {
			char textChar = text.charAt(i);
			//wenn das schlüssel zu ende ist beginnt das schlüssel wort von Anfang wieder
			char schluesselwortChar = schluesselwort.charAt(i%schluesselwort.length());
			int textAscii =(int) textChar;
			int schluesselwortAscii = (int) schluesselwortChar;
			if(textAscii >=65 && textAscii <=90) {
				verschuesselteText +=Character.toString( (char) ( ( (textAscii%65) +( (schluesselwortAscii-32)%65) ) %26) +65);
			}else if(textAscii >=97 && textAscii <=122) {
				verschuesselteText +=Character.toString((char) (((textAscii%97) +((schluesselwortAscii)%97))%26)+97);
			}else if(textAscii == 32 || textAscii == '\n') {
				verschuesselteText +=" ";   
				  
			}else {
				throw new Exception ("Bitte nur Buchsstaben eingeben! ");
			}	
		}
		return verschuesselteText;
	}
	/**
	 * Die Methode schluesselwortgenerator dient zur Erstellung das Schluesselwort
	 * Das schluesselwort ist geeignet fuer die Textlaenge
	 * @param text ist der Text,der der Nutzer eingegeben hat und wird laut dem die Schluesselwortlange erstellt
	 * @return ist das schluesselwort das durch die Methode schluesselwortgenerator erstellt wird
	 */
	public static String schluesselwortgenerator(String text) {
		Random zufall = new Random();
		int schlueselwortlaenge;
		String schluesselwort=""; 
		if(text.length()<=2) {
			schlueselwortlaenge = 2;
		}else if (text.length()>2 && text.length()<10) {
			schlueselwortlaenge = (int) text.length()/2;
		}else {
			 schlueselwortlaenge = (int)(0.3*text.length()); 
			 
		}
		for(int i = 0 ; i < schlueselwortlaenge ; i++) {
			schluesselwort += Character.toString((char)zufall.nextInt(26)+97);
		} 
		return schluesselwort ;
	}
	/**
	 * laut der Name von der Methode dient die Methode entschluesslung zur Entschluesselung des Textes,der der Nutzer eingegeben hat 
	 * Die Methode macht das Gegenteil der rechnerichen Operationen von der Methode verschluesslung
	 * @param text ist der Text,der der Nutzer eingegeben hat 
	 * @param schluesselwort ist das schluesselwort und laut dem wird den Text enschlueseelt
	 * @return verschuesselteText ist der entschluessente Text,der durch die Methode entschluesslung erstellt wird
	 * @throws Exception wenn der eingegebene Text anderes als Buchstaben,space,Enter Knopf enthaelt 
	 */
	public static  String entschluesslung(String text , String schluesselwort)throws Exception {
		String verschuesselteText="";
		for(int i=0 ; i<text.length() ; i++) {
			char textChar = text.charAt(i);
			char schluesselwortChar = schluesselwort.charAt(i%schluesselwort.length());
			int textAscii =(int) textChar;
			int schluesselwortAscii = (int) schluesselwortChar;
			if(textAscii >=65 && textAscii <=90) {
				int tmp = ((textAscii%65) -((schluesselwortAscii-32)%65));
				if(tmp<0)
					tmp = tmp+26;
				verschuesselteText +=Character.toString((char) (tmp%26)+65);
			}else if(textAscii >=97 && textAscii <=122) {
				int tmp = ((textAscii%97) -((schluesselwortAscii)%97));
				if(tmp<0)
					tmp = tmp+26;
				verschuesselteText +=Character.toString((char) (tmp%26)+97);
			}else if(textAscii == 32 || textAscii == '\n') {
				
				verschuesselteText +=" ";
				
			}else {
				throw new Exception ("Bitte nur Buchsstaben eingeben! ");
			}	
		}
		return verschuesselteText;
	}
}
