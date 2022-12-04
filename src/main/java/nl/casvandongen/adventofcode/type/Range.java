package nl.casvandongen.adventofcode.type;

import java.util.Comparator;

public record Range<T extends Comparable<T>>(T minimum, T maximum)
{
    public Range(final T minimum, final T maximum)
    {
        if (minimum.compareTo(maximum) < 1)
        {
            this.minimum = minimum;
            this.maximum = maximum;
        }
        else
        {
            this.minimum = maximum;
            this.maximum = minimum;
        }
    }

    public boolean contains(T value)
    {
        if (value == null)
        {
            return false;
        }

        return minimum.compareTo(value) <= 0 && maximum.compareTo(value) >= 0;
    }

    public boolean contains(Range<T> range)
    {
        if (range == null)
        {
            return false;
        }

        return contains(range.minimum) && contains(range.maximum);
    }

    public boolean overlaps(Range<T> range)
    {
        if (range == null)
        {
            return false;
        }

        return range.contains(minimum) || range.contains(maximum) || contains(range.minimum);
    }

    private class ComparableComparator implements Comparator<T>
    {

        @Override
        public int compare(T o1, T o2)
        {
            return o1.compareTo(o2);
        }
    }
}
