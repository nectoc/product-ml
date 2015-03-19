/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.ml.project.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.carbon.ml.integration.common.utils.MLIntegrationBaseTest;
import org.wso2.carbon.ml.integration.common.utils.MLIntegrationTestConstants;

public class GetProjectsTestCase extends MLIntegrationBaseTest {
    
    private static final String projectName = "TestProjectForGetProjectTestcase";
    private static final String DatasetName = "SampleDataForGetProjectTestCase";

    @BeforeClass(alwaysRun = true, groups = "wso2.ml.integration")
    public void initTest() throws Exception {
        super.init();
        uploadDatasetFromCSV(DatasetName, "1.0", "data/fcSample.csv");
        createProject(projectName, DatasetName);
    }
    
    /**
     * Test retrieving all projects.
     * 
     * @throws ClientProtocolException
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test(groups = "wso2.ml.integration", description = "Retrieve a project")
    public void testGetAllProjects() throws ClientProtocolException, IOException, URISyntaxException {
        CloseableHttpResponse response = doHttpGet(new URI("https://localhost:9443/api/projects"));
        Assert.assertEquals(MLIntegrationTestConstants.HTTP_OK, response.getStatusLine().getStatusCode());
        response.close();
    }
    
    /**
     * Test retrieving a project.
     * 
     * @throws ClientProtocolException
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test(groups = "wso2.ml.integration", description = "Retrieve a project")
    public void testGetProject() throws ClientProtocolException, IOException, URISyntaxException {
        CloseableHttpResponse response = doHttpGet(new URI("https://localhost:9443/api/projects/" + projectName));
        Assert.assertEquals(MLIntegrationTestConstants.HTTP_OK, response.getStatusLine().getStatusCode());
        response.close();
    }
}