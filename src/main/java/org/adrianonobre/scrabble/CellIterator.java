package org.adrianonobre.scrabble;

/**
 * Created by adriano on 2017-03-05.
 */
public interface CellIterator {

    boolean hasPrevious();

    boolean hasNext();

    Cell current();

    Cell previous();

    Cell next();

}
