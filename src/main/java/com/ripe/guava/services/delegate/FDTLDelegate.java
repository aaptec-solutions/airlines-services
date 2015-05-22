package com.ripe.guava.services.delegate;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripe.guava.pilot_rules.model.FDTLModel;
import com.ripe.guava.services.constants.Operations;
import com.ripe.guava.services.kb.processor.KBProcessor;

public class FDTLDelegate extends AbstractDelegate {

	@Autowired
	KBProcessor processor;

	/**
	 * Authenticates the user against LDAP.
	 * 
	 * @param argument
	 * @return
	 */
	private Object applyPilotRules(Object argument) {
		FDTLModel model = new FDTLModel();
		
		model.setCrewType("PILOT");
		
		return model;
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object handleService(Operations operation, Object argument) {
		switch (operation) {
		case APPLY_FDTL:
			return applyPilotRules(argument);
		default:
			return "Invalid Operation";
		}
	}

}
