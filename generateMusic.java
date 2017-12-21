import java.lang.*;
import java.io.*;

public class generateMusic {
  	
  	public static int[] versePitch = new int[5];
  	public static int[] chorusPitch = new int[3];
	public static int[] pitch = new int[0];

	public static int[] verseD = new int[5];
  	public static int[] chorusD = new int[3];
	public static int[] duration = new int[0];  	

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
   				for (int i =0; i<a.length; i+=1) {
					drawStuff(pitch, duration);
				}
        	}
		};

		Thread thread2 = new Thread() {
   			public void run() {
   				for (int i =0; i<a.length; i+=4400) {
					StdAudio.play(ArrayTools.copy(a, i, i+4400));
				}
        	}
		};
    	
		thread1.start();
		thread2.start();

		thread1.join();
		thread2.join();
		
		// StdAudio.save("songnumber"+(int)weird+".wav", a);
		// StdAudio.play(a);
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

	    chorusD[0] = duration1;
	    chorusD[1] = duration2;
	    chorusD[2] = duration3;
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
	    return array;
 	}

	public static double[] verse(int[] scale) {
  		double[] array = new double[2];
  		int[] pickScale = ArrayTools.shuffle(scale);

  		versePitch = pickScale;

  		System.err.println("\nVerse:");
	    for (int i =0; i< 5; i++) {
		    double random = StdRandom.uniform();
		    int note = pickScale[i];
		    int duration = StdRandom.uniform(1,4);
		    verseD[i] = duration; 
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

   		double[] testA = new double[1];
   		for (int i = 0; i <notes.length; i++) {
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
  			double random = StdRandom.uniform(2);
  			double[] a = new double[2];

  			if (random == 0) {
  				a = chorus;
  				System.err.println("Chorus"); 
  				pitch = ArrayTools.concatenateArray(pitch, chorusPitch);
  				duration = ArrayTools.concatenateArray(duration, chorusD);

  			} else if (random == 1) {
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

  	public static void drawStuff(int[] a, int[] duration) {
  		double baseMin = ArrayTools.min(a);
  		double baseMax = ArrayTools.max(a);
  		StdDraw.enableDoubleBuffering();

  		for (int i = 0; i< a.length; i+=1) {
  			StdDraw.clear();
  			if (a[i] >= 0) {
  				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
  				StdDraw.filledCircle(0.5, 0.5, ArrayTools.scale(a[i], baseMin, baseMax, .1, .2)+0.075);
  				StdDraw.setPenColor(StdDraw.BLACK);
  				StdDraw.filledCircle(0.5, 0.5, ArrayTools.scale(a[i], baseMin, baseMax, .1, .2));
  			} else if (a[i]<0) {
   				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
  				StdDraw.filledCircle(0.5, 0.5, ArrayTools.scale(a[i]*-1, baseMin, baseMax, .1, .2)+0.075);
				StdDraw.setPenColor(StdDraw.BLACK);
   				StdDraw.filledCircle(0.5, 0.5, ArrayTools.scale(a[i]*-1, baseMin, baseMax, .1, .2));
   			}
  
       		StdDraw.show();
       		StdDraw.pause(duration[i]*1000);
  		}
  	}
}