package com.seabath.endtoend.seloger;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

public class SeLogerTest extends AbstractTSeLoger {



    @Test
    public void shouldFilterById() {
        AtomicReference<Long> listSize = new AtomicReference<>();

        getDirector().start()
                .clickFilter()
                .filterId("5")
                .retrieveFilterTableSize(listSize)
                .waitNoResultAbsent()
                .end();

        assert listSize.get() == 1 : "Not enough / Too much row after filtering";
    }

    @Test
    public void shouldFilterByUserName() {
        AtomicReference<Long> listSize = new AtomicReference<>();

        getDirector().start()
                .clickFilter()
                .filterUserName("jacobs")
                .retrieveFilterTableSize(listSize)
                .waitNoResultAbsent()
                .end();

        assert listSize.get() == 1 : "Not enough / Too much row after filtering";
    }
    @Test
    public void shouldFilterByFirstName() {
        AtomicReference<Long> listSize = new AtomicReference<>();

        getDirector().start()
                .clickFilter()
                .filterFirstName("e")
                .retrieveFilterTableSize(listSize)
                .waitNoResultAbsent()
                .end();

        assert listSize.get() == 3 : "Not enough / Too much row after filtering";
    }
    @Test
    public void shouldFilterByLastName() {
        AtomicReference<Long> listSize = new AtomicReference<>();

        getDirector().start()
                .clickFilter()
                .filterLastName("Dimarison")
                .retrieveFilterTableSize(listSize)
                .waitNoResultAbsent()
                .end();

        assert listSize.get() == 1 : "Not enough / Too much row after filtering";
    }


    @Test
    public void shouldGetNoResult() {
        AtomicReference<Long> listSize = new AtomicReference<>();

        getDirector().start()
                .clickFilter()
                .filterLastName("Something that doesn't exists")
                .retrieveFilterTableSize(listSize)
                .waitNoResult()
                .end();

        assert listSize.get() == 1 : "Not enough / Too much row after filtering";
    }

}
