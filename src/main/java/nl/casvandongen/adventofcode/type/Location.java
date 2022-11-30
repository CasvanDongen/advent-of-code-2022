package nl.casvandongen.adventofcode.type;

import java.util.List;

public record Location(Long x, Long y)
{
    public Location move(Long x, Long y)
    {
        return new Location(this.x + x, this.y + y);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public Location rotate()
    {
        return new Location(this.y, this.x);
    }

    public double distanceTo(Location other)
    {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
    }

    public List<Long> toList()
    {
        return List.of(x, y);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Location pt)
        {
            return (x.equals(pt.x)) && (y.equals(pt.y));
        }
        return false;
    }
}
