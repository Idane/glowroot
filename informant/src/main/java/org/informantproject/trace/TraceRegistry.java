/**
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.informantproject.trace;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.google.inject.Singleton;

/**
 * Registry to hold all active traces. Also holds the current trace state for each thread via
 * ThreadLocals.
 * 
 * @author Trask Stalnaker
 * @since 0.5
 */
@Singleton
public class TraceRegistry {

    // collection of active running traces, ordered by start time
    // TODO precise ordering by start time would require synchronization or some other method
    private final Collection<Trace> traces = new ConcurrentLinkedQueue<Trace>();

    // active running trace being executed by the current thread
    private final ThreadLocal<Trace> currentTraceHolder = new ThreadLocal<Trace>();

    // this is used to disable tracing of the current root span
    // it is used in case tracing is later enabled while this root span is still active
    // in which case tracing should stay disabled for the root span
    private final ThreadLocal<Boolean> currentRootSpanDisabledHolder = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    Trace getCurrentTrace() {
        return currentTraceHolder.get();
    }

    void setCurrentTrace(Trace trace) {
        currentTraceHolder.set(trace);
    }

    boolean isCurrentRootSpanDisabled() {
        return currentRootSpanDisabledHolder.get();
    }

    void setCurrentRootSpanDisabled(boolean disabled) {
        currentRootSpanDisabledHolder.set(disabled);
    }

    void addTrace(Trace trace) {
        traces.add(trace);
    }

    void removeTrace(Trace trace) {
        traces.remove(trace);
    }

    // returns list of traces ordered by start time
    public Collection<Trace> getTraces() {
        return traces;
    }
}
