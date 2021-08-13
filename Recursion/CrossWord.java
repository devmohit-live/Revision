public class CrossWord {
    static char[][] box = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' }, { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' }, { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } };

    static String[] words = { "agra", "norway", "england", "gwalior" };

    public static void print() {
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box.length; j++) {
                System.out.print(box[i][j]);
            }
            System.out.println();
        }
    }

    static boolean isValidToPlaceH(int r, int c, String word) {
        // check if the word perfectly fits or is lesser/longer than the horizontal
        // space provided

        int len = 0, C = c;
        while (C < 10 && box[r][C++] != '+')
            len++;

        // also checks if we are not overwriting the words ex: norway, way are two
        // different words so way part can overwrite the "way" part of worway, so we
        // have to check if the [c-1]th part should be +(block)(avoid merging of two
        // words if both words have something in common)
        if (len != word.length() || (c != 0 && box[r][c - 1] != '+'))
            return false;

        // checks if the workarea is - or correct word at that place(as box[x][y] ca be
        // word[i] or anything else)
        for (int i = 0; i < word.length(); i++) {
            if (box[r][c + i] != '-' && box[r][c + i] != word.charAt(i))
                return false;
        }
        return true;
    }

    static boolean isValidToPlaceV(int r, int c, String word) {
        int len = 0, R = r;
        while (R < 10 && box[R++][c] != '+')
            len++;
        if (len != word.length() || (r != 0 && box[r - 1][c] != '+'))
            return false;

        for (int i = 0; i < word.length(); i++) {
            if (box[r + i][c] != '-' && box[r + i][c] != word.charAt(i))
                return false;
        }

        return true;
    }

    public static int placeH(int r, int c, String word) {
        int loc = 0;
        for (int i = 0; i < word.length(); i++) {
            if (box[r][i + c] == '-') {
                box[r][i + c] = word.charAt(i);
                loc |= (1 << i);
            }
        }
        return loc;
    }

    public static void unPlaceH(int r, int c, int loc, String word) {
        for (int i = 0; i < word.length(); i++) {
            if ((loc & (1 << i)) != 0)
                box[r][i + c] = '-';
        }
    }

    public static int placeV(int r, int c, String word) {
        int loc = 0;
        for (int i = 0; i < word.length(); i++) {
            if (box[r + i][c] == '-') {
                box[r + i][c] = word.charAt(i);
                loc |= (1 << i);
            }
        }
        return loc;
    }

    public static void unPlaceV(int r, int c, int loc, String word) {
        for (int i = 0; i < word.length(); i++) {
            if ((loc & (1 << i)) != 0)
                box[r + i][c] = '-';
        }
    }

    public static void crossWord(int idx) {
        if (idx == words.length) {
            print();
            return;
        }

        String word = words[idx];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (box[i][j] == '-' || box[i][j] == word.charAt(0)) {
                    if (isValidToPlaceH(i, j, word)) {
                        int loc = placeH(i, j, word);
                        crossWord(idx + 1);
                        unPlaceH(i, j, loc, word);
                    }
                    if (isValidToPlaceV(i, j, word)) {
                        int loc = placeV(i, j, word);
                        crossWord(idx + 1);
                        unPlaceV(i, j, loc, word);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        crossWord(0);
    }

}