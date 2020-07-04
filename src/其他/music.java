package 其他;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;


    // TODO: Auto-generated Javadoc
/**
     * The Class music.
     *
     * @author yujiaqing
     * @version  v1.0
     * @date 2020年7月4日
     */
    
public class music extends Thread {
	
	/** The player. */
	Player player;
	
	/** The music. */
	String music;
	
	/**
	 * Instantiates a new music.
	 *
	 * @param file the file
	 */
	public music(String file) {
		this.music = file;
	}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @see java.lang.Thread#run()
	    */
	    
	public void run() {
		try {
	      	play();
		} catch (FileNotFoundException | JavaLayerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Play.
	 *
	 * @throws FileNotFoundException the file not found exception
	 * @throws JavaLayerException the java layer exception
	 */
	public void play() throws FileNotFoundException, JavaLayerException {
		BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
		player = new Player(buffer);
		player.play();
		System.out.println("循环1");
		while(true) {
			if(player.isComplete()) {
			player= new Player(buffer);
			player.play();
			System.out.println("循环2");
		}
		}
		
	}
}
