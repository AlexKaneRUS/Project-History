import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Random;

class ThisPartCreator implements PartsCreator {
    final String partSource;
    final StringBuilder story;
    final int maxSizeOfSequence;
    final int libraryStuff;
    String line;

    public ThisPartCreator(final String partSource, final StringBuilder story, final int maxSizeOfSequence, final int libraryStuff) {
        this.partSource = partSource;
        this.story = story;
        this.maxSizeOfSequence = maxSizeOfSequence;
        this.libraryStuff = libraryStuff;
    }

    public void createPart() {
        try {
            final ArrayList<String> textArray = new ArrayList<String>();
            final LineNumberReader expositionSourceLineReader = new LineNumberReader(new FileReader(partSource));
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
                story.append(textArray.get(random));
                story.append(" ");
                amountOfVersions = amountOfVersions + 4;
                i++;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file " + partSource);
        } catch (IOException ex) {
            System.out.println("Error reading file " + partSource);
        }
    }
}
