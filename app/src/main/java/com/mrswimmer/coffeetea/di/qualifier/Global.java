package com.mrswimmer.coffeetea.di.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by sma on 11.09.17.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Global {
}
