/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.scheduler;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class LinenTask {
    private static final AtomicLong NEXT_ID = new AtomicLong(0);

    private final long id;
    private final LinenTaskExecutor executor;
    private final LinenScheduler scheduler;

    private boolean cancelled = false;
    private long delayTicks = 0;
    private @Nullable Long intervalTicks = null;
    private @Nullable Long maxExecution = null;

    private long executionCount = 0;

    public LinenTask(LinenTaskExecutor executor, LinenScheduler scheduler) {
        this.executor = executor;
        this.scheduler = scheduler;
        this.id = NEXT_ID.getAndIncrement();
    }

    protected LinenTaskExecutor executor() {
        return executor;
    }

    public LinenTask delay(long ticks) {
        this.delayTicks = ticks;
        return this;
    }

    public LinenTask interval(long ticks) {
        this.intervalTicks = ticks;
        return this;
    }

    public Optional<Long> intervalTicks() {
        return Optional.ofNullable(intervalTicks);
    }

    public LinenTask atMost(long executions) {
        this.maxExecution = executions;
        return this;
    }

    public Optional<Long> maxExecutions() {
        return Optional.ofNullable(maxExecution);
    }

    public void schedule() {
        scheduler.schedule(this);
    }

    public void cancel() {
        this.cancelled = true;
    }

    /**
     * Indicates whether this task has been cancelled or has reached its maximum number of executions.
     *
     * @return true if the task is cancelled or has reached its maximum executions, false otherwise
     */
    public boolean isCancelled() {
        return cancelled || maxExecutions().map(max -> executionCount >= max).orElse(false);
    }

    public long delayTicks() {
        return delayTicks;
    }

    public long executionCount() {
        return executionCount;
    }

    public boolean isFinalExecution() {
        return maxExecutions().map(max -> executionCount + 1 >= max).orElse(false);
    }

    protected void incrementExecutionCount() {
        this.executionCount++;
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
