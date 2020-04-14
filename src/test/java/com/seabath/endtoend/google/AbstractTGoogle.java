package com.seabath.endtoend.google;

import com.seabath.endtoend.AbstractT;
import com.seabath.ui.google.director.GoogleTestDirector;

public abstract class AbstractTGoogle extends AbstractT<GoogleTestDirector> {

    @Override
    protected GoogleTestDirector initDirector() {
        return new GoogleTestDirector(getTestParam());
    }
}
