package cryptofun;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TransmatGui implements CipherGui{

	protected Shell TransmatShell;
	private Text key1;
	private Text key2;
	private String returnString; 
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public String prepare(String inputString) {
		Display display = Display.getDefault();
		createContents(inputString);
		TransmatShell.open();
		TransmatShell.layout();
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
				Transmat encryptTransmat = new Transmat();
				String fullKey = key1.getText() + ":" + key2.getText();
				setReturn(encryptTransmat.encrypt(inputString, fullKey));
				TransmatShell.dispose();
			}
		});
		
		Button decrypt = new Button(TransmatShell, SWT.NONE);
		decrypt.setBounds(171, 85, 138, 30);
		decrypt.setText("Entschlüsseln");

	}
	private void setReturn(String returnString) {
		this.returnString = returnString; 
	}
}
