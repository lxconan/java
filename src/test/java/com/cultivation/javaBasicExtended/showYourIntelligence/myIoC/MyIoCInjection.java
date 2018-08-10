package com.cultivation.javaBasicExtended.showYourIntelligence.myIoC;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO: Please add additional annotation so that it can be found at runtime. And make it field only.
// <-Start
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
// --end->
public @interface MyIoCInjection {
}
