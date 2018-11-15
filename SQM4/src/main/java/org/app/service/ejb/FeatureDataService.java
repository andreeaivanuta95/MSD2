package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Features;

@Remote
public interface FeatureDataService {
	//create or update
	Features addFeature(Features FeatureToAdd);
	Features updateFeature(Features FeatureToUpdate);
	Features closeFeature(Features FeatureToClose);	

	//delete
	Features deleteFeature(Features FeatureToDelete);	
	//read
	Features getFeatureById(String FeatureId);
	Collection<Features> getFeatures();

	Features getFeatureByTitle(String title);

	//Others
	String sayRest();
}
