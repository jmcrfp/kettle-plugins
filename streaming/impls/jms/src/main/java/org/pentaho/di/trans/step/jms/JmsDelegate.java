/*! ******************************************************************************
 *
 * Pentaho Data Integration
 *
 * Copyright (C) 2002-2018 by Hitachi Vantara : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.pentaho.di.trans.step.jms;


import org.pentaho.di.core.injection.Injection;
import org.pentaho.di.core.util.serialization.Sensitive;
import org.pentaho.di.trans.step.jms.context.JmsProvider;

import javax.jms.Destination;
import javax.jms.JMSContext;
import java.util.List;

import static org.pentaho.di.i18n.BaseMessages.getString;
import static org.pentaho.di.trans.step.jms.JmsConstants.PKG;
import static org.pentaho.di.trans.step.jms.context.JmsProvider.ConnectionType.WEBSPHERE;
import static org.pentaho.di.trans.step.jms.context.JmsProvider.DestinationType.QUEUE;


/**
 * Handles creation of JMSContext and JMS Destination via the appropriate {@link JmsProvider} implementation.
 *
 * JMS related connection info is captured here as @Injection attributes so the same attributes can be shared
 * by any Meta implementation requiring Jms connection support.
 */
public  class JmsDelegate {

  @Injection ( name = "DESTINATION" ) public String destinationName = "DEV.QUEUE.1";

  @Injection ( name = "URL" ) public String url = "mq://localhost:1414/QM1?channel=DEV.APP.SVRCONN";

  @Injection ( name = "USERNAME" ) public String username = "username";

  @Sensitive @Injection ( name = "PASSWORD" ) public String password = "password";

  @Injection ( name = "JNDI_URL" ) public String jndiUrl = "";

  @Injection ( name = "USE_JNDI" ) public boolean useJndi = false;

  @Injection ( name = "CONNECTION_TYPE" ) public String connectionType = WEBSPHERE.name();

  @Injection ( name = "DESTINATION_TYPE" ) public String destinationType = QUEUE.name();


  private final List<JmsProvider> jmsProviders;

  public JmsDelegate( List<JmsProvider> jmsProviders ) {
    super();
    this.jmsProviders = jmsProviders;
  }

  Destination getDestination() {
    return getJmsProvider().getDestination( this );
  }

  JMSContext getJmsContext() {
    return getJmsProvider().getContext( this );
  }

  private JmsProvider getJmsProvider() {
    return jmsProviders.stream()
      .filter( prov -> prov.supports( JmsProvider.ConnectionType.valueOf( connectionType ) ) )
      .findFirst()
      .orElseThrow( () -> new RuntimeException( getString( PKG, "JmsDelegate.UnsupportedConnectionType" ) ) );
  }
}
