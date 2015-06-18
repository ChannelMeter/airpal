package com.airbnb.airpal.sql.dao;

import com.airbnb.airpal.api.Job;
import com.hubspot.rosetta.jdbi.RosettaBinder;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface JobDAO
{
    @SqlUpdate(
            "INSERT INTO jobs (query, \"user\", uuid, queryStats, state, columns, query_finished, query_started, error) " +
            "VALUES (" +
                    ":query, " +
                    ":user, " +
                    ":uuid, " +
                    ":queryStats, " +
                    ":state\\:\\:_states, " +
                    ":columns, " +
                    "TO_TIMESTAMP(ROUND(:queryFinished\\:\\:bigint / 1000)), " +
                    "TO_TIMESTAMP(ROUND(:queryStarted\\:\\:bigint / 1000)), " +
                    ":error)")
    @GetGeneratedKeys
    long createJob(
            @RosettaBinder Job job
    );
}