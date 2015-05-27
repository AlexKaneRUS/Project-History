package ru.ifmo.sadovnikov.storyCreation;

import ru.ifmo.sadovnikov.interfaces.PartsCreator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Random;

class ThisPartCreator implements PartsCreator {
    private final String partSource;
    private String story;
    private final int maxSizeOfSequence;
    private final int libraryStuff;

    public ThisPartCreator(final String partSource, final String story, final int maxSizeOfSequence, final int libraryStuff) {
        this.partSource = partSource;
        this.story = story;
        this.maxSizeOfSequence = maxSizeOfSequence;
        this.libraryStuff = libraryStuff;
    }

    public String createPart() {
        try {
            final ArrayList<String> textArray = new ArrayList<String>();
            final LineNumberReader expositionSourceLineReader = new LineNumberReader(new FileReader(partSource));
            String line;
            while ((line = expositionSourceLineReader.readLine()) != null) {
                textArray.add(line);
            }
            expositionSourceLineReader.close();
            final Random randomNumber = new Random();
            final int numberOfLines = randomNumber.nextInt(maxSizeOfSequence) + 1;
            int i = 0;
            int amountOfVersions = 0;
            while (i < numberOfLines) {
                int random = randomNumber.nextInt(textArray.size()) % libraryStuff + amountOfVersions;
                story = story + textArray.get(random) + " ";
                amountOfVersions = amountOfVersions + libraryStuff;
                i++;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file " + partSource);
        } catch (IOException ex) {
            System.out.println("Error reading file " + partSource);
        }
        return story;
    }
}
