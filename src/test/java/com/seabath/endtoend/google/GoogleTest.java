package com.seabath.endtoend.google;

import com.seabath.endtoend.AbstractT;
import com.seabath.ui.google.director.GoogleTestDirector;
import org.junit.jupiter.api.Test;

public class GoogleTest extends AbstractT<GoogleTestDirector> {

    @Override
    protected GoogleTestDirector initDirector() {
        return new GoogleTestDirector(getTestParam());
    }

    @Test
    public void shouldClickMainLogo() {
        getDirector()
            .start()
            .clickLogo()
            .end();
    }

}
