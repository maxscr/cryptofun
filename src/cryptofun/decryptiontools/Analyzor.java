package cryptofun.decryptiontools;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class Analyzor {

	protected Shell shlHufigkeitsanalyse;
	protected String encryptedText;
	Decrypter decrypter = new Decrypter();
	private Text show;
	private Text count;
	
	
	public Analyzor(String encryptedText) {
		super();
		this.encryptedText = encryptedText;
	}
	
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlHufigkeitsanalyse.open();
		shlHufigkeitsanalyse.layout();
		while (!shlHufigkeitsanalyse.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlHufigkeitsanalyse = new Shell();
		shlHufigkeitsanalyse.setSize(419, 750);
		shlHufigkeitsanalyse.setText("Häufigkeitsanalyse");
		
		Label lblTest = new Label(shlHufigkeitsanalyse, SWT.NONE);
		lblTest.setBounds(10, 25, 334, 20);
		lblTest.setText("Auswahl der Optionen:");
		
		show = new Text(shlHufigkeitsanalyse, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		show.setBounds(10, 103, 376, 565);
		show.setText(this.encryptedText);
		
		count = new Text(shlHufigkeitsanalyse, SWT.BORDER);
		count.setBounds(227, 51, 78, 30);
		
		Button letters = new Button(shlHufigkeitsanalyse, SWT.NONE);
		letters.setBounds(10, 51, 90, 30);
		letters.setText("Buchstaben");
		
		letters.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent letter) {
				HashMap<String, Integer> results = new HashMap<String, Integer>();
				String input = makeNice(show.getText());
				results = decrypter.findNgrams(input, 1);
				String toPrint = "";
				Collection<Integer> values = results.values();
				Iterator<Integer> valIter = values.iterator();
				Set<String> keys = results.keySet();
				Iterator<String> keyIter = keys.iterator();
				while(keyIter.hasNext()) {
					toPrint = toPrint + keyIter.next() + ": " + valIter.next() + "\n";
				}
				show.setText(toPrint);
			}
		});
		
		Button ngram = new Button(shlHufigkeitsanalyse, SWT.NONE);
		ngram.setBounds(118, 51, 90, 30);
		ngram.setText("\"Silben\"");
		
		ngram.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent ngram) {
				HashMap<String, Integer> results = new HashMap<String, Integer>();
				int n = Integer.parseInt(count.getText());
				String input = makeNice(show.getText());
				results = decrypter.findNgrams(input, n);
				String toPrint = "";
				Collection<Integer> values = results.values();
				Iterator<Integer> valIter = values.iterator();
				Set<String> keys = results.keySet();
				Iterator<String> keyIter = keys.iterator();
				while(keyIter.hasNext()&& valIter.hasNext()) {
					toPrint = toPrint + keyIter.next() + ": " + valIter.next() + "\n";
				}
				show.setText(toPrint);
			}
			
		});
		
		Menu menu = new Menu(shlHufigkeitsanalyse, SWT.BAR);
		shlHufigkeitsanalyse.setMenuBar(menu);
		
		MenuItem datei = new MenuItem(menu, SWT.CASCADE);
		datei.setText("Datei");
		
		Menu menu_1 = new Menu(datei);
		datei.setMenu(menu_1);
		
		MenuItem load = new MenuItem(menu_1, SWT.NONE);
		load.setText("Laden");
		
		load.addSelectionListener(new SelectionAdapter() {
			@Override 
			public void widgetSelected(SelectionEvent load) {
				show.setText(load());
			}
		});
		
		MenuItem save = new MenuItem(menu_1, SWT.NONE);
		save.setText("Speichern");
		
		save.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent save) {
				save(show);
			}
		});
		
	}
	
	
	private String load() {
		String returnString;
		FileDialog open = new FileDialog(shlHufigkeitsanalyse, SWT.OPEN);
		open.setFilterPath("D:\\Programme\\Git\\cryptoPlayground\\Cryptofun\\save");
		try {
			 String fileName = open.open();
			 deleteInput();
			 BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))  ;
			 StringBuffer stringBuffer = new StringBuffer();
			 String line = null;
			 while((line =bufferedReader.readLine())!=null){
				 stringBuffer.append(line).append("\n");
			 }
			 returnString = stringBuffer.toString();
			 bufferedReader.close();
			 return returnString;
		} catch(IOException e) {
			
			return "";
		}
		
	}
	
	private void save(Text putput) {
		FileDialog save = new FileDialog(shlHufigkeitsanalyse, SWT.SAVE);
		save.setFilterPath("D:\\Programme\\Git\\cryptoPlayground\\Cryptofun\\save");
		save.setFilterExtensions(new String[] {"*.txt", "*.*"});
		String savingData = putput.getText();
		String fileName = save.open();
		if(fileName != null) {
		        try {
		            FileWriter fw = new FileWriter(fileName);
		            fw.write(savingData.toString());
		            fw.flush();
		            fw.close();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		   }   
	}
	
	private void deleteInput() {
		show.setText("");
		count.setText("");
	}
	
	private static String makeNice( String inputString) {
		inputString = umlautGone(inputString);
		inputString = inputString.toUpperCase();
		inputString = inputString.replaceAll("[^a-zA-Z]", "");
		return inputString;
	}
	
	private static String umlautGone(String inputString) {
		inputString = inputString.replace("ä", "ae");
		inputString = inputString.replace("ö", "oe");
		inputString = inputString.replace("ü", "ue");
		return inputString;
	}
	
}
