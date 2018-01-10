/**
*Generate Music
*This generateMusic program does exactly what it sounds like.  
*It randomly generates a 2 to 4 minute song by randomly choosing a scale, generating a chorus and verse, and choosing a song structure.
*
*Volume changes and other effects can be implemented onto pieces of music as well.
* @author  Katherine Vella
* @author  Ayesha Ali
* @version 1.0
* @since   12/30/17 
*/

import java.lang.*;
import java.io.*;

public class generateMusic {
  	
  	public static int[] versePitch = new int[5];
  	public static int[] chorusPitch = new int[3];
	public static int[] pitch = new int[0];

	public static double[] verseD;
  	public static double[] chorusD = new double[3];
	public static double[] duration = new double[0];  	

	public static void main(String[] args) throws Exception {
    	double weird = (StdRandom.uniform())*100;
    	System.out.println(weird);

    	FileOutputStream f = new FileOutputStream("sheetMusic"+(int)weird+".txt");
    	System.setErr(new PrintStream(f));

    	int[] scale = chooseScale();
    	double[] chorus = chorus(scale);
  		double[] verse = verse(scale);
  		double[] a = structure(chorus,verse);


  		Thread thread1 = new Thread() {
   			public void run() {
				drawStuff(pitch, duration);
        	}
		};

		Thread thread2 = new Thread() {
   			public void run() {
				StdAudio.play(a);
        	}
		};
    	
    	double[] conArr = new double[1];
    	for (int i = 0; i< versePitch.length; i++) {
    		double hz = 440 * Math.pow(2, versePitch[i] / 12.0);
    		double[] b = StdAudio.note(hz, 0.5, 1);
    		conArr = ArrayTools.concatenateArray(conArr, b);
    	}

    	// StdAudio.play(MusicLibrary.echo(conArr, 5, 2, 5));
    	// StdAudio.save("songnumber"+(int)weird+".wav", a);
		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();
		
		
	}

  	public static int[] chooseScale() {
  		int[][] scales;
  		scales  = new int[][] {
  			{3, 5, 7, 10, 12},
	   		{10, 12, 2, 5, 7},
	    	{5, 7, 9, 0, 2},
	    	{0, 2, 4, 7, 9},
	   		{7, 9, 11, 2, 4},
	    	{2, 4, 6, 9, 11},
	    	{8, 11, 1, 4, 6},
	    	{4, 6, 8, 11, 1},
	    	{8, 10, 0, 3, 5},
	    	{1, 3, 5, 8, 10},
	    	{6, 3, 10, 1, 3},
	    	{11, 1, 3, 6, 8}
	    };

	    int num = StdRandom.uniform(1,12);
	    System.err.print("Scale: ");
	    ArrayTools.printArray(scales[num], "poop");
	    return scales[num];
  	}
  
 	public static double[] chorus(int[] scale) {
	  	int[] pickScale = ArrayTools.shuffle(scale);

	    int note1 = pickScale[0];
	    int note2 = pickScale[1];
	    int note3 = pickScale[2];
	    
	    chorusPitch[0] = note1;
	    chorusPitch[1] = note2;
	    chorusPitch[2] = note3;
	    chorusPitch = ArrayTools.concatenateArray(chorusPitch, chorusPitch);

	    int duration1 = StdRandom.uniform(1,4);
	    int duration2 = StdRandom.uniform(1,4);
	    int duration3 = StdRandom.uniform(1,4);

	    chorusD[0] = (double) duration1;
	    chorusD[1] = (double) duration2;
	    chorusD[2] = (double) duration3;
	    chorusD = ArrayTools.concatenateArray(chorusD, chorusD);

	    double[] a = MusicLibrary.majorChord(note1, duration1);
	    double[] b = MusicLibrary.majorChord(note2, duration2);
	    double[] c = MusicLibrary.majorChord(note3, duration3);

	    System.err.println("\nChorus:");
	    System.err.println(note1+" "+duration1+" Major");
	    System.err.println(note2+" "+duration2+" Major");
	    System.err.println(note3+" "+duration3+" Major");
	    System.err.println(note1+" "+duration1+" Major");
	    System.err.println(note2+" "+duration2+" Major");
	    System.err.println(note3+" "+duration3+" Major");

	    double[] d = ArrayTools.concatenateArray(ArrayTools.concatenateArray(a, b), c);
	    double[] array = ArrayTools.concatenateArray(d,d);
	    double[] zeros = new double[44000];
	    for (int i =0; i< zeros.length; i++) {
	    	zeros[i] = 0;
	    }
	    
	    return ArrayTools.concatenateArray(array, zeros);
 	}

	public static double[] verse(int[] scale) {
  		double[] array = new double[2];
  		int[] pickScale = ArrayTools.shuffle(scale);

  		System.err.println("\nVerse:");
	    for (int i =0; i< 5; i++) {
		    double random = StdRandom.uniform();
		    int note = pickScale[i];
		    int duration = StdRandom.uniform(1,4); 
	  		double[] a = new double[2];
	  		if (random > 0.5) {
	  			a = MusicLibrary.majorChord(note, duration);
	  			System.err.println(note+" "+duration+" Major");
	  		} else if (random<=0.5) {
	  			a = MusicLibrary.minorChord(note, duration); 
	  			System.err.println(note+" "+duration+" Minor");
	  		}	

	  		array = ArrayTools.concatenateArray(array,a);
  		}
		

		double[] notesA = notesArray(scale, array.length);
		double[] returnedA = ArrayTools.add(array, notesA, .5, .5);
		return returnedA;
 	}

	public static double[] notesArray(int[] scale, int verseLength){
	    int [] a = ArrayTools.shuffle(scale);
	    int [] b = ArrayTools.shuffle(scale);
		
	    int[] oneRepeat = ArrayTools.concatenateArray(a,b);

	    int repetition = (int)(verseLength/StdAudio.SAMPLE_RATE)/5; 
	    int[] notes = new int[oneRepeat.length*repetition];

	    for(int x=0; x<repetition; x++){
	    	for(int y=0; y<oneRepeat.length; y++){
	      	notes[x*oneRepeat.length + y] = oneRepeat[y];
	     	}  
	    }

	    verseD = new double[notes.length];
	    versePitch = notes; 
   		double[] testA = new double[1];
   		for (int i = 0; i <notes.length; i++) {
   			verseD[i] = 0.5; 
   			System.err.println(notes[i]+" 0.5");
   			double[] singleN = MusicLibrary.sinstuff(.5, 440 * Math.pow(2, notes[i] / 12.0));
   			testA = ArrayTools.concatenateArray(testA,singleN);
   		}
   		return testA;
	}


  	public static double[] structure(double[] chorus, double[] verse) {
  		double[] array = new double[2];
  		System.err.println("\nStructure:");
  		
  		for (int i = 0; i< 15; i++) {
  			double random = StdRandom.gaussian(0.0, 0.33);
  			double[] a = new double[2];

  			if (random > 0) {
  				a = chorus;
  				System.err.println("Chorus"); 
  				pitch = ArrayTools.concatenateArray(pitch, chorusPitch);
  				duration = ArrayTools.concatenateArray(duration, chorusD);

  			} else if (random <= 0) {
  				a = verse; 
  				System.err.println("Verse");
  				pitch = ArrayTools.concatenateArray(pitch, versePitch);
  				duration = ArrayTools.concatenateArray(duration, verseD);
  			}
  			array = ArrayTools.concatenateArray(array, a);
  		}


  		double[] faded = MusicLibrary.fadeIn(array, 3);
  		double[] faded2 = MusicLibrary.fadeOut(faded, 3);
  		return faded2;
  	}

  	public static void drawStuff(int[] a, double[] duration) {
  		double baseMin = ArrayTools.min(a);
  		double baseMax = ArrayTools.max(a);
  		StdDraw.enableDoubleBuffering();

  		int[][] colors;
  		colors  = new int[][] {
	   		{229, 55, 0},
	   		{206, 53, 19},
	    	{183, 51, 38},
	    	{160, 49, 57},
	   		{137, 48, 76},
	    	{114, 46, 95},
	    	{91, 44, 114},
	    	{68, 43, 133},
	    	{45, 41, 152},
	    	{22, 39, 171},
	    	{0, 38, 191}
	    };
  		
  		for (int i = 0; i< a.length; i+=1) {
  			double radius = ArrayTools.scale(Math.abs(a[i]), baseMin, baseMax, .1, .2);
  			double radius2 = ArrayTools.scale(Math.abs(a[i+1]), baseMin, baseMax, .1, .2); 
  			double difference = radius2-radius;
  			
  			double colorD = ArrayTools.scale(Math.abs(a[i]), baseMin, baseMax, 2, 10);
  			int[] color1 = colors[(int)colorD-2];
  			int[] color2 = colors[(int)colorD-1];
  			
  			StdDraw.clear();
   			StdDraw.setPenColor(color1[0], color1[1], color1[2]);
  			StdDraw.filledCircle(0.5, 0.5, radius+0.075);
  			StdDraw.setPenColor(color2[0], color2[1], color2[2]);
  			StdDraw.filledCircle(0.5, 0.5, radius);
  			StdDraw.show();
  			StdDraw.pause((int)duration[i]*970);
       		
       		for (int j =0; j <150; j++) {
  				StdDraw.clear();
   				StdDraw.setPenColor(color1[0], color1[1], color1[2]);
  				StdDraw.filledCircle(0.5, 0.5, radius+(difference*(double)j/150)+0.075);
  				StdDraw.setPenColor(color2[0], color2[1], color2[2]);
  				StdDraw.filledCircle(0.5, 0.5, radius+(difference*(double)j/150));
  				StdDraw.show();
  			}
  		}

  	}
}