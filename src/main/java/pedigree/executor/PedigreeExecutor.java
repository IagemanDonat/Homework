package pedigree.executor;


import pedigree.entitys.Human;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class PedigreeExecutor {

    public void printPersonPedigree(Human target) {
        printAnsestors(target);
        printProgenitors(target);
    }

    private void printAnsestors(Human target) {
        Human father = target.getFather();

        Human mother = target.getMother();

        if (Objects.nonNull(father)) {
            printAnsestors(father);
        }

        if (mother != null) {
            printAnsestors(mother);
        }
        target.printInfo();
    }

    private void printProgenitors(Human target) {
        target.getChildren().forEach(child -> {
            child.printInfo();
            printProgenitors(child);
        });
    }

    public void printCloseRelatives(Human target) {
        Human father = target.getFather();
        Human mother = target.getMother();
        father.printInfo();
        mother.printInfo();

        Predicate<Human> predicate = child -> child != target;

        father.getChildren().stream().filter(predicate).forEach(Human::printInfo);
        mother.getChildren().stream().filter(predicate).forEach(Human::printInfo);
        target.getChildren().forEach(Human::printInfo);
    }


    public void printStatistics(Human target) {
        Set<Human> pedigree = new HashSet<>();

        pedigree.addAll(getAnsestors(target));

//        Map<Human, List<Human>>

        pedigree.addAll(getProdenitors(target));

        Map<String, Long> sexes = pedigree.stream().collect(groupingBy(Human::getSex, Collectors.counting()));

        sexes.forEach((key, value) -> System.out.println(value + " relatives are " + key));

        Map<Boolean, Long> alive = pedigree.stream().collect(groupingBy(Human::getAlive, Collectors.counting()));
        alive.forEach((key, value) -> System.out.println(value + " relatives are " + (key ? "alive" : "daed")));

        OptionalDouble averageChildren = pedigree.stream().mapToInt(human -> human.getChildren().size()).average();

        if (averageChildren.isPresent()) {
            System.out.println("Average amount of children per person is:" + averageChildren.getAsDouble());
        }
    }

    private Set<Human> getAnsestors(Human target) {
        Set<Human> ansestors = new HashSet<>();
        Human father = target.getFather();
        Human mother = target.getMother();

        if (father != null) {
            ansestors.add(father);
            ansestors.addAll(getAnsestors(father));
        }

        if (mother != null) {
            ansestors.add(mother);
            ansestors.addAll(getAnsestors(mother));
        }
        return ansestors;
    }

    private Set<Human> getProdenitors(Human target) {
        Set<Human> progenitors = new HashSet<>();

        target.getChildren().forEach(child -> {
            progenitors.add(child);
            progenitors.addAll(getProdenitors(child));
        });

        return progenitors;
    }

    private int countAnsestorRelitivity(Human target, Human targetRelative) {
        int[] result = {0};
        Human father = target.getFather();
        Human mother = target.getMother();
        if (father != null) {
            if (father.equals(targetRelative)) {
                result[0] += 1;
            } else {
                result[0] += 1 + countAnsestorRelitivity(father, targetRelative);
            }
        }

        if (mother != null) {
            if (mother.equals(targetRelative)) {
                result[0] += 1;
            } else {
                result[0] += 1 + countAnsestorRelitivity(mother, targetRelative);
            }
        }
        return result[0];
    }

    private int countAnsestorRelitivity1(Human target, Human targetRelative) {
        int[] result = {0};
        Human father = target.getFather();
        Human mother = target.getMother();

        if (father != null) {
            if (father.equals(targetRelative)) {
                result[0] += 1;
            } else {
                result[0] += 1 + countAnsestorRelitivity(father, targetRelative);
            }
        }

        result[0] = father.equals(targetRelative) ? 1 : 1 + countAnsestorRelitivity(father, targetRelative);

        if (mother != null) {
            if (mother.equals(targetRelative)) {
                result[0] += 1;
            } else {
                result[0] += countAnsestorRelitivity(mother, targetRelative);
            }
        }

        return result[0];
    }

    private int countProgenitorRelitivity(Human target, Human targetRelative) {
        int[] result = {0};

        target.getChildren().forEach(child -> {
            if (child.equals(targetRelative)) {
                result[0] += 1;
            } else {
                result[0] += 1 + countProgenitorRelitivity(child, targetRelative);
            }
        });
        return result[0];
    }

    public void printRelativityState(Human target, Human targetRelative) {
        System.out.println(countAnsestorRelitivity(target, targetRelative) +
                countProgenitorRelitivity(target, targetRelative));
    }
}
