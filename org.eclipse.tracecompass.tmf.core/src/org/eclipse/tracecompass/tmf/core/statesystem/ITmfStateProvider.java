/*******************************************************************************
 * Copyright (c) 2012, 2015 Ericsson
 * Copyright (c) 2010, 2011 École Polytechnique de Montréal
 * Copyright (c) 2010, 2011 Alexandre Montplaisir <alexandre.montplaisir@gmail.com>
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package org.eclipse.tracecompass.tmf.core.statesystem;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.tracecompass.statesystem.core.ITmfStateSystem;
import org.eclipse.tracecompass.statesystem.core.ITmfStateSystemBuilder;
import org.eclipse.tracecompass.tmf.core.event.ITmfEvent;
import org.eclipse.tracecompass.tmf.core.trace.ITmfTrace;

/**
 * This is the interface used to define the "state change input", which is the
 * main type of input that goes in the state system.
 *
 * Usually a state change input, also called "state provider" is the piece of
 * the pipeline which converts trace events to state changes.
 *
 * @author Alexandre Montplaisir
 */
public interface ITmfStateProvider {

    /**
     * Event handler plugins should provide a version number. This is used to
     * determine if a potential existing file can be re-opened later (if the
     * versions in the file and in the viewer match), or if the file should be
     * rebuilt from scratch (if the versions don't match).
     *
     * @return The version number of the input plugin
     */
    int getVersion();

    /**
     * Get the trace with which this state input plugin is associated.
     *
     * @return The associated trace
     */
    ITmfTrace getTrace();

    /**
     * Return the start time of this "state change input", which is normally the
     * start time of the originating trace (or it can be the time of the first
     * state-changing event).
     *
     * @return The start time
     */
    long getStartTime();

    /**
     * Assign the target state system where this SCI will insert its state
     * changes. Because of dependencies issues, this can normally not be done at
     * the constructor.
     *
     * This needs to be called before .run()!
     *
     * @param ssb
     *            Target state system for the state changes generated by this
     *            input plugin
     */
    void assignTargetStateSystem(ITmfStateSystemBuilder ssb);

    /**
     * Return the currently assigned target state system.
     *
     * @return Reference to the currently assigned state system, or null if no
     *         SS is assigned yet
     */
    @Nullable ITmfStateSystem getAssignedStateSystem();

    /**
     * Send an event to this input plugin for processing. The implementation
     * should check the contents, and call the state-modifying methods of its
     * IStateSystemBuilder object accordingly.
     *
     * @param event
     *            The event (which should be safe to cast to the
     *            expectedEventType) that has to be processed.
     */
    void processEvent(ITmfEvent event);

    /**
     * Provide a non-initialized copy of this state input plugin. You will need
     * to call {@link #assignTargetStateSystem} on it to assign its target.
     *
     * @return A new state change input object, of the same type, but without an
     *         assigned target state system
     */
    ITmfStateProvider getNewInstance();

    /**
     * Indicate to the state history building process that we are done (for now),
     * and that it should close its current history.
     */
    void dispose();
}
