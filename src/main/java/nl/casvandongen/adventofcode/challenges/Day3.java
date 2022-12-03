package nl.casvandongen.adventofcode.challenges;

import lombok.extern.slf4j.Slf4j;
import nl.casvandongen.adventofcode.annotations.DailyChallenge;
import nl.casvandongen.adventofcode.type.Pair;
import nl.casvandongen.adventofcode.utils.Input;

import java.util.*;
import java.util.stream.Stream;

@Slf4j
@DailyChallenge(day = 3)
public class Day3 implements Challenge
{
    @Override
    public void part1()
    {
        Optional<Integer> score = Input.readString("day03_1.txt")
                .map(line -> new Pair<>(line.substring(0, line.length() / 2), line.substring(line.length() / 2)))
                .map(pair -> pair.convert(this::toSet))
                .map(rucksack -> {
                    rucksack.first().retainAll(rucksack.second());
                    return rucksack.first();
                })
                .flatMap(Collection::stream)
                .map(this::priority)
                .reduce(Math::addExact);
        score.ifPresent(val -> log.info("Total priority: {}", val));
    }

    @Override
    public void part2()
    {
        Optional<Integer> score = Input.readStringGrouped("day03_1.txt", 3)
                .map(group -> {
                    Set<Character> commonCharacters = new HashSet<>();
                    group.forEach(line -> {
                        if (commonCharacters.size() == 0)
                        {
                            commonCharacters.addAll(toSet(line));
                        }
                        else
                        {
                            commonCharacters.retainAll(toSet(line));
                        }
                    });
                    return commonCharacters.stream()
                            .map(this::priority)
                            .reduce(Math::addExact)
                            .orElse(0);
                })
                .reduce(Math::addExact);
        score.ifPresent(val -> log.info("Total group priority: {}", val));
    }

    private Integer priority(char input)
    {
        return !Character.isUpperCase(input) ? (int) input - (int) 'a' + 1 : (int) input - (int) 'A' + 27;
    }

    private Set<Character> toSet(String input)
    {
        return new HashSet<>(input.chars().mapToObj(c -> (char) c).toList());
    }
}
