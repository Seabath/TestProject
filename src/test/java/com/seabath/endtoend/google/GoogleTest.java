package com.seabath.endtoend.google;

import org.junit.jupiter.api.Test;

public class GoogleTest extends AbstractTGoogle {

    @Test
    public void shouldClickMainLogo() {
        getDirector()
            .start()
            .clickLogo()
            .end();
    }

}
