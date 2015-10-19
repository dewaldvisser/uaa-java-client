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
package org.cloudfoundry.identity.uaa.api.store.impl;

import static org.cloudfoundry.identity.uaa.scim.ScimCore.SCHEMAS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.cloudfoundry.identity.uaa.api.common.impl.UaaConnectionHelper;
import org.cloudfoundry.identity.uaa.api.common.model.WrappedSearchResults;
import org.cloudfoundry.identity.uaa.api.common.model.expr.FilterRequest;
import org.cloudfoundry.identity.uaa.api.common.model.expr.FilterRequestBuilder;
import org.cloudfoundry.identity.uaa.api.group.UaaGroupOperations;
import org.cloudfoundry.identity.uaa.api.store.UaaStoreOperations;
import org.cloudfoundry.identity.uaa.rest.SearchResults;
import org.cloudfoundry.identity.uaa.scim.ScimGroup;
import org.cloudfoundry.identity.uaa.scim.ScimGroupExternalMember;
import org.cloudfoundry.identity.uaa.scim.ScimGroupMember;
import org.cloudfoundry.identity.uaa.scim.ScimUserStore;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class UaaStoreOperationsImpl implements UaaStoreOperations {

	private static final ParameterizedTypeReference<List<ScimUserStore>> STORE_LIST_REF = new ParameterizedTypeReference<List<ScimUserStore>>() {
	};
	
	private static final ParameterizedTypeReference<Object> OBJ_REF = new ParameterizedTypeReference<Object>() {
	};

	private static final ParameterizedTypeReference<ScimUserStore> STORE_REF = new ParameterizedTypeReference<ScimUserStore>() {
	};
	
	private static final ParameterizedTypeReference<WrappedSearchResults<ScimUserStore>> STORES_REF = new ParameterizedTypeReference<WrappedSearchResults<ScimUserStore>>() {
	};

	private UaaConnectionHelper helper;

	public UaaStoreOperationsImpl(UaaConnectionHelper helper) {
		this.helper = helper;
	}
	
	@Override
	public List<ScimUserStore> createStores(String userId, List<ScimUserStore> stores) {
		Assert.notNull(stores);
		
		return helper.post("/Stores", stores, STORE_LIST_REF);	
	}

	@Override
	public List<ScimUserStore> deleteStores(String userId) {
		Assert.hasText(userId);
		
		return helper.delete("/Stores/{id}", STORE_LIST_REF, userId);
	}

	@Override
	public List<ScimUserStore> getStores(String userId) {
		Assert.hasText(userId);
		
		return helper.get("/Stores/{id}", STORE_LIST_REF,  userId);
	}

	@Override
	public List<ScimUserStore> updateStores(String userId, List<ScimUserStore> stores) {
		Assert.notNull(stores);
		Assert.hasText(userId);
		
		return helper.put("/Stores/{id}", stores, STORE_LIST_REF, userId);		
	}

	@Override
	public SearchResults<ScimUserStore> getStores(FilterRequest request) {
		Assert.notNull(request);
		
		return helper.get(helper.buildScimFilterUrl("/Stores", request), STORES_REF);
	}
	
}
