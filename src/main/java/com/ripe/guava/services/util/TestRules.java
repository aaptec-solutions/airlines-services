package com.ripe.guava.services.util;

import com.aaptec.dgca.model.FDTLModel;
import com.ripe.guava.services.kb.processor.impl.KBProcessorImpl;

public class TestRules {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KBProcessorImpl processor = new KBProcessorImpl();
		FDTLModel model = (FDTLModel)processor.fireRules(null, null);
		System.out.println(model.getViolationText());
	}

}
