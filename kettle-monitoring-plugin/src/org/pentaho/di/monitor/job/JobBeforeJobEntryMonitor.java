/*!
* This program is free software; you can redistribute it and/or modify it under the
* terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
* Foundation.
*
* You should have received a copy of the GNU Lesser General Public License along with this
* program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
* or from the Free Software Foundation, Inc.,
* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
* without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
* See the GNU Lesser General Public License for more details.
*
* Copyright (c) 2002-2014 Pentaho Corporation..  All rights reserved.
*/
package org.pentaho.di.monitor.job;

import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.extension.ExtensionPoint;
import org.pentaho.di.core.extension.ExtensionPointInterface;
import org.pentaho.di.job.JobExecutionExtension;
import org.pentaho.di.monitor.MonitorAbstract;
import org.pentaho.di.monitor.base.EventType;
import org.pentaho.di.monitor.base.IKettleMonitoringEvent;

/**
 * @link http://wiki.pentaho.com/display/EAI/PDI+Extension+Point+Plugins
 */

@ExtensionPoint(
  id = "JobBeforeJobEntryExecutionMonitor",
  extensionPointId = "JobBeforeJobEntryExecution",
  description = "Before a job entry executes"
)
public class JobBeforeJobEntryMonitor extends MonitorAbstract implements ExtensionPointInterface {

  @Override
  public IKettleMonitoringEvent toKettleEvent( Object o ) throws KettleException {

    if ( o == null || !( o instanceof JobExecutionExtension ) ) {
      return null;
    }

    JobEntryEvent event = new JobEntryEvent( EventType.Job.BEFORE_JOB_ENTRY ).build( ( JobExecutionExtension ) o );

    //logInfo( "[PDI Extension Point Plugin] Dispathing to Event Bus " + event.toString() );

    return event;
  }
}
