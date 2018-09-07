package com.mike.rappi.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by mike
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
