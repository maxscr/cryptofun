package cryptofun.ciphergui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import cryptofun.ciphers.Caesar;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

public class CaesarGui implements CipherGui{

	protected Shell CaesarShell;
	private String returnString;
	private Text key;
	private String[] settings; 
	
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	@Override
	public String prepare(String inputString) {
		Display display = Display.getDefault();
		createContents(inputString);
		CaesarShell.open();
		CaesarShell.layout();
		settings = GuiHelpers.load(new File("settings\\caesar.txt"));
		key.setText(settings[0]);
		while (!CaesarShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return returnString;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(String inputString) {
		CaesarShell = new Shell();
		CaesarShell.setSize(300, 150);
		CaesarShell.setText("Caesar Konfiguration");
		Button encrypt = new Button(CaesarShell, SWT.NONE);
		
		encrypt.setBounds(10, 60, 120, 30);
		encrypt.setText("Verschlüsseln");
		encrypt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(testInput()) {
					Caesar encryptCaesar = new Caesar();
					setReturn(encryptCaesar.encrypt(inputString, key.getText()));
					String[] savingData = new String[1];
					savingData[0] = key.getText();
					GuiHelpers.save(savingData, new File("settings\\caesar.txt"));
					CaesarShell.dispose();
				}
			}
		});
		key = new Text(CaesarShell, SWT.BORDER);
		key.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		key.setBounds(152, 13, 120, 26);
		
		Label lblVerschiebung = new Label(CaesarShell, SWT.NONE);
		lblVerschiebung.setBounds(10, 16, 108, 20);
		lblVerschiebung.setText("Verschiebung:");
		
		Button decrypt = new Button(CaesarShell, SWT.NONE);
		decrypt.setBounds(152, 60, 120, 30);
		decrypt.setText("Entschlüsseln");
		decrypt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(testInput()) {
					Caesar decryptCaesar = new Caesar();
					setReturn(decryptCaesar.decrypt(inputString, key.getText()));
					String[] savingData = new String[1];
					savingData[0] = key.getText();
					GuiHelpers.save(savingData, new File("settings\\caesar.txt"));
					CaesarShell.dispose();
				}
			}
		});
	}
	
	private void setReturn(String returnString) {
		this.returnString = returnString;
	}
	
	@Override
	public boolean testInput() {
		Integer caesarinteger = 0;
		try {
			caesarinteger = Integer.parseInt(key.getText());
		} catch(NumberFormatException e) {
			this.key.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			MessageDialog.openError(CaesarShell, "Das war nix!", "Das war nix. Gib eine natürliche Zahl zwischen 0 und 26 ein!");
			return false;
		}
		if(caesarinteger < 0 || caesarinteger > 26) {
			this.key.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			MessageDialog.openError(CaesarShell, "Das war nix!", "Gib eine Zahl zwischen 0 und 26 ein!");
			return false;
		}
		return true;
	}

}

	