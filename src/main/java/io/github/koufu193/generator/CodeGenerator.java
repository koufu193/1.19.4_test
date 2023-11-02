package io.github.koufu193.generator;

import org.jboss.forge.roaster.model.source.JavaClassSource;

public abstract class CodeGenerator {
    abstract JavaClassSource generate();
}
