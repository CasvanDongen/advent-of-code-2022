package nl.casvandongen.adventofcode.type;

import java.util.List;

public record Location3D(Long x, Long y, Long z)
{
    public List<Long> toList()
    {
        return List.of(x, y, z);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Location3D pt)
        {
            return (x.equals(pt.x)) && (y.equals(pt.y)) && (z.equals(pt.z));
        }
        return false;
    }
}
