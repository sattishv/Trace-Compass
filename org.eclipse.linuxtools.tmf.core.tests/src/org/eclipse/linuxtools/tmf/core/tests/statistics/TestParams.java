/*******************************************************************************
 * Copyright (c) 2012 Ericsson
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Matthew Khouzam - Initial API and implementation
 *******************************************************************************/

package org.eclipse.linuxtools.tmf.core.tests.statistics;

import org.eclipse.linuxtools.tmf.core.ctfadaptor.CtfTmfEvent;
import org.eclipse.linuxtools.tmf.core.ctfadaptor.CtfTmfTrace;
import org.eclipse.linuxtools.tmf.core.exceptions.TmfTraceException;

/**
 * This is the set up for the trace files to be used with the TmfStatistics
 * tests.
 */
abstract class TestParams {

    /* Path to test traces */
    private static final String testTracePath1 = "../org.eclipse.linuxtools.ctf.core.tests/traces/kernel"; //$NON-NLS-1$
    private static CtfTmfTrace testTrace1 = null;

    public synchronized static CtfTmfTrace createTrace() throws TmfTraceException {
        if (testTrace1 == null) {
            testTrace1 = new CtfTmfTrace();
            testTrace1.initTrace(null, testTracePath1, CtfTmfEvent.class);
        }
        return testTrace1;
    }
}