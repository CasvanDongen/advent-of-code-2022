package nl.casvandongen.adventofcode.type;

public record Pair<T>(T first, T second)
{
    public boolean isEqual()
    {
        return first.equals(second);
    }
}
