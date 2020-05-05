package com.seabath.endtoend.seloger;

import com.seabath.endtoend.AbstractT;
import com.seabath.ui.seleniumeasy.director.SeleniumEasyTestDirector;

public class AbstractTSeLoger extends AbstractT<SeleniumEasyTestDirector> {
    @Override
    protected SeleniumEasyTestDirector initDirector() {
        return new SeleniumEasyTestDirector(getTestParam());
    }


}
