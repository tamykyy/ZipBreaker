import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DictionaryAttack implements AttackPattern {

    private BufferedReader dictionary;

    public DictionaryAttack(DictionarySize size) {
        try {
            dictionary = new BufferedReader(new FileReader(size.getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long attack(ZipFile zipFile) {

        String attackWord = "";
        boolean isUnlocked = false;

        long startTime = System.currentTimeMillis();
        while (!isUnlocked) {
            try {
                if ((attackWord = dictionary.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(attackWord);
            zipFile.setPassword(attackWord.toCharArray());
            try {
                zipFile.extractAll("extract");
            } catch (ZipException e) {
                continue;
            }
            System.out.println("Success, password: " + attackWord);
            isUnlocked = true;
        }

        if (!isUnlocked) {
            System.out.println("Wasn't unlocked, try a bigger dictionary");
        }

        return System.currentTimeMillis() - startTime;
    }
}
