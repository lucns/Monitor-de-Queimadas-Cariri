package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import utils.Log;

public class MainAction {
	
	public void makeAction() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Log.d("Lucas", "start");
				File desktop = FileSystemView.getFileSystemView().getHomeDirectory();
				File file = new File(desktop, "focos-queimadas-brasil-2011-2021.csv");
				if (!file.exists()) {
					Log.d("lucas", "File not exists!");
					return;
				}
				File csv = new File(desktop, "FiltroQueimadasEmJava/ChapadaAraripeQueimadas 2011-2021.csv");
				File folder = csv.getParentFile();
				if (!folder.exists()) folder.mkdir();
				
				String state = "CEARA";
				String[] cities = new String[]{
					"UMARI", "BAIXIO", "IPAUMIRIM", "LAVRAS DA MANGABEIRA", "GRANJEIRO", "BARRO", 
					"AURORA", "MAURITI", "MILAGRES", "ABAIARA", "BREJO SANTO", "JATI", "PENA FORTE", 
					"PORTEIRAS", "TARRAFAS", "ALTANEIRA", "ASSARE", "POTENGI", "ANTONIA DO NORTE", 
					"ARARIPE", "SALITRE", "CAMPO SALES", "BARBALHA", "CARIRIAÇU", "CARIRIACU", "CRATO", 
					"FARIAS BRITO", "JARDIM", "JUAZEIRO DO NORTE", "MISSAO VELHA", "NOVA OLINDA", "SANTANA DO CARIRI"
				};

				long startTime = System.currentTimeMillis();
				int count = 0;
				BufferedWriter writer = null;
				BufferedReader reader = null;
		        try {
					if (!csv.exists()) csv.createNewFile();
					writer = new BufferedWriter(new FileWriter(csv));					
			        reader = new BufferedReader(new FileReader(file));
		            String line = reader.readLine();
					writer.write(line);
					writer.flush();
		            while ((line = reader.readLine()) != null) {
		            	for (String city : cities) {
		            		if (line.contains(state) && line.contains(city)) {
		            			count++;
		            			Log.d("Lucas", "lines: " + count);
		            			writer.write("\n" + line);
								writer.flush();
								break;
		            		}
		            	}
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
		            try {
		                if (writer != null) writer.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		            try {
		                if (reader != null) reader.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }

		        long seconds = (System.currentTimeMillis() - startTime) / 1000;
				Log.d("Lucas", count + " linhas em " + seconds + " segundos.");
			}
			
		}).start();
	}
}
