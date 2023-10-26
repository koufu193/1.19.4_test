package io.github.koufu193.core.game.data;

public record Identifier(String namespace,String value) {
    public Identifier {
        if (!namespace.matches("^[a-z0-9.-_]*$")) throw new IllegalArgumentException("Illegal namespace " + namespace);
        if (!namespace.matches("^[a-z0-9.-_/]*$")) throw new IllegalArgumentException("Illegal value " + value);
    }
    public Identifier(String value){
        this("minecraft",value);
    }

    @Override
    public String toString() {
        return namespace+":"+value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifier that)) return false;

        if (!namespace.equals(that.namespace)) return false;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        int result = namespace.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
    public static Identifier from(String identifier){
        String[] split=identifier.split(":",-1);
        if(split.length==1) return new Identifier(identifier);
        if(split.length==2) return new Identifier(split[0],split[1]);
        throw new RuntimeException("too many : found "+identifier);
    }
}
