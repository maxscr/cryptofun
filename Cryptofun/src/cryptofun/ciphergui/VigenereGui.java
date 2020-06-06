package cryptofun.ciphergui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.File;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import cryptofun.ciphers.Vigenere;

import org.eclipse.swt.widgets.Button;

public class VigenereGui implements CipherGui{

	protected Shell VigenereShell;
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
		VigenereShell.open();
		VigenereShell.layout();
		settings = GuiHelpers.load(new File("settings\\vigenere.txt"));
		key.setText(settings[0]);
		while (!VigenereShell.isDisposed()) {
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
		VigenereShell = new Shell();
		VigenereShell.setSize(450, 175);
		VigenereShell.setText("Vigenere Konfiguration");
		
		Label lblVerschlsselungswort = new Label(VigenereShell, SWT.NONE);
		lblVerschlsselungswort.setBounds(10, 29, 153, 20);
		lblVerschlsselungswort.setText("Verschlüsselungswort:");
		
		key = new Text(VigenereShell, SWT.BORDER);
		key.setBounds(169, 26, 225, 26);
		
		Button encrypt = new Button(VigenereShell, SWT.NONE);
		encrypt.setBounds(10, 73, 143, 30);
		encrypt.setText("Verschlüsseln");
		encrypt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Vigenere encryptVigenere = new Vigenere();
				setReturn(encryptVigenere.encrypt(inputString, key.getText()));
				String[] savingData = new String[1];
				savingData[0] = key.getText();
				GuiHelpers.save(savingData, new File("settings\\vigenere.txt"));
				VigenereShell.dispose();
			}
		});
		
		Button decrypt = new Button(VigenereShell, SWT.NONE);
		decrypt.setBounds(169, 73, 143, 30);
		decrypt.setText("Entschlüsseln");
		
	}
	
	private void setReturn(String returnString) {
		this.returnString = returnString;
	}

	@Override
	public boolean testInput() {
		String inputkey = key.getText();
		if(inputkey == "") {
			this.key.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			MessageDialog.openError(VigenereShell, "Das war nix!", "Gib einen gültigen Schlüssel an!");
			return false;
		}
		return true;
	}

}
