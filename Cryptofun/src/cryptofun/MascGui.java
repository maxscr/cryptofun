package cryptofun;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class MascGui implements CipherGui{

	protected Shell MascShell;
	private Text key;
	private String returnString;


	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public String prepare(String inputString) {
		Display display = Display.getDefault();
		createContents(inputString);
		MascShell.open();
		MascShell.layout();
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
				Masc encryptMasc = new Masc();
				setReturn(encryptMasc.encrypt(inputString, key.getText()));
				MascShell.dispose();
			}
		});
		
		Button decrypt = new Button(MascShell, SWT.NONE);
		decrypt.setBounds(179, 67, 152, 30);
		decrypt.setText("Entschlüsseln");

	}
	
	private void setReturn(String returnString) {
		this.returnString = returnString; 
	}
}
