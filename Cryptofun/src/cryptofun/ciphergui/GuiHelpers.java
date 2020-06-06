package cryptofun.ciphergui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GuiHelpers {

	public static String[] load(File settingFile) {
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(settingFile));
			int anz = 0;
			while(br1.readLine()!= null) {
				anz++;
			}
			br1.close();
			String[] settings = new String[anz];
			BufferedReader br = new BufferedReader(new FileReader(settingFile));
			for(int i = 0; i < anz; i++) {
				settings[i] = br.readLine();
			}
			br.close();
			return settings;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
	public static void save(String[] savingData, File settingFile) {
		try {
			FileWriter fw = new FileWriter(settingFile);
			for(int i = 0; i < savingData.length; i++) {
				fw.write(savingData[i] + "\n");
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
