package br.edu.utfpr.javaii;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class UsingIO {

	private static final String MY_FILE = "f:" + File.separator + "file-using-io.txt";

	public UsingIO() {
		try {
			writeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		readFile();
	}

	private void writeFile() throws IOException {
		// Utilizando OutputStream
		/* 
			final File file = new File(MY_FILE);
			boolean fileIsCreated = false;

			if (!file.exists()) {
				fileIsCreated = file.createNewFile();
			}

			if (fileIsCreated || file.exists()) {
				final OutputStream output = new FileOutputStream(file);
				output.write("Olá mundo!".getBytes("UTF-8"));
				output.close();

				System.out.println("Dados gravados no arquivo.");
			}
		*/
		
		try (final FileReader reader = new FileReader(MY_FILE)) {
			int content = reader.read();

			while (content != -1) {
				System.out.println((char) content);
				content = reader.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	private void readFile() {
		// 1 byte = 8 bits
		// 1 char UTF-8 = 1 byte
		// 1 char UTF-16 = 2 bytes

		// Ate o Java 1.7
		/* 
			InputStream input = null; 
			
			try {
				input = new FileInputStream(MY_FILE);

				int content;

				while ((content = input.read())!= -1) {
					System.out.println((char) content);
					
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
 
		*/

		try (final InputStream input = new FileInputStream(MY_FILE)) {
			int content;

			while ((content = input.read()) != -1) {
				System.out.println((char) content);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new UsingIO();
	}
}
