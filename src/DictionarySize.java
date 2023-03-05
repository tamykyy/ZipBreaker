enum DictionarySize {
    Small("dictionary/DictionarySmall.txt"),
    Medium("dictionary/DictionaryMedium.txt"),
    Huge("dictionary/DictionaryHuge.txt");

    private String file;

    DictionarySize(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }
}