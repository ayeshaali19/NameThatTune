import java.lang.*;
import java.io.*;

public class generateMusic {
	public static int random = (int) StdRandom.uniform()*100;
	public static int[] scale = chooseScale();
  
	public static void main(String[] args) throws Exception {
    	FileOutputStream f = new FileOutputStream("chords"+random+".txt");
    	System.setOut(new PrintStream(f));

    	FileOutputStream f1 = new FileOutputStream("notes"+random+".txt");
    	System.setErr(new PrintStream(f1));

    	double[] chorus = chorus(scale);
  		double[] verse = verse(scale);
    	
		double[] a = structure(chorus,verse);
		StdAudio.play(a);
		StdAudio.save("songnumber"+random+".wav", a);
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
	    return scales[num];
  	}
  
 	public static double[] chorus(int[] scale) {
	  	int[] pickScale = ArrayTools.shuffle(scale);

	    int note1 = pickScale[0];
	    int note2 = pickScale[1];
	    int note3 = pickScale[2];
	    
	    int duration1 = StdRandom.uniform(1,4);
	    int duration2 = StdRandom.uniform(1,4);
	    int duration3 = StdRandom.uniform(1,4);
	    double[] a = MusicLibrary.majorChord(note1, duration1);
	    double[] b = MusicLibrary.majorChord(note2, duration2);
	    double[] c = MusicLibrary.majorChord(note3, duration3);

	    System.out.println("Chorus:");
	    System.out.println(note1+" "+duration1+" Major");
	    System.out.println(note2+" "+duration2+" Major");
	    System.out.println(note3+" "+duration3+" Major");

	    double[] d = ArrayTools.concatenateArray(ArrayTools.concatenateArray(a, b), c);
	    double[] array = ArrayTools.concatenateArray(d,d);
	    return array;
 	}

	public static double[] verse(int[] scale) {
  		double[] array = new double[2];
  		int[] pickScale = ArrayTools.shuffle(scale);
  		System.out.println("\nVerse:");
	    for (int i =0; i< 5; i++) {
		    double random = StdRandom.uniform();
		    int note = pickScale[i];
		    int duration = StdRandom.uniform(1,4);
	  		double[] a = new double[2];
	  		if (random > 0.5) {
	  			a = MusicLibrary.majorChord(note, duration);
	  			System.out.println(note+" "+duration+" Major");
	  		} else if (random<=0.5) {
	  			a = MusicLibrary.minorChord(note, duration); 
	  			System.out.println(note+" "+duration+" Minor");
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
  		System.out.println("\nStructure:");
  		for (int i = 0; i< 7; i++) {
  			double random = StdRandom.uniform();
  			double[] a = new double[2];
  			if (random > 0.5) {
  				a = chorus;
  				System.out.println("Chorus"); 
  			} else if (random<=0.5) {
  				a = verse; 
  				System.out.println("Verse");
  			}
  			array = ArrayTools.concatenateArray(array, a);
  		}
  		double[] faded = MusicLibrary.fadeIn(array, 3);
  		double[] faded2 = MusicLibrary.fadeOut(faded, 3);
  		return faded2;
  	}
}