public class MusicLibrary {

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


    public static double[] sinstuff(double duration, double chord) {
        int n = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[n+1];
        for (int i = 0; i <= n; i++) {
            a[i] = Math.sin(2 * Math.PI * i * chord / StdAudio.SAMPLE_RATE);
        }

        return a;
    }
}
