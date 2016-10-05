package com.jvmed.joderdroid;

import java.util.Random;

public class JODER {

    static public String Generate(int min, int max)
    {
        Random RNG = new Random();
        int length;
        String[] letters = new String[5];
        letters[0] = "";
        letters[1] = "";
        letters[2] = "";
        letters[3] = "";
        letters[4] = "";

        if(min == max) {
            length = min;
        } else {
            length = RNG.nextInt((max - min) + 1) + min;
        }

        int generate = length - 5;
        int genletter;

        //J
        genletter = RNG.nextInt(generate + 1);
        generate -= genletter;
        for(int i = 0; i <= genletter; i++)
        {
            letters[0] += "J";
        }
        //O
        genletter = RNG.nextInt(generate + 1);
        generate -= genletter;
        for (int i = 0; i <= genletter; i++)
        {
            letters[1] += "O";
        }
        //D
        genletter = RNG.nextInt(generate + 1);
        generate -= genletter;
        for (int i = 0; i <= genletter; i++)
        {
            letters[2] += "D";
        }
        //E
        genletter = RNG.nextInt(generate + 1);
        generate -= genletter;
        for (int i = 0; i <= genletter; i++)
        {
            letters[3] += "E";
        }
        //R
        genletter = generate;
        for (int i = 0; i <= genletter; i++)
        {
            letters[4] += "R";
        }

        return (letters[0] + letters[1] + letters[2] + letters[3] + letters[4]);
    }
}