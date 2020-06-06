package cryptofun.maingui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import cryptofun.ciphergui.CaesarGui;
import cryptofun.ciphergui.MascGui;
import cryptofun.ciphergui.TransmatGui;
import cryptofun.ciphergui.VigenereGui;
import cryptofun.decryptiontools.Analyzor;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;


public class CryptoWorld {

	protected Shell shlCryptofun;
	private Text input;
	private Text output;


	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CryptoWorld window = new CryptoWorld();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCryptofun.open();
		shlCryptofun.layout();
		while (!shlCryptofun.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {

		
		shlCryptofun = new Shell();
		shlCryptofun.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shlCryptofun.setSize(951, 632);
		shlCryptofun.setText("Cryptofun");
		
		input = new Text(shlCryptofun,SWT.MULTI | SWT.BORDER);
		input.setBounds(10, 69, 397, 472);
		
		output = new Text(shlCryptofun, SWT.MULTI | SWT.BORDER);
		output.setBounds(526, 69, 397, 472);

		
		Label anzeige = new Label(shlCryptofun, SWT.NONE);
		anzeige.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		anzeige.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		anzeige.setBounds(10, 35, 345, 30);
		anzeige.setText("Eingabetext");
		
		Menu menu = new Menu(shlCryptofun, SWT.BAR);
		shlCryptofun.setMenuBar(menu);
		
		MenuItem mntmDatei = new MenuItem(menu, SWT.CASCADE);
		mntmDatei.setText("Datei");
		
		Menu datei = new Menu(mntmDatei);
		mntmDatei.setMenu(datei);
		
		MenuItem loadText = new MenuItem(datei, SWT.NONE);
		loadText.setText("Text öffnen");
		
		loadText.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent open) {
				String putput = load();
				if(putput != null) {
					input.setText(putput);
				} else {
					return;
				}
			}
		});
		
		MenuItem saveText = new MenuItem(datei, SWT.NONE);
		saveText.setText("Text speichern");
		
		saveText.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent save) {
				save(input);
			}
		});
		
		new MenuItem(datei, SWT.SEPARATOR);
		
		MenuItem loadEncrypted = new MenuItem(datei, SWT.NONE);
		loadEncrypted.setText("Verschlüsselten Text öffnen");
		
		MenuItem saveEncrypted = new MenuItem(datei, SWT.NONE);
		saveEncrypted.setText("Verschlüsselten Text speichern");
		
		MenuItem mntmEntschlsseln = new MenuItem(menu, SWT.CASCADE);
		mntmEntschlsseln.setText("Entschlüsseln");
		
		Menu menu_1 = new Menu(mntmEntschlsseln);
		mntmEntschlsseln.setMenu(menu_1);
		
		MenuItem Hanalyse = new MenuItem(menu_1, SWT.NONE);
		Hanalyse.setText("Häufigkeitsanalyse");
		
		Hanalyse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent hanalyse) {
				analyze();
			}
		});
		
		saveEncrypted.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent save) {
				save(output);
			}
		});
		
		loadEncrypted.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent openE) {
				String putput = "";
				putput = load();
				if(putput != "") {
					output.setText(putput);
				}
			}
		});
		
		ToolBar toolBar = new ToolBar(shlCryptofun, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		toolBar.setBounds(0, 0, 886, 28);
		
		ToolItem caesar = new ToolItem(toolBar, SWT.NONE);
		caesar.setText("Cäsar");
		caesar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent tCaesar) {
				CaesarGui caesarGui = new CaesarGui();
				String putput = caesarGui.prepare(input.getText());
				if(putput != null) {
					output.setText(putput);
				} else {
					return; 
				}
			}
		});
		
		ToolItem masc = new ToolItem(toolBar, SWT.NONE);
		masc.setText("Masc");
		
		masc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent tMasc) {
				MascGui mascGui = new MascGui();
				String putput = mascGui.prepare(input.getText());
				if(putput != null) {
					output.setText(putput);
				}
			}
		});
		
		ToolItem vigenere = new ToolItem(toolBar, SWT.NONE);
		vigenere.setText("Vigenere");
		vigenere.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent tVigenere) {
				VigenereGui vigenereGui = new VigenereGui();
				String putput = vigenereGui.prepare(input.getText());
				if(putput != null) {
					output.setText(putput);
				}
			}
		});
		
		
		ToolItem transmat = new ToolItem(toolBar, SWT.NONE);
		transmat.setText("Transmat");
		transmat.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent tTransmat) {
				TransmatGui transmatGui = new TransmatGui();
				String putput = transmatGui.prepare(input.getText());
				if(putput != null) {
					output.setText(putput);
				}
			}
		});
		
		Label label = new Label(shlCryptofun, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(442, 87, 54, 47);
		label.setText("=>");
		
		Label lblAusgabetext = new Label(shlCryptofun, SWT.NONE);
		lblAusgabetext.setText("Ausgabetext");
		lblAusgabetext.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblAusgabetext.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblAusgabetext.setBounds(526, 35, 345, 30);
		
		
			
	}

	/*
	 * save/load/open
	 * 
	 */
	
	
	private void analyze() {
		Analyzor test = new Analyzor(this.output.getText());
		test.open();
	}
	
	private String load() {
		String returnString;
		FileDialog open = new FileDialog(shlCryptofun, SWT.OPEN);
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
		FileDialog save = new FileDialog(shlCryptofun, SWT.SAVE);
		save.setFilterPath("D:\\Programme\\Git\\cryptoPlayground\\Cryptofun\\save");
		save.setFilterExtensions(new String[] {"*.txt", "*.*"});
		String savingData = putput.getText();
		if(savingData !=null) {
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
	}
	

	private void deleteInput() {
		this.input.setText("");
	}
	
}
