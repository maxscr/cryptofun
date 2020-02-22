package cryptofun;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.SelectionAdapterFactory;
import java.io.File;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Composite;

public class CryptoWorld {

	protected Shell shlCryptofun;
	private Text input;
	Encrypter encrypter = new Encrypter();
	private Text output;
	private Text userinput;
	private String encryption;
	private Label anzeige;
	private ToolItem caesar;
	private ToolItem vigenere;
	private Button encryptor;
	
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
		this.encryption = "caesar";
		
		shlCryptofun = new Shell();
		shlCryptofun.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shlCryptofun.setSize(951, 632);
		shlCryptofun.setText("Cryptofun");
		
		input = new Text(shlCryptofun,SWT.MULTI | SWT.BORDER);
		input.setBounds(10, 69, 397, 472);
		
		output = new Text(shlCryptofun, SWT.MULTI | SWT.BORDER);
		output.setBounds(526, 69, 397, 472);
		
		userinput = new Text(shlCryptofun, SWT.BORDER);
		userinput.setBounds(361, 34, 236, 26);

		
		Label anzeige = new Label(shlCryptofun, SWT.NONE);
		anzeige.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		anzeige.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		anzeige.setBounds(10, 35, 345, 30);
		anzeige.setText("Wählen Sie eine Verschlüsselungsmethode: ");
		
		Menu menu = new Menu(shlCryptofun, SWT.BAR);
		shlCryptofun.setMenuBar(menu);
		
		MenuItem mntmDatei = new MenuItem(menu, SWT.CASCADE);
		mntmDatei.setText("Datei");
		
		Menu datei = new Menu(mntmDatei);
		mntmDatei.setMenu(datei);
		
		MenuItem openText = new MenuItem(datei, SWT.NONE);
		openText.setText("Text öffnen");
		
		openText.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String inputString = load();
			}
		});
		
		new MenuItem(datei, SWT.SEPARATOR);
		
		MenuItem saveText = new MenuItem(datei, SWT.NONE);
		saveText.setText("Text speichern");
		
		new MenuItem(datei, SWT.SEPARATOR);
		
		MenuItem loadEncrypted = new MenuItem(datei, SWT.NONE);
		loadEncrypted.setText("Verschlüsselten Text öffnen");
		
		new MenuItem(datei, SWT.SEPARATOR);
		
		MenuItem saveEncrypted = new MenuItem(datei, SWT.NONE);
		saveEncrypted.setText("Verschlüsselten Text speichern");
		
		
		
		Button encryptor = new Button(shlCryptofun, SWT.NONE);
		encryptor.setBounds(614, 33, 110, 30);
		encryptor.setText("Verschlüsseln");
		
		encryptor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent encrypt) {
				toEncrypt();
			}
		});
		
		ToolBar toolBar = new ToolBar(shlCryptofun, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		toolBar.setBounds(0, 0, 118, 28);
		
		ToolItem caesar = new ToolItem(toolBar, SWT.NONE);
		caesar.setText("Cäsar");
		caesar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent tCaesar) {
				anzeige.setText("Geben Sie eine Zahl zwischen 0 und 26 ein: ");
				encryptor.setText("Cäsar");
				setCaesar();
			}
		});
		
		ToolItem vigenere = new ToolItem(toolBar, SWT.NONE);
		vigenere.setText("Vigenere");
		
		vigenere.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent tVigenere) {
				anzeige.setText("Geben Sie den Schlüssel ein: ");
				encryptor.setText("Vigenere");
				setVigenere();
			}
		});
		
		Label label = new Label(shlCryptofun, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(442, 87, 54, 47);
		label.setText("=>");
		
		
			
	}
	
	/*
	 * 
	 *  Handling methods
	 */
	private void setVigenere() {
		this.encryption = "vigenere";
	}
	
	private void setCaesar() {
		this.encryption = "caesar";
	}
	
	private void toEncrypt() {
		String outputString;
		switch(this.encryption) {
		case "caesar":
			 outputString = toCaesar();
			output.setText(outputString);
			break;
		case "vigenere":
			toVigenere();
			outputString = toVigenere();
			output.setText(outputString);
			break;
		default:
			output.setText("Keine Methode gewählt!");
			break;
		}
		
	}
	
	/*
	 * Encryption methods
	 * 
	 */
	
	private String toCaesar() {
		String inputString = input.getText();
		Integer caesarinteger = 0;
		try {
			caesarinteger = Integer.parseInt(userinput.getText());
		} catch(NumberFormatException e) {
			this.userinput.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			MessageDialog.openError(shlCryptofun, "Das war nix!", "Das war nix. Gib eine natürliche Zahl ein!");
			return "";
		}
		if(caesarinteger < 0 || caesarinteger > 26) {
			this.userinput.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			MessageDialog.openError(shlCryptofun, "Das war nix!", "Gib eine Zahl zwischen 0 und 26 ein!");
			return "";
		}
		if(inputString == "") {
			MessageDialog.openError(shlCryptofun, "Wirklich?", "Du musst schon einen Text eingeben...");
			return "";
		}
		return encrypter.toCaesar(inputString, caesarinteger);
	}
	
	private String toVigenere() {
		String inputString = input.getText();
		String inputkey = userinput.getText();
		String returnString;
		return returnString = encrypter.toVigenere(inputString, inputkey);
		
	}
	
	/*
	 * save/load
	 * 
	 */
	
	private String load() {
		String returnString = "";
		
		
		return returnString;
	}
}
