/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.proxy.backend.mysql.handler.admin.executor.sysvar.provider;

import org.apache.shardingsphere.proxy.backend.mysql.handler.admin.executor.sysvar.Scope;
import org.apache.shardingsphere.proxy.backend.mysql.handler.admin.executor.sysvar.SystemVariable;
import org.apache.shardingsphere.proxy.backend.mysql.handler.admin.executor.sysvar.SystemVariableValueProvider;
import org.apache.shardingsphere.proxy.backend.session.ConnectionSession;
import org.apache.shardingsphere.sql.parser.sql.common.enums.TransactionIsolationLevel;

import java.util.Optional;

/**
 * Transaction isolation value provider.
 */
public final class TransactionIsolationValueProvider implements SystemVariableValueProvider {
    
    @Override
    public String get(final Scope scope, final ConnectionSession connectionSession, final SystemVariable variable) {
        return Scope.GLOBAL == scope ? variable.getDefaultValue()
                : Optional.ofNullable(connectionSession.getIsolationLevel()).map(TransactionIsolationLevel::getIsolationLevel).orElseGet(TransactionIsolationLevel.REPEATABLE_READ::getIsolationLevel);
    }
}
