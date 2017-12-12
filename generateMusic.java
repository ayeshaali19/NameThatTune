public class generateMusic {
	public static int[] scale = chooseScale();
	public static double[] chorus = chorus(scale);
  	public static double[] verse = verse(scale);
  
	public static void main(String[] args) {
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

	    double[] d = ArrayTools.concatenateArray(ArrayTools.concatenateArray(a, b), c);
	    double[] array = ArrayTools.concatenateArray(d,d);
	    return array;
 	}

	public static double[] verse(int[] scale) {
  		int[] a = ArrayTools.shuffle(scale);
  
  	}

	public static double notesArray(double scale, int verseLength){
	    double [] a = ArrayTools.shuffle(scale);
	    double [] b = ArrayTools.shuffle(scale);
		
	    double[] oneRepeat = ArrayTools.concatenateArray(a,b);

	    int repetition = int (verseLength/10); 
	    double[] notes = new int[oneRepeat.length*repetition];

	    for(int x=0; x<repetition; x++){
	    	for(int y=0; y<oneRepeat.length; y++){
	      	notes[x*oneRepeat.length + y] = oneRepeat[y];
	     	}  
	    }

   		double[] array = new double[1];

   		for (int i = 0; i <notes.length; i++) {
   			array = ArrayTools.concatenateArray(array, MusicLibrary.sinstuff(1, 440 * Math.pow(2, notes[i] / 12.0)));
   		}
   		return array;
	}


  	public static double[] structure(double[] chorus, double[] verse) {
  		double[] array = new double[2];
  		for (int i = 0; i< 7; i++) {
  			double random = StdRandom.uniform();
  			if (random > 0.5) {
  				double[] a = chorus; 
  			} else if (random<=0.5) {
  				double[] a = verse; 
  			}	
  			array = ArrayTools.concatenateArray(array, a);
  		}
  		return array;
  	}
}