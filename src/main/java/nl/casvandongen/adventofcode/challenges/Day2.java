package nl.casvandongen.adventofcode.challenges;

import lombok.extern.slf4j.Slf4j;
import nl.casvandongen.adventofcode.annotations.DailyChallenge;
import nl.casvandongen.adventofcode.type.Pair;
import nl.casvandongen.adventofcode.utils.Input;

import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@DailyChallenge(day = 2)
public class Day2 implements Challenge
{
    public void part1()
    {
        Optional<Integer> score = games()
                .map(this::score)
                .reduce(Math::addExact);
        score.ifPresent(val -> log.info("Total score: {}", val));
    }

    public void part2()
    {
        Optional<Integer> score = games()
                .map(pair -> new Pair<>(pair.first(), (pair.first() + pair.second() + 2) % 3))
                .map(this::score)
                .reduce(Math::addExact);
        score.ifPresent(val -> log.info("Total score: {}", val));
    }

    private Stream<Pair<Integer>> games()
    {
        return Input.readString("day02_1.txt", "\r\n").map(val -> {
            String[] in = val.split(" ");
            return new Pair<>(moveValue(in[0]), moveValue(in[1]));
        });
    }

    private Integer moveValue(String input)
    {
        return switch (input)
                {
                    case "A", "X" -> 0;
                    case "B", "Y" -> 1;
                    case "C", "Z" -> 2;
                    default -> null;
                };
    }

    private Integer score(Pair<Integer> pair)
    {
        int score = pair.second();
        if (pair.second().equals((pair.first() + 1) % 3))
        {
            score += 6;
        }
        else if (pair.isEqual())
        {
            score += 3;
        }

        return score + 1;
    }
}
