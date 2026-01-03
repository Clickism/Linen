/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.scheduler;

import java.util.*;

public class LinenScheduler {

    private static final LinenScheduler INSTANCE = new LinenScheduler();
    private long currentTick = 0;

    private final Map<Long, Deque<LinenTask>> scheduledTasks = new HashMap<>();

    public static LinenScheduler scheduler() {
        return INSTANCE;
    }

    protected LinenScheduler() {
        // Static class
    }

    public LinenTask task(LinenTaskExecutor executor) {
        return new LinenTask(executor, this);
    }

    public void schedule(LinenTask task) {
        long scheduledTick = currentTick + task.delayTicks();
        scheduleTaskAtTick(task, scheduledTick);
    }

    public void tick() {
        var tasks = pollTasksForCurrentTick();
        if (tasks != null) {
            tasks.forEach(this::runTaskAndReschedule);
        }
        currentTick++;
    }

    private Collection<LinenTask> pollTasksForCurrentTick() {
        return scheduledTasks.remove(currentTick);
    }

    private void runTaskAndReschedule(LinenTask task) {
        if (task.isCancelled()) return;
        task.executor().execute(task);
        task.incrementExecutionCount();
        // Reschedule task.
        task.intervalTicks().ifPresent(interval -> {
            if (task.isCancelled()) return;
            scheduleTaskAtTick(task, currentTick + interval);
        });
    }

    private void scheduleTaskAtTick(LinenTask task, long tick) {
        scheduledTasks.computeIfAbsent(tick, k -> new ArrayDeque<>()).add(task);
    }

}
