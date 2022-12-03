package nl.casvandongen.adventofcode.type;

import java.util.function.Function;

public record Pair<T>(T first, T second)
{
    public boolean isEqual()
    {
        return first.equals(second);
    }

    public <R> R map(Function<? super Pair<T>, ? extends R> mapper)
    {
        return mapper.apply(this);
    }

    public <R> Pair<R> convert(Function<? super T, ? extends R> converter)
    {
        return new Pair<>(converter.apply(first()), converter.apply(second()));
    }
}
