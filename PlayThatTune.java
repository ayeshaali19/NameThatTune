/******************************************************************************
 *  Compilation:  javac PlayThatTune.java
 *  Execution:    java PlayThatTune < input.txt
 *  Dependencies: StdAudio.java StdAudio.java
 *
 *  This is a data-driven program that plays pure tones from
 *  the notes on the chromatic scale, specified on standard input
 *  by their distance from concert A.
 *
 *  % java PlayThatTune < elise.txt
 *
 *
 *  Data files
 *  ----------
 *  http://www.cs.princeton.edu/introcs/21function/elise.txt
 *  http://www.cs.princeton.edu/introcs/21function/freebird.txt
 *  http://www.cs.princeton.edu/introcs/21function/Ascale.txt
 *  http://www.cs.princeton.edu/introcs/21function/National_Anthem.txt
 *  http://www.cs.princeton.edu/introcs/21function/looney.txt
 *  http://www.cs.princeton.edu/introcs/21function/StairwayToHeaven.txt
 *  http://www.cs.princeton.edu/introcs/21function/entertainer.txt
 *  http://www.cs.princeton.edu/introcs/21function/old-nassau.txt
 *  http://www.cs.princeton.edu/introcs/21function/arabesque.txt
 *  http://www.cs.princeton.edu/introcs/21function/firstcut.txt 
 *  http://www.cs.princeton.edu/introcs/21function/tomsdiner.txt
 *
 ******************************************************************************/

public class PlayThatTune {

    public static void main(String[] args) {
        // repeat as long as there are more integers to read in
        while (!StdIn.isEmpty()) {
            // read in the pitch, where 0 = Concert A (A4)
            int pitch = StdIn.readInt();
            double hz = 440 * Math.pow(2, pitch / 12.0);
            // read in duration in seconds
            double duration = StdIn.readDouble();

            // StdAudio.play(majorChord(pitch, duration));
            StdAudio.play(minorChord(pitch, duration));


        }
    }

    public static double[] majorChord(int pitch, double duration) {
        double[] a = sinstuff(duration, 440 * Math.pow(2, pitch / 12.0));
        double[] b = sinstuff(duration, 440 * Math.pow(2, (pitch + 4) / 12.0));
        double[] c = sinstuff(duration, 440 * Math.pow(2, (pitch + 7) / 12.0));

        double[] e = ArrayTools.add(a, b, 0.5, 0.5);
        double[] f = ArrayTools.add(e, c, 0.5, 0.5);
        return f;
    }

    public static double[] minorChord(int pitch, double duration) {
        double[] a = sinstuff(duration, 440 * Math.pow(2, pitch / 12.0));
        double[] b = sinstuff(duration, 440 * Math.pow(2, (pitch + 3) / 12.0));
        double[] c = sinstuff(duration, 440 * Math.pow(2, (pitch + 7) / 12.0));

        double[] e = ArrayTools.add(a, b, 0.5, 0.5);
        double[] f = ArrayTools.add(e, c, 0.5, 0.5);
        return f;
    }

    public static double[] changeVolume(double pitch, double duration, double volume) {
        double hz = 440 * Math.pow(2, pitch / 12.0);
        double[] e = sinstuff(duration, hz);
        return ArrayTools.scale(e, volume);
    }

    public static double[] sum(double[] a, double[] b, double c, double d) {
        return ArrayTools.add(a,b,c,d);
    }

    public static double[] clip(double[] a, double threshold) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > threshold) {
                a[i] = threshold;
            }
        }
        return a;
    }

    public static double[] fadeIn(double[] a, double secs) {
       int n = (int) (StdAudio.SAMPLE_RATE * secs);
       for (int i = 0; i<n; i++) {
            a[i] = 0;
       }
       return a;
    }

    public static double[] fadeOut(double[] a, double secs) {
       int n = (int) (StdAudio.SAMPLE_RATE * secs);
       for (int i = a.length; i<a.length-n; i++) {
            a[i] = 0;
       }
       return a;
    }

    public static double[] sinstuff(double duration, double chord) {
        int n = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[n+1];
        for (int i = 0; i <= n; i++) {
            a[i] = Math.sin(2 * Math.PI * i * chord / StdAudio.SAMPLE_RATE);
        }

        return a;
    }
}
