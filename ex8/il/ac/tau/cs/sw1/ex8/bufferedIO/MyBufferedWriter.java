package il.ac.tau.cs.sw1.ex8.bufferedIO;

import java.io.FileWriter;
import java.io.IOException;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class MyBufferedWriter implements IBufferedWriter {

	private int bufferSize;
	private int currentLoc;
	private char[] buffer;
	private FileWriter fWriter;

	public MyBufferedWriter(FileWriter fWriter, int bufferSize) {
		this.fWriter = fWriter;
		this.bufferSize = bufferSize;
		this.buffer = new char[bufferSize];
		this.currentLoc = 0;
	}


	@Override
	public void write(String str) throws IOException {
		int i = 0;
		int len = str.length();
		while (i < len) {
			this.buffer[this.currentLoc] = str.charAt(i);
			this.currentLoc++;
			if (this.currentLoc == this.bufferSize) {
				this.fWriter.write(this.buffer);
				this.currentLoc = 0;
			}
			i++;
		}
	}

	@Override
	public void close() throws IOException {
		for (int i = 0; i < this.currentLoc; i++)
			this.fWriter.write(this.buffer[i]);
		this.fWriter.close();
	}

}
