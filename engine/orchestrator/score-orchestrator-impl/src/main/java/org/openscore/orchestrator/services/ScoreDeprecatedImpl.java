/*******************************************************************************
* (c) Copyright 2014 Hewlett-Packard Development Company, L.P.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Apache License v2.0 which accompany this distribution.
*
* The Apache License is available at
* http://www.apache.org/licenses/LICENSE-2.0
*
*******************************************************************************/

package org.openscore.orchestrator.services;

import org.openscore.api.ScoreDeprecated;
import org.openscore.api.TriggeringProperties;
import org.openscore.engine.data.IdentityGenerator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by peerme on 23/07/2014.
 */
public class ScoreDeprecatedImpl implements ScoreDeprecated {

    @Autowired
    private ScoreTriggering scoreTriggering;

    @Autowired
    private IdentityGenerator idGenerator;

    @Override
    public Long generateExecutionId() {
        Long executionId = idGenerator.next();
        return executionId;
    }

    @Override
    public Long trigger(Long executionId, TriggeringProperties triggeringProperties) {
         return scoreTriggering.trigger(executionId, triggeringProperties);
    }

}
