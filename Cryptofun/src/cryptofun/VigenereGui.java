package cryptofun;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class VigenereGui implements CipherGui{

	protected Shell vigenereShell;
	private String returnString;
	private Text key;

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	@Override
	public String prepare(String inputString) {
		Display display = Display.getDefault();
		createContents(inputString);
		vigenereShell.open();
		vigenereShell.layout();
		while (!vigenereShell.isDisposed()) {
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
		vigenereShell = new Shell();
		vigenereShell.setSize(450, 175);
		vigenereShell.setText("Vigenere Konfiguration");
		
		Label lblVerschlsselungswort = new Label(vigenereShell, SWT.NONE);
		lblVerschlsselungswort.setBounds(10, 29, 153, 20);
		lblVerschlsselungswort.setText("Verschlüsselungswort:");
		
		key = new Text(vigenereShell, SWT.BORDER);
		key.setBounds(169, 26, 225, 26);
		
		Button encrypt = new Button(vigenereShell, SWT.NONE);
		encrypt.setBounds(10, 73, 143, 30);
		encrypt.setText("Verschlüsseln");
		encrypt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Vigenere encryptVigenere = new Vigenere();
				setReturn(encryptVigenere.encrypt(inputString, key.getText()));
				vigenereShell.dispose();
			}
		});
		
		Button decrypt = new Button(vigenereShell, SWT.NONE);
		decrypt.setBounds(169, 73, 143, 30);
		decrypt.setText("Entschlüsseln");
		
	}
	
	private void setReturn(String returnString) {
		this.returnString = returnString;
	}
}
