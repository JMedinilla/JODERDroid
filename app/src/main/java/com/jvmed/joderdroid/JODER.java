package com.jvmed.joderdroid;

import java.util.Random;

/**
 * Class made by AlexPowerUp
 */
class JODER {

    /**
     * Method that creates the JODER, based on a random length
     * @param min The minimum characters the JODER has to have
     * @param max The maximum characters the JODER can have
     * @return The whole JODER
     */
    static String Generate(int min, int max)
    {
        Random RNG = new Random();
        int length;

        //An array will have all the letters of the JODER
        String[] letters = new String[5];
        letters[0] = "";
        letters[1] = "";
        letters[2] = "";
        letters[3] = "";
        letters[4] = "";

        //The length is selected by a random between the minimum and the maximum characters
        if(min == max) {
            length = min;
        } else {
            length = RNG.nextInt((max - min) + 1) + min;
        }

        //A JODER needs at least 1 of all the 5 letters, so they cannot be random
        int generate = length - 5;
        int genletter;

        //J
        //It takes all the remaining letters available, adds one, and generates random Js
        genletter = RNG.nextInt(generate + 1);
        generate -= genletter;
        for(int i = 0; i <= genletter; i++)
        {
            letters[0] += "J";
        }
        //O
        //It takes all the remaining letters available, adds one, and generates random Os
        genletter = RNG.nextInt(generate + 1);
        generate -= genletter;
        for (int i = 0; i <= genletter; i++)
        {
            letters[1] += "O";
        }
        //D
        //It takes all the remaining letters available, adds one, and generates random Ds
        genletter = RNG.nextInt(generate + 1);
        generate -= genletter;
        for (int i = 0; i <= genletter; i++)
        {
            letters[2] += "D";
        }
        //E
        //It takes all the remaining letters available, adds one, and generates random Es
        genletter = RNG.nextInt(generate + 1);
        generate -= genletter;
        for (int i = 0; i <= genletter; i++)
        {
            letters[3] += "E";
        }
        //R
        //It takes all the remaining letters available, adds one, and generates random Rs
        genletter = generate;
        for (int i = 0; i <= genletter; i++)
        {
            letters[4] += "R";
        }

        /*
        At the end of the process you added the 5 letters that you subtracted at the begingin
        and adding 1 on each letter, so you know that the JODER will have all the 5 letters
         */
        return (letters[0] + letters[1] + letters[2] + letters[3] + letters[4]);
    }
}