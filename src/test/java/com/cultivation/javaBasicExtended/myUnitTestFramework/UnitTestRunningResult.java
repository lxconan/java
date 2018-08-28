package com.cultivation.javaBasicExtended.myUnitTestFramework;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class UnitTestRunningResult {
    private final boolean success;
    private String reason;
    private final List<TestResultItem> results;

    boolean isSuccess() {
        return success;
    }

    List<TestResultItem> getDetails() {
        return results;
    }

    UnitTestRunningResult(Iterable<TestResultItem> items) {
        results = StreamSupport.stream(items.spliterator(), false)
            .collect(Collectors.toList());
        success = results.stream().allMatch(TestResultItem::isSuccess);
        reason = success ? null : "Please see details";
    }

    UnitTestRunningResult(String errorReason) {
        results = Collections.emptyList();
        success = false;
        reason = errorReason;
    }

    String getReason() {
        return reason;
    }
}
