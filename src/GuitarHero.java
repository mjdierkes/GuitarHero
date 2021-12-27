package com.masondierkes;

public class GuitarHero {

    String keyboard = "q2we4r5ty7u8i9op[=zxdcfvgbnjmk,.;/' ";
    GuitarString[] strings = getStrings();

    public void run(){
        while (true) {

            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                System.out.println(key);

                if(keyboard.indexOf(key) != -1)
                    pluck(key);
            }


            StdAudio.play(sample());
            tic();
        }
    }

    private void pluck(char key){
        int index = keyboard.indexOf(key);
        strings[index].pluck();
    }

    private double sample(){
        double output = 0;
        for(GuitarString string : strings){
            output += string.sample();
        }

        return output;
    }

    private void tic(){
        for(GuitarString string : strings)
            string.tic();
    }

    private GuitarString[] getStrings(){

        GuitarString[] output = new GuitarString[keyboard.length()];
        final double CONCERT_A = 440.0;

        for (int i = 0; i < keyboard.length(); i++) {
            double CONCERT_I = CONCERT_A * Math.pow(1.0595, i - 24);
            output[i] = new GuitarString(CONCERT_I);
        }

        return output;
    }

}
