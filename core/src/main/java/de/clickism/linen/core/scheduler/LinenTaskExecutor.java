/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.scheduler;

@FunctionalInterface
public interface LinenTaskExecutor {
    void execute(LinenTask task);
}
