package ru.ifmo.sadovnikov.storyCreation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexkane on 5/2/15.
 */
public class Russianiser {
    private String im;
    private String rod;
    private String dat;
    private String vin;
    private String tvor;
    private String predl;

    public Russianiser(final String im) {
        this.im = im;
    }

    public void Russianise() {
        if (im.endsWith("а") || im.endsWith("я")) {
            String stringEx = im.substring(0, im.length() - 1);
            String consonant = stringEx.substring(im.length() - 1);
            if (im.endsWith("а")) {
                if ((consonant.equals("щ")) || (consonant.equals("ш")) || (consonant.equals("ж")) || (consonant.equals("ч")) || (consonant.equals("г")) || (consonant.equals("к"))) {
                    rod = stringEx + "и";
                    dat = stringEx + "е";
                    vin = stringEx + "у";
                    tvor = stringEx + "ей";
                    predl = stringEx + "е";
                }
            } else if (im.endsWith("я")) {
                if ((consonant.equals("т")) || (consonant.equals("н")) || (consonant.equals("д")) || (consonant.equals("м")) || (consonant.equals("л"))) {
                    rod = stringEx + "и";
                    dat = stringEx + "е";
                    vin = stringEx + "ю";
                    tvor = stringEx + "ей";
                    predl = stringEx + "е";
                }
            }
            if (rod == null) {
                if ((consonant.equals("а")) || (consonant.equals("е")) || (consonant.equals("ё")) || (consonant.equals("и")) || (consonant.equals("о")) || (consonant.equals("у")) || (consonant.equals("ы")) || (consonant.equals("э")) || (consonant.equals("ю")) || (consonant.equals("я"))) {
                    rod = stringEx + "и";
                } else {
                    rod = stringEx + "ы";
                }
                dat = stringEx + "е";
                if ((consonant.equals("а")) || (consonant.equals("е")) || (consonant.equals("ё")) || (consonant.equals("и")) || (consonant.equals("о")) || (consonant.equals("у")) || (consonant.equals("ы")) || (consonant.equals("э")) || (consonant.equals("ю")) || (consonant.equals("я"))) {
                    vin = stringEx + "ю";
                } else {
                    vin = stringEx + "у";
                }
                if ((consonant.equals("а")) || (consonant.equals("е")) || (consonant.equals("ё")) || (consonant.equals("и")) || (consonant.equals("о")) || (consonant.equals("у")) || (consonant.equals("ы")) || (consonant.equals("э")) || (consonant.equals("ю")) || (consonant.equals("я"))) {
                    tvor = stringEx + "ей";
                } else {
                    tvor = stringEx + "ой";
                }
                predl = stringEx + "е";
            }
        } else if (im.endsWith("и") || im.endsWith("о") || im.endsWith("у") || im.endsWith("ъ")) {
            rod = im;
            dat = im;
            vin = im;
            tvor = im;
            predl = im;
        } else if (im.endsWith("й") || im.endsWith("ь")) {
            String stringEx = im.substring(0, im.length() - 1);
            rod = stringEx + "я";
            dat = stringEx + "е";
            vin = stringEx + "ю";
            if (im.endsWith("ь")) {
                tvor = stringEx + "ём";
            } else {
                tvor = stringEx + "ем";
            }
            predl = stringEx + "е";
        } else {
            rod = im + "а";
            dat = im + "у";
            vin = im + "а";
            tvor = im + "ом";
            predl = im + "е";
        }
    }

    public ArrayList<String> getRussianisedName() {
        ArrayList<String> name = new ArrayList<String>();
        name.add(im);
        name.add(rod);
        name.add(dat);
        name.add(vin);
        name.add(tvor);
        name.add(predl);
        return name;
    }
}
