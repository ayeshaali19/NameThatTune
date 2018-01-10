/**
* <h1>Array Tools</h1>
* The Music Library program implements an application that
* can manipulate pitches and durations to form long arrays of playable music.
* Chords and harmonics can be played.
* Volume changes and other effects can be implemented onto pieces of music as well.
* <p>
* 
* @author  Katherine Vella
* @author  Ayesha Ali
* @version 1.0
* @since   12/22/17 
*/

public class MusicLibrary {
    public static void main(String[] args) {
        // repeat as long as there are more integers to read in
        while (!StdIn.isEmpty()) {
            // read in the pitch, where 0 = Concert A (A4)
            int pitch = StdIn.readInt();
            //change pitch to frequency in hertz
            double hz = 440 * Math.pow(2, pitch / 12.0);
            // read in duration in seconds
            double duration = StdIn.readDouble();

            //tests
            // StdAudio.play(harmonics(pitch, duration));
            // StdAudio.play(majorChord(pitch, duration));
            // StdAudio.play(minorChord(pitch, duration));
            // StdAudio.play(changeVolume(minorChord(pitch, duration),10));
           	// StdAudio.play(fadeIn(minorChord(pitch,duration),2));
           	// StdAudio.play(fadeOut(majorChord(pitch,duration),2));
           	
           	// double[] a = {0,0,0,0,0,0,0,22,28,91,34,18,29,22,0,0,0,0,0,0};
           	// double[] b = {0,0,0,0,0,0,0,22,28,91,34,18,29,22,0,0,0,0,0,0};
            // trim(a);
            // clip(a, 5.0);

            StdAudio.play(echo(majorChord(pitch,duration), 3, 5));
            // StdAudio.play(changeVolume(majorChord(pitch,duration),0.8));
            // StdAudio.play( majorChord(pitch,duration));
            
        }
    }
    
    /**
    * This method is used to turn a frequency into a double array, containing a single note with a given duration.
    * @param duration The number of seconds the note will play
    * @param hz The frequency of the notes
    * @return a An array that holds the sin graph that can be played using StdAudio
    */
    public static double[] sinstuff(double duration, double hz) {
        int n = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[n+1];
        for (int i = 0; i <= n; i++) {
            a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
        }

        return a;
    }

    /**
    * This method is used to turn a pitch into a double array, containing a harmonic with a given duration. It uses the sinstuff method to generate two arrays of a single note each: one an octave higher and another an octave lower than the inputted pitch. These two arrays are then layered to form a harmonic. 
    * @param duration The number of seconds the harmonic will play
    * @param pitch The root pitch of the harmonic
    * @return a An array that holds the sin graph of a harmonic that can be played using StdAudio
    */
    public static double[] harmonics(int pitch, double duration) {
        double hz = 440.0 * Math.pow(2, pitch / 12.0);
        double[] hi = sinstuff(duration, 2*hz);
        double[] lo = sinstuff(duration, hz/2);
        double[] h  = sum(hi, lo, 0.5, 0.5);
        return h;
    }


    /**
    * This method is used to turn a pitch into a double array, containing a major chord with a given duration. It uses the sinstuff method to generate three arrays of a single note each: the inputted pitch (root note), the major third, and the perfect fifth. These arrays are then layered to form a major chord. 
    * @param duration The number of seconds the major chord will play
    * @param pitch The root pitch of the chord
    * @return a An array that holds the sin graph of a major chord that can be played using StdAudio
    */
    public static double[] majorChord(int pitch, double duration) {
        double[] a = sinstuff(duration, 440 * Math.pow(2, pitch / 12.0));
        double[] b = sinstuff(duration, 440 * Math.pow(2, (pitch + 4) / 12.0));
        double[] c = sinstuff(duration, 440 * Math.pow(2, (pitch + 7) / 12.0));

        double[] e = sum(a, b, 0.5, 0.5);
        double[] f = sum(e, c, 0.5, 0.5);
        return f;
    }

    /**
    * This method is used to turn a pitch into a double array, containing a minor chord with a given duration. It uses the sinstuff method to generate three arrays of a single note each: the inputted pitch (root note), the minor third, and the perfect fifth. These arrays are then layered to form a minor chord. 
    * @param duration The number of seconds the minor chord will play
    * @param pitch The root pitch of the chord
    * @return a An array that holds the sin graph of a minor chord that can be played using StdAudio
    */
    public static double[] minorChord(int pitch, double duration) {
        double[] a = sinstuff(duration, 440 * Math.pow(2, pitch / 12.0));
        double[] b = sinstuff(duration, 440 * Math.pow(2, (pitch + 3) / 12.0));
        double[] c = sinstuff(duration, 440 * Math.pow(2, (pitch + 7) / 12.0));

        double[] e = sum(a, b, 0.5, 0.5);
        double[] f = sum(e, c, 0.5, 0.5);
        return f;
    }

    /**
    * This method scales the amplitude of the playable array (one that has been returned from sinstuff()) to change the volume. It uses the scale method from the ArrayTools class. 
    * @param a The array to be scaled
    * @param volume The scalar
    * @return array An array that holds the scaled version of the inputted array
    */
    public static double[] changeVolume(double[] a, double volume) {
        return ArrayTools.scale(a, volume);
    }

    /**
    * This method performs weighted addition on two sine waves arrays using the add method from the ArrayTools class. This allows the user to layer music. 
    * @param a One of the arrays to be added
    * @param b One of the arrays to be added
    * @param c Scalar of array a
    * @param d Scalar of array b
    * @return array The result of adding the two inputted arrays
    */
    public static double[] sum(double[] a, double[] b, double c, double d) {
    	return ArrayTools.add(a, b, c, d);
    }

    /**
    * This method replaces every value greater than a given threshold with that threshold. 
    * @param a The array to be clipped
    * @param b The threshold
    * @return c The clipped array
    */
    public static double[] clip(double[] a, double b) {
    	double[] c = ArrayTools.duplicate(a);
        for (int i = 0; i < c.length; i++) {
			if (c[i] >= b) {
				c[i] = b;
			}
		}
		return c;
    }

    /**
    * This method removes leading and trailing 0's from an array. 
    * @param a The array to be trimmed
    * @return e The trimmed array
    */
    public static double[] trim(double[] a) {
    	int index1 = 0;
    	int length = a.length;
    	for (int i = 0; i < length; i++) {
			if (a[i] ==0) {
				index1 = i;
			} else {
				length = i;
			}
		}

		length = 0;
		int index2 = 0;
		for (int y = a.length-1; y > length; y--) {
			if (a[y]==0) {
				index2 = y;
			} else {
				length = y;
			}
		}

		double[] e = ArrayTools.copy(a,index1,index2);

		return e;
    }

    /**
    * This method scales the amplitude of the first given number of seconds of an array from 0 to the usual value. This allows the user to fade in music. 
    * @param a The array to be manipulated
    * @param secs The number of seconds the music should fade in for
    * @return e The manipulated array
    */
    public static double[] fadeIn(double[] a, int secs) {
    	int time = (int) (StdAudio.SAMPLE_RATE * secs);
    	double[] b = ArrayTools.duplicate(a);

    	for (int i =0; i<time; i++) {
    		b[i]= a[i]*i/time;
    	}

    	return b;

    }

    /**
    * This method scales the amplitude of the last given number of seconds of an array from the usual value to 0. This allows the user to fade out music. 
    * @param a The array to be manipulated
    * @param secs The number of seconds the music should fade out for
    * @return e The manipulated array
    */
    public static double[] fadeOut(double[] a, int secs) {
 		int time = (int) (StdAudio.SAMPLE_RATE * secs);
    	double[] b = ArrayTools.duplicate(a);


    	for (int i =0; i<time; i++) {
    		b[a.length-i-1]= a[a.length-i-1]*i/time;
    	}
    	
    	return b;
    }

    /**
    * This method repeats a tone based on an echo volume and a time interval
    * @param a The array to be manipulated
    * @param volume The volume at which the echo should start
    * @param secs The number of seconds the music should fade out for
    * @param repetition The number of times the tone should repeat 
    * @return finalA The final array
    */
    public static double[] echo(double[] a, double secs, int repetition){
        int time = (int) (StdAudio.SAMPLE_RATE * secs);
        double[] finalA = new double[0];
        double[] zeros = new double[time];


    	int volumeInterval = 1/repetition;
    	for(int x=0; x<=repetition-1; x++){
    		double[] c = changeVolume(a, 1-volumeInterval*x);
            finalA = ArrayTools.concatenateArray(finalA, c);
            finalA = ArrayTools.concatenateArray(finalA, zeros);
        }	

    	return finalA;
    }
}
