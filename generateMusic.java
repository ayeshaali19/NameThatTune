public class generateMusic {

  public static void main(String[] args) {

  }

  public static double[] chooseScale() {
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

    int num = math.random()*0+11;
    int scale[] = 0;

    if(num==0){ 
    	scale[] = cMajor[]; }
    if(num==1){
    	scale[] = gMajor[];}
    if(num==2){
    	scale[] = dMajor[];}
    if(num==3){
    	scale[] = aMajor[];}
    if(num==4){
    	scale[] = eMajor[];}
    if(num==5){
    	scale[] = bMajor[];}
    if(num==6){
    	scale[] = fSharpMajor[];}
    if(num==7){
    	scale[] = cSharpMajor[];}
    if(num==8){
    	scale[] = fMajor[];}
    if(num==9){
    	scale[] = bFlatMajor[];}
    if(num==10){
    	scale[] = eFlatMajor[];}
    if(num==11){
    	scale[] = aFlatMajor[];}

    return scale;

  }
  
  public static double[] chorus(int[] scale) {
  	int[] a = ArrayTools.shuffle(scale);
    int note1 = a[0];
    int note2 = a[1];
    int note3 = a[2];
    
    MusicLibrary.majorChord(note1, );
  }
  
  public static double[] verse(int[] scale) {
    int[] a = ArrayTools.shuffle(scale);
  
  }
  
  
}