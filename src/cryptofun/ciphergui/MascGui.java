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

import cryptofun.ciphers.Masc;

import org.eclipse.swt.widgets.Button;

public class MascGui implements CipherGui{

	protected Shell MascShell;
	private Text key;
	private String returnString;
	private String[] settings; 
	

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public String prepare(String inputString) {
		Display display = Display.getDefault();
		createContents(inputString);
		MascShell.open();
		MascShell.layout();
		settings = GuiHelpers.load(new File("settings\\masc.txt"));
		key.setText(settings[0]);
		while (!MascShell.isDisposed()) {
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
		MascShell = new Shell();
		MascShell.setSize(370, 175);
		MascShell.setText("Masc Konfiguration");
		
		
		Label lblVerschlsselungswort = new Label(MascShell, SWT.NONE);
		lblVerschlsselungswort.setBounds(10, 20, 152, 20);
		lblVerschlsselungswort.setText("Verschlüsselungswort:");
		
		key = new Text(MascShell, SWT.BORDER);
		key.setBounds(180, 17, 152, 26);
		
		Button encrypt = new Button(MascShell, SWT.NONE);
		encrypt.setBounds(10, 67, 152, 30);
		encrypt.setText("Verschlüsseln");
		encrypt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(testInput()) {
					Masc encryptMasc = new Masc();
					setReturn(encryptMasc.encrypt(inputString, key.getText()));
					String[] savingData = new String[1];
					savingData[0] = key.getText();
					GuiHelpers.save(savingData, new File("settings\\masc.txt"));
					MascShell.dispose();
				}
			}
		});
		
		Button decrypt = new Button(MascShell, SWT.NONE);
		decrypt.setBounds(179, 67, 152, 30);
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
			MessageDialog.openError(MascShell, "Das war nix!", "Gib einen gültigen Schlüssel an!");
			return false;
		}
		return true;
	}

}
