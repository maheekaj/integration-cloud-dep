/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.intcloud.services.tenant.carbonapps.undeployer;

import org.wso2.carbon.application.mgt.ApplicationAdmin;
import org.wso2.carbon.context.PrivilegedCarbonContext;

public class TenantCarbonAppUnDeployer extends ApplicationAdmin {

    public String[] listAllApplicationsInTenant(int tenantId) throws Exception {
        try {
            PrivilegedCarbonContext.startTenantFlow();
            PrivilegedCarbonContext.getThreadLocalCarbonContext().setTenantId(tenantId, true);

            return listAllApplications();

        } finally {
            PrivilegedCarbonContext.endTenantFlow();
        }
    }

    public void deleteApplicationInTenant(int tenantId, String appName) throws Exception {
        try {
            PrivilegedCarbonContext.startTenantFlow();
            PrivilegedCarbonContext.getThreadLocalCarbonContext().setTenantId(tenantId, true);

            deleteApplication(appName);

        } finally {
            PrivilegedCarbonContext.endTenantFlow();
        }
    }

}
