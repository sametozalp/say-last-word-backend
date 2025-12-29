package com.ozalp.last_word_service.exceptions.errors;

public class AlreadySaidYourLastWord extends RuntimeException {
    public AlreadySaidYourLastWord(String message) {
        super(message);
    }
}
