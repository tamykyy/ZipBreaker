import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;


public class Program {

    public static void main(String[] args) throws ZipException {
        AttackPattern enumAttack = new EnumerationAttack(PasswordType.NumericAndLatinAllCases, 4);
        AttackPattern dict = new DictionaryAttack(DictionarySize.Huge);
        double time = dict.attack(new ZipFile("Vykhodtsev_Mykyta_PD-41_04.03.23.zip"));
        System.out.println(time / 1000 + " seconds");
    }
}
