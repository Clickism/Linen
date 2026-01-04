/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.scheduler;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Represents a task in the scheduler.
 */
public class LinenTask {
    private static final AtomicLong NEXT_ID = new AtomicLong(0);

    private final long id;
    private final LinenTaskExecutor executor;
    private final LinenScheduler scheduler;

    private boolean cancelled = false;
    private long delayTicks = 0;
    private @Nullable Long intervalTicks = null;
    private @Nullable Long maxExecutions = null;

    private long executions = 0;

    /**
     * Creates a new LinenTask with the specified executor and scheduler.
     *
     * @param executor  the task executor
     * @param scheduler the scheduler managing this task
     */
    public LinenTask(LinenTaskExecutor executor, LinenScheduler scheduler) {
        this.executor = executor;
        this.scheduler = scheduler;
        this.id = NEXT_ID.getAndIncrement();
    }

    /**
     * Gets the executor associated with this task.
     *
     * @return the task executor
     */
    protected LinenTaskExecutor executor() {
        return executor;
    }

    /**
     * Sets the delay in ticks before the task is executed.
     *
     * @param ticks the delay in ticks
     * @return the current LinenTask instance
     */
    public LinenTask delay(long ticks) {
        this.delayTicks = ticks;
        return this;
    }

    /**
     * Sets the task to be executed at fixed intervals.
     * <p>
     * The first execution will occur after the initial delay set by {@link #delay(long)}.
     *
     * @param ticks the interval in ticks between executions
     * @return the current LinenTask instance
     */
    public LinenTask interval(long ticks) {
        this.intervalTicks = ticks;
        return this;
    }

    /**
     * Gets the interval in ticks between executions, if set.
     *
     * @return an Optional containing the interval ticks, or empty if not set
     */
    public Optional<Long> intervalTicks() {
        return Optional.ofNullable(intervalTicks);
    }

    /**
     * Sets the maximum number of times this task should be executed.
     * <p>
     * If not set, the task will continue executing indefinitely (or until cancelled).
     *
     * @param executions the maximum number of executions
     * @return the current LinenTask instance
     */
    public LinenTask atMost(long executions) {
        this.maxExecutions = executions;
        return this;
    }

    /**
     * Gets the maximum number of executions for this task, if set.
     *
     * @return maximum executions, or -1 if not set
     */
    public long atMost() {
        return maxExecutions != null ? maxExecutions : -1;
    }

    /**
     * Schedules this task for execution.
     *
     * @return the current LinenTask instance
     */
    public LinenTask schedule() {
        scheduler.schedule(this);
        return this;
    }

    /**
     * Cancels this task, preventing any further executions.
     *
     * @return the current LinenTask instance
     */
    public LinenTask cancel() {
        this.cancelled = true;
        return this;
    }

    /**
     * Checks whether this task has been cancelled or has reached its maximum number of executions.
     *
     * @return true if the task is cancelled or has reached its maximum executions, false otherwise
     */
    public boolean isCancelled() {
        if (maxExecutions == null) {
            return cancelled;
        }
        return cancelled || executions >= maxExecutions;
    }

    /**
     * Gets the delay in ticks before the task is executed.
     *
     * @return the delay in ticks
     */
    public long delayTicks() {
        return delayTicks;
    }

    /**
     * Gets the number of times this task has been executed.
     * <p>
     * Will start at 0 and increment after each execution.
     *
     * @return the execution count
     */
    public long executions() {
        return executions;
    }

    /**
     * Checks whether the current execution of this task is the final one based on the maximum executions set.
     * <p>
     * See {@link #atMost(long)}.
     *
     * @return true if this is the final execution, false otherwise
     */
    public boolean isFinalExecution() {
        return maxExecutions != null && executions + 1 >= maxExecutions;
    }

    /**
     * Increments the execution count by one.
     */
    protected void incrementExecutions() {
        this.executions++;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof LinenTask other) && other.id == this.id;
    }
}
