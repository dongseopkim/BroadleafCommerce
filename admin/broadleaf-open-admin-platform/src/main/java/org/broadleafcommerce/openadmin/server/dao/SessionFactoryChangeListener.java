/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.broadleafcommerce.openadmin.server.dao;

import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;

/**
 * Clear the static entity metadata caches from {@code DynamicEntityDao}
 * upon recycling of the session factory.
 *
 * @author jfischer
 */
public class SessionFactoryChangeListener implements SessionFactoryObserver {

    @Override
    public void sessionFactoryClosed(SessionFactory factory) {
        //do nothing
    }

    @Override
    public void sessionFactoryCreated(SessionFactory factory) {
        synchronized (DynamicEntityDaoImpl.LOCK_OBJECT) {
            DynamicEntityDaoImpl.METADATA_CACHE.clear();
            DynamicEntityDaoImpl.POLYMORPHIC_ENTITY_CACHE.clear();
        }
    }

}
