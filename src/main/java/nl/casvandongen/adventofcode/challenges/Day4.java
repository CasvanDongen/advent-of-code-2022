package nl.casvandongen.adventofcode.challenges;

import lombok.extern.slf4j.Slf4j;
import nl.casvandongen.adventofcode.annotations.DailyChallenge;
import nl.casvandongen.adventofcode.type.Pair;
import nl.casvandongen.adventofcode.type.Range;
import nl.casvandongen.adventofcode.utils.Input;

import java.util.stream.Stream;

@Slf4j
@DailyChallenge(day = 4)
public class Day4 implements Challenge
{
    @Override
    public void part1()
    {
        long contains = assignments()
                .filter(pair -> pair.first().contains(pair.second()) || pair.second().contains(pair.first()))
                .count();

        log.info("In how many assignment pairs does one range fully contain the other? {}", contains);
    }

    @Override
    public void part2()
    {
        long overlaps = assignments()
                .filter(pair -> pair.first().overlaps(pair.second()) || pair.second().overlaps(pair.first()))
                .count();

        log.info("In how many assignment pairs do the ranges overlap? {}", overlaps);
    }

    private Stream<Pair<Range<Integer>>> assignments()
    {
        return Input.readString("day04_1.txt")
                .map(line -> {
                    String[] in = line.split(",");
                    return new Pair<>(range(in[0]), range(in[1]));
                });
    }

    private Range<Integer> range(String range)
    {
        String[] in = range.split("-");
        return new Range<>(Integer.parseInt(in[0]), Integer.parseInt(in[1]));
    }
}
