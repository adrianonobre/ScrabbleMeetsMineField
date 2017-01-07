package org.adrianonobre.scrabble;

/**
 * Created by adriano on 2017-01-05.
 */
public interface SquareContent {
    class Letter implements SquareContent {
        private final Character letter;

        public Letter(Character letter) {
            this.letter = letter;
        }

        public Character getLetter() {
            return letter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Letter otherSquareContent = (Letter) o;

            return letter != null ? letter.equals(otherSquareContent.letter) : otherSquareContent.letter == null;

        }

        @Override
        public int hashCode() {
            return letter != null ? letter.hashCode() : 0;
        }
    }

    class Mine implements SquareContent {
    }
}
