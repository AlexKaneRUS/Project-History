import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alexkane on 3/4/15.
 */
class ThisPartCreator implements PartsCreator {
    String partSource;
    StringBuilder story;
    int libraryStuff;
    int maxSizeOfSequence;
    String line;

    public ThisPartCreator(String partSource, StringBuilder story, int maxSizeOfSequence, int libraryStuff){
        this.partSource = partSource;
        this.story = story;
        this.maxSizeOfSequence = maxSizeOfSequence;
        this.libraryStuff = libraryStuff;
    }

    public void createPart() {
        try {
            ArrayList<String> textArray = new ArrayList<String>();
            final FileReader expositionSourceReader = new FileReader(partSource);
            final LineNumberReader expositionSourceLineReader = new LineNumberReader(expositionSourceReader);
            while ((line = expositionSourceLineReader.readLine()) != null){
                textArray.add(line);
            }
            expositionSourceLineReader.close();
            Random randomNumber = new Random();
            int numberOfLines = randomNumber.nextInt(maxSizeOfSequence) + 1;
            int i = 0;
            int amountOfVersions = 0;
            while (i < numberOfLines){
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
