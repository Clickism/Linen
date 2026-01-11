/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.scheduler;

/**
 * Functional interface for executing Linen tasks.
 */
@FunctionalInterface
public interface LinenTaskExecutor {
    /**
     * Executes a Linen task.
     *
     * @param task The task that triggered the execution.
     */
    void execute(LinenTask task);
}
