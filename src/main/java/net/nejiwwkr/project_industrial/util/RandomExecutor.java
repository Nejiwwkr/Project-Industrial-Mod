package net.nejiwwkr.project_industrial.util;

import net.minecraft.util.Pair;
import net.nejiwwkr.project_industrial.util.annotation.Dangerous;

import java.util.Random;

public class RandomExecutor {
    private static final Random random = new Random();

    @SafeVarargs
    @Dangerous
    public static void execute(Pair<Integer, Runnable>... tasks) {
        int totalWeight = 0;
        for (Pair<Integer, Runnable> task : tasks) {
            totalWeight += task.getLeft();
        }
        int randomWeight = random.nextInt(totalWeight);
        int currentWeightSum = 0;
        for (Pair<Integer, Runnable> task : tasks) {
            currentWeightSum += task.getLeft();
            if (currentWeightSum > randomWeight) {
                task.getRight().run();
                break;
            }
        }
    }
}
