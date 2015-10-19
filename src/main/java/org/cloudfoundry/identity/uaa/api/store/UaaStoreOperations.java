/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cloudfoundry.identity.uaa.api.store;

import java.util.List;

import org.cloudfoundry.identity.uaa.api.common.model.expr.FilterRequest;
import org.cloudfoundry.identity.uaa.rest.SearchResults;
import org.cloudfoundry.identity.uaa.scim.ScimUserStore;

public interface UaaStoreOperations {
	
	public List<ScimUserStore> createStores(String userId, List<ScimUserStore> stores);
	
	public List<ScimUserStore> updateStores(String userId, List<ScimUserStore> stores);
	
	public SearchResults<ScimUserStore> getStores(FilterRequest request);
	
	public List<ScimUserStore> deleteStores(String userId);
	
	public List<ScimUserStore> getStores(String userId);
}
