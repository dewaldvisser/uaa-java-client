package org.cloudfoundry.identity.uaa.api.client.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.cloudfoundry.identity.uaa.api.store.UaaStoreOperations;
import org.cloudfoundry.identity.uaa.api.user.UaaUserOperations;
import org.cloudfoundry.identity.uaa.scim.ScimUser;
import org.cloudfoundry.identity.uaa.scim.ScimUser.Email;
import org.cloudfoundry.identity.uaa.scim.ScimUser.Group;
import org.cloudfoundry.identity.uaa.scim.ScimUser.Name;
import org.cloudfoundry.identity.uaa.scim.ScimUser.PhoneNumber;
import org.cloudfoundry.identity.uaa.scim.ScimUserStore;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

public class UaaStoreOperationsTest extends AbstractOperationTest {
	
	@ClassRule public static UaaServerAvailable uaaServerAvailable = new UaaServerAvailable();
	
	private UaaStoreOperations storeOperations;
	
	private UaaUserOperations userOperations;

	@Before
	public void setUp() throws Exception {		
		storeOperations = getConnection().storeOperations();
		userOperations = getConnection().userOperations();
		ScimUser testUser = userOperations.getUserByName("testuser");
		if (testUser != null) {
			userOperations.deleteUser(testUser.getId());
		}
	}
	

	@Test
	public void testStoreCreateUpdateDelete() {
		
		ScimUser newUser = createNewTestUser();

		ScimUser createdUser = userOperations.createUser(newUser);
		assertNotNull(createdUser.getId());
		
		List<ScimUserStore> stores = new ArrayList<>();
		ScimUserStore store = new ScimUserStore();
		store.setStoreId("1234");
		store.setUserId(createdUser.getId());
		stores.add(store);

		List<ScimUserStore> result = storeOperations.updateStores(createdUser.getId(), stores);
		assertNotNull(result);
		
	}
	
	
	private ScimUser createNewTestUser() {

		ScimUser newUser = new ScimUser();
		
		newUser.setGroups(new ArrayList<Group>(){});
		
		newUser.setUserName("testuser");
		newUser.setPassword("p4ssw0rd");

		newUser.setName(new Name("Test", "User"));

		Email email = new Email();
		email.setValue("testuser@test.com");

		newUser.setEmails(Collections.singletonList(email));

		PhoneNumber phone = new PhoneNumber();
		phone.setValue("303-555-1212");
		newUser.setPhoneNumbers(Collections.singletonList(phone));

		return newUser;
	}
	
	

}
