/*
 * Licensed to Hewlett-Packard Development Company, L.P. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
*/
package org.eclipse.score.engine.partitions.entities;

import org.eclipse.score.engine.data.AbstractIdentifiable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Date: 4/23/12
 *
 * @author Dima Rassin
 */
@Entity
@Table(name = "OO_PARTITION_GROUPS")
public class PartitionGroup extends AbstractIdentifiable {
	@Column(name = "NAME", length = 27, nullable = false, unique = true)
	private String name;

	@Column(name = "GROUP_SIZE", nullable = false)
	private int groupSize;

	@Column(name = "TIME_THRESHOLD", nullable = false)
	private long timeThreshold;

	@Column(name = "SIZE_THRESHOLD", nullable = false)
	private long sizeThreshold;

	@Column(name = "ACTIVE_PARTITION", nullable = false)
	private int activePartition;

	@Column(name = "LAST_ROLL_TIME", nullable = false)
	private long lastRollTime = System.currentTimeMillis();

	@SuppressWarnings("UnusedDeclaration")
	private PartitionGroup() {} // used by JPA

	public PartitionGroup(String name, int size, long timeThreshold, long sizeThreshold) {
		this.name = name;
		this.groupSize = size;
		this.timeThreshold = timeThreshold;
		this.sizeThreshold = sizeThreshold;
		activePartition = 1;
		lastRollTime = System.currentTimeMillis();
	}

	public String getName() {
		return name;
	}

	public int getGroupSize() {
		return groupSize;
	}

	public long getTimeThreshold() {
		return timeThreshold;
	}

	public long getSizeThreshold() {
		return sizeThreshold;
	}

	public int getActivePartition() {
		return activePartition;
	}

	public PartitionGroup setActivePartition(int activePartition) {
		this.activePartition = activePartition;
		return this;
	}

	public long getLastRollTime() {
		return lastRollTime;
	}

	public PartitionGroup setLastRollTime(long lastRollTime) {
		this.lastRollTime = lastRollTime;
		return this;
	}

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public void setTimeThreshold(long timeThreshold) {
        this.timeThreshold = timeThreshold;
    }

    public void setSizeThreshold(long sizeThreshold) {
        this.sizeThreshold = sizeThreshold;
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PartitionGroup)) return false;

		PartitionGroup that = (PartitionGroup) o;

		return new EqualsBuilder().append(this.name, that.name).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).toHashCode();
	}
}