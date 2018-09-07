package com.mike.rappi.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by mike on
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
