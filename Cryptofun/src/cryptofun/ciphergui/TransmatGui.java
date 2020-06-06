package cryptofun.ciphergui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.File;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import cryptofun.ciphers.Transmat;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TransmatGui implements CipherGui{

	protected Shell TransmatShell;
	private Text key1;
	private Text key2;
	private String returnString; 
	private String[] settings; 
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public String prepare(String inputString) {
		Display display = Display.getDefault();
		createContents(inputString);
		TransmatShell.open();
		TransmatShell.layout();
		settings = GuiHelpers.load(new File("settings\\transmat.txt"));
		key1.setText(settings[0]);
		key2.setText(settings[1]);
		while (!TransmatShell.isDisposed()) {
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
		TransmatShell = new Shell();
		TransmatShell.setSize(349, 182);
		TransmatShell.setText("Transmat Konfiguration");
		
		Label lblZahlzeilen = new Label(TransmatShell, SWT.NONE);
		lblZahlzeilen.setBounds(10, 13, 111, 30);
		lblZahlzeilen.setText("1. Zahl (Zeilen)");
		
		Label lblZahlspalten = new Label(TransmatShell, SWT.NONE);
		lblZahlspalten.setBounds(10, 46, 111, 20);
		lblZahlspalten.setText("2. Zahl (Spalten)");

		
		
		key1 = new Text(TransmatShell, SWT.BORDER);
		key1.setBounds(171, 10, 138, 26);
		
		key2 = new Text(TransmatShell, SWT.BORDER);
		key2.setBounds(171, 43, 139, 26);
		
		Button encrypt = new Button(TransmatShell, SWT.NONE);
		encrypt.setBounds(10, 85, 139, 30);
		encrypt.setText("Verschlüsseln");
		encrypt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(testInput()) {
					Transmat encryptTransmat = new Transmat();
					String fullKey = key1.getText() + ":" + key2.getText();
					setReturn(encryptTransmat.encrypt(inputString, fullKey));
					String[] savingData = new String[2];
					savingData[0] = key1.getText();
					savingData[1] = key2.getText();
					GuiHelpers.save(savingData, new File("settings\\caesar.txt"));
					TransmatShell.dispose();
				}
			}
		});
		
		Button decrypt = new Button(TransmatShell, SWT.NONE);
		decrypt.setBounds(171, 85, 138, 30);
		decrypt.setText("Entschlüsseln");

	}
	private void setReturn(String returnString) {
		this.returnString = returnString; 
	}

	@Override
	public boolean testInput() {
		Integer key1Integer = 0;
		Integer key2Integer = 0; 
		try {
			key1Integer = Integer.parseInt(key1.getText());
		} catch(NumberFormatException e) {
			this.key1.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			MessageDialog.openError(TransmatShell, "Das war nix!", "Das war nix. Gib eine positive natürliche Zahl ein!");
			return false;
		}
		try {
			key2Integer = Integer.parseInt(key2.getText());
		} catch(NumberFormatException e) {
			this.key2.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			MessageDialog.openError(TransmatShell, "Das war nix!", "Das war nix. Gib eine positive natürliche Zahl ein!");
			return false;
		}
		if(key1Integer < 0) {
			this.key1.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			MessageDialog.openError(TransmatShell, "Das war nix!", "Das war nix. Gib eine positive natürliche Zahl ein!");
			return false;
		}
		if(key2Integer < 0) {
			this.key2.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			MessageDialog.openError(TransmatShell, "Das war nix!", "Das war nix. Gib eine positive natürliche Zahl ein!");
			return false;
		}
		return true;
	}

}
