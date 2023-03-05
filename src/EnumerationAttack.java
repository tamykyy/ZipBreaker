import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.util.Arrays;
import java.util.List;

public class EnumerationAttack implements AttackPattern {

    private final PasswordType passwordType;
    private final int lenght;
    private boolean isUnlocked = false;

    public EnumerationAttack(PasswordType passwordType, int lenght) {
        this.passwordType = passwordType;
        this.lenght = lenght;
    }

    @Override
    public long attack(ZipFile zipFile) {

        List<Character> mins = passwordType.getMins();
        List<Character> maxs = passwordType.getMaxs();

        int symbolsSum = maxs.stream().mapToInt(i -> i).sum() - mins.stream().mapToInt(i -> i).sum() + mins.size();

        char[] attackWord = new char[lenght];
        Arrays.fill(attackWord, mins.get(0));
        attackWord[attackWord.length - 1]--;

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < Math.pow(symbolsSum, lenght) - 1; i++) {

            int wordPointer = lenght - 1;
            while (attackWord[wordPointer] == maxs.get(maxs.size() - 1)) {
                attackWord[wordPointer] = mins.get(0);
                wordPointer--;
            }

            int index = maxs.indexOf(attackWord[wordPointer]);
            if (index == -1) {
                attackWord[wordPointer]++;
            } else {
                attackWord[wordPointer] = mins.get(index + 1);
            }

            isUnlocked = tryToUnlock(zipFile, attackWord);
            if (isUnlocked) {
                System.out.println("Success, password: " + Arrays.toString(attackWord));
                break;
            }
        }
        isUnlocked = false;
        return System.currentTimeMillis() - startTime;
    }

    private boolean tryToUnlock(ZipFile zipFile, char[] attackWord) {
        try {
            zipFile.setPassword(attackWord);
            System.out.println(Arrays.toString(attackWord));
            zipFile.extractAll("extract");
        } catch (ZipException e) {
            return false;
        }
        return true;
    }
}
