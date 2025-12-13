package hangman;

public class Words {
    private Words() {
        throw new RuntimeException("Do not instantiate");
    }

    static String[] length2Words = new String[]{"in", "ex", "he", "is", "me",
            "up", "am", "or", "as", "be"};

    static String[] getLength2Words() {
        return new String[]{"in", "ex", "he", "is", "me",
                "up", "am", "or", "as", "be"};
    }

    static String[] getLength3Words = new String[]{"cat","dog","sun","pen","box",
                "car","hat","cup","map","bed"};

    static String[] getLength4Words = new String[]{"book","tree","lamp","door","road",
                "wind","star","milk","fish","snow"};


    static String[] getLength5Words = new String[]{"apple","grape","chair","table","brick",
                "plane","mouse","river","light","stone"};

}
