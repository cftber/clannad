package com.tgzhao.clannad.common.guavautil;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

/**
 * Created by tgzhao on 17/1/1.
 */
public class Job implements Comparable<Job>{

    public Job(String jobName) {
        Preconditions.checkNotNull(jobName, "jobname can not be null {0}", "haha");
        this.jobName = jobName;
    }
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    private String jobName;

    @Override
    public int compareTo(Job other) {
        return ComparisonChain
                .start()
                .compare(this.jobName, other.jobName, Ordering.natural().reverse().nullsLast())
                .result();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("job", this.jobName)
                .toString();
    }
}
