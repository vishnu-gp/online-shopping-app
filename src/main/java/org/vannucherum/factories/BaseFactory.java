package org.vannucherum.factories;

public class BaseFactory {
    protected BaseFactory() {
        throw new AssertionError("Factory class should not be instantiated.");
    }
}
