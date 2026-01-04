/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.scheduler;

import java.util.*;

/**
 * A simple scheduler for scheduling and executing tasks based on ticks.
 */
public class LinenScheduler {

    private static final LinenScheduler INSTANCE = new LinenScheduler();
    private long currentTick = 0;

    private final Map<Long, Deque<LinenTask>> scheduledTasks = new HashMap<>();

    /**
     * Gets the scheduler instance.
     *
     * @return The LinenScheduler instance.
     */
    public static LinenScheduler scheduler() {
        return INSTANCE;
    }

    /**
     * Creates a new LinenScheduler.
     */
    protected LinenScheduler() {
        // Static class
    }

    /**
     * Creates a new task with the given executor.
     *
     * @param executor The task executor.
     * @return The created LinenTask.
     */
    public LinenTask task(LinenTaskExecutor executor) {
        return new LinenTask(executor, this);
    }

    /**
     * Schedules the given task for execution.
     *
     * @param task The task to schedule.
     */
    public void schedule(LinenTask task) {
        long scheduledTick = currentTick + task.delayTicks();
        scheduleTaskAtTick(task, scheduledTick);
    }

    /**
     * Advances the scheduler by one tick, executing any tasks scheduled for the current tick.
     */
    public void tick() {
        var tasks = pollTasksForCurrentTick();
        if (tasks != null) {
            tasks.forEach(this::runTaskAndReschedule);
        }
        currentTick++;
    }

    /**
     * Polls and removes tasks scheduled for the current tick.
     *
     * @return The collection of tasks scheduled for the current tick, or null if none exist.
     */
    private Collection<LinenTask> pollTasksForCurrentTick() {
        return scheduledTasks.remove(currentTick);
    }

    /**
     * Runs the given task and reschedules it if it is a repeating task.
     *
     * @param task The task to run and potentially reschedule.
     */
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

    /**
     * Schedules the given task at the specified tick.
     *
     * @param task The task to schedule.
     * @param tick The tick at which to schedule the task.
     */
    private void scheduleTaskAtTick(LinenTask task, long tick) {
        scheduledTasks.computeIfAbsent(tick, k -> new ArrayDeque<>()).add(task);
    }

}
