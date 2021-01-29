package com.shub.restApi.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.shub.restApi.messenger.database.DatabaseClass;
import com.shub.restApi.messenger.model.Profile;

public class ProfileService {

	private Map<String, Profile> profileMap = DatabaseClass.getProfiles();

	public ProfileService() {
		profileMap.put("shub", new Profile(1, "shub", "Shublakhan", "Kaur"));
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profileMap.values());
	}

	public Profile getProfile(String profileName) {
		return profileMap.get(profileName);
	}

	public Profile addProfile(Profile profile) {
		profile.setId(profileMap.size() + 1);
		profileMap.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		profileMap.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile removeProfile(String profileName) {
		return profileMap.remove(profileName);
	}
}
