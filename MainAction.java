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
	
	/*
	private class Data {
		double r1, r2, tension;
	}
	
	public void makeAction() {
		double[] values = new double[] { // 8505 and 0603
			1000, 1800, 3000, 4700, 5100, 7500
		};
		double[] values2 = new double[] { // 8505 and 0603
			//1000, 1200, 1500, 2000, 2200, 2700, 3000, 3300, 3900, 4700
			1000, 1800, 3000, 4700, 5100, 7500
		};
		
		List<Data> list = new ArrayList<>();
		double vIn = 4.2d;
		double vMin = 2.1d;
		double vMax = 3.4d;
		
		for (int a = 0; a < values.length; a++) {
			double r1 = values[a];
			for (int b = 0; b < values2.length; b++) {
				double r2 = values[b];
				double tension = vIn / (1 + (r1 / r2));
				//Log.d("Lucas", "r1:" + r1 + " r2:" + r2 + " tension:" + tension);
				
				if (tension > vMin && tension < vMax) {
					Data data = new Data();
					data.r1 = r1;
					data.r2 = r2;
					data.tension = tension;
					list.add(data);
				}
				
			}
		}
		
		Log.d("Lucas", "results:" + list.size());
		for (Data data : list) {
			Log.d("Lucas", "R1:" + data.r1 + " R2:" + data.r2 + " tension:" + data.tension);
		}
	}
}
	*/
