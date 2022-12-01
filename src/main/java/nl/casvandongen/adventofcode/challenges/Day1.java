package nl.casvandongen.adventofcode.challenges;

import lombok.extern.slf4j.Slf4j;
import nl.casvandongen.adventofcode.annotations.DailyChallenge;
import nl.casvandongen.adventofcode.utils.Input;

import java.util.*;
import java.util.stream.Stream;

@Slf4j
@DailyChallenge(day = 1)
public class Day1 implements Challenge
{
    public void part1()
    {
        Optional<Long> largest = totals().reduce(Math::max);
        largest.ifPresent(val -> log.info("Largest stack: {}", val));
    }

    public void part2()
    {
        Optional<Long> largestThree = totals()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(Math::addExact);
        largestThree.ifPresent(val -> log.info("Total of largest three stacks: {}", val));
    }

    private Stream<Long> totals()
    {
        return Input.readString("day01_1.txt", "\r\n\r\n")
                .map(group -> Arrays.stream(group.split("\r\n")).mapToLong(Long::parseLong).sum());
    }
}
