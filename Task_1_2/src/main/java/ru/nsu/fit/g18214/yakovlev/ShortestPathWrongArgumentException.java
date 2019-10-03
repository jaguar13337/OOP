package ru.nsu.fit.g18214.yakovlev;

class ShortestPathWrongArgumentException extends ArrayIndexOutOfBoundsException {
    ShortestPathWrongArgumentException(){
        super();
    }
    ShortestPathWrongArgumentException(String message) {
        super(message);
    }
}
