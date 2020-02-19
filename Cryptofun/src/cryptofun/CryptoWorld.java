package cryptofun;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.SelectionAdapterFactory;
import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CryptoWorld {

	protected Shell shlCryptofun;
	private Text input;
	Encrypter encrypter = new Encrypter();
	private Text output;
	private Text caesarint;
	
	/**
	 * Launch the application.
	 * @param args
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
		shlCryptofun.setSize(951, 560);
		shlCryptofun.setText("Cryptofun");
		
		input = new Text(shlCryptofun,SWT.MULTI | SWT.BORDER);
		input.setBounds(10, 31, 397, 472);
		
		output = new Text(shlCryptofun, SWT.MULTI | SWT.BORDER);
		output.setBounds(526, 31, 397, 472);
		
		caesarint = new Text(shlCryptofun, SWT.BORDER);
		caesarint.setBounds(480, 46, 33, 26);
		
		Label lblNewLabel = new Label(shlCryptofun, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setBounds(10, 0, 118, 30);
		lblNewLabel.setText("Textinput");
		
		Button btnCsar = new Button(shlCryptofun, SWT.NONE);
		btnCsar.setBounds(418, 44, 56, 30);
		btnCsar.setText("Cäsar");
		
		btnCsar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent caesar) {
				String out = toCaesar();
				output.setText(out);
		}
		});
		
		
		Label lblOutputtext = new Label(shlCryptofun, SWT.NONE);
		lblOutputtext.setText("Outputtext");
		lblOutputtext.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblOutputtext.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblOutputtext.setBounds(526, 0, 118, 30);
		
		

	}
	
	public String toCaesar() {
		String inputString = input.getText();
		Integer caesarinteger = Integer.parseInt(caesarint.getText());
		return encrypter.toCaesar(inputString, caesarinteger);
	}
	
}
