package nl.casvandongen.adventofcode.days;

import lombok.extern.slf4j.Slf4j;
import nl.casvandongen.adventofcode.utils.Input;

import java.util.*;
import java.util.stream.Stream;

@Slf4j
public class Day1
{
    public static void main(String[] args)
    {
        Day1 day = new Day1();
        day.part1();
        day.part2();
    }

    public void part1()
    {
        Optional<Long> largest = totals().reduce(Math::max);
        largest.ifPresent(val -> log.info("Largest amount of calories: {}", val));
    }

    public void part2()
    {
        Optional<Long> largestThree = totals()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(Math::addExact);
        largestThree.ifPresent(val -> log.info("Largest three calories stacks: {}", val));
    }

    private Stream<Long> totals()
    {
        return Input.readString("day01_1.txt", "\r\n\r\n")
                .map(group -> Arrays.stream(group.split("\r\n")).mapToLong(Long::parseLong).sum());
    }
}
