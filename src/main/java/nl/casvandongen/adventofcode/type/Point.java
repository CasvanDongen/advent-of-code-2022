package nl.casvandongen.adventofcode.type;

public record Point(Long x, Long y)
{
    public Point setPoint(double x, double y)
    {
        return new Point((long) Math.floor(x + 0.5), (long) Math.floor(y + 0.5));
    }

    public Double distanceTo(Point other)
    {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
    }

    public Long distanceTo2D(Point other)
    {
        return Math.max(Math.abs(this.x - other.x), Math.abs(this.y - other.y));
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Point pt)
        {
            return (x.equals(pt.x)) && (y.equals(pt.y));
        }
        return false;
    }
}
