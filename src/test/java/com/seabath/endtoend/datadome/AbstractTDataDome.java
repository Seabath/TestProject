package com.seabath.endtoend.datadome;

import com.seabath.endtoend.AbstractT;
import com.seabath.ui.datadome.director.DataDomeTestDirector;

public class AbstractTDataDome extends AbstractT<DataDomeTestDirector> {
    @Override
    protected DataDomeTestDirector initDirector() {
        return new DataDomeTestDirector(getTestParam());
    }

}
