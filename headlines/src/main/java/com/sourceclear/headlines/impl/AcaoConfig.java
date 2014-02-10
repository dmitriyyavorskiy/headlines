package com.sourceclear.headlines.impl;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class AcaoConfig {

    ///////////////////////////// Class Attributes \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private volatile String origin;

    private boolean enabled = true;

    /////////////////////////////// Constructors \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public AcaoConfig() {
    }

    public AcaoConfig(String origin) {
        this.origin = origin;
    }

    ////////////////////////////////// Methods \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    //------------------------ Implements:

    //------------------------ Overrides:

    //---------------------------- Abstract Methods -----------------------------

    //---------------------------- Utility Methods ------------------------------

    //---------------------------- Property Methods -----------------------------

    /**
     * @return space-separated list of origins, it is either a single origin or the string "null"
     */
    public String getOrigin() {
        return origin;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
