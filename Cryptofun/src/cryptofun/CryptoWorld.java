package cryptofun;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class CryptoWorld {

	protected Shell shlCryptofun;
	private Text input;
	Encrypter encrypter = new Encrypter();
	private Text output;
	
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
		shlCryptofun.setText("Cryptofun!");
		
		input = new Text(shlCryptofun, SWT.BORDER);
		input.setBounds(0, 31, 397, 472);
		
		Label lblNewLabel = new Label(shlCryptofun, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setBounds(10, 0, 118, 30);
		lblNewLabel.setText("Textinput");
		
		Button btnCsar = new Button(shlCryptofun, SWT.NONE);
		btnCsar.setBounds(416, 39, 90, 30);
		btnCsar.setText("Cäsar");
		
		
		output = new Text(shlCryptofun, SWT.BORDER);
		output.setBounds(526, 31, 397, 472);
		
		Label lblOutputtext = new Label(shlCryptofun, SWT.NONE);
		lblOutputtext.setText("Outputtext");
		lblOutputtext.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblOutputtext.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblOutputtext.setBounds(526, 0, 118, 30);

	}

	
}
