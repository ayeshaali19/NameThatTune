public class generateMusic {
	public static double random = StdRandom.uniform()*100;
	public static PrintWriter writer1 = new PrintWriter("chorusVerse"+(int)random+".txt", "UTF-8");
	public static PrintWriter writer2 = new PrintWriter("notes"+(int)random+".txt", "UTF-8");
		
	public static int[] scale = chooseScale();
	public static double[] chorus = chorus(scale, writer1);
  	public static double[] verse = verse(scale, writer1);
  
	public static void main(String[] args) {
		
		double[] a = structure(chorus,verse);
		writer1.close();
		writer2.close();
		StdAudio.play(a);
		StdAudio.save("songnumber"+(int)random+".wav", a);
		// StdAudio.play(structure(chorus, notesArray(scale)));
	}

  	public static int[] chooseScale() {
    	int[] cMajor = {3, 5, 7, 10, 12};
	    int[] gMajor = {10, 12, 2, 5, 7};
	    int[] dMajor = {5, 7, 9, 0, 2};
	    int[] aMajor = {0, 2, 4, 7, 9};
	    int[] eMajor = {7, 9, 11, 2, 4};
	    int[] bMajor = {2, 4, 6, 9, 11};
	    int[] fSharpMajor = {8, 11, 1, 4, 6};
	    int[] cSharpMajor = {4, 6, 8, 11, 1};
	    int[] fMajor = {8, 10, 0, 3, 5};
	    int[] bFlatMajor = {1, 3, 5, 8, 10};
	    int[] eFlatMajor = {6, 3, 10, 1, 3};
	    int[] aFlatMajor = {11, 1, 3, 6, 8};

	    int num = StdRandom.uniform(1,12);
	    int[] scale = new int[5];
	    if(num==0){ 
	    	scale = cMajor; }
	    if(num==1){
	    	scale= gMajor;}
	    if(num==2){
	    	scale = dMajor;}
	    if(num==3){
	    	scale= aMajor;}
	    if(num==4){
	    	scale=  eMajor;}
	    if(num==5){
	    	scale= bMajor;}
	    if(num==6){
	    	scale= fSharpMajor;}
	    if(num==7){
	    	scale= cSharpMajor;}
	    if(num==8){
	    	scale= fMajor;}
	    if(num==9){
	    	scale = bFlatMajor;}
	    if(num==10){
	    	scale = eFlatMajor;}
	    if(num==11){
	    	scale = aFlatMajor;}

	    return scale;
  	}
  
 	public static double[] chorus(int[] scale, PrintWriter writer1) {
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

	    writer1.println(note1, duration1, "Major");
	    writer1.println(note2, duration2, "Major");
	    writer1.println(note3, duration3, "Major");
	    double[] d = ArrayTools.concatenateArray(ArrayTools.concatenateArray(a, b), c);
	    double[] array = ArrayTools.concatenateArray(d,d);
	    return array;
 	}

	public static double[] verse(int[] scale, PrintWriter writer1) {
  		double[] array = new double[2];
  		int[] pickScale = ArrayTools.shuffle(scale);

	    for (int i =0; i< 5; i++) {
		    double random = StdRandom.uniform();
		    int note = pickScale[i];
		    int duration = StdRandom.uniform(1,4);
	  		double[] a = new double[2];
	  		if (random > 0.5) {
	  			a = MusicLibrary.majorChord(note, duration);
	  			writer1.println(note, duration, "Major");
	  		} else if (random<=0.5) {
	  			a = MusicLibrary.minorChord(note, duration); 
	  			writer1.println(note, duration, "Minor");
	  		}	

	  		array = ArrayTools.concatenateArray(array,a);
  		}
		

		double[] notesA = notesArray(scale, array.length, writer2);
		double[] returnedA = ArrayTools.add(array, notesA, .5, .5);
		return returnedA;
 	}

	public static double[] notesArray(int[] scale, int verseLength, PrintWriter writer2){
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
   			writer2.println(notes[i], .5);
   			double[] singleN = MusicLibrary.sinstuff(.5, 440 * Math.pow(2, notes[i] / 12.0));
   			testA = ArrayTools.concatenateArray(testA,singleN);
   		}
   		return testA;
	}


  	public static double[] structure(double[] chorus, double[] verse) {
  		double[] array = new double[2];
  		for (int i = 0; i< 7; i++) {
  			double random = StdRandom.uniform();
  			double[] a = new double[2];
  			if (random > 0.5) {
  				a = chorus; 
  			} else if (random<=0.5) {
  				a = verse; 
  			}
  			array = ArrayTools.concatenateArray(array, a);
  		}
  		return array;
  	}
}