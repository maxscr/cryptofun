package cryptofun;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

public class CaesarGui implements CipherGui{

	protected Shell CaesarShell;
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
		CaesarShell.open();
		CaesarShell.layout();
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
				Caesar encryptCaesar = new Caesar();
				setReturn(encryptCaesar.encrypt(inputString, key.getText()));
				CaesarShell.dispose();
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
	}
	
	private void setReturn(String returnString) {
		this.returnString = returnString;
	}
}

	