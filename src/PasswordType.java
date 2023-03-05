import java.util.List;

public enum PasswordType {

    Numeric(List.of('0'), List.of('9')),
    LatinLowerCase(List.of('a'), List.of('z')),
    LatinUpperCase(List.of('A'), List.of('Z')),
    LatinAllCases(List.of('a', 'A'), List.of('z', 'Z')),
    NumericAndLatinAllCases(List.of('a', 'A', '0'), List.of('z', 'Z', '9'));

    private final List<Character> mins;
    private final List<Character> maxs;

    PasswordType(List<Character> mins, List<Character> maxs) {
        this.mins = mins;
        this.maxs = maxs;
    }

    public List<Character> getMins() {
        return mins;
    }

    public List<Character> getMaxs() {
        return maxs;
    }
}
